// Generated from D:/Facultate/CPL/tema-01/src/cool/lexer/CoolLexer.g4 by ANTLR 4.13.1

package cool.lexer;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, CLASS=2, INHERITS=3, NEW=4, IF=5, THEN=6, ELSE=7, FI=8, LET=9, 
		IN=10, CASE=11, OF=12, ESAC=13, LOOP=14, POOL=15, WHILE=16, ISVOID=17, 
		NOT=18, COMPLEMENT=19, PLUS=20, MINUS=21, MULTIPLY=22, DIVIDE=23, EQ=24, 
		LESS=25, LESS_EQ=26, LPAREN=27, RPAREN=28, LCURLY=29, RCURLY=30, OF_TYPE=31, 
		AT=32, DOT=33, COMMA=34, CASE_ARROW=35, ASSIGN=36, SEMICOLON=37, TYPE=38, 
		ID=39, WS=40, INTEGER=41, TRUE=42, FALSE=43, LINE_COMMENT=44, EOF_BLOCK_COMMENT=45, 
		BLOCK_COMMENT=46, UNMATCHED_BLOCK_COMMENT=47, UNTERMINATED_STRING=48, 
		EOF_STRING=49, STRING=50, UNKNOWN_CHARACTER=51;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CLASS", 
			"INHERITS", "NEW", "IF", "THEN", "ELSE", "FI", "LET", "IN", "CASE", "OF", 
			"ESAC", "LOOP", "POOL", "WHILE", "ISVOID", "NOT", "COMPLEMENT", "PLUS", 
			"MINUS", "MULTIPLY", "DIVIDE", "EQ", "LESS", "LESS_EQ", "LPAREN", "RPAREN", 
			"LCURLY", "RCURLY", "OF_TYPE", "AT", "DOT", "COMMA", "CASE_ARROW", "ASSIGN", 
			"SEMICOLON", "TYPE", "ID", "WS", "INTEGER", "TRUE", "FALSE", "LINE_COMMENT", 
			"EOF_BLOCK_COMMENT", "BLOCK_COMMENT", "UNMATCHED_BLOCK_COMMENT", "UNTERMINATED_STRING", 
			"EOF_STRING", "STRING", "UNKNOWN_CHARACTER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "'~'", "'+'", "'-'", "'*'", 
			"'/'", "'='", "'<'", "'<='", "'('", "')'", "'{'", "'}'", "':'", "'@'", 
			"'.'", "','", "'=>'", "'<-'", "';'", null, null, null, null, null, null, 
			null, null, null, "'*)'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "CLASS", "INHERITS", "NEW", "IF", "THEN", "ELSE", "FI", 
			"LET", "IN", "CASE", "OF", "ESAC", "LOOP", "POOL", "WHILE", "ISVOID", 
			"NOT", "COMPLEMENT", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "EQ", "LESS", 
			"LESS_EQ", "LPAREN", "RPAREN", "LCURLY", "RCURLY", "OF_TYPE", "AT", "DOT", 
			"COMMA", "CASE_ARROW", "ASSIGN", "SEMICOLON", "TYPE", "ID", "WS", "INTEGER", 
			"TRUE", "FALSE", "LINE_COMMENT", "EOF_BLOCK_COMMENT", "BLOCK_COMMENT", 
			"UNMATCHED_BLOCK_COMMENT", "UNTERMINATED_STRING", "EOF_STRING", "STRING", 
			"UNKNOWN_CHARACTER"
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


	private void raiseError(String msg) {
	    setText(msg);
	    setType(ERROR);
	}


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

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
		case 69:
			EOF_BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 71:
			UNMATCHED_BLOCK_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 72:
			UNTERMINATED_STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 73:
			EOF_STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 74:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 75:
			UNKNOWN_CHARACTER_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void EOF_BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 raiseError("EOF in comment"); 
			break;
		}
	}
	private void UNMATCHED_BLOCK_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 raiseError("Unmatched *)"); 
			break;
		}
	}
	private void UNTERMINATED_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			 raiseError("Unterminated string constant"); 
			break;
		}
	}
	private void EOF_STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 raiseError("EOF in string constant"); 
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:

			var content = getText();
			// Remove leading & trailing quote character
			content = content.substring(1, content.length() - 1);
			// Parse escaped
			var builder = new StringBuilder(content.length());
			boolean escaping = false;
			for (int i = 0; i < content.length(); ++i) {
			    char c = content.charAt(i);
			    if (escaping) {
			        switch (c) {
			            case 'n':
			                builder.append('\n');
			                break;
			            case 't':
			                builder.append('\t');
			                break;
			            case 'b':
			                builder.append('\b');
			                break;
			            case 'f':
			                builder.append('\f');
			                break;
			            default:
			                builder.append(c);
			                break;
			        }
			        escaping = false;
			    } else if (c == '\\') escaping = true;
			    else builder.append(c);
			}
			content = builder.toString();
			// Verify string literal constraints
			if (content.indexOf('\0') != -1) raiseError("String contains null character");
			else if (content.length() > 1024) raiseError("String constant too long");
			else setText(content);

			break;
		}
	}
	private void UNKNOWN_CHARACTER_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			 raiseError("Invalid character: " + getText()); 
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u00003\u01c6\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007"+
		"?\u0002@\u0007@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007"+
		"D\u0002E\u0007E\u0002F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007"+
		"I\u0002J\u0007J\u0002K\u0007K\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f"+
		"\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0018"+
		"\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001"+
		" \u0001 \u0001!\u0001!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001#\u0001"+
		"#\u0001#\u0001#\u0001#\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001)\u0001)\u0001"+
		")\u0001)\u0001)\u0001)\u0001)\u0001*\u0001*\u0001*\u0001*\u0001+\u0001"+
		"+\u0001,\u0001,\u0001-\u0001-\u0001.\u0001.\u0001/\u0001/\u00010\u0001"+
		"0\u00011\u00011\u00012\u00012\u00012\u00013\u00013\u00014\u00014\u0001"+
		"5\u00015\u00016\u00016\u00017\u00017\u00018\u00018\u00019\u00019\u0001"+
		":\u0001:\u0001;\u0001;\u0001;\u0001<\u0001<\u0001<\u0001=\u0001=\u0001"+
		">\u0001>\u0005>\u014b\b>\n>\f>\u014e\t>\u0001?\u0001?\u0005?\u0152\b?"+
		"\n?\f?\u0155\t?\u0001@\u0004@\u0158\b@\u000b@\f@\u0159\u0001@\u0001@\u0001"+
		"A\u0004A\u015f\bA\u000bA\fA\u0160\u0001B\u0001B\u0001B\u0001B\u0001B\u0001"+
		"C\u0001C\u0001C\u0001C\u0001C\u0001C\u0001D\u0001D\u0001D\u0001D\u0005"+
		"D\u0172\bD\nD\fD\u0175\tD\u0001D\u0001D\u0001E\u0001E\u0001E\u0001E\u0001"+
		"E\u0001E\u0001E\u0001E\u0005E\u0181\bE\nE\fE\u0184\tE\u0001E\u0001E\u0001"+
		"E\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0001F\u0005F\u0191"+
		"\bF\nF\fF\u0194\tF\u0001F\u0001F\u0001F\u0001F\u0001F\u0001G\u0001G\u0001"+
		"G\u0001G\u0001G\u0001H\u0001H\u0001H\u0001H\u0005H\u01a4\bH\nH\fH\u01a7"+
		"\tH\u0001H\u0001H\u0001H\u0001I\u0001I\u0001I\u0001I\u0005I\u01b0\bI\n"+
		"I\fI\u01b3\tI\u0001I\u0001I\u0001I\u0001J\u0001J\u0001J\u0001J\u0005J"+
		"\u01bc\bJ\nJ\fJ\u01bf\tJ\u0001J\u0001J\u0001J\u0001K\u0001K\u0001K\u0000"+
		"\u0000L\u0001\u0000\u0003\u0000\u0005\u0000\u0007\u0000\t\u0000\u000b"+
		"\u0000\r\u0000\u000f\u0000\u0011\u0000\u0013\u0000\u0015\u0000\u0017\u0000"+
		"\u0019\u0000\u001b\u0000\u001d\u0000\u001f\u0000!\u0000#\u0000%\u0000"+
		"\'\u0000)\u0000+\u0000-\u0000/\u00001\u00003\u00005\u00027\u00039\u0004"+
		";\u0005=\u0006?\u0007A\bC\tE\nG\u000bI\fK\rM\u000eO\u000fQ\u0010S\u0011"+
		"U\u0012W\u0013Y\u0014[\u0015]\u0016_\u0017a\u0018c\u0019e\u001ag\u001b"+
		"i\u001ck\u001dm\u001eo\u001fq s!u\"w#y${%}&\u007f\'\u0081(\u0083)\u0085"+
		"*\u0087+\u0089,\u008b-\u008d.\u008f/\u00910\u00931\u00952\u00973\u0001"+
		"\u0000#\u0002\u0000AAaa\u0002\u0000BBbb\u0002\u0000CCcc\u0002\u0000DD"+
		"dd\u0002\u0000EEee\u0002\u0000FFff\u0002\u0000GGgg\u0002\u0000HHhh\u0002"+
		"\u0000IIii\u0002\u0000JJjj\u0002\u0000KKkk\u0002\u0000LLll\u0002\u0000"+
		"MMmm\u0002\u0000NNnn\u0002\u0000OOoo\u0002\u0000PPpp\u0002\u0000QQqq\u0002"+
		"\u0000RRrr\u0002\u0000SSss\u0002\u0000TTtt\u0002\u0000UUuu\u0002\u0000"+
		"VVvv\u0002\u0000WWww\u0002\u0000XXxx\u0002\u0000YYyy\u0002\u0000ZZzz\u0001"+
		"\u0000AZ\u0004\u000009AZ__az\u0001\u0000az\u0003\u0000\t\n\f\r  \u0001"+
		"\u000009\u0002\u0000\n\n\r\r\u0001\u0000))\u0001\u0000**\u0003\u0000\n"+
		"\n\r\r\"\"\u01be\u00005\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000"+
		"\u0000\u00009\u0001\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000"+
		"=\u0001\u0000\u0000\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001"+
		"\u0000\u0000\u0000\u0000C\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000"+
		"\u0000\u0000G\u0001\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0000"+
		"K\u0001\u0000\u0000\u0000\u0000M\u0001\u0000\u0000\u0000\u0000O\u0001"+
		"\u0000\u0000\u0000\u0000Q\u0001\u0000\u0000\u0000\u0000S\u0001\u0000\u0000"+
		"\u0000\u0000U\u0001\u0000\u0000\u0000\u0000W\u0001\u0000\u0000\u0000\u0000"+
		"Y\u0001\u0000\u0000\u0000\u0000[\u0001\u0000\u0000\u0000\u0000]\u0001"+
		"\u0000\u0000\u0000\u0000_\u0001\u0000\u0000\u0000\u0000a\u0001\u0000\u0000"+
		"\u0000\u0000c\u0001\u0000\u0000\u0000\u0000e\u0001\u0000\u0000\u0000\u0000"+
		"g\u0001\u0000\u0000\u0000\u0000i\u0001\u0000\u0000\u0000\u0000k\u0001"+
		"\u0000\u0000\u0000\u0000m\u0001\u0000\u0000\u0000\u0000o\u0001\u0000\u0000"+
		"\u0000\u0000q\u0001\u0000\u0000\u0000\u0000s\u0001\u0000\u0000\u0000\u0000"+
		"u\u0001\u0000\u0000\u0000\u0000w\u0001\u0000\u0000\u0000\u0000y\u0001"+
		"\u0000\u0000\u0000\u0000{\u0001\u0000\u0000\u0000\u0000}\u0001\u0000\u0000"+
		"\u0000\u0000\u007f\u0001\u0000\u0000\u0000\u0000\u0081\u0001\u0000\u0000"+
		"\u0000\u0000\u0083\u0001\u0000\u0000\u0000\u0000\u0085\u0001\u0000\u0000"+
		"\u0000\u0000\u0087\u0001\u0000\u0000\u0000\u0000\u0089\u0001\u0000\u0000"+
		"\u0000\u0000\u008b\u0001\u0000\u0000\u0000\u0000\u008d\u0001\u0000\u0000"+
		"\u0000\u0000\u008f\u0001\u0000\u0000\u0000\u0000\u0091\u0001\u0000\u0000"+
		"\u0000\u0000\u0093\u0001\u0000\u0000\u0000\u0000\u0095\u0001\u0000\u0000"+
		"\u0000\u0000\u0097\u0001\u0000\u0000\u0000\u0001\u0099\u0001\u0000\u0000"+
		"\u0000\u0003\u009b\u0001\u0000\u0000\u0000\u0005\u009d\u0001\u0000\u0000"+
		"\u0000\u0007\u009f\u0001\u0000\u0000\u0000\t\u00a1\u0001\u0000\u0000\u0000"+
		"\u000b\u00a3\u0001\u0000\u0000\u0000\r\u00a5\u0001\u0000\u0000\u0000\u000f"+
		"\u00a7\u0001\u0000\u0000\u0000\u0011\u00a9\u0001\u0000\u0000\u0000\u0013"+
		"\u00ab\u0001\u0000\u0000\u0000\u0015\u00ad\u0001\u0000\u0000\u0000\u0017"+
		"\u00af\u0001\u0000\u0000\u0000\u0019\u00b1\u0001\u0000\u0000\u0000\u001b"+
		"\u00b3\u0001\u0000\u0000\u0000\u001d\u00b5\u0001\u0000\u0000\u0000\u001f"+
		"\u00b7\u0001\u0000\u0000\u0000!\u00b9\u0001\u0000\u0000\u0000#\u00bb\u0001"+
		"\u0000\u0000\u0000%\u00bd\u0001\u0000\u0000\u0000\'\u00bf\u0001\u0000"+
		"\u0000\u0000)\u00c1\u0001\u0000\u0000\u0000+\u00c3\u0001\u0000\u0000\u0000"+
		"-\u00c5\u0001\u0000\u0000\u0000/\u00c7\u0001\u0000\u0000\u00001\u00c9"+
		"\u0001\u0000\u0000\u00003\u00cb\u0001\u0000\u0000\u00005\u00cd\u0001\u0000"+
		"\u0000\u00007\u00d3\u0001\u0000\u0000\u00009\u00dc\u0001\u0000\u0000\u0000"+
		";\u00e0\u0001\u0000\u0000\u0000=\u00e3\u0001\u0000\u0000\u0000?\u00e8"+
		"\u0001\u0000\u0000\u0000A\u00ed\u0001\u0000\u0000\u0000C\u00f0\u0001\u0000"+
		"\u0000\u0000E\u00f4\u0001\u0000\u0000\u0000G\u00f7\u0001\u0000\u0000\u0000"+
		"I\u00fc\u0001\u0000\u0000\u0000K\u00ff\u0001\u0000\u0000\u0000M\u0104"+
		"\u0001\u0000\u0000\u0000O\u0109\u0001\u0000\u0000\u0000Q\u010e\u0001\u0000"+
		"\u0000\u0000S\u0114\u0001\u0000\u0000\u0000U\u011b\u0001\u0000\u0000\u0000"+
		"W\u011f\u0001\u0000\u0000\u0000Y\u0121\u0001\u0000\u0000\u0000[\u0123"+
		"\u0001\u0000\u0000\u0000]\u0125\u0001\u0000\u0000\u0000_\u0127\u0001\u0000"+
		"\u0000\u0000a\u0129\u0001\u0000\u0000\u0000c\u012b\u0001\u0000\u0000\u0000"+
		"e\u012d\u0001\u0000\u0000\u0000g\u0130\u0001\u0000\u0000\u0000i\u0132"+
		"\u0001\u0000\u0000\u0000k\u0134\u0001\u0000\u0000\u0000m\u0136\u0001\u0000"+
		"\u0000\u0000o\u0138\u0001\u0000\u0000\u0000q\u013a\u0001\u0000\u0000\u0000"+
		"s\u013c\u0001\u0000\u0000\u0000u\u013e\u0001\u0000\u0000\u0000w\u0140"+
		"\u0001\u0000\u0000\u0000y\u0143\u0001\u0000\u0000\u0000{\u0146\u0001\u0000"+
		"\u0000\u0000}\u0148\u0001\u0000\u0000\u0000\u007f\u014f\u0001\u0000\u0000"+
		"\u0000\u0081\u0157\u0001\u0000\u0000\u0000\u0083\u015e\u0001\u0000\u0000"+
		"\u0000\u0085\u0162\u0001\u0000\u0000\u0000\u0087\u0167\u0001\u0000\u0000"+
		"\u0000\u0089\u016d\u0001\u0000\u0000\u0000\u008b\u0178\u0001\u0000\u0000"+
		"\u0000\u008d\u0188\u0001\u0000\u0000\u0000\u008f\u019a\u0001\u0000\u0000"+
		"\u0000\u0091\u019f\u0001\u0000\u0000\u0000\u0093\u01ab\u0001\u0000\u0000"+
		"\u0000\u0095\u01b7\u0001\u0000\u0000\u0000\u0097\u01c3\u0001\u0000\u0000"+
		"\u0000\u0099\u009a\u0007\u0000\u0000\u0000\u009a\u0002\u0001\u0000\u0000"+
		"\u0000\u009b\u009c\u0007\u0001\u0000\u0000\u009c\u0004\u0001\u0000\u0000"+
		"\u0000\u009d\u009e\u0007\u0002\u0000\u0000\u009e\u0006\u0001\u0000\u0000"+
		"\u0000\u009f\u00a0\u0007\u0003\u0000\u0000\u00a0\b\u0001\u0000\u0000\u0000"+
		"\u00a1\u00a2\u0007\u0004\u0000\u0000\u00a2\n\u0001\u0000\u0000\u0000\u00a3"+
		"\u00a4\u0007\u0005\u0000\u0000\u00a4\f\u0001\u0000\u0000\u0000\u00a5\u00a6"+
		"\u0007\u0006\u0000\u0000\u00a6\u000e\u0001\u0000\u0000\u0000\u00a7\u00a8"+
		"\u0007\u0007\u0000\u0000\u00a8\u0010\u0001\u0000\u0000\u0000\u00a9\u00aa"+
		"\u0007\b\u0000\u0000\u00aa\u0012\u0001\u0000\u0000\u0000\u00ab\u00ac\u0007"+
		"\t\u0000\u0000\u00ac\u0014\u0001\u0000\u0000\u0000\u00ad\u00ae\u0007\n"+
		"\u0000\u0000\u00ae\u0016\u0001\u0000\u0000\u0000\u00af\u00b0\u0007\u000b"+
		"\u0000\u0000\u00b0\u0018\u0001\u0000\u0000\u0000\u00b1\u00b2\u0007\f\u0000"+
		"\u0000\u00b2\u001a\u0001\u0000\u0000\u0000\u00b3\u00b4\u0007\r\u0000\u0000"+
		"\u00b4\u001c\u0001\u0000\u0000\u0000\u00b5\u00b6\u0007\u000e\u0000\u0000"+
		"\u00b6\u001e\u0001\u0000\u0000\u0000\u00b7\u00b8\u0007\u000f\u0000\u0000"+
		"\u00b8 \u0001\u0000\u0000\u0000\u00b9\u00ba\u0007\u0010\u0000\u0000\u00ba"+
		"\"\u0001\u0000\u0000\u0000\u00bb\u00bc\u0007\u0011\u0000\u0000\u00bc$"+
		"\u0001\u0000\u0000\u0000\u00bd\u00be\u0007\u0012\u0000\u0000\u00be&\u0001"+
		"\u0000\u0000\u0000\u00bf\u00c0\u0007\u0013\u0000\u0000\u00c0(\u0001\u0000"+
		"\u0000\u0000\u00c1\u00c2\u0007\u0014\u0000\u0000\u00c2*\u0001\u0000\u0000"+
		"\u0000\u00c3\u00c4\u0007\u0015\u0000\u0000\u00c4,\u0001\u0000\u0000\u0000"+
		"\u00c5\u00c6\u0007\u0016\u0000\u0000\u00c6.\u0001\u0000\u0000\u0000\u00c7"+
		"\u00c8\u0007\u0017\u0000\u0000\u00c80\u0001\u0000\u0000\u0000\u00c9\u00ca"+
		"\u0007\u0018\u0000\u0000\u00ca2\u0001\u0000\u0000\u0000\u00cb\u00cc\u0007"+
		"\u0019\u0000\u0000\u00cc4\u0001\u0000\u0000\u0000\u00cd\u00ce\u0003\u0005"+
		"\u0002\u0000\u00ce\u00cf\u0003\u0017\u000b\u0000\u00cf\u00d0\u0003\u0001"+
		"\u0000\u0000\u00d0\u00d1\u0003%\u0012\u0000\u00d1\u00d2\u0003%\u0012\u0000"+
		"\u00d26\u0001\u0000\u0000\u0000\u00d3\u00d4\u0003\u0011\b\u0000\u00d4"+
		"\u00d5\u0003\u001b\r\u0000\u00d5\u00d6\u0003\u000f\u0007\u0000\u00d6\u00d7"+
		"\u0003\t\u0004\u0000\u00d7\u00d8\u0003#\u0011\u0000\u00d8\u00d9\u0003"+
		"\u0011\b\u0000\u00d9\u00da\u0003\'\u0013\u0000\u00da\u00db\u0003%\u0012"+
		"\u0000\u00db8\u0001\u0000\u0000\u0000\u00dc\u00dd\u0003\u001b\r\u0000"+
		"\u00dd\u00de\u0003\t\u0004\u0000\u00de\u00df\u0003-\u0016\u0000\u00df"+
		":\u0001\u0000\u0000\u0000\u00e0\u00e1\u0003\u0011\b\u0000\u00e1\u00e2"+
		"\u0003\u000b\u0005\u0000\u00e2<\u0001\u0000\u0000\u0000\u00e3\u00e4\u0003"+
		"\'\u0013\u0000\u00e4\u00e5\u0003\u000f\u0007\u0000\u00e5\u00e6\u0003\t"+
		"\u0004\u0000\u00e6\u00e7\u0003\u001b\r\u0000\u00e7>\u0001\u0000\u0000"+
		"\u0000\u00e8\u00e9\u0003\t\u0004\u0000\u00e9\u00ea\u0003\u0017\u000b\u0000"+
		"\u00ea\u00eb\u0003%\u0012\u0000\u00eb\u00ec\u0003\t\u0004\u0000\u00ec"+
		"@\u0001\u0000\u0000\u0000\u00ed\u00ee\u0003\u000b\u0005\u0000\u00ee\u00ef"+
		"\u0003\u0011\b\u0000\u00efB\u0001\u0000\u0000\u0000\u00f0\u00f1\u0003"+
		"\u0017\u000b\u0000\u00f1\u00f2\u0003\t\u0004\u0000\u00f2\u00f3\u0003\'"+
		"\u0013\u0000\u00f3D\u0001\u0000\u0000\u0000\u00f4\u00f5\u0003\u0011\b"+
		"\u0000\u00f5\u00f6\u0003\u001b\r\u0000\u00f6F\u0001\u0000\u0000\u0000"+
		"\u00f7\u00f8\u0003\u0005\u0002\u0000\u00f8\u00f9\u0003\u0001\u0000\u0000"+
		"\u00f9\u00fa\u0003%\u0012\u0000\u00fa\u00fb\u0003\t\u0004\u0000\u00fb"+
		"H\u0001\u0000\u0000\u0000\u00fc\u00fd\u0003\u001d\u000e\u0000\u00fd\u00fe"+
		"\u0003\u000b\u0005\u0000\u00feJ\u0001\u0000\u0000\u0000\u00ff\u0100\u0003"+
		"\t\u0004\u0000\u0100\u0101\u0003%\u0012\u0000\u0101\u0102\u0003\u0001"+
		"\u0000\u0000\u0102\u0103\u0003\u0005\u0002\u0000\u0103L\u0001\u0000\u0000"+
		"\u0000\u0104\u0105\u0003\u0017\u000b\u0000\u0105\u0106\u0003\u001d\u000e"+
		"\u0000\u0106\u0107\u0003\u001d\u000e\u0000\u0107\u0108\u0003\u001f\u000f"+
		"\u0000\u0108N\u0001\u0000\u0000\u0000\u0109\u010a\u0003\u001f\u000f\u0000"+
		"\u010a\u010b\u0003\u001d\u000e\u0000\u010b\u010c\u0003\u001d\u000e\u0000"+
		"\u010c\u010d\u0003\u0017\u000b\u0000\u010dP\u0001\u0000\u0000\u0000\u010e"+
		"\u010f\u0003-\u0016\u0000\u010f\u0110\u0003\u000f\u0007\u0000\u0110\u0111"+
		"\u0003\u0011\b\u0000\u0111\u0112\u0003\u0017\u000b\u0000\u0112\u0113\u0003"+
		"\t\u0004\u0000\u0113R\u0001\u0000\u0000\u0000\u0114\u0115\u0003\u0011"+
		"\b\u0000\u0115\u0116\u0003%\u0012\u0000\u0116\u0117\u0003+\u0015\u0000"+
		"\u0117\u0118\u0003\u001d\u000e\u0000\u0118\u0119\u0003\u0011\b\u0000\u0119"+
		"\u011a\u0003\u0007\u0003\u0000\u011aT\u0001\u0000\u0000\u0000\u011b\u011c"+
		"\u0003\u001b\r\u0000\u011c\u011d\u0003\u001d\u000e\u0000\u011d\u011e\u0003"+
		"\'\u0013\u0000\u011eV\u0001\u0000\u0000\u0000\u011f\u0120\u0005~\u0000"+
		"\u0000\u0120X\u0001\u0000\u0000\u0000\u0121\u0122\u0005+\u0000\u0000\u0122"+
		"Z\u0001\u0000\u0000\u0000\u0123\u0124\u0005-\u0000\u0000\u0124\\\u0001"+
		"\u0000\u0000\u0000\u0125\u0126\u0005*\u0000\u0000\u0126^\u0001\u0000\u0000"+
		"\u0000\u0127\u0128\u0005/\u0000\u0000\u0128`\u0001\u0000\u0000\u0000\u0129"+
		"\u012a\u0005=\u0000\u0000\u012ab\u0001\u0000\u0000\u0000\u012b\u012c\u0005"+
		"<\u0000\u0000\u012cd\u0001\u0000\u0000\u0000\u012d\u012e\u0005<\u0000"+
		"\u0000\u012e\u012f\u0005=\u0000\u0000\u012ff\u0001\u0000\u0000\u0000\u0130"+
		"\u0131\u0005(\u0000\u0000\u0131h\u0001\u0000\u0000\u0000\u0132\u0133\u0005"+
		")\u0000\u0000\u0133j\u0001\u0000\u0000\u0000\u0134\u0135\u0005{\u0000"+
		"\u0000\u0135l\u0001\u0000\u0000\u0000\u0136\u0137\u0005}\u0000\u0000\u0137"+
		"n\u0001\u0000\u0000\u0000\u0138\u0139\u0005:\u0000\u0000\u0139p\u0001"+
		"\u0000\u0000\u0000\u013a\u013b\u0005@\u0000\u0000\u013br\u0001\u0000\u0000"+
		"\u0000\u013c\u013d\u0005.\u0000\u0000\u013dt\u0001\u0000\u0000\u0000\u013e"+
		"\u013f\u0005,\u0000\u0000\u013fv\u0001\u0000\u0000\u0000\u0140\u0141\u0005"+
		"=\u0000\u0000\u0141\u0142\u0005>\u0000\u0000\u0142x\u0001\u0000\u0000"+
		"\u0000\u0143\u0144\u0005<\u0000\u0000\u0144\u0145\u0005-\u0000\u0000\u0145"+
		"z\u0001\u0000\u0000\u0000\u0146\u0147\u0005;\u0000\u0000\u0147|\u0001"+
		"\u0000\u0000\u0000\u0148\u014c\u0007\u001a\u0000\u0000\u0149\u014b\u0007"+
		"\u001b\u0000\u0000\u014a\u0149\u0001\u0000\u0000\u0000\u014b\u014e\u0001"+
		"\u0000\u0000\u0000\u014c\u014a\u0001\u0000\u0000\u0000\u014c\u014d\u0001"+
		"\u0000\u0000\u0000\u014d~\u0001\u0000\u0000\u0000\u014e\u014c\u0001\u0000"+
		"\u0000\u0000\u014f\u0153\u0007\u001c\u0000\u0000\u0150\u0152\u0007\u001b"+
		"\u0000\u0000\u0151\u0150\u0001\u0000\u0000\u0000\u0152\u0155\u0001\u0000"+
		"\u0000\u0000\u0153\u0151\u0001\u0000\u0000\u0000\u0153\u0154\u0001\u0000"+
		"\u0000\u0000\u0154\u0080\u0001\u0000\u0000\u0000\u0155\u0153\u0001\u0000"+
		"\u0000\u0000\u0156\u0158\u0007\u001d\u0000\u0000\u0157\u0156\u0001\u0000"+
		"\u0000\u0000\u0158\u0159\u0001\u0000\u0000\u0000\u0159\u0157\u0001\u0000"+
		"\u0000\u0000\u0159\u015a\u0001\u0000\u0000\u0000\u015a\u015b\u0001\u0000"+
		"\u0000\u0000\u015b\u015c\u0006@\u0000\u0000\u015c\u0082\u0001\u0000\u0000"+
		"\u0000\u015d\u015f\u0007\u001e\u0000\u0000\u015e\u015d\u0001\u0000\u0000"+
		"\u0000\u015f\u0160\u0001\u0000\u0000\u0000\u0160\u015e\u0001\u0000\u0000"+
		"\u0000\u0160\u0161\u0001\u0000\u0000\u0000\u0161\u0084\u0001\u0000\u0000"+
		"\u0000\u0162\u0163\u0005t\u0000\u0000\u0163\u0164\u0003#\u0011\u0000\u0164"+
		"\u0165\u0003)\u0014\u0000\u0165\u0166\u0003\t\u0004\u0000\u0166\u0086"+
		"\u0001\u0000\u0000\u0000\u0167\u0168\u0005f\u0000\u0000\u0168\u0169\u0003"+
		"\u0001\u0000\u0000\u0169\u016a\u0003\u0017\u000b\u0000\u016a\u016b\u0003"+
		"%\u0012\u0000\u016b\u016c\u0003\t\u0004\u0000\u016c\u0088\u0001\u0000"+
		"\u0000\u0000\u016d\u016e\u0005-\u0000\u0000\u016e\u016f\u0005-\u0000\u0000"+
		"\u016f\u0173\u0001\u0000\u0000\u0000\u0170\u0172\b\u001f\u0000\u0000\u0171"+
		"\u0170\u0001\u0000\u0000\u0000\u0172\u0175\u0001\u0000\u0000\u0000\u0173"+
		"\u0171\u0001\u0000\u0000\u0000\u0173\u0174\u0001\u0000\u0000\u0000\u0174"+
		"\u0176\u0001\u0000\u0000\u0000\u0175\u0173\u0001\u0000\u0000\u0000\u0176"+
		"\u0177\u0006D\u0000\u0000\u0177\u008a\u0001\u0000\u0000\u0000\u0178\u0179"+
		"\u0005(\u0000\u0000\u0179\u017a\u0005*\u0000\u0000\u017a\u0182\u0001\u0000"+
		"\u0000\u0000\u017b\u0181\u0003\u008bE\u0000\u017c\u0181\u0003\u008dF\u0000"+
		"\u017d\u017e\u0005*\u0000\u0000\u017e\u0181\b \u0000\u0000\u017f\u0181"+
		"\b!\u0000\u0000\u0180\u017b\u0001\u0000\u0000\u0000\u0180\u017c\u0001"+
		"\u0000\u0000\u0000\u0180\u017d\u0001\u0000\u0000\u0000\u0180\u017f\u0001"+
		"\u0000\u0000\u0000\u0181\u0184\u0001\u0000\u0000\u0000\u0182\u0180\u0001"+
		"\u0000\u0000\u0000\u0182\u0183\u0001\u0000\u0000\u0000\u0183\u0185\u0001"+
		"\u0000\u0000\u0000\u0184\u0182\u0001\u0000\u0000\u0000\u0185\u0186\u0005"+
		"\u0000\u0000\u0001\u0186\u0187\u0006E\u0001\u0000\u0187\u008c\u0001\u0000"+
		"\u0000\u0000\u0188\u0189\u0005(\u0000\u0000\u0189\u018a\u0005*\u0000\u0000"+
		"\u018a\u0192\u0001\u0000\u0000\u0000\u018b\u0191\u0003\u008bE\u0000\u018c"+
		"\u0191\u0003\u008dF\u0000\u018d\u018e\u0005*\u0000\u0000\u018e\u0191\b"+
		" \u0000\u0000\u018f\u0191\b!\u0000\u0000\u0190\u018b\u0001\u0000\u0000"+
		"\u0000\u0190\u018c\u0001\u0000\u0000\u0000\u0190\u018d\u0001\u0000\u0000"+
		"\u0000\u0190\u018f\u0001\u0000\u0000\u0000\u0191\u0194\u0001\u0000\u0000"+
		"\u0000\u0192\u0190\u0001\u0000\u0000\u0000\u0192\u0193\u0001\u0000\u0000"+
		"\u0000\u0193\u0195\u0001\u0000\u0000\u0000\u0194\u0192\u0001\u0000\u0000"+
		"\u0000\u0195\u0196\u0005*\u0000\u0000\u0196\u0197\u0005)\u0000\u0000\u0197"+
		"\u0198\u0001\u0000\u0000\u0000\u0198\u0199\u0006F\u0000\u0000\u0199\u008e"+
		"\u0001\u0000\u0000\u0000\u019a\u019b\u0005*\u0000\u0000\u019b\u019c\u0005"+
		")\u0000\u0000\u019c\u019d\u0001\u0000\u0000\u0000\u019d\u019e\u0006G\u0002"+
		"\u0000\u019e\u0090\u0001\u0000\u0000\u0000\u019f\u01a5\u0005\"\u0000\u0000"+
		"\u01a0\u01a1\u0005\\\u0000\u0000\u01a1\u01a4\t\u0000\u0000\u0000\u01a2"+
		"\u01a4\b\"\u0000\u0000\u01a3\u01a0\u0001\u0000\u0000\u0000\u01a3\u01a2"+
		"\u0001\u0000\u0000\u0000\u01a4\u01a7\u0001\u0000\u0000\u0000\u01a5\u01a3"+
		"\u0001\u0000\u0000\u0000\u01a5\u01a6\u0001\u0000\u0000\u0000\u01a6\u01a8"+
		"\u0001\u0000\u0000\u0000\u01a7\u01a5\u0001\u0000\u0000\u0000\u01a8\u01a9"+
		"\u0007\u001f\u0000\u0000\u01a9\u01aa\u0006H\u0003\u0000\u01aa\u0092\u0001"+
		"\u0000\u0000\u0000\u01ab\u01b1\u0005\"\u0000\u0000\u01ac\u01ad\u0005\\"+
		"\u0000\u0000\u01ad\u01b0\t\u0000\u0000\u0000\u01ae\u01b0\b\"\u0000\u0000"+
		"\u01af\u01ac\u0001\u0000\u0000\u0000\u01af\u01ae\u0001\u0000\u0000\u0000"+
		"\u01b0\u01b3\u0001\u0000\u0000\u0000\u01b1\u01af\u0001\u0000\u0000\u0000"+
		"\u01b1\u01b2\u0001\u0000\u0000\u0000\u01b2\u01b4\u0001\u0000\u0000\u0000"+
		"\u01b3\u01b1\u0001\u0000\u0000\u0000\u01b4\u01b5\u0005\u0000\u0000\u0001"+
		"\u01b5\u01b6\u0006I\u0004\u0000\u01b6\u0094\u0001\u0000\u0000\u0000\u01b7"+
		"\u01bd\u0005\"\u0000\u0000\u01b8\u01b9\u0005\\\u0000\u0000\u01b9\u01bc"+
		"\t\u0000\u0000\u0000\u01ba\u01bc\b\"\u0000\u0000\u01bb\u01b8\u0001\u0000"+
		"\u0000\u0000\u01bb\u01ba\u0001\u0000\u0000\u0000\u01bc\u01bf\u0001\u0000"+
		"\u0000\u0000\u01bd\u01bb\u0001\u0000\u0000\u0000\u01bd\u01be\u0001\u0000"+
		"\u0000\u0000\u01be\u01c0\u0001\u0000\u0000\u0000\u01bf\u01bd\u0001\u0000"+
		"\u0000\u0000\u01c0\u01c1\u0005\"\u0000\u0000\u01c1\u01c2\u0006J\u0005"+
		"\u0000\u01c2\u0096\u0001\u0000\u0000\u0000\u01c3\u01c4\t\u0000\u0000\u0000"+
		"\u01c4\u01c5\u0006K\u0006\u0000\u01c5\u0098\u0001\u0000\u0000\u0000\u0010"+
		"\u0000\u014c\u0153\u0159\u0160\u0173\u0180\u0182\u0190\u0192\u01a3\u01a5"+
		"\u01af\u01b1\u01bb\u01bd\u0007\u0006\u0000\u0000\u0001E\u0000\u0001G\u0001"+
		"\u0001H\u0002\u0001I\u0003\u0001J\u0004\u0001K\u0005";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}