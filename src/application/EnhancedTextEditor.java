package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

public class EnhancedTextEditor extends Application {
    private CodeArea textArea = new CodeArea();
    private TextFlow terminalArea = new TextFlow();
    private TextArea lineNumbers = new TextArea();
    private File currentFile = null;
    // Theme colors
    private final String BACKGROUND_COLOR = "#282c34";
    private final String TEXT_COLOR = "#abb2bf";
    private final String LINE_HIGHLIGHT_COLOR = "#2c313c";
    private final String KEYWORD_COLOR = "#c678dd";
    private final String STRING_COLOR = "#98c379";
    private final String COMMENT_COLOR = "#5c6370";
    private final String NUMBER_COLOR = "#d19a66";
    private final String METHOD_COLOR = "#61afef";
    private final String TYPE_COLOR = "#e5c07b";
    private final String TERMINAL_BG = "#21252b";

    @SuppressWarnings("exports")
	@Override
    public void start(Stage primaryStage) {
        // Setup scrollable container for terminal output
        ScrollPane terminalScroll = new ScrollPane(terminalArea);
        terminalScroll.setFitToWidth(true);
        terminalScroll.setFitToHeight(true);
        terminalScroll.setStyle("-fx-background-color: " + TERMINAL_BG + ";");
        
        // Terminal area styling
        terminalArea.setPadding(new Insets(10));
        terminalArea.setStyle("-fx-background-color: " + TERMINAL_BG + ";");
        
        // Configure line numbers
        lineNumbers.setEditable(false);
        lineNumbers.setStyle("-fx-background-color: #21252b; -fx-text-fill: #5c6370;");
        lineNumbers.setFont(Font.font("JetBrains Mono", 12));
        lineNumbers.setPrefWidth(40);
        lineNumbers.setId("line-numbers");

        // Configure text editor area
        textArea.setFont(Font.font("JetBrains Mono", 12));
        textArea.setEditable(true);
        
        // Set theme
        textArea.setBackgroundColor(BACKGROUND_COLOR);
        textArea.setTextColor(TEXT_COLOR);
        
        // Synchronize line numbers with text area
        textArea.textProperty().addListener((_, _, newText) -> {
            updateLineNumbers(newText);
            highlightSyntax();
        });
        textArea.caretPositionProperty().addListener((_, _, _) -> highlightCurrentLine());
        textArea.scrollTopProperty().addListener((_, _, newVal) -> lineNumbers.setScrollTop(newVal.doubleValue()));
        
        // File menu
        Menu fileMenu = new Menu("Archivo");
        MenuItem openItem = new MenuItem("Abrir (ctrl+o)");
        MenuItem saveItem = new MenuItem("Guardar (ctrl+s)");
        MenuItem saveAsItem = new MenuItem("Guardar Como...(ctrl+shift+s)");
        MenuItem exitItem = new MenuItem("Salir");
 
        Menu runMenu = new Menu("Run");
        MenuItem execItem = new MenuItem("Ejecutar (ctrl+r)");
        MenuItem clearTerminalItem = new MenuItem("Limpiar terminal (ctrl+alt+c)");
        
        // Theme menu
        Menu themeMenu = new Menu("Tema");
        MenuItem darkThemeItem = new MenuItem("Oscuro (predeterminado)");
        MenuItem lightThemeItem = new MenuItem("Claro");
        themeMenu.getItems().addAll(darkThemeItem, lightThemeItem);
        
        darkThemeItem.setOnAction(_ -> applyDarkTheme());
        lightThemeItem.setOnAction(_ -> applyLightTheme());
        
        fileMenu.getItems().addAll(openItem, saveItem, saveAsItem, new SeparatorMenuItem(), exitItem);
        runMenu.getItems().addAll(execItem, new SeparatorMenuItem(), clearTerminalItem);

        openItem.setOnAction(_ -> openFile(primaryStage));
        saveItem.setOnAction(_ -> saveFile(primaryStage, false));
        saveAsItem.setOnAction(_ -> saveFile(primaryStage, true));
        exitItem.setOnAction(_ -> primaryStage.close());
        execItem.setOnAction(_ -> {
            try {
                executeFile(primaryStage);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        clearTerminalItem.setOnAction(_ -> clearTerminal());

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, runMenu, themeMenu);
        menuBar.setStyle("-fx-background-color: #21252b; -fx-text-fill: white;");

        // Contenedor del área de texto con números de línea
        HBox textContainer = new HBox(lineNumbers, textArea);
        HBox.setHgrow(textArea, Priority.ALWAYS);
        HBox.setHgrow(lineNumbers, Priority.NEVER);

        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        splitPane.getItems().addAll(textContainer, terminalScroll);
        splitPane.setDividerPositions(0.7); // Inicialmente, 70% para el área de texto

        // Layout principal con márgenes
        VBox layout = new VBox(0);
        VBox.setVgrow(splitPane, Priority.ALWAYS);
        
        layout.getChildren().addAll(menuBar, splitPane);
        
        Scene scene = new Scene(layout, 800, 600);
        
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN),
            () -> saveFile(primaryStage, false)
        );
        
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN),
            () -> saveFile(primaryStage, true)
        );

        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN),
            () -> openFile(primaryStage)
        );
        
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN),
            () -> {
                try {
                    executeFile(primaryStage);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        );
                
        scene.getAccelerators().put(
            new KeyCodeCombination(KeyCode.C, KeyCombination.ALT_DOWN, KeyCombination.CONTROL_DOWN), 
            () -> clearTerminal()
        );
        
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("Editor de Código Avanzado");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Initial highlighting
        highlightSyntax();
        highlightCurrentLine();
    }

    private void openFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Archivos Java", "*.java"),
            new FileChooser.ExtensionFilter("Archivos de texto", "*.txt"),
            new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String content = reader.lines().collect(Collectors.joining("\n"));
                textArea.setText(content);
                updateLineNumbers(content);
                highlightSyntax();
                currentFile = file;
                addTerminalText("Archivo cargado: ", Color.WHITE);
                addTerminalText(file.getName() + "\n\n", Color.LIGHTGREEN);
            } catch (IOException e) {
                addTerminalText("Error al abrir el archivo: ", Color.RED);
                addTerminalText(e.getMessage() + "\n\n", Color.WHITE);
                e.printStackTrace();
            }
        }
    }

    private void saveFile(Stage stage, boolean saveAs) {
        if (currentFile == null || saveAs) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Archivos Java", "*.java"),
                new FileChooser.ExtensionFilter("Archivos de texto", "*.txt")
            );
            File file = fileChooser.showSaveDialog(stage);
            if (file != null) {
                currentFile = file;
            } else {
                return;
            }
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
            writer.write(textArea.getText());
            addTerminalText("Archivo guardado: ", Color.WHITE);  
            addTerminalText(currentFile.getName() + "\n\n", Color.LIGHTGREEN);
        } 
        catch (IOException e) {
            addTerminalText("Error al guardar el archivo: ", Color.RED);
            addTerminalText(e.getMessage() + "\n\n", Color.WHITE);
            e.printStackTrace();
        }
    }
    
    private void executeFile(Stage stage) throws UnsupportedEncodingException {
        try {
            ANTLRInputStream input = null;
            String s = textArea.getText();
            @SuppressWarnings("deprecation")
            StringBufferInputStream str = new StringBufferInputStream(s);
            try {
                input = new ANTLRInputStream(str);
            } catch (IOException ex) {
                addTerminalText("Error al crear el flujo de entrada: ", Color.RED);
                addTerminalText(ex.getMessage() + "\n", Color.WHITE);
                ex.printStackTrace();
                return;
            }
            
            sintaxLexer lexer = new sintaxLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            
            CustomParser2 parser = new CustomParser2(tokens);
            
            try {
                parser.program();
            } catch (RecognitionException e) {
                addTerminalText("Error de reconocimiento: ", Color.RED);
                addTerminalText(e.getMessage() + "\n", Color.WHITE);
                e.printStackTrace();
            } catch (Exception e) {
                addTerminalText("Error no esperado: ", Color.RED);
                addTerminalText(e.getMessage() + "\n", Color.WHITE);
                e.printStackTrace();
            }
            clearTerminal();
            
            // Process and highlight the output
            String output = parser.textOutput;
            processParserOutput(output);
            
            // After execution, re-highlight the code to show warnings/errors
            highlightExecutionResults(parser.textOutput);
        } catch (Exception e) {
            addTerminalText("Error fatal en la ejecución: ", Color.RED);
            addTerminalText(e.getMessage() + "\n", Color.WHITE);
            e.printStackTrace();
        }
    }
    
    private void processParserOutput(String output) {
        String[] lines = output.split("\n");
        for (String line : lines) {
            if (line.trim().isEmpty()) continue;
            
            if (line.contains("REDUCIBLE EXPRESSION")) {
                String expressionPart = line.substring(line.indexOf(":") + 1).trim();
                addTerminalText("REDUCIBLE EXPRESSION: ", Color.GREEN);
                addTerminalText(expressionPart + "\n", Color.LIGHTGREEN);
            } 
            else if (line.contains("DYNAMIC EXPRESSION")) {
                String expressionPart = line.substring(line.indexOf(":") + 1).trim();
                addTerminalText("DYNAMIC EXPRESSION: ", Color.ORANGE);
                addTerminalText(expressionPart + "\n", Color.LIGHTYELLOW);
            } 
            else if (line.contains("WARNING")) {
                addTerminalText(line + "\n", Color.YELLOW);
            } 
            else if (line.contains("ERROR")) {
                addTerminalText(line + "\n", Color.RED);
            } 
            else {
                addTerminalText(line + "\n", Color.WHITE);
            }
        }
    }
    
    private void highlightExecutionResults(String output) {
        // Find REDUCIBLE EXPRESSIONS and highlight them
        Pattern reduciblePattern = Pattern.compile("REDUCIBLE EXPRESSION: ([^\\n]+)");
        Matcher reducibleMatcher = reduciblePattern.matcher(output);
        
        while (reducibleMatcher.find()) {
            String expr = reducibleMatcher.group(1).trim();
            highlightExpression(expr, "#4CAF50"); // Green for reducible
        }
        
        // Find DYNAMIC EXPRESSIONS and highlight them
        Pattern dynamicPattern = Pattern.compile("DYNAMIC EXPRESSION: ([^\\n]+)");
        Matcher dynamicMatcher = dynamicPattern.matcher(output);
        
        while (dynamicMatcher.find()) {
            String expr = dynamicMatcher.group(1).trim();
            highlightExpression(expr, "#FF9800"); // Orange for dynamic
        }
        
        // Re-highlight syntax to ensure all colors are applied correctly
        highlightSyntax();
    }
    
    private void highlightExpression(String expr, String color) {
        String text = textArea.getText();
        int startIndex = text.indexOf(expr);
        if (startIndex >= 0) {
            textArea.setStyle(startIndex, startIndex + expr.length(), "-fx-underline: true; -fx-underline-color: " + color + ";");
        }
    }
    
    private void clearTerminal() {
        terminalArea.getChildren().clear();
    }

    private void addTerminalText(String text, Color color) {
        Text textNode = new Text(text);
        textNode.setFill(color);
        textNode.setFont(Font.font("JetBrains Mono", 12));
        terminalArea.getChildren().add(textNode);
    }

    private void updateLineNumbers(String content) {
        StringBuilder numbers = new StringBuilder();
        int lineCount = content.split("\n", -1).length;
        for (int i = 1; i <= lineCount; i++) {
            numbers.append(i).append("\n");
        }
        lineNumbers.setText(numbers.toString());
    }
    
    private void highlightSyntax() {
        String code = textArea.getText();
        textArea.resetStyles();
        
        // Reset all styling
        textArea.setStyle(0, code.length(), "");
        
        // Highlight Java keywords
        highlightPattern("\\b(public|private|protected|class|void|int|double|float|if|else|for|while|return|static|final|new)\\b", KEYWORD_COLOR);
        
        // Highlight strings
        highlightPattern("\"[^\"]*\"", STRING_COLOR);
        
        // Highlight numbers
        highlightPattern("\\b\\d+(\\.\\d+)?([fd])?\\b", NUMBER_COLOR);
        
        // Highlight methods
        highlightPattern("\\b[a-zA-Z_][a-zA-Z0-9_]*(?=\\s*\\()", METHOD_COLOR);
        
        // Highlight types/classes
        highlightPattern("\\b[A-Z][a-zA-Z0-9_]*\\b", TYPE_COLOR);
        
        // Highlight comments
        highlightPattern("//.*$", COMMENT_COLOR);
        highlightPattern("/\\*[\\s\\S]*?\\*/", COMMENT_COLOR);
    }
    
    private void highlightPattern(String pattern, String color) {
        String code = textArea.getText();
        Pattern p = Pattern.compile(pattern, Pattern.MULTILINE);
        Matcher m = p.matcher(code);
        
        while (m.find()) {
            try {
                textArea.setStyle(m.start(), m.end(), "-fx-fill: " + color + ";");
            } catch (Exception e) {
                System.err.println("Error highlighting pattern: " + pattern + " at position " + m.start() + "-" + m.end());
                e.printStackTrace();
            }
        }
    }
    
    private void highlightCurrentLine() {
        try {
            int caretPosition = textArea.getCaretPosition();
            String text = textArea.getText();
            
            // Find the start of the line
            int lineStart = text.lastIndexOf('\n', caretPosition - 1) + 1;
            if (lineStart < 0) lineStart = 0;
            
            // Find the end of the line
            int lineEnd = text.indexOf('\n', caretPosition);
            if (lineEnd < 0) lineEnd = text.length();
            
            // Apply background color to the current line
            textArea.setLineBackground(lineStart, lineEnd, LINE_HIGHLIGHT_COLOR);
        } catch (Exception e) {
            System.err.println("Error highlighting current line");
            e.printStackTrace();
        }
    }
    
    private void applyDarkTheme() {
        textArea.setBackgroundColor(BACKGROUND_COLOR);
        textArea.setTextColor(TEXT_COLOR);
        lineNumbers.setStyle("-fx-background-color: #21252b; -fx-text-fill: #5c6370;");
        terminalArea.setStyle("-fx-background-color: " + TERMINAL_BG + ";");
        highlightSyntax();
    }
    
    private void applyLightTheme() {
        textArea.setBackgroundColor("#f5f5f5");
        textArea.setTextColor("#333333");
        lineNumbers.setStyle("-fx-background-color: #e0e0e0; -fx-text-fill: #757575;");
        terminalArea.setStyle("-fx-background-color: #f0f0f0;");
        highlightSyntax();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Extended TextArea class with syntax highlighting capabilities
     */
    private class CodeArea extends TextArea {
        private String backgroundColor = BACKGROUND_COLOR;
        private String textColor = TEXT_COLOR;
        
        public CodeArea() {
            super();
            this.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: " + textColor + ";");
        }
        
        public void setBackgroundColor(String color) {
            this.backgroundColor = color;
            this.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: " + textColor + ";");
        }
        
        public void setTextColor(String color) {
            this.textColor = color;
            this.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: " + textColor + ";");
        }
        
        public void setStyle(int start, int end, String style) {
            if (end <= getLength()) {
                // Use the parent TextArea's setStyle method to avoid recursion
                super.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: " + textColor + "; " + style);
            }
        }
        
        public void setLineBackground(int start, int end, String color) {
            try {
                if (end <= getLength()) {
                    String currentStyle = this.getStyle();
                    super.setStyle("-fx-highlight-fill: " + color + "; -fx-highlight-text-fill: " + textColor + ";");
                    this.selectRange(start, end);
                    super.setStyle(currentStyle);
                    this.deselect();
                }
            } catch (Exception e) {
                System.err.println("Error setting line background");
                e.printStackTrace();
            }
        }
        
        public void resetStyles() {
            try {
                super.setStyle("-fx-background-color: " + backgroundColor + "; -fx-text-fill: " + textColor + ";");
            } catch (Exception e) {
                System.err.println("Error resetting styles");
                e.printStackTrace();
            }
        }
    }
}

class CustomParser2 extends sintaxParser {
    public CustomParser2(TokenStream input) {
        super(input);
    }

    @Override
    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        String hdr = getErrorHeader(e);
        String msg = getErrorMessage(e, tokenNames);
        textOutput += "\nError!!!!!: " + hdr + " " + msg + "\n\n";
        System.out.println("\nError!!!!!: " + hdr + " " + msg + "\n");
    }

    // Genera el encabezado con la línea y la posición del error
    public String getErrorHeader(RecognitionException e) {
        return "fix in Line " + e.line + ":" + e.charPositionInLine;
    }

    // Genera un mensaje de error personalizado
    public String getErrorMessage(RecognitionException e, String[] tokenNames) {
        if (e.token != null) {
            return "Error before '" + e.token.getText() + "'";
        }
        return "Unknown error";
    }
}