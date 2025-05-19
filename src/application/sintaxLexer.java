// $ANTLR 3.5.2 sintax.g 2025-05-18 22:21:56

package application;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class sintaxLexer extends Lexer {
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
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public sintaxLexer() {} 
	public sintaxLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public sintaxLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "sintax.g"; }

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:6:7: ( '(' )
			// sintax.g:6:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:7:7: ( ')' )
			// sintax.g:7:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:8:7: ( '*' )
			// sintax.g:8:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:9:7: ( '+' )
			// sintax.g:9:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:10:7: ( ',' )
			// sintax.g:10:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:11:7: ( '-' )
			// sintax.g:11:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:12:7: ( '=' )
			// sintax.g:12:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:13:7: ( 'class' )
			// sintax.g:13:9: 'class'
			{
			match("class"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:14:7: ( 'main' )
			// sintax.g:14:9: 'main'
			{
			match("main"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:15:7: ( '{' )
			// sintax.g:15:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:16:7: ( '}' )
			// sintax.g:16:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "CONTAR"
	public final void mCONTAR() throws RecognitionException {
		try {
			int _type = CONTAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:366:10: ( 'contar' )
			// sintax.g:366:12: 'contar'
			{
			match("contar"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONTAR"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:367:10: ( 'int' )
			// sintax.g:367:12: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "DOUBLE"
	public final void mDOUBLE() throws RecognitionException {
		try {
			int _type = DOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:368:10: ( 'double' )
			// sintax.g:368:12: 'double'
			{
			match("double"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOUBLE"

	// $ANTLR start "FLOAT"
	public final void mFLOAT() throws RecognitionException {
		try {
			int _type = FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:369:10: ( 'float' )
			// sintax.g:369:12: 'float'
			{
			match("float"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOAT"

	// $ANTLR start "VOID"
	public final void mVOID() throws RecognitionException {
		try {
			int _type = VOID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:370:10: ( 'void' )
			// sintax.g:370:12: 'void'
			{
			match("void"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VOID"

	// $ANTLR start "PUBLIC"
	public final void mPUBLIC() throws RecognitionException {
		try {
			int _type = PUBLIC;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:371:10: ( 'public' )
			// sintax.g:371:12: 'public'
			{
			match("public"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PUBLIC"

	// $ANTLR start "PRIVATE"
	public final void mPRIVATE() throws RecognitionException {
		try {
			int _type = PRIVATE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:372:10: ( 'private' )
			// sintax.g:372:12: 'private'
			{
			match("private"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRIVATE"

	// $ANTLR start "PROTECTED"
	public final void mPROTECTED() throws RecognitionException {
		try {
			int _type = PROTECTED;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:373:10: ( 'protected' )
			// sintax.g:373:12: 'protected'
			{
			match("protected"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PROTECTED"

	// $ANTLR start "SEMICOLON"
	public final void mSEMICOLON() throws RecognitionException {
		try {
			int _type = SEMICOLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:374:10: ( ';' )
			// sintax.g:374:12: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMICOLON"

	// $ANTLR start "DOT"
	public final void mDOT() throws RecognitionException {
		try {
			int _type = DOT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:375:4: ( '.' )
			// sintax.g:375:6: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "DOT"

	// $ANTLR start "ID"
	public final void mID() throws RecognitionException {
		try {
			int _type = ID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:377:3: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
			// sintax.g:377:5: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// sintax.g:377:36: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')||(LA1_0 >= 'A' && LA1_0 <= 'Z')||LA1_0=='_'||(LA1_0 >= 'a' && LA1_0 <= 'z')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// sintax.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop1;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ID"

	// $ANTLR start "CFLOAT"
	public final void mCFLOAT() throws RecognitionException {
		try {
			int _type = CFLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:378:7: ( CINT DOT CINT 'f' )
			// sintax.g:378:9: CINT DOT CINT 'f'
			{
			mCINT(); 

			mDOT(); 

			mCINT(); 

			match('f'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CFLOAT"

	// $ANTLR start "CDOUBLE"
	public final void mCDOUBLE() throws RecognitionException {
		try {
			int _type = CDOUBLE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:379:8: ( CINT DOT CINT )
			// sintax.g:379:10: CINT DOT CINT
			{
			mCINT(); 

			mDOT(); 

			mCINT(); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CDOUBLE"

	// $ANTLR start "CINT"
	public final void mCINT() throws RecognitionException {
		try {
			int _type = CINT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:380:5: ( ( '0' .. '9' )+ )
			// sintax.g:380:7: ( '0' .. '9' )+
			{
			// sintax.g:380:7: ( '0' .. '9' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// sintax.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CINT"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// sintax.g:382:4: ( ( ' ' | '\\n' | '\\t' | '\\r' )+ )
			// sintax.g:382:7: ( ' ' | '\\n' | '\\t' | '\\r' )+
			{
			// sintax.g:382:7: ( ' ' | '\\n' | '\\t' | '\\r' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '\t' && LA3_0 <= '\n')||LA3_0=='\r'||LA3_0==' ') ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// sintax.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	@Override
	public void mTokens() throws RecognitionException {
		// sintax.g:1:8: ( T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | CONTAR | INT | DOUBLE | FLOAT | VOID | PUBLIC | PRIVATE | PROTECTED | SEMICOLON | DOT | ID | CFLOAT | CDOUBLE | CINT | WS )
		int alt4=26;
		alt4 = dfa4.predict(input);
		switch (alt4) {
			case 1 :
				// sintax.g:1:10: T__19
				{
				mT__19(); 

				}
				break;
			case 2 :
				// sintax.g:1:16: T__20
				{
				mT__20(); 

				}
				break;
			case 3 :
				// sintax.g:1:22: T__21
				{
				mT__21(); 

				}
				break;
			case 4 :
				// sintax.g:1:28: T__22
				{
				mT__22(); 

				}
				break;
			case 5 :
				// sintax.g:1:34: T__23
				{
				mT__23(); 

				}
				break;
			case 6 :
				// sintax.g:1:40: T__24
				{
				mT__24(); 

				}
				break;
			case 7 :
				// sintax.g:1:46: T__25
				{
				mT__25(); 

				}
				break;
			case 8 :
				// sintax.g:1:52: T__26
				{
				mT__26(); 

				}
				break;
			case 9 :
				// sintax.g:1:58: T__27
				{
				mT__27(); 

				}
				break;
			case 10 :
				// sintax.g:1:64: T__28
				{
				mT__28(); 

				}
				break;
			case 11 :
				// sintax.g:1:70: T__29
				{
				mT__29(); 

				}
				break;
			case 12 :
				// sintax.g:1:76: CONTAR
				{
				mCONTAR(); 

				}
				break;
			case 13 :
				// sintax.g:1:83: INT
				{
				mINT(); 

				}
				break;
			case 14 :
				// sintax.g:1:87: DOUBLE
				{
				mDOUBLE(); 

				}
				break;
			case 15 :
				// sintax.g:1:94: FLOAT
				{
				mFLOAT(); 

				}
				break;
			case 16 :
				// sintax.g:1:100: VOID
				{
				mVOID(); 

				}
				break;
			case 17 :
				// sintax.g:1:105: PUBLIC
				{
				mPUBLIC(); 

				}
				break;
			case 18 :
				// sintax.g:1:112: PRIVATE
				{
				mPRIVATE(); 

				}
				break;
			case 19 :
				// sintax.g:1:120: PROTECTED
				{
				mPROTECTED(); 

				}
				break;
			case 20 :
				// sintax.g:1:130: SEMICOLON
				{
				mSEMICOLON(); 

				}
				break;
			case 21 :
				// sintax.g:1:140: DOT
				{
				mDOT(); 

				}
				break;
			case 22 :
				// sintax.g:1:144: ID
				{
				mID(); 

				}
				break;
			case 23 :
				// sintax.g:1:147: CFLOAT
				{
				mCFLOAT(); 

				}
				break;
			case 24 :
				// sintax.g:1:154: CDOUBLE
				{
				mCDOUBLE(); 

				}
				break;
			case 25 :
				// sintax.g:1:162: CINT
				{
				mCINT(); 

				}
				break;
			case 26 :
				// sintax.g:1:167: WS
				{
				mWS(); 

				}
				break;

		}
	}


	protected DFA4 dfa4 = new DFA4(this);
	static final String DFA4_eotS =
		"\10\uffff\2\23\2\uffff\5\23\3\uffff\1\37\1\uffff\11\23\2\uffff\3\23\1"+
		"\57\6\23\1\66\2\23\1\72\1\uffff\2\23\1\75\3\23\2\uffff\1\101\1\23\1\uffff"+
		"\1\23\1\104\1\uffff\3\23\1\uffff\1\110\1\111\1\uffff\1\112\2\23\3\uffff"+
		"\1\115\1\23\1\uffff\1\23\1\120\1\uffff";
	static final String DFA4_eofS =
		"\121\uffff";
	static final String DFA4_minS =
		"\1\11\7\uffff\1\154\1\141\2\uffff\1\156\1\157\1\154\1\157\1\162\3\uffff"+
		"\1\56\1\uffff\1\141\1\156\1\151\1\164\1\165\1\157\1\151\1\142\1\151\1"+
		"\uffff\1\60\1\163\1\164\1\156\1\60\1\142\1\141\1\144\1\154\1\166\1\164"+
		"\1\60\1\163\1\141\1\60\1\uffff\1\154\1\164\1\60\1\151\1\141\1\145\2\uffff"+
		"\1\60\1\162\1\uffff\1\145\1\60\1\uffff\1\143\1\164\1\143\1\uffff\2\60"+
		"\1\uffff\1\60\1\145\1\164\3\uffff\1\60\1\145\1\uffff\1\144\1\60\1\uffff";
	static final String DFA4_maxS =
		"\1\175\7\uffff\1\157\1\141\2\uffff\1\156\1\157\1\154\1\157\1\165\3\uffff"+
		"\1\71\1\uffff\1\141\1\156\1\151\1\164\1\165\1\157\1\151\1\142\1\157\1"+
		"\uffff\1\71\1\163\1\164\1\156\1\172\1\142\1\141\1\144\1\154\1\166\1\164"+
		"\1\146\1\163\1\141\1\172\1\uffff\1\154\1\164\1\172\1\151\1\141\1\145\2"+
		"\uffff\1\172\1\162\1\uffff\1\145\1\172\1\uffff\1\143\1\164\1\143\1\uffff"+
		"\2\172\1\uffff\1\172\1\145\1\164\3\uffff\1\172\1\145\1\uffff\1\144\1\172"+
		"\1\uffff";
	static final String DFA4_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\2\uffff\1\12\1\13\5\uffff\1\24\1"+
		"\25\1\26\1\uffff\1\32\11\uffff\1\31\17\uffff\1\15\6\uffff\1\30\1\27\2"+
		"\uffff\1\11\2\uffff\1\20\3\uffff\1\10\2\uffff\1\17\3\uffff\1\14\1\16\1"+
		"\21\2\uffff\1\22\2\uffff\1\23";
	static final String DFA4_specialS =
		"\121\uffff}>";
	static final String[] DFA4_transitionS = {
			"\2\25\2\uffff\1\25\22\uffff\1\25\7\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\22"+
			"\1\uffff\12\24\1\uffff\1\21\1\uffff\1\7\3\uffff\32\23\4\uffff\1\23\1"+
			"\uffff\2\23\1\10\1\15\1\23\1\16\2\23\1\14\3\23\1\11\2\23\1\20\5\23\1"+
			"\17\4\23\1\12\1\uffff\1\13",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\26\2\uffff\1\27",
			"\1\30",
			"",
			"",
			"\1\31",
			"\1\32",
			"\1\33",
			"\1\34",
			"\1\36\2\uffff\1\35",
			"",
			"",
			"",
			"\1\40\1\uffff\12\24",
			"",
			"\1\41",
			"\1\42",
			"\1\43",
			"\1\44",
			"\1\45",
			"\1\46",
			"\1\47",
			"\1\50",
			"\1\51\5\uffff\1\52",
			"",
			"\12\53",
			"\1\54",
			"\1\55",
			"\1\56",
			"\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
			"\1\60",
			"\1\61",
			"\1\62",
			"\1\63",
			"\1\64",
			"\1\65",
			"\12\53\54\uffff\1\67",
			"\1\70",
			"\1\71",
			"\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
			"",
			"\1\73",
			"\1\74",
			"\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
			"\1\76",
			"\1\77",
			"\1\100",
			"",
			"",
			"\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
			"\1\102",
			"",
			"\1\103",
			"\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
			"",
			"\1\105",
			"\1\106",
			"\1\107",
			"",
			"\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
			"\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
			"",
			"\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
			"\1\113",
			"\1\114",
			"",
			"",
			"",
			"\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
			"\1\116",
			"",
			"\1\117",
			"\12\23\7\uffff\32\23\4\uffff\1\23\1\uffff\32\23",
			""
	};

	static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
	static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
	static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
	static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
	static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
	static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
	static final short[][] DFA4_transition;

	static {
		int numStates = DFA4_transitionS.length;
		DFA4_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
		}
	}

	protected class DFA4 extends DFA {

		public DFA4(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 4;
			this.eot = DFA4_eot;
			this.eof = DFA4_eof;
			this.min = DFA4_min;
			this.max = DFA4_max;
			this.accept = DFA4_accept;
			this.special = DFA4_special;
			this.transition = DFA4_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | CONTAR | INT | DOUBLE | FLOAT | VOID | PUBLIC | PRIVATE | PROTECTED | SEMICOLON | DOT | ID | CFLOAT | CDOUBLE | CINT | WS );";
		}
	}

}
