package application;

import java.io.IOException;

import org.antlr.runtime.*;

public class Test {
	public static void main(String[] args) throws IOException, RecognitionException {
		ANTLRInputStream input = new ANTLRInputStream(System.in);
		sintaxLexer lexer = new sintaxLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		sintaxParser parser = new sintaxParser(tokens);
		parser.program();
	}
}
