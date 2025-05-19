// $ANTLR 3.5.2 sintax.g 2025-05-18 22:21:55

package application;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class sintaxParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "CDOUBLE", "CFLOAT", "CINT", "CONTAR", 
		"DOT", "DOUBLE", "FLOAT", "ID", "INT", "PRIVATE", "PROTECTED", "PUBLIC", 
		"SEMICOLON", "VOID", "WS", "'('", "')'", "'*'", "'+'", "','", "'-'", "'='", 
		"'class'", "'main'", "'{'", "'}'"
	};
	public static final int EOF=-1;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int CDOUBLE=4;
	public static final int CFLOAT=5;
	public static final int CINT=6;
	public static final int CONTAR=7;
	public static final int DOT=8;
	public static final int DOUBLE=9;
	public static final int FLOAT=10;
	public static final int ID=11;
	public static final int INT=12;
	public static final int PRIVATE=13;
	public static final int PROTECTED=14;
	public static final int PUBLIC=15;
	public static final int SEMICOLON=16;
	public static final int VOID=17;
	public static final int WS=18;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public sintaxParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public sintaxParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return sintaxParser.tokenNames; }
	@Override public String getGrammarFileName() { return "sintax.g"; }


	  HashMap TSG = new HashMap();
	  HashMap TSL = new HashMap();
	  HashMap<String, Integer> calls = new HashMap<>();
	  // Para BTA: Añadimos una tabla para almacenar si las variables son estáticas (1) o dinámicas (0)
	  HashMap<String, Integer> staticVars = new HashMap<>();
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
	      // Por defecto, todas las variables son dinámicas al crearse
	      staticVars.put(ID, 0);
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
	      // Por defecto, todas las variables son dinámicas al crearse
	      staticVars.put(ID, 0);
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
	  
	// Detecta si una variable no está inicializada
	  boolean isInitialized(String ID) {
	    // Verificamos si la variable existe en nuestras tablas
	    if(TSL.get(ID) != null || TSG.get(ID) != null) {
	      // Verificamos si ya está incluida en la tabla de estáticos
	      return staticVars.containsKey(ID);
	    }
	    return false;
	  }
	  
	  // Para BTA: Verificar si un ID es estático (1) o dinámico (0)
	  Integer isStatic(String ID) {
	    Integer est = staticVars.get(ID);
	    if (est == null) {
	      return 0; // Por defecto es dinámico si no existe o no está inicializado
	    }
	    return est;
	  }
	  
	  int checkTypes(int t1, int t2) {
	    if(t1!=t2) {
	      //textOutput += "WARNING! mismatch types\n";
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



	// $ANTLR start "program"
	// sintax.g:150:1: program : ( clase )+ ;
	public final void program() throws RecognitionException {
		try {
			// sintax.g:150:10: ( ( clase )+ )
			// sintax.g:150:12: ( clase )+
			{
			// sintax.g:150:12: ( clase )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= PRIVATE && LA1_0 <= PUBLIC)||LA1_0==26) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// sintax.g:150:12: clase
					{
					pushFollow(FOLLOW_clase_in_program32);
					clase();
					state._fsp--;

					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			 countCalls(); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "program"



	// $ANTLR start "contar"
	// sintax.g:152:1: contar : CONTAR ;
	public final void contar() throws RecognitionException {
		try {
			// sintax.g:152:7: ( CONTAR )
			// sintax.g:152:9: CONTAR
			{
			match(input,CONTAR,FOLLOW_CONTAR_in_contar42); 

			    System.out.println("Cantidad de errores de expresion: " + contarError);
			    
			    countCalls();

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "contar"



	// $ANTLR start "clase"
	// sintax.g:158:1: clase : ( modificAcceso )? 'class' ID '{' ( miembro )* metodo_principal '}' ;
	public final void clase() throws RecognitionException {
		Token ID1=null;

		try {
			// sintax.g:159:5: ( ( modificAcceso )? 'class' ID '{' ( miembro )* metodo_principal '}' )
			// sintax.g:159:7: ( modificAcceso )? 'class' ID '{' ( miembro )* metodo_principal '}'
			{
			// sintax.g:159:7: ( modificAcceso )?
			int alt2=2;
			int LA2_0 = input.LA(1);
			if ( ((LA2_0 >= PRIVATE && LA2_0 <= PUBLIC)) ) {
				alt2=1;
			}
			switch (alt2) {
				case 1 :
					// sintax.g:159:7: modificAcceso
					{
					pushFollow(FOLLOW_modificAcceso_in_clase56);
					modificAcceso();
					state._fsp--;

					}
					break;

			}

			match(input,26,FOLLOW_26_in_clase59); 
			ID1=(Token)match(input,ID,FOLLOW_ID_in_clase61); 

			      pushClass((ID1!=null?ID1.getText():null));
			      pushTSG((ID1!=null?ID1.getText():null), "class");
			    
			match(input,28,FOLLOW_28_in_clase73); 
			// sintax.g:165:7: ( miembro )*
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( (LA3_0==PUBLIC) ) {
					int LA3_1 = input.LA(2);
					if ( (LA3_1==VOID) ) {
						int LA3_3 = input.LA(3);
						if ( (LA3_3==ID) ) {
							alt3=1;
						}

					}
					else if ( ((LA3_1 >= DOUBLE && LA3_1 <= FLOAT)||LA3_1==INT) ) {
						alt3=1;
					}

				}
				else if ( ((LA3_0 >= DOUBLE && LA3_0 <= FLOAT)||(LA3_0 >= INT && LA3_0 <= PROTECTED)||LA3_0==VOID) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// sintax.g:165:7: miembro
					{
					pushFollow(FOLLOW_miembro_in_clase81);
					miembro();
					state._fsp--;

					}
					break;

				default :
					break loop3;
				}
			}

			pushFollow(FOLLOW_metodo_principal_in_clase90);
			metodo_principal();
			state._fsp--;

			match(input,29,FOLLOW_29_in_clase96); 

			      TSG.clear();
			      staticVars.clear();
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "clase"



	// $ANTLR start "miembro"
	// sintax.g:173:1: miembro : ( metodo | propiedad );
	public final void miembro() throws RecognitionException {
		try {
			// sintax.g:174:5: ( metodo | propiedad )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( ((LA4_0 >= PRIVATE && LA4_0 <= PUBLIC)) ) {
				int LA4_1 = input.LA(2);
				if ( ((LA4_1 >= DOUBLE && LA4_1 <= FLOAT)||LA4_1==INT||LA4_1==VOID) ) {
					int LA4_2 = input.LA(3);
					if ( (LA4_2==ID) ) {
						int LA4_3 = input.LA(4);
						if ( (LA4_3==19) ) {
							alt4=1;
						}
						else if ( (LA4_3==SEMICOLON||LA4_3==23) ) {
							alt4=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 4, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 4, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( ((LA4_0 >= DOUBLE && LA4_0 <= FLOAT)||LA4_0==INT||LA4_0==VOID) ) {
				int LA4_2 = input.LA(2);
				if ( (LA4_2==ID) ) {
					int LA4_3 = input.LA(3);
					if ( (LA4_3==19) ) {
						alt4=1;
					}
					else if ( (LA4_3==SEMICOLON||LA4_3==23) ) {
						alt4=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 4, 3, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// sintax.g:174:7: metodo
					{
					pushFollow(FOLLOW_metodo_in_miembro114);
					metodo();
					state._fsp--;

					}
					break;
				case 2 :
					// sintax.g:175:7: propiedad
					{
					pushFollow(FOLLOW_propiedad_in_miembro122);
					propiedad();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "miembro"



	// $ANTLR start "propiedad"
	// sintax.g:179:1: propiedad : ( modificAcceso )? tipo id1= ID ( ',' id2= ID )* SEMICOLON ;
	public final void propiedad() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope tipo2 =null;

		try {
			// sintax.g:180:5: ( ( modificAcceso )? tipo id1= ID ( ',' id2= ID )* SEMICOLON )
			// sintax.g:180:7: ( modificAcceso )? tipo id1= ID ( ',' id2= ID )* SEMICOLON
			{
			// sintax.g:180:7: ( modificAcceso )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( ((LA5_0 >= PRIVATE && LA5_0 <= PUBLIC)) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// sintax.g:180:7: modificAcceso
					{
					pushFollow(FOLLOW_modificAcceso_in_propiedad140);
					modificAcceso();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_tipo_in_propiedad143);
			tipo2=tipo();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_propiedad147); 

			      pushTSG((id1!=null?id1.getText():null), (tipo2!=null?input.toString(tipo2.start,tipo2.stop):null));
			    
			// sintax.g:184:5: ( ',' id2= ID )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==23) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// sintax.g:184:6: ',' id2= ID
					{
					match(input,23,FOLLOW_23_in_propiedad160); 
					id2=(Token)match(input,ID,FOLLOW_ID_in_propiedad164); 

					        pushTSG((id2!=null?id2.getText():null), (tipo2!=null?input.toString(tipo2.start,tipo2.stop):null));
					      
					}
					break;

				default :
					break loop6;
				}
			}

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_propiedad181); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "propiedad"



	// $ANTLR start "metodo"
	// sintax.g:191:1: metodo : ( modificAcceso )? tipo ID '(' ( declaracion_args )? ')' '{' ( instruccion )* '}' ;
	public final void metodo() throws RecognitionException {
		Token ID3=null;
		ParserRuleReturnScope tipo4 =null;

		try {
			// sintax.g:192:5: ( ( modificAcceso )? tipo ID '(' ( declaracion_args )? ')' '{' ( instruccion )* '}' )
			// sintax.g:192:7: ( modificAcceso )? tipo ID '(' ( declaracion_args )? ')' '{' ( instruccion )* '}'
			{
			// sintax.g:192:7: ( modificAcceso )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( ((LA7_0 >= PRIVATE && LA7_0 <= PUBLIC)) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// sintax.g:192:7: modificAcceso
					{
					pushFollow(FOLLOW_modificAcceso_in_metodo198);
					modificAcceso();
					state._fsp--;

					}
					break;

			}

			pushFollow(FOLLOW_tipo_in_metodo201);
			tipo4=tipo();
			state._fsp--;

			ID3=(Token)match(input,ID,FOLLOW_ID_in_metodo203); 

			      pushTSG((ID3!=null?ID3.getText():null), (tipo4!=null?input.toString(tipo4.start,tipo4.stop):null));
			    
			match(input,19,FOLLOW_19_in_metodo216); 
			// sintax.g:196:9: ( declaracion_args )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( ((LA8_0 >= DOUBLE && LA8_0 <= FLOAT)||LA8_0==INT||LA8_0==VOID) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// sintax.g:196:9: declaracion_args
					{
					pushFollow(FOLLOW_declaracion_args_in_metodo218);
					declaracion_args();
					state._fsp--;

					}
					break;

			}

			match(input,20,FOLLOW_20_in_metodo221); 
			match(input,28,FOLLOW_28_in_metodo223); 
			// sintax.g:196:35: ( instruccion )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( ((LA9_0 >= DOUBLE && LA9_0 <= INT)||LA9_0==VOID) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// sintax.g:196:35: instruccion
					{
					pushFollow(FOLLOW_instruccion_in_metodo225);
					instruccion();
					state._fsp--;

					}
					break;

				default :
					break loop9;
				}
			}

			match(input,29,FOLLOW_29_in_metodo228); 

			      calls.put((ID3!=null?ID3.getText():null), 0);
			      TSL.clear();
			      // Al salir del método, limpiamos también la información sobre variables estáticas locales
			      // Las variables globales se mantienen entre métodos
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "metodo"



	// $ANTLR start "instruccion"
	// sintax.g:205:1: instruccion : ( asignacion | declaracion_local | llamada SEMICOLON );
	public final void instruccion() throws RecognitionException {
		try {
			// sintax.g:206:5: ( asignacion | declaracion_local | llamada SEMICOLON )
			int alt10=3;
			int LA10_0 = input.LA(1);
			if ( (LA10_0==ID) ) {
				int LA10_1 = input.LA(2);
				if ( (LA10_1==25) ) {
					alt10=1;
				}
				else if ( (LA10_1==19) ) {
					alt10=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 10, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( ((LA10_0 >= DOUBLE && LA10_0 <= FLOAT)||LA10_0==INT||LA10_0==VOID) ) {
				alt10=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 10, 0, input);
				throw nvae;
			}

			switch (alt10) {
				case 1 :
					// sintax.g:206:7: asignacion
					{
					pushFollow(FOLLOW_asignacion_in_instruccion251);
					asignacion();
					state._fsp--;

					}
					break;
				case 2 :
					// sintax.g:207:7: declaracion_local
					{
					pushFollow(FOLLOW_declaracion_local_in_instruccion259);
					declaracion_local();
					state._fsp--;

					}
					break;
				case 3 :
					// sintax.g:208:7: llamada SEMICOLON
					{
					pushFollow(FOLLOW_llamada_in_instruccion267);
					llamada();
					state._fsp--;

					match(input,SEMICOLON,FOLLOW_SEMICOLON_in_instruccion269); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "instruccion"



	// $ANTLR start "asignacion"
	// sintax.g:212:1: asignacion : ID '=' expr SEMICOLON ;
	public final void asignacion() throws RecognitionException {
		Token ID5=null;
		ParserRuleReturnScope expr6 =null;

		try {
			// sintax.g:213:5: ( ID '=' expr SEMICOLON )
			// sintax.g:213:7: ID '=' expr SEMICOLON
			{
			ID5=(Token)match(input,ID,FOLLOW_ID_in_asignacion287); 
			match(input,25,FOLLOW_25_in_asignacion289); 
			pushFollow(FOLLOW_expr_in_asignacion291);
			expr6=expr();
			state._fsp--;

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_asignacion293); 

			      String exp = (ID5!=null?ID5.getText():null) + '=' + (expr6!=null?input.toString(expr6.start,expr6.stop):null);
			      checkTypes(findId((ID5!=null?ID5.getText():null)), (expr6!=null?((sintaxParser.expr_return)expr6).t:0), exp);
			      
			      // BTA: Guardamos si la expresión es estática (1) o dinámica (0)
			      staticVars.put((ID5!=null?ID5.getText():null), (expr6!=null?((sintaxParser.expr_return)expr6).isStatic:0));
			      
			      // BTA: Informamos si la asignación es reducible o no
			      if ((expr6!=null?((sintaxParser.expr_return)expr6).isStatic:0) == 1) {
			        System.out.println("REDUCIBLE EXPRESSION: " + exp + " (Type: " + (expr6!=null?((sintaxParser.expr_return)expr6).t:0) + ", Static: " + (expr6!=null?((sintaxParser.expr_return)expr6).isStatic:0) + ")");
			        textOutput += "REDUCIBLE EXPRESSION: " + exp + "\n";
			      } else {
			        System.out.println("DYNAMIC EXPRESSION: " + exp + " (Type: " + (expr6!=null?((sintaxParser.expr_return)expr6).t:0) + ", Static: " + (expr6!=null?((sintaxParser.expr_return)expr6).isStatic:0) + ")");
			        textOutput += "DYNAMIC EXPRESSION: " + exp + "\n";
			      }
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "asignacion"



	// $ANTLR start "declaracion_local"
	// sintax.g:233:1: declaracion_local : tipo id1= ID ( ',' id2= ID )* SEMICOLON ;
	public final void declaracion_local() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope tipo7 =null;

		try {
			// sintax.g:234:5: ( tipo id1= ID ( ',' id2= ID )* SEMICOLON )
			// sintax.g:234:7: tipo id1= ID ( ',' id2= ID )* SEMICOLON
			{
			pushFollow(FOLLOW_tipo_in_declaracion_local317);
			tipo7=tipo();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_declaracion_local321); 

			      pushTSL((id1!=null?id1.getText():null), (tipo7!=null?input.toString(tipo7.start,tipo7.stop):null));
			    
			// sintax.g:238:5: ( ',' id2= ID )*
			loop11:
			while (true) {
				int alt11=2;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==23) ) {
					alt11=1;
				}

				switch (alt11) {
				case 1 :
					// sintax.g:238:6: ',' id2= ID
					{
					match(input,23,FOLLOW_23_in_declaracion_local334); 
					id2=(Token)match(input,ID,FOLLOW_ID_in_declaracion_local338); 

					        pushTSL((id2!=null?id2.getText():null), (tipo7!=null?input.toString(tipo7.start,tipo7.stop):null));
					      
					}
					break;

				default :
					break loop11;
				}
			}

			match(input,SEMICOLON,FOLLOW_SEMICOLON_in_declaracion_local355); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "declaracion_local"



	// $ANTLR start "declaracion_args"
	// sintax.g:246:1: declaracion_args : tipo1= tipo id1= ID ( ',' tipo2= tipo id2= ID )* ;
	public final void declaracion_args() throws RecognitionException {
		Token id1=null;
		Token id2=null;
		ParserRuleReturnScope tipo1 =null;
		ParserRuleReturnScope tipo2 =null;

		try {
			// sintax.g:247:5: (tipo1= tipo id1= ID ( ',' tipo2= tipo id2= ID )* )
			// sintax.g:247:7: tipo1= tipo id1= ID ( ',' tipo2= tipo id2= ID )*
			{
			pushFollow(FOLLOW_tipo_in_declaracion_args375);
			tipo1=tipo();
			state._fsp--;

			id1=(Token)match(input,ID,FOLLOW_ID_in_declaracion_args379); 

			      pushTSL((id1!=null?id1.getText():null), (tipo1!=null?input.toString(tipo1.start,tipo1.stop):null));
			      // Los argumentos de función son dinámicos por defecto
			      staticVars.put((id1!=null?id1.getText():null), 0);
			    
			// sintax.g:253:5: ( ',' tipo2= tipo id2= ID )*
			loop12:
			while (true) {
				int alt12=2;
				int LA12_0 = input.LA(1);
				if ( (LA12_0==23) ) {
					alt12=1;
				}

				switch (alt12) {
				case 1 :
					// sintax.g:253:6: ',' tipo2= tipo id2= ID
					{
					match(input,23,FOLLOW_23_in_declaracion_args392); 
					pushFollow(FOLLOW_tipo_in_declaracion_args396);
					tipo2=tipo();
					state._fsp--;

					id2=(Token)match(input,ID,FOLLOW_ID_in_declaracion_args400); 

					        pushTSL((id2!=null?id2.getText():null), (tipo2!=null?input.toString(tipo2.start,tipo2.stop):null));
					        // Los argumentos de función son dinámicos por defecto
					        staticVars.put((id2!=null?id2.getText():null), 0);
					      
					}
					break;

				default :
					break loop12;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "declaracion_args"


	public static class llamada_return extends ParserRuleReturnScope {
		public int t;
		public int isStatic=0;
	};


	// $ANTLR start "llamada"
	// sintax.g:262:1: llamada returns [int t, int isStatic=0] : ID '(' ')' ;
	public final sintaxParser.llamada_return llamada() throws RecognitionException {
		sintaxParser.llamada_return retval = new sintaxParser.llamada_return();
		retval.start = input.LT(1);

		Token ID8=null;

		try {
			// sintax.g:263:5: ( ID '(' ')' )
			// sintax.g:263:7: ID '(' ')'
			{
			ID8=(Token)match(input,ID,FOLLOW_ID_in_llamada436); 
			match(input,19,FOLLOW_19_in_llamada438); 
			match(input,20,FOLLOW_20_in_llamada440); 

			      pushCall((ID8!=null?ID8.getText():null));
			      retval.t = findId((ID8!=null?ID8.getText():null));
			      // Las llamadas a funciones siempre son dinámicas
			      retval.isStatic = 0;
			    
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "llamada"


	public static class expr_return extends ParserRuleReturnScope {
		public int t;
		public int isStatic;
	};


	// $ANTLR start "expr"
	// sintax.g:272:1: expr returns [int t, int isStatic] : m1= multExpr (op= ( '+' | '-' ) m2= multExpr )* ;
	public final sintaxParser.expr_return expr() throws RecognitionException {
		sintaxParser.expr_return retval = new sintaxParser.expr_return();
		retval.start = input.LT(1);

		Token op=null;
		ParserRuleReturnScope m1 =null;
		ParserRuleReturnScope m2 =null;

		try {
			// sintax.g:273:5: (m1= multExpr (op= ( '+' | '-' ) m2= multExpr )* )
			// sintax.g:273:7: m1= multExpr (op= ( '+' | '-' ) m2= multExpr )*
			{
			pushFollow(FOLLOW_multExpr_in_expr470);
			m1=multExpr();
			state._fsp--;

			 
			      retval.t =(m1!=null?((sintaxParser.multExpr_return)m1).t:0); 
			      retval.isStatic =(m1!=null?((sintaxParser.multExpr_return)m1).isStatic:0);
			    
			// sintax.g:278:5: (op= ( '+' | '-' ) m2= multExpr )*
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==22||LA13_0==24) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// sintax.g:278:6: op= ( '+' | '-' ) m2= multExpr
					{
					op=input.LT(1);
					if ( input.LA(1)==22||input.LA(1)==24 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_multExpr_in_expr493);
					m2=multExpr();
					state._fsp--;


					        retval.t = checkTypes(retval.t, (m2!=null?((sintaxParser.multExpr_return)m2).t:0));
					        // Si alguno de los operandos es dinámico, toda la expresión es dinámica
					        retval.isStatic = (retval.isStatic == 1 && (m2!=null?((sintaxParser.multExpr_return)m2).isStatic:0) == 1) ? 1 : 0;
					      
					}
					break;

				default :
					break loop13;
				}
			}

			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "expr"


	public static class multExpr_return extends ParserRuleReturnScope {
		public int t;
		public int isStatic;
	};


	// $ANTLR start "multExpr"
	// sintax.g:287:1: multExpr returns [int t, int isStatic] : a1= atom ( '*' a2= atom )* ;
	public final sintaxParser.multExpr_return multExpr() throws RecognitionException {
		sintaxParser.multExpr_return retval = new sintaxParser.multExpr_return();
		retval.start = input.LT(1);

		ParserRuleReturnScope a1 =null;
		ParserRuleReturnScope a2 =null;

		try {
			// sintax.g:288:5: (a1= atom ( '*' a2= atom )* )
			// sintax.g:288:7: a1= atom ( '*' a2= atom )*
			{
			pushFollow(FOLLOW_atom_in_multExpr530);
			a1=atom();
			state._fsp--;


			      retval.t =(a1!=null?((sintaxParser.atom_return)a1).t:0);
			      retval.isStatic =(a1!=null?((sintaxParser.atom_return)a1).isStatic:0);
			    
			// sintax.g:293:5: ( '*' a2= atom )*
			loop14:
			while (true) {
				int alt14=2;
				int LA14_0 = input.LA(1);
				if ( (LA14_0==21) ) {
					alt14=1;
				}

				switch (alt14) {
				case 1 :
					// sintax.g:293:6: '*' a2= atom
					{
					match(input,21,FOLLOW_21_in_multExpr543); 
					pushFollow(FOLLOW_atom_in_multExpr547);
					a2=atom();
					state._fsp--;


					        retval.t = checkTypes(retval.t, (a2!=null?((sintaxParser.atom_return)a2).t:0));
					        // Si alguno de los operandos es dinámico, toda la expresión es dinámica
					        retval.isStatic = (retval.isStatic == 1 && (a2!=null?((sintaxParser.atom_return)a2).isStatic:0) == 1) ? 1 : 0;
					      
					}
					break;

				default :
					break loop14;
				}
			}

			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "multExpr"


	public static class atom_return extends ParserRuleReturnScope {
		public int t;
		public int isStatic;
	};


	// $ANTLR start "atom"
	// sintax.g:302:1: atom returns [int t, int isStatic] : ( CINT | CFLOAT | CDOUBLE | ID | '(' e= expr ')' | llamada );
	public final sintaxParser.atom_return atom() throws RecognitionException {
		sintaxParser.atom_return retval = new sintaxParser.atom_return();
		retval.start = input.LT(1);

		Token ID9=null;
		ParserRuleReturnScope e =null;
		ParserRuleReturnScope llamada10 =null;

		try {
			// sintax.g:303:5: ( CINT | CFLOAT | CDOUBLE | ID | '(' e= expr ')' | llamada )
			int alt15=6;
			switch ( input.LA(1) ) {
			case CINT:
				{
				alt15=1;
				}
				break;
			case CFLOAT:
				{
				alt15=2;
				}
				break;
			case CDOUBLE:
				{
				alt15=3;
				}
				break;
			case ID:
				{
				int LA15_4 = input.LA(2);
				if ( (LA15_4==19) ) {
					alt15=6;
				}
				else if ( (LA15_4==SEMICOLON||(LA15_4 >= 20 && LA15_4 <= 22)||LA15_4==24) ) {
					alt15=4;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 15, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 19:
				{
				alt15=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 15, 0, input);
				throw nvae;
			}
			switch (alt15) {
				case 1 :
					// sintax.g:303:7: CINT
					{
					match(input,CINT,FOLLOW_CINT_in_atom582); 
					 
					      retval.t =1; 
					      retval.isStatic =1; // Las constantes son siempre estáticas
					    
					}
					break;
				case 2 :
					// sintax.g:308:7: CFLOAT
					{
					match(input,CFLOAT,FOLLOW_CFLOAT_in_atom597); 
					 
					      retval.t =3; 
					      retval.isStatic =1; // Las constantes son siempre estáticas
					    
					}
					break;
				case 3 :
					// sintax.g:313:7: CDOUBLE
					{
					match(input,CDOUBLE,FOLLOW_CDOUBLE_in_atom612); 
					 
					      retval.t =2; 
					      retval.isStatic =1; // Las constantes son siempre estáticas
					    
					}
					break;
				case 4 :
					// sintax.g:318:7: ID
					{
					ID9=(Token)match(input,ID,FOLLOW_ID_in_atom627); 
					 
					      retval.t = findId((ID9!=null?ID9.getText():null));
					      // Una variable es estática solo si ya ha sido determinada como tal
					      retval.isStatic = isStatic((ID9!=null?ID9.getText():null));
					      
					      // Si la variable no fue inicializada explícitamente, es dinámica
					      if (!isInitialized((ID9!=null?ID9.getText():null))) {
					        System.out.println("WARNING: Variable " + (ID9!=null?ID9.getText():null) + " not explicitly initialized, treating as dynamic");
					        textOutput += "WARNING: Variable " + (ID9!=null?ID9.getText():null) + " not explicitly initialized, treating as dynamic\n";
					        retval.isStatic = 0;
					      }
					    
					}
					break;
				case 5 :
					// sintax.g:331:7: '(' e= expr ')'
					{
					match(input,19,FOLLOW_19_in_atom642); 
					pushFollow(FOLLOW_expr_in_atom646);
					e=expr();
					state._fsp--;


					      retval.t =(e!=null?((sintaxParser.expr_return)e).t:0);
					      retval.isStatic =(e!=null?((sintaxParser.expr_return)e).isStatic:0);
					    
					match(input,20,FOLLOW_20_in_atom658); 
					}
					break;
				case 6 :
					// sintax.g:337:7: llamada
					{
					pushFollow(FOLLOW_llamada_in_atom666);
					llamada10=llamada();
					state._fsp--;


					        retval.t = (llamada10!=null?((sintaxParser.llamada_return)llamada10).t:0);
					        retval.isStatic = 0;
					    
					}
					break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "atom"



	// $ANTLR start "metodo_principal"
	// sintax.g:344:1: metodo_principal : 'public' 'void' 'main' '(' ')' '{' ( instruccion )* '}' ;
	public final void metodo_principal() throws RecognitionException {
		try {
			// sintax.g:345:5: ( 'public' 'void' 'main' '(' ')' '{' ( instruccion )* '}' )
			// sintax.g:345:7: 'public' 'void' 'main' '(' ')' '{' ( instruccion )* '}'
			{
			match(input,PUBLIC,FOLLOW_PUBLIC_in_metodo_principal689); 
			match(input,VOID,FOLLOW_VOID_in_metodo_principal691); 
			match(input,27,FOLLOW_27_in_metodo_principal693); 
			match(input,19,FOLLOW_19_in_metodo_principal695); 
			match(input,20,FOLLOW_20_in_metodo_principal697); 
			match(input,28,FOLLOW_28_in_metodo_principal699); 
			// sintax.g:345:42: ( instruccion )*
			loop16:
			while (true) {
				int alt16=2;
				int LA16_0 = input.LA(1);
				if ( ((LA16_0 >= DOUBLE && LA16_0 <= INT)||LA16_0==VOID) ) {
					alt16=1;
				}

				switch (alt16) {
				case 1 :
					// sintax.g:345:42: instruccion
					{
					pushFollow(FOLLOW_instruccion_in_metodo_principal701);
					instruccion();
					state._fsp--;

					}
					break;

				default :
					break loop16;
				}
			}

			match(input,29,FOLLOW_29_in_metodo_principal704); 

			      TSG.clear();
			      TSL.clear();
			      staticVars.clear();
			    
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "metodo_principal"



	// $ANTLR start "modificAcceso"
	// sintax.g:353:1: modificAcceso : ( PUBLIC | PRIVATE | PROTECTED );
	public final void modificAcceso() throws RecognitionException {
		try {
			// sintax.g:354:5: ( PUBLIC | PRIVATE | PROTECTED )
			// sintax.g:
			{
			if ( (input.LA(1) >= PRIVATE && input.LA(1) <= PUBLIC) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "modificAcceso"


	public static class tipo_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "tipo"
	// sintax.g:359:1: tipo : ( INT | DOUBLE | FLOAT | VOID );
	public final sintaxParser.tipo_return tipo() throws RecognitionException {
		sintaxParser.tipo_return retval = new sintaxParser.tipo_return();
		retval.start = input.LT(1);

		try {
			// sintax.g:360:5: ( INT | DOUBLE | FLOAT | VOID )
			// sintax.g:
			{
			if ( (input.LA(1) >= DOUBLE && input.LA(1) <= FLOAT)||input.LA(1)==INT||input.LA(1)==VOID ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "tipo"

	// Delegated rules



	public static final BitSet FOLLOW_clase_in_program32 = new BitSet(new long[]{0x000000000400E002L});
	public static final BitSet FOLLOW_CONTAR_in_contar42 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_modificAcceso_in_clase56 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_clase59 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ID_in_clase61 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_clase73 = new BitSet(new long[]{0x000000000002F600L});
	public static final BitSet FOLLOW_miembro_in_clase81 = new BitSet(new long[]{0x000000000002F600L});
	public static final BitSet FOLLOW_metodo_principal_in_clase90 = new BitSet(new long[]{0x0000000020000000L});
	public static final BitSet FOLLOW_29_in_clase96 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_metodo_in_miembro114 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_propiedad_in_miembro122 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_modificAcceso_in_propiedad140 = new BitSet(new long[]{0x0000000000021600L});
	public static final BitSet FOLLOW_tipo_in_propiedad143 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ID_in_propiedad147 = new BitSet(new long[]{0x0000000000810000L});
	public static final BitSet FOLLOW_23_in_propiedad160 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ID_in_propiedad164 = new BitSet(new long[]{0x0000000000810000L});
	public static final BitSet FOLLOW_SEMICOLON_in_propiedad181 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_modificAcceso_in_metodo198 = new BitSet(new long[]{0x0000000000021600L});
	public static final BitSet FOLLOW_tipo_in_metodo201 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ID_in_metodo203 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_metodo216 = new BitSet(new long[]{0x0000000000121600L});
	public static final BitSet FOLLOW_declaracion_args_in_metodo218 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_metodo221 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_metodo223 = new BitSet(new long[]{0x0000000020021E00L});
	public static final BitSet FOLLOW_instruccion_in_metodo225 = new BitSet(new long[]{0x0000000020021E00L});
	public static final BitSet FOLLOW_29_in_metodo228 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_asignacion_in_instruccion251 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_declaracion_local_in_instruccion259 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_llamada_in_instruccion267 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_SEMICOLON_in_instruccion269 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_asignacion287 = new BitSet(new long[]{0x0000000002000000L});
	public static final BitSet FOLLOW_25_in_asignacion289 = new BitSet(new long[]{0x0000000000080870L});
	public static final BitSet FOLLOW_expr_in_asignacion291 = new BitSet(new long[]{0x0000000000010000L});
	public static final BitSet FOLLOW_SEMICOLON_in_asignacion293 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tipo_in_declaracion_local317 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ID_in_declaracion_local321 = new BitSet(new long[]{0x0000000000810000L});
	public static final BitSet FOLLOW_23_in_declaracion_local334 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ID_in_declaracion_local338 = new BitSet(new long[]{0x0000000000810000L});
	public static final BitSet FOLLOW_SEMICOLON_in_declaracion_local355 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_tipo_in_declaracion_args375 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ID_in_declaracion_args379 = new BitSet(new long[]{0x0000000000800002L});
	public static final BitSet FOLLOW_23_in_declaracion_args392 = new BitSet(new long[]{0x0000000000021600L});
	public static final BitSet FOLLOW_tipo_in_declaracion_args396 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_ID_in_declaracion_args400 = new BitSet(new long[]{0x0000000000800002L});
	public static final BitSet FOLLOW_ID_in_llamada436 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_llamada438 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_llamada440 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_multExpr_in_expr470 = new BitSet(new long[]{0x0000000001400002L});
	public static final BitSet FOLLOW_set_in_expr485 = new BitSet(new long[]{0x0000000000080870L});
	public static final BitSet FOLLOW_multExpr_in_expr493 = new BitSet(new long[]{0x0000000001400002L});
	public static final BitSet FOLLOW_atom_in_multExpr530 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_21_in_multExpr543 = new BitSet(new long[]{0x0000000000080870L});
	public static final BitSet FOLLOW_atom_in_multExpr547 = new BitSet(new long[]{0x0000000000200002L});
	public static final BitSet FOLLOW_CINT_in_atom582 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CFLOAT_in_atom597 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CDOUBLE_in_atom612 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_atom627 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_19_in_atom642 = new BitSet(new long[]{0x0000000000080870L});
	public static final BitSet FOLLOW_expr_in_atom646 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_atom658 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_llamada_in_atom666 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PUBLIC_in_metodo_principal689 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_VOID_in_metodo_principal691 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_metodo_principal693 = new BitSet(new long[]{0x0000000000080000L});
	public static final BitSet FOLLOW_19_in_metodo_principal695 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_20_in_metodo_principal697 = new BitSet(new long[]{0x0000000010000000L});
	public static final BitSet FOLLOW_28_in_metodo_principal699 = new BitSet(new long[]{0x0000000020021E00L});
	public static final BitSet FOLLOW_instruccion_in_metodo_principal701 = new BitSet(new long[]{0x0000000020021E00L});
	public static final BitSet FOLLOW_29_in_metodo_principal704 = new BitSet(new long[]{0x0000000000000002L});
}
