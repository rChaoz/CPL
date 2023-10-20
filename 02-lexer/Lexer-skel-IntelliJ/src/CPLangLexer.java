// Generated from D:/UPB/CPL/Idea/Lexer-skel\CPLangLexer.g4 by ANTLR 4.10.1

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CPLangLexer extends Lexer {
    static {
        RuntimeMetaData.checkVersion("4.10.1", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            IF = 1, INT = 2, ID = 3, REAL = 4, STRING = 5, BLOCK_COMMENT = 6, WS = 7;
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };

    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    private static String[] makeRuleNames() {
        return new String[]{
                "IF", "DIGIT", "INT", "LETTER", "ID", "DIGITS", "FRACTION", "EXPONENT",
                "REAL", "STRING", "BLOCK_COMMENT", "WS"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'if'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, "IF", "INT", "ID", "REAL", "STRING", "BLOCK_COMMENT", "WS"
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
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "CPLangLexer.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getChannelNames() {
        return channelNames;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    @Override
    public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
        switch (ruleIndex) {
            case 9:
                STRING_action((RuleContext) _localctx, actionIndex);
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

    public static final String _serializedATN =
            "\u0004\u0000\u0007i\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001" +
                    "\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004" +
                    "\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007" +
                    "\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b" +
                    "\u0007\u000b\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001" +
                    "\u0001\u0002\u0004\u0002 \b\u0002\u000b\u0002\f\u0002!\u0001\u0003\u0001" +
                    "\u0003\u0001\u0004\u0001\u0004\u0003\u0004(\b\u0004\u0001\u0004\u0001" +
                    "\u0004\u0001\u0004\u0005\u0004-\b\u0004\n\u0004\f\u00040\t\u0004\u0001" +
                    "\u0005\u0004\u00053\b\u0005\u000b\u0005\f\u00054\u0001\u0006\u0001\u0006" +
                    "\u0003\u00069\b\u0006\u0003\u0006;\b\u0006\u0001\u0007\u0001\u0007\u0003" +
                    "\u0007?\b\u0007\u0001\u0007\u0003\u0007B\b\u0007\u0001\b\u0001\b\u0001" +
                    "\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0005\tL\b\t\n\t\f\tO\t\t\u0001" +
                    "\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005\nY\b" +
                    "\n\n\n\f\n\\\t\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0004" +
                    "\u000bd\b\u000b\u000b\u000b\f\u000be\u0001\u000b\u0001\u000b\u0002MZ\u0000" +
                    "\f\u0001\u0001\u0003\u0000\u0005\u0002\u0007\u0000\t\u0003\u000b\u0000" +
                    "\r\u0000\u000f\u0000\u0011\u0004\u0013\u0005\u0015\u0006\u0017\u0007\u0001" +
                    "\u0000\u0004\u0001\u000009\u0002\u0000AZaz\u0002\u0000++--\u0003\u0000" +
                    "\t\n\r\r  r\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000" +
                    "\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000" +
                    "\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000" +
                    "\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0001\u0019\u0001\u0000\u0000" +
                    "\u0000\u0003\u001c\u0001\u0000\u0000\u0000\u0005\u001f\u0001\u0000\u0000" +
                    "\u0000\u0007#\u0001\u0000\u0000\u0000\t\'\u0001\u0000\u0000\u0000\u000b" +
                    "2\u0001\u0000\u0000\u0000\r:\u0001\u0000\u0000\u0000\u000fA\u0001\u0000" +
                    "\u0000\u0000\u0011C\u0001\u0000\u0000\u0000\u0013G\u0001\u0000\u0000\u0000" +
                    "\u0015S\u0001\u0000\u0000\u0000\u0017c\u0001\u0000\u0000\u0000\u0019\u001a" +
                    "\u0005i\u0000\u0000\u001a\u001b\u0005f\u0000\u0000\u001b\u0002\u0001\u0000" +
                    "\u0000\u0000\u001c\u001d\u0007\u0000\u0000\u0000\u001d\u0004\u0001\u0000" +
                    "\u0000\u0000\u001e \u0003\u0003\u0001\u0000\u001f\u001e\u0001\u0000\u0000" +
                    "\u0000 !\u0001\u0000\u0000\u0000!\u001f\u0001\u0000\u0000\u0000!\"\u0001" +
                    "\u0000\u0000\u0000\"\u0006\u0001\u0000\u0000\u0000#$\u0007\u0001\u0000" +
                    "\u0000$\b\u0001\u0000\u0000\u0000%(\u0003\u0007\u0003\u0000&(\u0005_\u0000" +
                    "\u0000\'%\u0001\u0000\u0000\u0000\'&\u0001\u0000\u0000\u0000(.\u0001\u0000" +
                    "\u0000\u0000)-\u0003\u0007\u0003\u0000*-\u0005_\u0000\u0000+-\u0003\u0003" +
                    "\u0001\u0000,)\u0001\u0000\u0000\u0000,*\u0001\u0000\u0000\u0000,+\u0001" +
                    "\u0000\u0000\u0000-0\u0001\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000" +
                    "./\u0001\u0000\u0000\u0000/\n\u0001\u0000\u0000\u00000.\u0001\u0000\u0000" +
                    "\u000013\u0003\u0003\u0001\u000021\u0001\u0000\u0000\u000034\u0001\u0000" +
                    "\u0000\u000042\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u00005\f\u0001" +
                    "\u0000\u0000\u000068\u0005.\u0000\u000079\u0003\u000b\u0005\u000087\u0001" +
                    "\u0000\u0000\u000089\u0001\u0000\u0000\u00009;\u0001\u0000\u0000\u0000" +
                    ":6\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000;\u000e\u0001\u0000" +
                    "\u0000\u0000<>\u0005e\u0000\u0000=?\u0007\u0002\u0000\u0000>=\u0001\u0000" +
                    "\u0000\u0000>?\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@B\u0003" +
                    "\u000b\u0005\u0000A<\u0001\u0000\u0000\u0000AB\u0001\u0000\u0000\u0000" +
                    "B\u0010\u0001\u0000\u0000\u0000CD\u0003\u000b\u0005\u0000DE\u0003\r\u0006" +
                    "\u0000EF\u0003\u000f\u0007\u0000F\u0012\u0001\u0000\u0000\u0000GM\u0005" +
                    "\"\u0000\u0000HI\u0005\\\u0000\u0000IL\u0005\"\u0000\u0000JL\t\u0000\u0000" +
                    "\u0000KH\u0001\u0000\u0000\u0000KJ\u0001\u0000\u0000\u0000LO\u0001\u0000" +
                    "\u0000\u0000MN\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000NP\u0001" +
                    "\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000PQ\u0005\"\u0000\u0000QR\u0006" +
                    "\t\u0000\u0000R\u0014\u0001\u0000\u0000\u0000ST\u0005/\u0000\u0000TU\u0005" +
                    "*\u0000\u0000UZ\u0001\u0000\u0000\u0000VY\u0003\u0015\n\u0000WY\t\u0000" +
                    "\u0000\u0000XV\u0001\u0000\u0000\u0000XW\u0001\u0000\u0000\u0000Y\\\u0001" +
                    "\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000" +
                    "[]\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000]^\u0005*\u0000\u0000" +
                    "^_\u0005/\u0000\u0000_`\u0001\u0000\u0000\u0000`a\u0006\n\u0001\u0000" +
                    "a\u0016\u0001\u0000\u0000\u0000bd\u0007\u0003\u0000\u0000cb\u0001\u0000" +
                    "\u0000\u0000de\u0001\u0000\u0000\u0000ec\u0001\u0000\u0000\u0000ef\u0001" +
                    "\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0006\u000b\u0001\u0000" +
                    "h\u0018\u0001\u0000\u0000\u0000\u000f\u0000!\',.48:>AKMXZe\u0002\u0001" +
                    "\t\u0000\u0006\u0000\u0000";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}