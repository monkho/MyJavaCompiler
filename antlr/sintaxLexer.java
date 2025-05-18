// Generated from sintax.g4 by ANTLR 4.13.2

  package compiler.antlr;
  import java.util.List;
  import java.util.ArrayList;
  import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class sintaxLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, INT=11, DOUBLE=12, FLOAT=13, VOID=14, PUBLIC=15, PRIVATE=16, 
		PROTECTED=17, SEMICOLON=18, DOT=19, ID=20, CFLOAT=21, CDOUBLE=22, CINT=23, 
		WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "INT", "DOUBLE", "FLOAT", "VOID", "PUBLIC", "PRIVATE", "PROTECTED", 
			"SEMICOLON", "DOT", "ID", "CFLOAT", "CDOUBLE", "CINT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'class'", "'{'", "'}'", "','", "'('", "')'", "'='", "'+'", "'-'", 
			"'*'", "'int'", "'double'", "'float'", "'void'", "'public'", "'private'", 
			"'protected'", "';'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "INT", 
			"DOUBLE", "FLOAT", "VOID", "PUBLIC", "PRIVATE", "PROTECTED", "SEMICOLON", 
			"DOT", "ID", "CFLOAT", "CDOUBLE", "CINT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	  HashMap TSG = new HashMap();
	  HashMap TSL = new HashMap();
	  List<String> metodos = new ArrayList<String>();

	  void pushMetodo (String ID) {
	    if (metodos.contains(ID)) {
	      System.out.println("Method: " + ID + " already declared.");
	    } else {
	      metodos.add(ID);
	    }
	  }

	  void pushTSG (String ID, String tipo) {
	    Integer existe = (Integer) TSG.get(ID);
	    if (existe == null) {
	      if (tipo.compareTo("int")==0) TSG.put(ID, 1);
	      else if (tipo.compareTo("double")==0) TSG.put(ID, 2);
	      else if (tipo.compareTo("float")==0) TSG.put(ID, 3);
	      else if (tipo.compareTo("class")==0) TSG.put(ID, 4);
	    } else {
	      System.out.println("ID: " + ID + " already in TSG.");
	    }
	  }

	  void pushTSL (String ID, String tipo) {
	    Integer existe = (Integer) TSL.get(ID);
	    if (existe == null) {
	      if (tipo.compareTo("int")==0) TSL.put(ID, 1);
	      else if (tipo.compareTo("double")==0) TSL.put(ID, 2);
	      else if (tipo.compareTo("float")==0) TSG.put(ID, 3);
	      else if (tipo.compareTo("class")==0) TSL.put(ID, 4);
	    } else {
	      System.out.println("ID: " + ID + " already in TSL.");
	    }
	  }

	  Integer findId(String ID) {
	    Integer existe = (Integer) TSL.get(ID);
	    if (existe == null) {
	      existe = (Integer) TSG.get(ID);
	      if (existe == null) {
	        System.out.println("ID: " + ID + " not declared.");
	        return 12;
	      } else {
	        return existe;
	      }
	    } else {
	      return existe;
	    }
	  }
	  
	  int checkTypes(int t1, int t2) {
	    if(t1!=t2) {
	      System.out.println("Error! mismatch types");
	      return 11;
	    }
	    return t2;
	  }


	public sintaxLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "sintax.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0018\u0098\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0005\u0013"+
		"\u007f\b\u0013\n\u0013\f\u0013\u0082\t\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0016\u0004\u0016\u008e\b\u0016\u000b\u0016\f\u0016\u008f"+
		"\u0001\u0017\u0004\u0017\u0093\b\u0017\u000b\u0017\f\u0017\u0094\u0001"+
		"\u0017\u0001\u0017\u0000\u0000\u0018\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0015+\u0016-\u0017/\u0018\u0001\u0000\u0003\u0003\u0000"+
		"AZ__az\u0004\u000009AZ__az\u0003\u0000\t\n\r\r  \u009a\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000"+
		"\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000"+
		"\u00011\u0001\u0000\u0000\u0000\u00037\u0001\u0000\u0000\u0000\u00059"+
		"\u0001\u0000\u0000\u0000\u0007;\u0001\u0000\u0000\u0000\t=\u0001\u0000"+
		"\u0000\u0000\u000b?\u0001\u0000\u0000\u0000\rA\u0001\u0000\u0000\u0000"+
		"\u000fC\u0001\u0000\u0000\u0000\u0011E\u0001\u0000\u0000\u0000\u0013G"+
		"\u0001\u0000\u0000\u0000\u0015I\u0001\u0000\u0000\u0000\u0017M\u0001\u0000"+
		"\u0000\u0000\u0019T\u0001\u0000\u0000\u0000\u001bZ\u0001\u0000\u0000\u0000"+
		"\u001d_\u0001\u0000\u0000\u0000\u001ff\u0001\u0000\u0000\u0000!n\u0001"+
		"\u0000\u0000\u0000#x\u0001\u0000\u0000\u0000%z\u0001\u0000\u0000\u0000"+
		"\'|\u0001\u0000\u0000\u0000)\u0083\u0001\u0000\u0000\u0000+\u0088\u0001"+
		"\u0000\u0000\u0000-\u008d\u0001\u0000\u0000\u0000/\u0092\u0001\u0000\u0000"+
		"\u000012\u0005c\u0000\u000023\u0005l\u0000\u000034\u0005a\u0000\u0000"+
		"45\u0005s\u0000\u000056\u0005s\u0000\u00006\u0002\u0001\u0000\u0000\u0000"+
		"78\u0005{\u0000\u00008\u0004\u0001\u0000\u0000\u00009:\u0005}\u0000\u0000"+
		":\u0006\u0001\u0000\u0000\u0000;<\u0005,\u0000\u0000<\b\u0001\u0000\u0000"+
		"\u0000=>\u0005(\u0000\u0000>\n\u0001\u0000\u0000\u0000?@\u0005)\u0000"+
		"\u0000@\f\u0001\u0000\u0000\u0000AB\u0005=\u0000\u0000B\u000e\u0001\u0000"+
		"\u0000\u0000CD\u0005+\u0000\u0000D\u0010\u0001\u0000\u0000\u0000EF\u0005"+
		"-\u0000\u0000F\u0012\u0001\u0000\u0000\u0000GH\u0005*\u0000\u0000H\u0014"+
		"\u0001\u0000\u0000\u0000IJ\u0005i\u0000\u0000JK\u0005n\u0000\u0000KL\u0005"+
		"t\u0000\u0000L\u0016\u0001\u0000\u0000\u0000MN\u0005d\u0000\u0000NO\u0005"+
		"o\u0000\u0000OP\u0005u\u0000\u0000PQ\u0005b\u0000\u0000QR\u0005l\u0000"+
		"\u0000RS\u0005e\u0000\u0000S\u0018\u0001\u0000\u0000\u0000TU\u0005f\u0000"+
		"\u0000UV\u0005l\u0000\u0000VW\u0005o\u0000\u0000WX\u0005a\u0000\u0000"+
		"XY\u0005t\u0000\u0000Y\u001a\u0001\u0000\u0000\u0000Z[\u0005v\u0000\u0000"+
		"[\\\u0005o\u0000\u0000\\]\u0005i\u0000\u0000]^\u0005d\u0000\u0000^\u001c"+
		"\u0001\u0000\u0000\u0000_`\u0005p\u0000\u0000`a\u0005u\u0000\u0000ab\u0005"+
		"b\u0000\u0000bc\u0005l\u0000\u0000cd\u0005i\u0000\u0000de\u0005c\u0000"+
		"\u0000e\u001e\u0001\u0000\u0000\u0000fg\u0005p\u0000\u0000gh\u0005r\u0000"+
		"\u0000hi\u0005i\u0000\u0000ij\u0005v\u0000\u0000jk\u0005a\u0000\u0000"+
		"kl\u0005t\u0000\u0000lm\u0005e\u0000\u0000m \u0001\u0000\u0000\u0000n"+
		"o\u0005p\u0000\u0000op\u0005r\u0000\u0000pq\u0005o\u0000\u0000qr\u0005"+
		"t\u0000\u0000rs\u0005e\u0000\u0000st\u0005c\u0000\u0000tu\u0005t\u0000"+
		"\u0000uv\u0005e\u0000\u0000vw\u0005d\u0000\u0000w\"\u0001\u0000\u0000"+
		"\u0000xy\u0005;\u0000\u0000y$\u0001\u0000\u0000\u0000z{\u0005.\u0000\u0000"+
		"{&\u0001\u0000\u0000\u0000|\u0080\u0007\u0000\u0000\u0000}\u007f\u0007"+
		"\u0001\u0000\u0000~}\u0001\u0000\u0000\u0000\u007f\u0082\u0001\u0000\u0000"+
		"\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000"+
		"\u0081(\u0001\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u0003-\u0016\u0000\u0084\u0085\u0003%\u0012\u0000\u0085\u0086\u0003"+
		"-\u0016\u0000\u0086\u0087\u0005f\u0000\u0000\u0087*\u0001\u0000\u0000"+
		"\u0000\u0088\u0089\u0003-\u0016\u0000\u0089\u008a\u0003%\u0012\u0000\u008a"+
		"\u008b\u0003-\u0016\u0000\u008b,\u0001\u0000\u0000\u0000\u008c\u008e\u0002"+
		"09\u0000\u008d\u008c\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000"+
		"\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000"+
		"\u0000\u0090.\u0001\u0000\u0000\u0000\u0091\u0093\u0007\u0002\u0000\u0000"+
		"\u0092\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000"+
		"\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000"+
		"\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0097\u0006\u0017\u0000\u0000"+
		"\u00970\u0001\u0000\u0000\u0000\u0004\u0000\u0080\u008f\u0094\u0001\u0006"+
		"\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}