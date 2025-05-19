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
import javafx.concurrent.Task;
import javafx.stage.FileChooser;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.fxmisc.flowless.VirtualizedScrollPane;

import java.io.*;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.swing.text.Document;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;

public class EnhancedTextEditor extends Application {
    private CodeArea codeArea;
    private TextFlow terminalArea = new TextFlow();
    private File currentFile = null;
    private ExecutorService executor;
    
    // Theme colors - Dark theme (default)
    private static final String DARK_BG_COLOR = "#282c34";
    private static final String DARK_TEXT_COLOR = "#abb2bf";
    private static final String DARK_LINE_HIGHLIGHT = "#2c313c";
    private static final String DARK_TERMINAL_BG = "#f0f0f0";
    private static final String DARK_LINE_NUMBER_BG = "#21252b";
    private static final String DARK_LINE_NUMBER_FG = "#5c6370";
    
    // Light theme
    private static final String LIGHT_BG_COLOR = "#f5f5f5";
    private static final String LIGHT_TEXT_COLOR = "#333333";
    private static final String LIGHT_LINE_HIGHLIGHT = "#e8e8e8";
    private static final String LIGHT_TERMINAL_BG = "#f0f0f0";
    private static final String LIGHT_LINE_NUMBER_BG = "#e0e0e0";
    private static final String LIGHT_LINE_NUMBER_FG = "#757575";
    
    // Current theme state
    private boolean isDarkTheme = false;
    
    // Syntax colors - Dark theme
    private static final String KEYWORD_COLOR_DARK = "#c678dd";
    private static final String STRING_COLOR_DARK = "#98c379";
    private static final String COMMENT_COLOR_DARK = "#5c6370";
    private static final String NUMBER_COLOR_DARK = "#d19a66";
    private static final String METHOD_COLOR_DARK = "#61afef";
    private static final String TYPE_COLOR_DARK = "#e5c07b";
    
    // Syntax colors - Light theme
    private static final String KEYWORD_COLOR_LIGHT = "#7c0c89";
    private static final String STRING_COLOR_LIGHT = "#008000";
    private static final String COMMENT_COLOR_LIGHT = "#808080";
    private static final String NUMBER_COLOR_LIGHT = "#b05000";
    private static final String METHOD_COLOR_LIGHT = "#0068b5";
    private static final String TYPE_COLOR_LIGHT = "#815900";

    // Special highlighting for execution results
    private static final String REDUCIBLE_COLOR = "#4CAF50";
    private static final String DYNAMIC_COLOR = "#FF9800";
    private static final String ERROR_COLOR = "#F44336";
    
    private static final String[] KEYWORDS = new String[] {
            "abstract", "assert", "boolean", "break", "byte",
            "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else",
            "enum", "extends", "final", "finally", "float",
            "for", "goto", "if", "implements", "import",
            "instanceof", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super",
            "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while", "var"
    };

    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
    private static final String NUMBER_PATTERN = "\\b\\d+(\\.\\d+)?([fd])?\\b";
    private static final String METHOD_PATTERN = "\\b[a-zA-Z_][a-zA-Z0-9_]*(?=\\s*\\()";
    private static final String TYPE_PATTERN = "\\b[A-Z][a-zA-Z0-9_]*\\b";

    private static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
            + "|(?<STRING>" + STRING_PATTERN + ")"
            + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
            + "|(?<NUMBER>" + NUMBER_PATTERN + ")"
            + "|(?<METHOD>" + METHOD_PATTERN + ")"
            + "|(?<TYPE>" + TYPE_PATTERN + ")"
    );
    
    // Highlighting patterns for execution results
    private Pattern reduciblePattern = Pattern.compile("REDUCIBLE EXPRESSION: ([^\\n]+)");
    private Pattern dynamicPattern = Pattern.compile("DYNAMIC EXPRESSION: ([^\\n]+)");
    private Pattern errorPattern = Pattern.compile("ERROR: ([^\\n]+)");

    @SuppressWarnings("exports")
	@Override
    public void start(Stage primaryStage) {
        // Initialize thread pool for background tasks
        executor = Executors.newSingleThreadExecutor();
        
        // Initialize code area with syntax highlighting
        codeArea = new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea, digits -> "%1$" + digits + "s", null, null));
        codeArea.setStyle("-fx-font-family: 'FiraCode Nerd Font', monospace; -fx-font-size: 12px; color: #eee");
        
        // Delayed syntax highlighting
        codeArea.multiPlainChanges()
                .successionEnds(Duration.ofMillis(200))
                .retainLatestUntilLater(executor)
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(codeArea.multiPlainChanges())
                .filterMap(t -> {
                    if (t.isSuccess()) {
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                })
                .subscribe(this::applyHighlighting);

        // Setup scrollable container for code area
        VirtualizedScrollPane<CodeArea> codeScrollPane = new VirtualizedScrollPane<>(codeArea);
        
        // Setup scrollable container for terminal output
        ScrollPane terminalScroll = new ScrollPane(terminalArea);
        terminalScroll.setFitToWidth(true);
        terminalScroll.setFitToHeight(true);
        
        // Terminal area styling
        terminalArea.setPadding(new Insets(10));
        
        // Apply initial theme
        applyTheme(isDarkTheme);

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
        
        darkThemeItem.setOnAction(_ -> {
            isDarkTheme = true;
            applyTheme(true);
        });
        
        lightThemeItem.setOnAction(_ -> {
            isDarkTheme = false;
            applyTheme(false);
        });
        
        fileMenu.getItems().addAll(openItem, saveItem, saveAsItem, new SeparatorMenuItem(), exitItem);
        runMenu.getItems().addAll(execItem, new SeparatorMenuItem(), clearTerminalItem);

        openItem.setOnAction(_ -> openFile(primaryStage));
        saveItem.setOnAction(_ -> saveFile(primaryStage, false));
        saveAsItem.setOnAction(_ -> saveFile(primaryStage, true));
        exitItem.setOnAction(_ -> {
            executor.shutdown();
            primaryStage.close();
        });
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

        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        splitPane.getItems().addAll(codeScrollPane, terminalScroll);
        splitPane.setDividerPositions(0.7); // 70% for code area initially

        // Layout principal
        VBox layout = new VBox(0);
        VBox.setVgrow(splitPane, Priority.ALWAYS);
        
        layout.getChildren().addAll(menuBar, splitPane);
        
        Scene scene = new Scene(layout, 800, 600);
        
        // Keyboard shortcuts
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
        
        // Load CSS stylesheets
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        scene.getStylesheets().add(getClass().getResource("syntax-highlighting.css").toExternalForm());
        
        primaryStage.setTitle("Editor de Código Avanzado");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @Override
    public void stop() {
        executor.shutdown();
    }

    private void applyTheme(boolean dark) {
        String bgColor = dark ? DARK_BG_COLOR : LIGHT_BG_COLOR;
        String textColor = dark ? DARK_TEXT_COLOR : LIGHT_TEXT_COLOR;
        String terminalBg = dark ? DARK_TERMINAL_BG : LIGHT_TERMINAL_BG;
        
        codeArea.setStyle(String.format(
            "-fx-font-family: 'FiraCode Nerd Font', monospace; " +
            "-fx-font-size: 12px; " +
            "-fx-background-color: %s; " +
            "-fx-text-fill: %s;",
            bgColor, textColor
        ));
        
        terminalArea.setStyle("-fx-background-color: " + terminalBg + ";");
        if(terminalArea.getParent() != null)
        	terminalArea.getParent().setStyle("-fx-background-color: " + terminalBg + ";");
        
        // Rehighlight to apply new theme colors
        highlightSyntax();
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
                codeArea.replaceText(content);
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
            writer.write(codeArea.getText());
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
            String s = codeArea.getText();
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
            
            // After execution, highlight special expressions
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
        // Find and mark special expressions in the code
        String code = codeArea.getText();
        
        // Find REDUCIBLE EXPRESSIONS and highlight them
        Matcher reducibleMatcher = reduciblePattern.matcher(output);
        while (reducibleMatcher.find()) {
            String expr = reducibleMatcher.group(1).trim();
            highlightSpecialExpression(expr, "reducible");
        }
        
        // Find DYNAMIC EXPRESSIONS and highlight them
        Matcher dynamicMatcher = dynamicPattern.matcher(output);
        while (dynamicMatcher.find()) {
            String expr = dynamicMatcher.group(1).trim();
            highlightSpecialExpression(expr, "dynamic");
        }
        
        // Find ERROR expressions and highlight them
        Matcher errorMatcher = errorPattern.matcher(output);
        while (errorMatcher.find()) {
            String expr = errorMatcher.group(1).trim();
            highlightSpecialExpression(expr, "error");
        }
    }
    
    private void highlightSpecialExpression(String expr, String styleClass) {
        String code = codeArea.getText();
        int index = code.indexOf(expr);
        if (index >= 0) {
            // Add special style class to the expression
            codeArea.setStyleClass(index, index + expr.length(), styleClass);
        }
    }
    
    private void clearTerminal() {
        terminalArea.getChildren().clear();
    }

    private void addTerminalText(String text, Color color) {
        Text textNode = new Text(text);
        textNode.setFill(color);
        textNode.setFont(Font.font("FiraCode Nerd Font", 12));
        terminalArea.getChildren().add(textNode);
    }
    
    private StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
        
        while(matcher.find()) {
            String styleClass = 
                  matcher.group("KEYWORD") != null ? "keyword"
                : matcher.group("STRING") != null ? "string"
                : matcher.group("COMMENT") != null ? "comment"
                : matcher.group("NUMBER") != null ? "number"
                : matcher.group("METHOD") != null ? "method"
                : matcher.group("TYPE") != null ? "type"
                : null;
            
            assert styleClass != null;
            
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }
    
    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        String text = codeArea.getText();
        
        return new Task<StyleSpans<Collection<String>>>() {
            protected StyleSpans<Collection<String>> call() throws Exception {
                return computeHighlighting(text);
            }
        };
    }
    
    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        codeArea.setStyleSpans(0, highlighting);
    }
    
//    private void computeHighlightingAsync(Document document, int offset) {
//        new Thread(() -> {
//            try {
//                // Example logic: simulate a computation delay
//                Thread.sleep(100); // Simulate processing delay
//
//                // Perform highlighting logic (placeholder)
//                // For example: parse text and apply styles based on syntax
//
//                String text = document.getText(0, document.getLength());
//
//                // Example: dummy highlight logic (replace with real syntax analysis)
//                if (text.contains("public")) {
//                    // Apply style to "public" keywords (pseudocode)
//                    // SwingUtilities.invokeLater to update UI
//                }
//
//                // You could call a method like:
//                // applyHighlighting(document, highlights);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
//    }


    private void highlightSyntax() {
        String text = codeArea.getText();
        if (text != null && !text.isEmpty()) {
            StyleSpans<Collection<String>> highlighting = computeHighlighting(text);
            codeArea.setStyleSpans(0, highlighting);
        }
    }

    public static void main(String[] args) {
        launch(args);
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