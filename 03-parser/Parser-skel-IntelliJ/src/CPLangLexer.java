// Generated from D:/UPB/CPL/Idea/Parser-skel\CPLangLexer.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CPLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, THEN=2, ELSE=3, FI=4, BOOL=5, TYPE=6, ID=7, INT=8, FLOAT=9, STRING=10, 
		SEMI=11, COMMA=12, ASSIGN=13, LPAREN=14, RPAREN=15, LBRACE=16, RBRACE=17, 
		PLUS=18, MINUS=19, MULT=20, DIV=21, EQUAL=22, LT=23, LE=24, LINE_COMMENT=25, 
		BLOCK_COMMENT=26, WS=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "THEN", "ELSE", "FI", "BOOL", "TYPE", "LETTER", "ID", "DIGIT", 
			"INT", "DIGITS", "EXPONENT", "FLOAT", "STRING", "SEMI", "COMMA", "ASSIGN", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "PLUS", "MINUS", "MULT", "DIV", 
			"EQUAL", "LT", "LE", "NEW_LINE", "LINE_COMMENT", "BLOCK_COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'then'", "'else'", "'fi'", null, null, null, null, null, 
			null, "';'", "','", "'='", "'('", "')'", "'{'", "'}'", "'+'", "'-'", 
			"'*'", "'/'", "'=='", "'<'", "'<='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "THEN", "ELSE", "FI", "BOOL", "TYPE", "ID", "INT", "FLOAT", 
			"STRING", "SEMI", "COMMA", "ASSIGN", "LPAREN", "RPAREN", "LBRACE", "RBRACE", 
			"PLUS", "MINUS", "MULT", "DIV", "EQUAL", "LT", "LE", "LINE_COMMENT", 
			"BLOCK_COMMENT", "WS"
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


	public CPLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CPLangLexer.g4"; }

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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 13:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 30:
			BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 System.out.println("there are no strings in CPLang, but shhh.."); 
			break;
		}
	}
	private void BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 System.err.println("EOF in comment"); 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u00f1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6]\n\6\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7k\n\7\3\b\3\b\3\t\3\t\5\tq\n\t\3\t\3\t\3"+
		"\t\7\tv\n\t\f\t\16\ty\13\t\3\n\3\n\3\13\6\13~\n\13\r\13\16\13\177\3\f"+
		"\6\f\u0083\n\f\r\f\16\f\u0084\3\r\3\r\5\r\u0089\n\r\3\r\3\r\3\16\3\16"+
		"\3\16\5\16\u0090\n\16\5\16\u0092\n\16\3\16\3\16\5\16\u0096\n\16\3\16\5"+
		"\16\u0099\n\16\3\17\3\17\3\17\3\17\7\17\u009f\n\17\f\17\16\17\u00a2\13"+
		"\17\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3"+
		"\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3"+
		"\33\3\34\3\34\3\35\3\35\3\35\3\36\5\36\u00c6\n\36\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\7\37\u00ce\n\37\f\37\16\37\u00d1\13\37\3\37\3\37\5\37\u00d5"+
		"\n\37\3\37\3\37\3 \3 \3 \3 \3 \7 \u00de\n \f \16 \u00e1\13 \3 \3 \3 \3"+
		" \5 \u00e7\n \3 \3 \3!\6!\u00ec\n!\r!\16!\u00ed\3!\3!\5\u00a0\u00cf\u00df"+
		"\2\"\3\3\5\4\7\5\t\6\13\7\r\b\17\2\21\t\23\2\25\n\27\2\31\2\33\13\35\f"+
		"\37\r!\16#\17%\20\'\21)\22+\23-\24/\25\61\26\63\27\65\30\67\319\32;\2"+
		"=\33?\34A\35\3\2\6\4\2C\\c|\3\2\62;\4\2--//\5\2\13\f\17\17\"\"\2\u0102"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\21\3\2\2\2\2\25\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2"+
		"\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2"+
		"-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2"+
		"\2\29\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\3C\3\2\2\2\5F\3\2\2\2\7"+
		"K\3\2\2\2\tP\3\2\2\2\13\\\3\2\2\2\rj\3\2\2\2\17l\3\2\2\2\21p\3\2\2\2\23"+
		"z\3\2\2\2\25}\3\2\2\2\27\u0082\3\2\2\2\31\u0086\3\2\2\2\33\u0095\3\2\2"+
		"\2\35\u009a\3\2\2\2\37\u00a6\3\2\2\2!\u00a8\3\2\2\2#\u00aa\3\2\2\2%\u00ac"+
		"\3\2\2\2\'\u00ae\3\2\2\2)\u00b0\3\2\2\2+\u00b2\3\2\2\2-\u00b4\3\2\2\2"+
		"/\u00b6\3\2\2\2\61\u00b8\3\2\2\2\63\u00ba\3\2\2\2\65\u00bc\3\2\2\2\67"+
		"\u00bf\3\2\2\29\u00c1\3\2\2\2;\u00c5\3\2\2\2=\u00c9\3\2\2\2?\u00d8\3\2"+
		"\2\2A\u00eb\3\2\2\2CD\7k\2\2DE\7h\2\2E\4\3\2\2\2FG\7v\2\2GH\7j\2\2HI\7"+
		"g\2\2IJ\7p\2\2J\6\3\2\2\2KL\7g\2\2LM\7n\2\2MN\7u\2\2NO\7g\2\2O\b\3\2\2"+
		"\2PQ\7h\2\2QR\7k\2\2R\n\3\2\2\2ST\7v\2\2TU\7t\2\2UV\7w\2\2V]\7g\2\2WX"+
		"\7h\2\2XY\7c\2\2YZ\7n\2\2Z[\7u\2\2[]\7g\2\2\\S\3\2\2\2\\W\3\2\2\2]\f\3"+
		"\2\2\2^_\7K\2\2_`\7p\2\2`k\7v\2\2ab\7H\2\2bc\7n\2\2cd\7q\2\2de\7c\2\2"+
		"ek\7v\2\2fg\7D\2\2gh\7q\2\2hi\7q\2\2ik\7n\2\2j^\3\2\2\2ja\3\2\2\2jf\3"+
		"\2\2\2k\16\3\2\2\2lm\t\2\2\2m\20\3\2\2\2nq\5\17\b\2oq\7a\2\2pn\3\2\2\2"+
		"po\3\2\2\2qw\3\2\2\2rv\5\17\b\2sv\7a\2\2tv\5\23\n\2ur\3\2\2\2us\3\2\2"+
		"\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2x\22\3\2\2\2yw\3\2\2\2z{\t\3"+
		"\2\2{\24\3\2\2\2|~\5\23\n\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080"+
		"\3\2\2\2\u0080\26\3\2\2\2\u0081\u0083\5\23\n\2\u0082\u0081\3\2\2\2\u0083"+
		"\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\30\3\2\2"+
		"\2\u0086\u0088\7g\2\2\u0087\u0089\t\4\2\2\u0088\u0087\3\2\2\2\u0088\u0089"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\5\27\f\2\u008b\32\3\2\2\2\u008c"+
		"\u0091\5\27\f\2\u008d\u008f\7\60\2\2\u008e\u0090\5\27\f\2\u008f\u008e"+
		"\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u008d\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0096\3\2\2\2\u0093\u0094\7\60\2\2\u0094\u0096\5"+
		"\27\f\2\u0095\u008c\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0098\3\2\2\2\u0097"+
		"\u0099\5\31\r\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099\34\3\2\2"+
		"\2\u009a\u00a0\7$\2\2\u009b\u009c\7^\2\2\u009c\u009f\7$\2\2\u009d\u009f"+
		"\13\2\2\2\u009e\u009b\3\2\2\2\u009e\u009d\3\2\2\2\u009f\u00a2\3\2\2\2"+
		"\u00a0\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a0"+
		"\3\2\2\2\u00a3\u00a4\7$\2\2\u00a4\u00a5\b\17\2\2\u00a5\36\3\2\2\2\u00a6"+
		"\u00a7\7=\2\2\u00a7 \3\2\2\2\u00a8\u00a9\7.\2\2\u00a9\"\3\2\2\2\u00aa"+
		"\u00ab\7?\2\2\u00ab$\3\2\2\2\u00ac\u00ad\7*\2\2\u00ad&\3\2\2\2\u00ae\u00af"+
		"\7+\2\2\u00af(\3\2\2\2\u00b0\u00b1\7}\2\2\u00b1*\3\2\2\2\u00b2\u00b3\7"+
		"\177\2\2\u00b3,\3\2\2\2\u00b4\u00b5\7-\2\2\u00b5.\3\2\2\2\u00b6\u00b7"+
		"\7/\2\2\u00b7\60\3\2\2\2\u00b8\u00b9\7,\2\2\u00b9\62\3\2\2\2\u00ba\u00bb"+
		"\7\61\2\2\u00bb\64\3\2\2\2\u00bc\u00bd\7?\2\2\u00bd\u00be\7?\2\2\u00be"+
		"\66\3\2\2\2\u00bf\u00c0\7>\2\2\u00c08\3\2\2\2\u00c1\u00c2\7>\2\2\u00c2"+
		"\u00c3\7?\2\2\u00c3:\3\2\2\2\u00c4\u00c6\7\17\2\2\u00c5\u00c4\3\2\2\2"+
		"\u00c5\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\7\f\2\2\u00c8<\3"+
		"\2\2\2\u00c9\u00ca\7\61\2\2\u00ca\u00cb\7\61\2\2\u00cb\u00cf\3\2\2\2\u00cc"+
		"\u00ce\13\2\2\2\u00cd\u00cc\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00d0\3"+
		"\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d4\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d2"+
		"\u00d5\5;\36\2\u00d3\u00d5\7\2\2\3\u00d4\u00d2\3\2\2\2\u00d4\u00d3\3\2"+
		"\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d7\b\37\3\2\u00d7>\3\2\2\2\u00d8\u00d9"+
		"\7\61\2\2\u00d9\u00da\7,\2\2\u00da\u00df\3\2\2\2\u00db\u00de\5? \2\u00dc"+
		"\u00de\13\2\2\2\u00dd\u00db\3\2\2\2\u00dd\u00dc\3\2\2\2\u00de\u00e1\3"+
		"\2\2\2\u00df\u00e0\3\2\2\2\u00df\u00dd\3\2\2\2\u00e0\u00e6\3\2\2\2\u00e1"+
		"\u00df\3\2\2\2\u00e2\u00e3\7,\2\2\u00e3\u00e7\7\61\2\2\u00e4\u00e5\7\2"+
		"\2\3\u00e5\u00e7\b \4\2\u00e6\u00e2\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7"+
		"\u00e8\3\2\2\2\u00e8\u00e9\b \3\2\u00e9@\3\2\2\2\u00ea\u00ec\t\5\2\2\u00eb"+
		"\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed\u00ee\3\2"+
		"\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\b!\3\2\u00f0B\3\2\2\2\30\2\\jpuw"+
		"\177\u0084\u0088\u008f\u0091\u0095\u0098\u009e\u00a0\u00c5\u00cf\u00d4"+
		"\u00dd\u00df\u00e6\u00ed\5\3\17\2\b\2\2\3 \3";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}