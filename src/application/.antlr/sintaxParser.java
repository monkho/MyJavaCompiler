// Generated from c:/Users/Obgoh/OneDrive - Instituto Tecnológico de Morelia/Documentos/08_Semestre/03 - Lenguajes y automatas II/practicas/tst/src/application/sintax.g by ANTLR 4.13.1

package application;
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

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class sintaxParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, CONTAR=12, INT=13, DOUBLE=14, FLOAT=15, VOID=16, PUBLIC=17, 
		PRIVATE=18, PROTECTED=19, SEMICOLON=20, DOT=21, ID=22, CFLOAT=23, CDOUBLE=24, 
		CINT=25, WS=26;
	public static final int
		RULE_program = 0, RULE_contar = 1, RULE_clase = 2, RULE_miembro = 3, RULE_propiedad = 4, 
		RULE_metodo = 5, RULE_instruccion = 6, RULE_asignacion = 7, RULE_declaracion_local = 8, 
		RULE_declaracion_args = 9, RULE_llamada = 10, RULE_expr = 11, RULE_multExpr = 12, 
		RULE_atom = 13, RULE_metodo_principal = 14, RULE_modificAcceso = 15, RULE_tipo = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "contar", "clase", "miembro", "propiedad", "metodo", "instruccion", 
			"asignacion", "declaracion_local", "declaracion_args", "llamada", "expr", 
			"multExpr", "atom", "metodo_principal", "modificAcceso", "tipo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'class'", "'{'", "'}'", "','", "'('", "')'", "'='", "'+'", "'-'", 
			"'*'", "'main'", "'contar'", "'int'", "'double'", "'float'", "'void'", 
			"'public'", "'private'", "'protected'", "';'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			"CONTAR", "INT", "DOUBLE", "FLOAT", "VOID", "PUBLIC", "PRIVATE", "PROTECTED", 
			"SEMICOLON", "DOT", "ID", "CFLOAT", "CDOUBLE", "CINT", "WS"
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
	public String getGrammarFileName() { return "sintax.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	  HashMap TSG = new HashMap();
	  HashMap TSL = new HashMap();
	  HashMap<String, Integer> calls = new HashMap<>();
	  List<String> classess = new ArrayList<String>();
	  String textOutput="";
	  int contarError = 0;

	  void pushTSG (String ID, String tipo) {
	    Integer existe = (Integer) TSG.get(ID);
	    if (existe == null) {
	      if (tipo.compareTo("int")==0) TSG.put(ID, 1);
	      else if (tipo.compareTo("double")==0) TSG.put(ID, 2);
	      else if (tipo.compareTo("float")==0) TSG.put(ID, 3);
	      else if (tipo.compareTo("class")==0) TSG.put(ID, 4);
	      else if (tipo.compareTo("void")==0) TSG.put(ID, 5);
	    } else {
	      System.out.println("ERROR! ID: " + ID + " already in TSG.");
	      textOutput += "ERROR! ID: " + ID + " already in TSG.\n";
	    }
	  }

	  void pushTSL (String ID, String tipo) {
	    Integer existe = (Integer) TSL.get(ID);
	    if (existe == null) {
	      if (tipo.compareTo("int")==0) TSL.put(ID, 1);
	      else if (tipo.compareTo("double")==0) TSL.put(ID, 2);
	      else if (tipo.compareTo("float")==0) TSL.put(ID, 3);
	      else if (tipo.compareTo("class")==0) TSL.put(ID, 4);
	    } else {
	      System.out.println("ERROR! ID: " + ID + " already in TSL.");
	      textOutput += "ERROR! ID: " + ID + " already in TSL.\n";
	    }
	  }

	  void pushClass (String ID) {
	    if (classess.contains(ID)) {
	      System.out.println("ERROR! Class: " + ID + ", already declared");
	    } else {
	      classess.add(ID);
	    }
	  }

	  void pushCall (String ID) {
	    System.out.println("ID: " + ID + " found in TSG with type==> " + TSG.get(ID));
	    calls.put(ID, (calls.get(ID)==null?1:calls.get(ID)+1));
	  }

	  void countCalls () {
	    calls.forEach( (k, v) -> {
	        if(v < 2) {
	            System.out.println("WARNING: method: <<" + k + "()>> is only called once or never called");
	            textOutput += "WARNING: method: <<" + k + "()>> is only called once or never called\n";
	        }
	        //System.out.println("Calls for function: " + k + "=" + v);
	    });
	  }

	  Integer findId(String ID) {
	    Integer existe = (Integer) TSL.get(ID);
	    if (existe == null) {
	      // System.out.println("ID: " + ID  + " is not in TSL");
	      existe = (Integer) TSG.get(ID);
	      
	      if (existe == null) {
	        System.out.println("ERROR! ID: " + ID + " not declared.");
	        textOutput += "ERROR! ID: " + ID + " not declared.\n";
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
	      textOutput += "WARNING! mismatch types\n";
	      contarError ++;

	      //System.out.println("WARNING! mismatch types. Errores contados: " + contarError);
	      return 11;
	    }
	    return t2;
	  }
	  
	  int checkTypes(int t1, int t2, String exp) {
	    if(t1!=t2) {
	      textOutput += "WARNING! mismatch types on expression: <<"+exp+">>\n";
	      contarError ++;

	      //System.out.println("WARNING! mismatch types. Errores contados: " + contarError);
	      return 11;
	    }
	    return t2;
	  }

	  // Allows to take the exception and use it whenever it is needed
	  public void displayRecoginitionError(String[] tokenNames, RecognitionException e) {
	    String hdr = getErrorHeader(e);
	    String msg = getErrorMessage(e, tokenNames);
	    System.out.println("Found error!!: : " + hdr + " " + msg);
	  }

	  public void checkAsignacion(String id, int exprType) {
	    int idType = findId(id);
	    System.out.println("id tipo: " + idType + ", expr tipo: " + exprType);
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
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(34);
				clase();
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 917506L) != 0) );
			 countCalls(); 
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
	public static class ContarContext extends ParserRuleContext {
		public TerminalNode CONTAR() { return getToken(sintaxParser.CONTAR, 0); }
		public ContarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterContar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitContar(this);
		}
	}

	public final ContarContext contar() throws RecognitionException {
		ContarContext _localctx = new ContarContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_contar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(CONTAR);

			    System.out.println("Cantidad de errores de expresion: " + contarError);
			    
			    countCalls();

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
		public TerminalNode ID() { return getToken(sintaxParser.ID, 0); }
		public Metodo_principalContext metodo_principal() {
			return getRuleContext(Metodo_principalContext.class,0);
		}
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
		enterRule(_localctx, 4, RULE_clase);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 917504L) != 0)) {
				{
				setState(44);
				modificAcceso();
				}
			}

			setState(47);
			match(T__0);
			setState(48);
			match(ID);

			      pushClass((((ClaseContext)_localctx).ID!=null?((ClaseContext)_localctx).ID.getText():null));
			      pushTSG((((ClaseContext)_localctx).ID!=null?((ClaseContext)_localctx).ID.getText():null), "class");
			    
			setState(50);
			match(T__1);
			setState(54);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(51);
					miembro();
					}
					} 
				}
				setState(56);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			setState(57);
			metodo_principal();
			setState(58);
			match(T__2);

			      TSG.clear();
			    
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
		enterRule(_localctx, 6, RULE_miembro);
		try {
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				metodo();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
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
		enterRule(_localctx, 8, RULE_propiedad);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 917504L) != 0)) {
				{
				setState(65);
				modificAcceso();
				}
			}

			setState(68);
			tipo();
			setState(69);
			((PropiedadContext)_localctx).id1 = match(ID);

			      pushTSG((((PropiedadContext)_localctx).id1!=null?((PropiedadContext)_localctx).id1.getText():null), (((PropiedadContext)_localctx).tipo!=null?_input.getText(((PropiedadContext)_localctx).tipo.start,((PropiedadContext)_localctx).tipo.stop):null));
			    
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(71);
				match(T__3);
				setState(72);
				((PropiedadContext)_localctx).id2 = match(ID);

				        pushTSG((((PropiedadContext)_localctx).id2!=null?((PropiedadContext)_localctx).id2.getText():null), (((PropiedadContext)_localctx).tipo!=null?_input.getText(((PropiedadContext)_localctx).tipo.start,((PropiedadContext)_localctx).tipo.stop):null));
				      
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79);
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
		enterRule(_localctx, 10, RULE_metodo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 917504L) != 0)) {
				{
				setState(81);
				modificAcceso();
				}
			}

			setState(84);
			tipo();
			setState(85);
			match(ID);

			      pushTSG((((MetodoContext)_localctx).ID!=null?((MetodoContext)_localctx).ID.getText():null), (((MetodoContext)_localctx).tipo!=null?_input.getText(((MetodoContext)_localctx).tipo.start,((MetodoContext)_localctx).tipo.stop):null));
			    
			setState(87);
			match(T__4);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 122880L) != 0)) {
				{
				setState(88);
				declaracion_args();
				}
			}

			setState(91);
			match(T__5);
			setState(92);
			match(T__1);
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4317184L) != 0)) {
				{
				{
				setState(93);
				instruccion();
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
			match(T__2);

			      calls.put((((MetodoContext)_localctx).ID!=null?((MetodoContext)_localctx).ID.getText():null), 0);
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
		public LlamadaContext llamada() {
			return getRuleContext(LlamadaContext.class,0);
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
		enterRule(_localctx, 12, RULE_instruccion);
		try {
			setState(105);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				asignacion();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				declaracion_local();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
				llamada();
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
	public static class AsignacionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sintaxParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(sintaxParser.SEMICOLON, 0); }
		public LlamadaContext llamada() {
			return getRuleContext(LlamadaContext.class,0);
		}
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
		enterRule(_localctx, 14, RULE_asignacion);
		try {
			setState(118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				match(ID);
				setState(108);
				match(T__6);
				setState(109);
				expr();
				setState(110);
				match(SEMICOLON);

				      String exp = (((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null) + '=' + (((AsignacionContext)_localctx).expr!=null?_input.getText(((AsignacionContext)_localctx).expr.start,((AsignacionContext)_localctx).expr.stop):null);
				      checkTypes(findId((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null)), ((AsignacionContext)_localctx).expr.t, exp);
				      
				      checkAsignacion((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null), ((AsignacionContext)_localctx).expr.t);
				      //System.out.println("\tExpression: " + (((AsignacionContext)_localctx).expr!=null?_input.getText(((AsignacionContext)_localctx).expr.start,((AsignacionContext)_localctx).expr.stop):null) + " of type: " + ((AsignacionContext)_localctx).expr.t);
				      // textOutput += "\tExpression: " + (((AsignacionContext)_localctx).expr!=null?_input.getText(((AsignacionContext)_localctx).expr.start,((AsignacionContext)_localctx).expr.stop):null) + " of type: " + ((AsignacionContext)_localctx).expr.t + "\n";
				    
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(113);
				match(ID);
				setState(114);
				match(T__6);
				setState(115);
				llamada();

				      String exp = (((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null) + '=' + (((AsignacionContext)_localctx).llamada!=null?_input.getText(((AsignacionContext)_localctx).llamada.start,((AsignacionContext)_localctx).llamada.stop):null);
				      checkTypes(findId((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null)), ((AsignacionContext)_localctx).llamada.t, exp);
				      
				      checkAsignacion((((AsignacionContext)_localctx).ID!=null?((AsignacionContext)_localctx).ID.getText():null), ((AsignacionContext)_localctx).llamada.t);
				      //System.out.println("\tExpression: " + (((AsignacionContext)_localctx).llamada!=null?_input.getText(((AsignacionContext)_localctx).llamada.start,((AsignacionContext)_localctx).llamada.stop):null) + " of type: " + ((AsignacionContext)_localctx).llamada.t);
				      // textOutput += "\tExpression: " + (((AsignacionContext)_localctx).llamada!=null?_input.getText(((AsignacionContext)_localctx).llamada.start,((AsignacionContext)_localctx).llamada.stop):null) + " of type: " + ((AsignacionContext)_localctx).llamada.t + "\n";
				    
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
	public static class Declaracion_localContext extends ParserRuleContext {
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
		enterRule(_localctx, 16, RULE_declaracion_local);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			tipo();
			setState(121);
			((Declaracion_localContext)_localctx).id1 = match(ID);

			      pushTSL((((Declaracion_localContext)_localctx).id1!=null?((Declaracion_localContext)_localctx).id1.getText():null), (((Declaracion_localContext)_localctx).tipo!=null?_input.getText(((Declaracion_localContext)_localctx).tipo.start,((Declaracion_localContext)_localctx).tipo.stop):null));
			    
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(123);
				match(T__3);
				setState(124);
				((Declaracion_localContext)_localctx).id2 = match(ID);

				        pushTSL((((Declaracion_localContext)_localctx).id2!=null?((Declaracion_localContext)_localctx).id2.getText():null), (((Declaracion_localContext)_localctx).tipo!=null?_input.getText(((Declaracion_localContext)_localctx).tipo.start,((Declaracion_localContext)_localctx).tipo.stop):null));
				      
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(131);
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
		enterRule(_localctx, 18, RULE_declaracion_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			((Declaracion_argsContext)_localctx).tipo1 = tipo();
			setState(134);
			((Declaracion_argsContext)_localctx).id1 = match(ID);

			      pushTSL((((Declaracion_argsContext)_localctx).id1!=null?((Declaracion_argsContext)_localctx).id1.getText():null), (((Declaracion_argsContext)_localctx).tipo1!=null?_input.getText(((Declaracion_argsContext)_localctx).tipo1.start,((Declaracion_argsContext)_localctx).tipo1.stop):null));
			    
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(136);
				match(T__3);
				setState(137);
				((Declaracion_argsContext)_localctx).tipo2 = tipo();
				setState(138);
				((Declaracion_argsContext)_localctx).id2 = match(ID);

				        pushTSL((((Declaracion_argsContext)_localctx).id2!=null?((Declaracion_argsContext)_localctx).id2.getText():null), (((Declaracion_argsContext)_localctx).tipo2!=null?_input.getText(((Declaracion_argsContext)_localctx).tipo2.start,((Declaracion_argsContext)_localctx).tipo2.stop):null));
				      
				}
				}
				setState(145);
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
	public static class LlamadaContext extends ParserRuleContext {
		public int t;
		public TerminalNode ID() { return getToken(sintaxParser.ID, 0); }
		public TerminalNode SEMICOLON() { return getToken(sintaxParser.SEMICOLON, 0); }
		public LlamadaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_llamada; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterLlamada(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitLlamada(this);
		}
	}

	public final LlamadaContext llamada() throws RecognitionException {
		LlamadaContext _localctx = new LlamadaContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_llamada);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(ID);
			setState(147);
			match(T__4);
			setState(148);
			match(T__5);
			setState(149);
			match(SEMICOLON);

			      pushCall((((LlamadaContext)_localctx).ID!=null?((LlamadaContext)_localctx).ID.getText():null));
			      ((LlamadaContext)_localctx).t =  findId((((LlamadaContext)_localctx).ID!=null?((LlamadaContext)_localctx).ID.getText():null));
			    
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
		enterRule(_localctx, 22, RULE_expr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			((ExprContext)_localctx).m1 = multExpr();
			 ((ExprContext)_localctx).t = ((ExprContext)_localctx).m1.t; 
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7 || _la==T__8) {
				{
				{
				setState(154);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(155);
				((ExprContext)_localctx).m2 = multExpr();

				        ((ExprContext)_localctx).t =  checkTypes(_localctx.t, ((ExprContext)_localctx).m2.t);
				      
				}
				}
				setState(162);
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
		enterRule(_localctx, 24, RULE_multExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			((MultExprContext)_localctx).a1 = atom();

			      ((MultExprContext)_localctx).t = ((MultExprContext)_localctx).a1.t;
			    
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(165);
				match(T__9);
				setState(166);
				((MultExprContext)_localctx).a2 = atom();

				        ((MultExprContext)_localctx).t =  checkTypes(_localctx.t, ((MultExprContext)_localctx).a2.t);
				      
				}
				}
				setState(173);
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
		enterRule(_localctx, 26, RULE_atom);
		try {
			setState(187);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case CINT:
				enterOuterAlt(_localctx, 1);
				{
				setState(174);
				match(CINT);
				 ((AtomContext)_localctx).t = 1; 
				}
				break;
			case CFLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(176);
				match(CFLOAT);
				 ((AtomContext)_localctx).t = 3; 
				}
				break;
			case CDOUBLE:
				enterOuterAlt(_localctx, 3);
				{
				setState(178);
				match(CDOUBLE);
				 ((AtomContext)_localctx).t = 2; 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(180);
				match(ID);
				 /* search ID in TSL, if found, return its type 
				        if not found in TSL, search in TSG, if not found
				        print "Not declared variable", and return 11, because
				        it can be embeded in another expresion
				      */
				      ((AtomContext)_localctx).t =  findId((((AtomContext)_localctx).ID!=null?((AtomContext)_localctx).ID.getText():null));
				    
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 5);
				{
				setState(182);
				match(T__4);
				setState(183);
				expr();

				      ((AtomContext)_localctx).t = ((AtomContext)_localctx).expr.t;
				    
				setState(185);
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
	public static class Metodo_principalContext extends ParserRuleContext {
		public TerminalNode PUBLIC() { return getToken(sintaxParser.PUBLIC, 0); }
		public TerminalNode VOID() { return getToken(sintaxParser.VOID, 0); }
		public List<InstruccionContext> instruccion() {
			return getRuleContexts(InstruccionContext.class);
		}
		public InstruccionContext instruccion(int i) {
			return getRuleContext(InstruccionContext.class,i);
		}
		public Metodo_principalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_metodo_principal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).enterMetodo_principal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sintaxListener ) ((sintaxListener)listener).exitMetodo_principal(this);
		}
	}

	public final Metodo_principalContext metodo_principal() throws RecognitionException {
		Metodo_principalContext _localctx = new Metodo_principalContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_metodo_principal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			match(PUBLIC);
			setState(190);
			match(VOID);
			setState(191);
			match(T__10);
			setState(192);
			match(T__4);
			setState(193);
			match(T__5);
			setState(194);
			match(T__1);
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 4317184L) != 0)) {
				{
				{
				setState(195);
				instruccion();
				}
				}
				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(201);
			match(T__2);

			      TSG.clear();
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
		enterRule(_localctx, 30, RULE_modificAcceso);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 917504L) != 0)) ) {
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
		enterRule(_localctx, 32, RULE_tipo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 122880L) != 0)) ) {
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
		"\u0004\u0001\u001a\u00d1\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0004\u0000$\b\u0000\u000b"+
		"\u0000\f\u0000%\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0003\u0002.\b\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0005\u00025\b\u0002\n\u0002\f\u00028\t"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0003\u0003@\b\u0003\u0001\u0004\u0003\u0004C\b\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004"+
		"K\b\u0004\n\u0004\f\u0004N\t\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0003\u0005S\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005Z\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005_\b\u0005\n\u0005\f\u0005b\t\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006j\b\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007"+
		"w\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005\b\u007f"+
		"\b\b\n\b\f\b\u0082\t\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0005\t\u008e\b\t\n\t\f\t\u0091\t\t\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u009f\b\u000b\n"+
		"\u000b\f\u000b\u00a2\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0005\f\u00aa\b\f\n\f\f\f\u00ad\t\f\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u00bc\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00c5\b\u000e\n\u000e\f\u000e"+
		"\u00c8\t\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0000\u0000\u0011\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \u0000"+
		"\u0003\u0001\u0000\b\t\u0001\u0000\u0011\u0013\u0001\u0000\r\u0010\u00d4"+
		"\u0000#\u0001\u0000\u0000\u0000\u0002)\u0001\u0000\u0000\u0000\u0004-"+
		"\u0001\u0000\u0000\u0000\u0006?\u0001\u0000\u0000\u0000\bB\u0001\u0000"+
		"\u0000\u0000\nR\u0001\u0000\u0000\u0000\fi\u0001\u0000\u0000\u0000\u000e"+
		"v\u0001\u0000\u0000\u0000\u0010x\u0001\u0000\u0000\u0000\u0012\u0085\u0001"+
		"\u0000\u0000\u0000\u0014\u0092\u0001\u0000\u0000\u0000\u0016\u0098\u0001"+
		"\u0000\u0000\u0000\u0018\u00a3\u0001\u0000\u0000\u0000\u001a\u00bb\u0001"+
		"\u0000\u0000\u0000\u001c\u00bd\u0001\u0000\u0000\u0000\u001e\u00cc\u0001"+
		"\u0000\u0000\u0000 \u00ce\u0001\u0000\u0000\u0000\"$\u0003\u0004\u0002"+
		"\u0000#\"\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000%#\u0001\u0000"+
		"\u0000\u0000%&\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000\u0000\'(\u0006"+
		"\u0000\uffff\uffff\u0000(\u0001\u0001\u0000\u0000\u0000)*\u0005\f\u0000"+
		"\u0000*+\u0006\u0001\uffff\uffff\u0000+\u0003\u0001\u0000\u0000\u0000"+
		",.\u0003\u001e\u000f\u0000-,\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000"+
		"\u0000./\u0001\u0000\u0000\u0000/0\u0005\u0001\u0000\u000001\u0005\u0016"+
		"\u0000\u000012\u0006\u0002\uffff\uffff\u000026\u0005\u0002\u0000\u0000"+
		"35\u0003\u0006\u0003\u000043\u0001\u0000\u0000\u000058\u0001\u0000\u0000"+
		"\u000064\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u000079\u0001\u0000"+
		"\u0000\u000086\u0001\u0000\u0000\u00009:\u0003\u001c\u000e\u0000:;\u0005"+
		"\u0003\u0000\u0000;<\u0006\u0002\uffff\uffff\u0000<\u0005\u0001\u0000"+
		"\u0000\u0000=@\u0003\n\u0005\u0000>@\u0003\b\u0004\u0000?=\u0001\u0000"+
		"\u0000\u0000?>\u0001\u0000\u0000\u0000@\u0007\u0001\u0000\u0000\u0000"+
		"AC\u0003\u001e\u000f\u0000BA\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000"+
		"\u0000CD\u0001\u0000\u0000\u0000DE\u0003 \u0010\u0000EF\u0005\u0016\u0000"+
		"\u0000FL\u0006\u0004\uffff\uffff\u0000GH\u0005\u0004\u0000\u0000HI\u0005"+
		"\u0016\u0000\u0000IK\u0006\u0004\uffff\uffff\u0000JG\u0001\u0000\u0000"+
		"\u0000KN\u0001\u0000\u0000\u0000LJ\u0001\u0000\u0000\u0000LM\u0001\u0000"+
		"\u0000\u0000MO\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000OP\u0005"+
		"\u0014\u0000\u0000P\t\u0001\u0000\u0000\u0000QS\u0003\u001e\u000f\u0000"+
		"RQ\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000"+
		"\u0000TU\u0003 \u0010\u0000UV\u0005\u0016\u0000\u0000VW\u0006\u0005\uffff"+
		"\uffff\u0000WY\u0005\u0005\u0000\u0000XZ\u0003\u0012\t\u0000YX\u0001\u0000"+
		"\u0000\u0000YZ\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[\\\u0005"+
		"\u0006\u0000\u0000\\`\u0005\u0002\u0000\u0000]_\u0003\f\u0006\u0000^]"+
		"\u0001\u0000\u0000\u0000_b\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000"+
		"\u0000`a\u0001\u0000\u0000\u0000ac\u0001\u0000\u0000\u0000b`\u0001\u0000"+
		"\u0000\u0000cd\u0005\u0003\u0000\u0000de\u0006\u0005\uffff\uffff\u0000"+
		"e\u000b\u0001\u0000\u0000\u0000fj\u0003\u000e\u0007\u0000gj\u0003\u0010"+
		"\b\u0000hj\u0003\u0014\n\u0000if\u0001\u0000\u0000\u0000ig\u0001\u0000"+
		"\u0000\u0000ih\u0001\u0000\u0000\u0000j\r\u0001\u0000\u0000\u0000kl\u0005"+
		"\u0016\u0000\u0000lm\u0005\u0007\u0000\u0000mn\u0003\u0016\u000b\u0000"+
		"no\u0005\u0014\u0000\u0000op\u0006\u0007\uffff\uffff\u0000pw\u0001\u0000"+
		"\u0000\u0000qr\u0005\u0016\u0000\u0000rs\u0005\u0007\u0000\u0000st\u0003"+
		"\u0014\n\u0000tu\u0006\u0007\uffff\uffff\u0000uw\u0001\u0000\u0000\u0000"+
		"vk\u0001\u0000\u0000\u0000vq\u0001\u0000\u0000\u0000w\u000f\u0001\u0000"+
		"\u0000\u0000xy\u0003 \u0010\u0000yz\u0005\u0016\u0000\u0000z\u0080\u0006"+
		"\b\uffff\uffff\u0000{|\u0005\u0004\u0000\u0000|}\u0005\u0016\u0000\u0000"+
		"}\u007f\u0006\b\uffff\uffff\u0000~{\u0001\u0000\u0000\u0000\u007f\u0082"+
		"\u0001\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u0081\u0001"+
		"\u0000\u0000\u0000\u0081\u0083\u0001\u0000\u0000\u0000\u0082\u0080\u0001"+
		"\u0000\u0000\u0000\u0083\u0084\u0005\u0014\u0000\u0000\u0084\u0011\u0001"+
		"\u0000\u0000\u0000\u0085\u0086\u0003 \u0010\u0000\u0086\u0087\u0005\u0016"+
		"\u0000\u0000\u0087\u008f\u0006\t\uffff\uffff\u0000\u0088\u0089\u0005\u0004"+
		"\u0000\u0000\u0089\u008a\u0003 \u0010\u0000\u008a\u008b\u0005\u0016\u0000"+
		"\u0000\u008b\u008c\u0006\t\uffff\uffff\u0000\u008c\u008e\u0001\u0000\u0000"+
		"\u0000\u008d\u0088\u0001\u0000\u0000\u0000\u008e\u0091\u0001\u0000\u0000"+
		"\u0000\u008f\u008d\u0001\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000"+
		"\u0000\u0090\u0013\u0001\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000"+
		"\u0000\u0092\u0093\u0005\u0016\u0000\u0000\u0093\u0094\u0005\u0005\u0000"+
		"\u0000\u0094\u0095\u0005\u0006\u0000\u0000\u0095\u0096\u0005\u0014\u0000"+
		"\u0000\u0096\u0097\u0006\n\uffff\uffff\u0000\u0097\u0015\u0001\u0000\u0000"+
		"\u0000\u0098\u0099\u0003\u0018\f\u0000\u0099\u00a0\u0006\u000b\uffff\uffff"+
		"\u0000\u009a\u009b\u0007\u0000\u0000\u0000\u009b\u009c\u0003\u0018\f\u0000"+
		"\u009c\u009d\u0006\u000b\uffff\uffff\u0000\u009d\u009f\u0001\u0000\u0000"+
		"\u0000\u009e\u009a\u0001\u0000\u0000\u0000\u009f\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u00a1\u0001\u0000\u0000"+
		"\u0000\u00a1\u0017\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a4\u0003\u001a\r\u0000\u00a4\u00ab\u0006\f\uffff\uffff"+
		"\u0000\u00a5\u00a6\u0005\n\u0000\u0000\u00a6\u00a7\u0003\u001a\r\u0000"+
		"\u00a7\u00a8\u0006\f\uffff\uffff\u0000\u00a8\u00aa\u0001\u0000\u0000\u0000"+
		"\u00a9\u00a5\u0001\u0000\u0000\u0000\u00aa\u00ad\u0001\u0000\u0000\u0000"+
		"\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ac\u0001\u0000\u0000\u0000"+
		"\u00ac\u0019\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000"+
		"\u00ae\u00af\u0005\u0019\u0000\u0000\u00af\u00bc\u0006\r\uffff\uffff\u0000"+
		"\u00b0\u00b1\u0005\u0017\u0000\u0000\u00b1\u00bc\u0006\r\uffff\uffff\u0000"+
		"\u00b2\u00b3\u0005\u0018\u0000\u0000\u00b3\u00bc\u0006\r\uffff\uffff\u0000"+
		"\u00b4\u00b5\u0005\u0016\u0000\u0000\u00b5\u00bc\u0006\r\uffff\uffff\u0000"+
		"\u00b6\u00b7\u0005\u0005\u0000\u0000\u00b7\u00b8\u0003\u0016\u000b\u0000"+
		"\u00b8\u00b9\u0006\r\uffff\uffff\u0000\u00b9\u00ba\u0005\u0006\u0000\u0000"+
		"\u00ba\u00bc\u0001\u0000\u0000\u0000\u00bb\u00ae\u0001\u0000\u0000\u0000"+
		"\u00bb\u00b0\u0001\u0000\u0000\u0000\u00bb\u00b2\u0001\u0000\u0000\u0000"+
		"\u00bb\u00b4\u0001\u0000\u0000\u0000\u00bb\u00b6\u0001\u0000\u0000\u0000"+
		"\u00bc\u001b\u0001\u0000\u0000\u0000\u00bd\u00be\u0005\u0011\u0000\u0000"+
		"\u00be\u00bf\u0005\u0010\u0000\u0000\u00bf\u00c0\u0005\u000b\u0000\u0000"+
		"\u00c0\u00c1\u0005\u0005\u0000\u0000\u00c1\u00c2\u0005\u0006\u0000\u0000"+
		"\u00c2\u00c6\u0005\u0002\u0000\u0000\u00c3\u00c5\u0003\f\u0006\u0000\u00c4"+
		"\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7"+
		"\u00c9\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9"+
		"\u00ca\u0005\u0003\u0000\u0000\u00ca\u00cb\u0006\u000e\uffff\uffff\u0000"+
		"\u00cb\u001d\u0001\u0000\u0000\u0000\u00cc\u00cd\u0007\u0001\u0000\u0000"+
		"\u00cd\u001f\u0001\u0000\u0000\u0000\u00ce\u00cf\u0007\u0002\u0000\u0000"+
		"\u00cf!\u0001\u0000\u0000\u0000\u0011%-6?BLRY`iv\u0080\u008f\u00a0\u00ab"+
		"\u00bb\u00c6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}