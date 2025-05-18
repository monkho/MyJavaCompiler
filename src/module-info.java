module tst {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
//	requires antlr;
	
	requires transitive jdk.unsupported;
	requires antlr;

	exports application;
	
	opens application to javafx.graphics, javafx.fxml;
}
