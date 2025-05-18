@echo off

set JAVAFX_PATH=lib\javafx-sdk-23.0.2\lib
set ANTLR_PATH=lib\antlr-3.5.2-complete.jar
set BIN_PATH=bin
set MAIN_CLASS=application.SimpleTextEditor
set JAVA_FILES=src\application\*.java
set JAVA_MODULE=src\module-info.java

REM TODO: Compile the grammar

javac --module-path "%JAVAFX_PATH%" --add-modules javafx.controls,javafx.fxml,javafx.graphics --add-reads tst=ALL-UNNAMED -cp "%ANTLR_PATH%;." -d %BIN_PATH% %JAVA_FILES% %JAVA_MODULE%

java --module-path "%JAVAFX_PATH%;%BIN_PATH%" --add-modules tst,javafx.controls,javafx.fxml,javafx.graphics --add-reads tst=ALL-UNNAMED --class-path "%ANTLR_PATH%;%BIN_PATH%" %MAIN_CLASS%

pause