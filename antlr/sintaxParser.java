// Generated from sintax.g4 by ANTLR 4.13.2

  package compiler.antlr;
  import java.util.List;
  import java.util.ArrayList;
  import java.util.HashMap;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class sintaxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, INT=11, DOUBLE=12, FLOAT=13, VOID=14, PUBLIC=15, PRIVATE=16, 
		PROTECTED=17, SEMICOLON=18, DOT=19, ID=20, CFLOAT=21, CDOUBLE=22, CINT=23, 
		WS=24;
	public static final int
		RULE_program = 0, RULE_clase = 1, RULE_miembro = 2, RULE_propiedad = 3, 
		RULE_metodo = 4, RULE_instruccion = 5, RULE_asignacion = 6, RULE_declaracion_local = 7, 
		RULE_declaracion_args = 8, RULE_expr = 9, RULE_multExpr = 10, RULE_atom = 11, 
		RULE_modificAcceso = 12, RULE_tipo = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "clase", "miembro", "propiedad", "metodo", "instruccion", 
			"asignacion", "declaracion_local", "declaracion_args", "expr", "multExpr", 
			"atom", "modificAcceso", "tipo"
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

	@Override
	public String getGrammarFileName() { return "sintax.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


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

	public sintaxParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<ClaseContext> clase() {
			return getRuleContexts(ClaseContext.class);
		}
		public ClaseContext clase(int i) {
			return getRuleContext(ClaseContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(28);
				clase();
				}
				}
				setState(31); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 229378L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClaseContext extends ParserRuleContext {
		public Token ID;
		public TerminalNode ID() { return getToken(sintaxParser.ID, 0); }
		public ModificAccesoContext modificAcceso() {
			return getRuleContext(ModificAccesoContext.class,0);
		}
		public List<MiembroContext> miembro() {
			return getRuleContexts(MiembroContext.class);
		}
		public MiembroContext miembro(int i) {
			return getRuleContext(MiembroContext.class,i);
		}
		public ClaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterClase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitClase(this);
		}
	}

	public final ClaseContext clase() throws RecognitionException {
		ClaseContext _localctx = new ClaseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_clase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 229376L) != 0)) {
				{
				setState(33);
				modificAcceso();
				}
			}

			setState(36);
			match(T__0);
			setState(37);
			((ClaseContext)_localctx).ID = match(ID);
			pushTSG((((ClaseContext)_localctx).ID!=null?((ClaseContext)_localctx).ID.getText():null), "class");
			setState(39);
			match(T__1);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 260096L) != 0)) {
				{
				{
				setState(40);
				miembro();
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MiembroContext extends ParserRuleContext {
		public MetodoContext metodo() {
			return getRuleContext(MetodoContext.class,0);
		}
		public PropiedadContext propiedad() {
			return getRuleContext(PropiedadContext.class,0);
		}
		public MiembroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_miembro; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterMiembro(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitMiembro(this);
		}
	}

	public final MiembroContext miembro() throws RecognitionException {
		MiembroContext _localctx = new MiembroContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_miembro);
		try {
			setState(50);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				metodo();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(49);
				propiedad();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PropiedadContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(sintaxParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(sintaxParser.ID, i);
		}
		public TerminalNode SEMICOLON() { return getToken(sintaxParser.SEMICOLON, 0); }
		public ModificAccesoContext modificAcceso() {
			return getRuleContext(ModificAccesoContext.class,0);
		}
		public PropiedadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propiedad; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterPropiedad(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitPropiedad(this);
		}
	}

	public final PropiedadContext propiedad() throws RecognitionException {
		PropiedadContext _localctx = new PropiedadContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_propiedad);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 229376L) != 0)) {
				{
				setState(52);
				modificAcceso();
				}
			}

			setState(55);
			tipo();
			setState(56);
			match(ID);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(57);
				match(T__3);
				setState(58);
				match(ID);
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MetodoContext extends ParserRuleContext {
		public Token ID;
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode ID() { return getToken(sintaxParser.ID, 0); }
		public ModificAccesoContext modificAcceso() {
			return getRuleContext(ModificAccesoContext.class,0);
		}
		public Declaracion_argsContext declaracion_args() {
			return getRuleContext(Declaracion_argsContext.class,0);
		}
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public MetodoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metodo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterMetodo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitMetodo(this);
		}
	}

	public final MetodoContext metodo() throws RecognitionException {
		MetodoContext _localctx = new MetodoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_metodo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 229376L) != 0)) {
				{
				setState(66);
				modificAcceso();
				}
			}

			setState(69);
			tipo();
			setState(70);
			((MetodoContext)_localctx).ID = match(ID);
			pushMetodo((((MetodoContext)_localctx).ID!=null?((MetodoContext)_localctx).ID.getText():null));
			setState(72);
			match(T__4);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 30720L) != 0)) {
				{
				setState(73);
				declaracion_args();
				}
			}

			setState(76);
			match(T__5);
			setState(77);
			match(T__1);
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1079296L) != 0)) {
				{
				{
				setState(78);
				instruccion();
				}
				}
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(84);
			match(T__2);
			TSL.clear();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InstruccionContext extends ParserRuleContext {
		public AsignacionContext asignacion() {
			return getRuleContext(AsignacionContext.class,0);
		}
		public Declaracion_localContext declaracion_local() {
			return getRuleContext(Declaracion_localContext.class,0);
		}
		public InstruccionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruccion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterInstruccion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitInstruccion(this);
		}
	}

	public final InstruccionContext instruccion() throws RecognitionException {
		InstruccionContext _localctx = new InstruccionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_instruccion);
		try {
			setState(89);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				asignacion();
				}
				break;
			case INT:
			case DOUBLE:
			case FLOAT:
			case VOID:
				enterOuterAlt(_localctx, 2);
				{
				setState(88);
				declaracion_local();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AsignacionContext extends ParserRuleContext {
		public ExprContext expr;
		public TerminalNode ID() { return getToken(sintaxParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(sintaxParser.SEMICOLON, 0); }
		public AsignacionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_asignacion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterAsignacion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitAsignacion(this);
		}
	}

	public final AsignacionContext asignacion() throws RecognitionException {
		AsignacionContext _localctx = new AsignacionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_asignacion);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(ID);
			setState(92);
			match(T__6);
			setState(93);
			((AsignacionContext)_localctx).expr = expr();
			setState(94);
			match(SEMICOLON);
			 System.out.println("Expr: " + (((AsignacionContext)_localctx).expr!=null?_input.getText(((AsignacionContext)_localctx).expr.start,((AsignacionContext)_localctx).expr.stop):null) + " Type: " + ((AsignacionContext)_localctx).expr.t); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Declaracion_localContext extends ParserRuleContext {
		public TipoContext tipo;
		public Token id1;
		public Token id2;
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(sintaxParser.SEMICOLON, 0); }
		public List<TerminalNode> ID() { return getTokens(sintaxParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(sintaxParser.ID, i);
		}
		public Declaracion_localContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracion_local; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterDeclaracion_local(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitDeclaracion_local(this);
		}
	}

	public final Declaracion_localContext declaracion_local() throws RecognitionException {
		Declaracion_localContext _localctx = new Declaracion_localContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_declaracion_local);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			((Declaracion_localContext)_localctx).tipo = tipo();
			setState(98);
			((Declaracion_localContext)_localctx).id1 = match(ID);
			pushTSL((((Declaracion_localContext)_localctx).id1!=null?((Declaracion_localContext)_localctx).id1.getText():null), (((Declaracion_localContext)_localctx).tipo!=null?_input.getText(((Declaracion_localContext)_localctx).tipo.start,((Declaracion_localContext)_localctx).tipo.stop):null));
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(100);
				match(T__3);
				setState(101);
				((Declaracion_localContext)_localctx).id2 = match(ID);
				pushTSL((((Declaracion_localContext)_localctx).id2!=null?((Declaracion_localContext)_localctx).id2.getText():null), (((Declaracion_localContext)_localctx).tipo!=null?_input.getText(((Declaracion_localContext)_localctx).tipo.start,((Declaracion_localContext)_localctx).tipo.stop):null));
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Declaracion_argsContext extends ParserRuleContext {
		public TipoContext tipo1;
		public Token id1;
		public TipoContext tipo2;
		public Token id2;
		public List<TipoContext> tipo() {
			return getRuleContexts(TipoContext.class);
		}
		public TipoContext tipo(int i) {
			return getRuleContext(TipoContext.class,i);
		}
		public List<TerminalNode> ID() { return getTokens(sintaxParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(sintaxParser.ID, i);
		}
		public Declaracion_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracion_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterDeclaracion_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitDeclaracion_args(this);
		}
	}

	public final Declaracion_argsContext declaracion_args() throws RecognitionException {
		Declaracion_argsContext _localctx = new Declaracion_argsContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_declaracion_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			((Declaracion_argsContext)_localctx).tipo1 = tipo();
			setState(111);
			((Declaracion_argsContext)_localctx).id1 = match(ID);
			pushTSL((((Declaracion_argsContext)_localctx).id1!=null?((Declaracion_argsContext)_localctx).id1.getText():null), (((Declaracion_argsContext)_localctx).tipo1!=null?_input.getText(((Declaracion_argsContext)_localctx).tipo1.start,((Declaracion_argsContext)_localctx).tipo1.stop):null));
			setState(120);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(113);
				match(T__3);
				setState(114);
				((Declaracion_argsContext)_localctx).tipo2 = tipo();
				setState(115);
				((Declaracion_argsContext)_localctx).id2 = match(ID);
				pushTSL((((Declaracion_argsContext)_localctx).id2!=null?((Declaracion_argsContext)_localctx).id2.getText():null), (((Declaracion_argsContext)_localctx).tipo2!=null?_input.getText(((Declaracion_argsContext)_localctx).tipo2.start,((Declaracion_argsContext)_localctx).tipo2.stop):null));
				}
				}
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public int t;
		public MultExprContext m1;
		public MultExprContext m2;
		public List<MultExprContext> multExpr() {
			return getRuleContexts(MultExprContext.class);
		}
		public MultExprContext multExpr(int i) {
			return getRuleContext(MultExprContext.class,i);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			((ExprContext)_localctx).m1 = multExpr();
			 ((ExprContext)_localctx).t = ((ExprContext)_localctx).m1.t; 
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7 || _la==T__8) {
				{
				{
				setState(125);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(126);
				((ExprContext)_localctx).m2 = multExpr();
				 ((ExprContext)_localctx).t = ((ExprContext)_localctx).m2.t; 
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MultExprContext extends ParserRuleContext {
		public int t;
		public AtomContext a1;
		public AtomContext a2;
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public MultExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterMultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitMultExpr(this);
		}
	}

	public final MultExprContext multExpr() throws RecognitionException {
		MultExprContext _localctx = new MultExprContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_multExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			((MultExprContext)_localctx).a1 = atom();
			 ((MultExprContext)_localctx).t = ((MultExprContext)_localctx).a1.t; 
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(136);
				match(T__9);
				setState(137);
				((MultExprContext)_localctx).a2 = atom();
				 ((MultExprContext)_localctx).t =  checkTypes(_localctx.t, ((MultExprContext)_localctx).a2.t); 
				}
				}
				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public int t;
		public Token ID;
		public ExprContext expr;
		public TerminalNode CINT() { return getToken(sintaxParser.CINT, 0); }
		public TerminalNode CFLOAT() { return getToken(sintaxParser.CFLOAT, 0); }
		public TerminalNode CDOUBLE() { return getToken(sintaxParser.CDOUBLE, 0); }
		public TerminalNode ID() { return getToken(sintaxParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitAtom(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_atom);
		try {
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				match(CINT);
				 ((AtomContext)_localctx).t = 1; 
				}
				break;
			case CFLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				match(CFLOAT);
				 ((AtomContext)_localctx).t = 3; 
				}
				break;
			case CDOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(149);
				match(CDOUBLE);
				 ((AtomContext)_localctx).t = 2; 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(151);
				((AtomContext)_localctx).ID = match(ID);
				 /* search ID in TSL, if found, return its type 
				                                if not found in TSL, search in TSG, if not found
				                                print "Not declared variable", and return 12, because
				                                it can be embeded in another expresion*/
				                              ((AtomContext)_localctx).t =  findId((((AtomContext)_localctx).ID!=null?((AtomContext)_localctx).ID.getText():null));
				                            
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(153);
				match(T__4);
				setState(154);
				((AtomContext)_localctx).expr = expr();
				 ((AtomContext)_localctx).t = ((AtomContext)_localctx).expr.t; 
				setState(156);
				match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModificAccesoContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(sintaxParser.PUBLIC, 0); }
		public TerminalNode PRIVATE() { return getToken(sintaxParser.PRIVATE, 0); }
		public TerminalNode PROTECTED() { return getToken(sintaxParser.PROTECTED, 0); }
		public ModificAccesoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modificAcceso; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterModificAcceso(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitModificAcceso(this);
		}
	}

	public final ModificAccesoContext modificAcceso() throws RecognitionException {
		ModificAccesoContext _localctx = new ModificAccesoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_modificAcceso);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 229376L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TipoContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(sintaxParser.INT, 0); }
		public TerminalNode DOUBLE() { return getToken(sintaxParser.DOUBLE, 0); }
		public TerminalNode FLOAT() { return getToken(sintaxParser.FLOAT, 0); }
		public TerminalNode VOID() { return getToken(sintaxParser.VOID, 0); }
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_tipo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 30720L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0018\u00a5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0004\u0000\u001e\b\u0000"+
		"\u000b\u0000\f\u0000\u001f\u0001\u0001\u0003\u0001#\b\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001*\b\u0001"+
		"\n\u0001\f\u0001-\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0003\u00023\b\u0002\u0001\u0003\u0003\u00036\b\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0005\u0003<\b\u0003\n\u0003\f\u0003?\t"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0003\u0004D\b\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004K\b"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004P\b\u0004\n\u0004"+
		"\f\u0004S\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0003\u0005Z\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0005\u0007h\b\u0007\n\u0007\f\u0007k\t"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0005\bw\b\b\n\b\f\bz\t\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0005\t\u0082\b\t\n\t\f\t\u0085\t\t\u0001\n"+
		"\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\n\u008d\b\n\n\n\f\n\u0090"+
		"\t\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u009f\b\u000b\u0001\f\u0001\f\u0001\r\u0001"+
		"\r\u0001\r\u0000\u0000\u000e\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u0000\u0003\u0001\u0000\b\t\u0001\u0000"+
		"\u000f\u0011\u0001\u0000\u000b\u000e\u00a8\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0002\"\u0001\u0000\u0000\u0000\u00042\u0001\u0000\u0000\u0000"+
		"\u00065\u0001\u0000\u0000\u0000\bC\u0001\u0000\u0000\u0000\nY\u0001\u0000"+
		"\u0000\u0000\f[\u0001\u0000\u0000\u0000\u000ea\u0001\u0000\u0000\u0000"+
		"\u0010n\u0001\u0000\u0000\u0000\u0012{\u0001\u0000\u0000\u0000\u0014\u0086"+
		"\u0001\u0000\u0000\u0000\u0016\u009e\u0001\u0000\u0000\u0000\u0018\u00a0"+
		"\u0001\u0000\u0000\u0000\u001a\u00a2\u0001\u0000\u0000\u0000\u001c\u001e"+
		"\u0003\u0002\u0001\u0000\u001d\u001c\u0001\u0000\u0000\u0000\u001e\u001f"+
		"\u0001\u0000\u0000\u0000\u001f\u001d\u0001\u0000\u0000\u0000\u001f \u0001"+
		"\u0000\u0000\u0000 \u0001\u0001\u0000\u0000\u0000!#\u0003\u0018\f\u0000"+
		"\"!\u0001\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000"+
		"\u0000$%\u0005\u0001\u0000\u0000%&\u0005\u0014\u0000\u0000&\'\u0006\u0001"+
		"\uffff\uffff\u0000\'+\u0005\u0002\u0000\u0000(*\u0003\u0004\u0002\u0000"+
		")(\u0001\u0000\u0000\u0000*-\u0001\u0000\u0000\u0000+)\u0001\u0000\u0000"+
		"\u0000+,\u0001\u0000\u0000\u0000,.\u0001\u0000\u0000\u0000-+\u0001\u0000"+
		"\u0000\u0000./\u0005\u0003\u0000\u0000/\u0003\u0001\u0000\u0000\u0000"+
		"03\u0003\b\u0004\u000013\u0003\u0006\u0003\u000020\u0001\u0000\u0000\u0000"+
		"21\u0001\u0000\u0000\u00003\u0005\u0001\u0000\u0000\u000046\u0003\u0018"+
		"\f\u000054\u0001\u0000\u0000\u000056\u0001\u0000\u0000\u000067\u0001\u0000"+
		"\u0000\u000078\u0003\u001a\r\u00008=\u0005\u0014\u0000\u00009:\u0005\u0004"+
		"\u0000\u0000:<\u0005\u0014\u0000\u0000;9\u0001\u0000\u0000\u0000<?\u0001"+
		"\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000"+
		">@\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000@A\u0005\u0012\u0000"+
		"\u0000A\u0007\u0001\u0000\u0000\u0000BD\u0003\u0018\f\u0000CB\u0001\u0000"+
		"\u0000\u0000CD\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000EF\u0003"+
		"\u001a\r\u0000FG\u0005\u0014\u0000\u0000GH\u0006\u0004\uffff\uffff\u0000"+
		"HJ\u0005\u0005\u0000\u0000IK\u0003\u0010\b\u0000JI\u0001\u0000\u0000\u0000"+
		"JK\u0001\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000LM\u0005\u0006\u0000"+
		"\u0000MQ\u0005\u0002\u0000\u0000NP\u0003\n\u0005\u0000ON\u0001\u0000\u0000"+
		"\u0000PS\u0001\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000QR\u0001\u0000"+
		"\u0000\u0000RT\u0001\u0000\u0000\u0000SQ\u0001\u0000\u0000\u0000TU\u0005"+
		"\u0003\u0000\u0000UV\u0006\u0004\uffff\uffff\u0000V\t\u0001\u0000\u0000"+
		"\u0000WZ\u0003\f\u0006\u0000XZ\u0003\u000e\u0007\u0000YW\u0001\u0000\u0000"+
		"\u0000YX\u0001\u0000\u0000\u0000Z\u000b\u0001\u0000\u0000\u0000[\\\u0005"+
		"\u0014\u0000\u0000\\]\u0005\u0007\u0000\u0000]^\u0003\u0012\t\u0000^_"+
		"\u0005\u0012\u0000\u0000_`\u0006\u0006\uffff\uffff\u0000`\r\u0001\u0000"+
		"\u0000\u0000ab\u0003\u001a\r\u0000bc\u0005\u0014\u0000\u0000ci\u0006\u0007"+
		"\uffff\uffff\u0000de\u0005\u0004\u0000\u0000ef\u0005\u0014\u0000\u0000"+
		"fh\u0006\u0007\uffff\uffff\u0000gd\u0001\u0000\u0000\u0000hk\u0001\u0000"+
		"\u0000\u0000ig\u0001\u0000\u0000\u0000ij\u0001\u0000\u0000\u0000jl\u0001"+
		"\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000lm\u0005\u0012\u0000\u0000"+
		"m\u000f\u0001\u0000\u0000\u0000no\u0003\u001a\r\u0000op\u0005\u0014\u0000"+
		"\u0000px\u0006\b\uffff\uffff\u0000qr\u0005\u0004\u0000\u0000rs\u0003\u001a"+
		"\r\u0000st\u0005\u0014\u0000\u0000tu\u0006\b\uffff\uffff\u0000uw\u0001"+
		"\u0000\u0000\u0000vq\u0001\u0000\u0000\u0000wz\u0001\u0000\u0000\u0000"+
		"xv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000\u0000y\u0011\u0001\u0000"+
		"\u0000\u0000zx\u0001\u0000\u0000\u0000{|\u0003\u0014\n\u0000|\u0083\u0006"+
		"\t\uffff\uffff\u0000}~\u0007\u0000\u0000\u0000~\u007f\u0003\u0014\n\u0000"+
		"\u007f\u0080\u0006\t\uffff\uffff\u0000\u0080\u0082\u0001\u0000\u0000\u0000"+
		"\u0081}\u0001\u0000\u0000\u0000\u0082\u0085\u0001\u0000\u0000\u0000\u0083"+
		"\u0081\u0001\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084"+
		"\u0013\u0001\u0000\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0003\u0016\u000b\u0000\u0087\u008e\u0006\n\uffff\uffff\u0000\u0088"+
		"\u0089\u0005\n\u0000\u0000\u0089\u008a\u0003\u0016\u000b\u0000\u008a\u008b"+
		"\u0006\n\uffff\uffff\u0000\u008b\u008d\u0001\u0000\u0000\u0000\u008c\u0088"+
		"\u0001\u0000\u0000\u0000\u008d\u0090\u0001\u0000\u0000\u0000\u008e\u008c"+
		"\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000\u008f\u0015"+
		"\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0091\u0092"+
		"\u0005\u0017\u0000\u0000\u0092\u009f\u0006\u000b\uffff\uffff\u0000\u0093"+
		"\u0094\u0005\u0015\u0000\u0000\u0094\u009f\u0006\u000b\uffff\uffff\u0000"+
		"\u0095\u0096\u0005\u0016\u0000\u0000\u0096\u009f\u0006\u000b\uffff\uffff"+
		"\u0000\u0097\u0098\u0005\u0014\u0000\u0000\u0098\u009f\u0006\u000b\uffff"+
		"\uffff\u0000\u0099\u009a\u0005\u0005\u0000\u0000\u009a\u009b\u0003\u0012"+
		"\t\u0000\u009b\u009c\u0006\u000b\uffff\uffff\u0000\u009c\u009d\u0005\u0006"+
		"\u0000\u0000\u009d\u009f\u0001\u0000\u0000\u0000\u009e\u0091\u0001\u0000"+
		"\u0000\u0000\u009e\u0093\u0001\u0000\u0000\u0000\u009e\u0095\u0001\u0000"+
		"\u0000\u0000\u009e\u0097\u0001\u0000\u0000\u0000\u009e\u0099\u0001\u0000"+
		"\u0000\u0000\u009f\u0017\u0001\u0000\u0000\u0000\u00a0\u00a1\u0007\u0001"+
		"\u0000\u0000\u00a1\u0019\u0001\u0000\u0000\u0000\u00a2\u00a3\u0007\u0002"+
		"\u0000\u0000\u00a3\u001b\u0001\u0000\u0000\u0000\u000f\u001f\"+25=CJQ"+
		"Yix\u0083\u008e\u009e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}