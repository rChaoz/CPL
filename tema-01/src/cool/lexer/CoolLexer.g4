lexer grammar CoolLexer;

tokens { ERROR } 

@header {
package cool.lexer;
}

@members {
private void raiseError(String msg) {
    setText(msg);
    setType(ERROR);
}
}

// For case insensivity
fragment A:[aA];
fragment B:[bB];
fragment C:[cC];
fragment D:[dD];
fragment E:[eE];
fragment F:[fF];
fragment G:[gG];
fragment H:[hH];
fragment I:[iI];
fragment J:[jJ];
fragment K:[kK];
fragment L:[lL];
fragment M:[mM];
fragment N:[nN];
fragment O:[oO];
fragment P:[pP];
fragment Q:[qQ];
fragment R:[rR];
fragment S:[sS];
fragment T:[tT];
fragment U:[uU];
fragment V:[vV];
fragment W:[wW];
fragment X:[xX];
fragment Y:[yY];
fragment Z:[zZ];

fragment ENDL : '\r'? '\n' ;

// Keywords
CLASS : C L A S S ;
INHERITS : I N H E R I T S ;
NEW : N E W ;

IF : I F ;
THEN : T H E N ;
ELSE : E L S E ;
FI : F I ;

LET : L E T ;
IN : I N ;

CASE : C A S E ;
OF : O F ;
ESAC : E S A C ;

LOOP : L O O P ;
POOL : P O O L ;
WHILE : W H I L E ;

ISVOID : I S V O I D ;
NOT : N O T ;

// Other special characters
COMPLEMENT : '~' ;
PLUS : '+' ;
MINUS : '-' ;
MULTIPLY : '*' ;
DIVIDE : '/' ;

EQ : '=' ;
LESS : '<' ;
LESS_EQ : '<=' ;

LPAREN : '(' ;
RPAREN : ')' ;
LCURLY : '{' ;
RCURLY : '}' ;

OF_TYPE : ':' ;
AT : '@' ;
DOT : '.' ;
COMMA : ',' ;
CASE_ARROW : '=>' ;
ASSIGN : '<-' ;

// Types and IDs
TYPE : [A-Z][a-zA-Z0-9_]* ; // this includes 'SELF_TYPE'
ID : [a-z][a-zA-Z0-9_]* ;   // this includes 'self'

// Whitespace
WS : [ \n\f\r\t]+ -> skip;

// Literals
INTEGER : [0-9]+ ;
TRUE : 't' R U E ;
FALSE : 'f' A L S E ;

// Comments
LINE_COMMENT : '--' [^\r\n]* -> skip;
BLOCK_COMMENT : '(*' -> skip, pushMode(BLOCK_COMMENT_MODE);
UNMATCHED_BLOCK_COMMENT : '*)' { raiseError("Unmatched *)"); } ;

// Strings
START_STRING
    :   '"' -> skip, pushMode(STRING_MODE);

// Unknown character

UNKNOWN_CHARACTER : . { raiseError("Invalid character: " + getText()); };


// For block comments
mode BLOCK_COMMENT_MODE;

BLOCK_COMMENT_CHAR : . -> skip;

END_BLOCK_COMMENT : '*)' -> skip, popMode;

EOF_IN_BLOCK_COMMENT : EOF { raiseError("EOF in comment"); };


// For strings
mode STRING_MODE;

ESC
    : '\\n' { setText("\n"); }
    | '\\t' { setText("\t"); }
    | '\\b' { setText("\b"); }
    | '\\f' { setText("\f"); }
    | '\\' . { setText(getText().substring(1)); };

STRING: (ESC | [^\r\n"])+ {
if (getText().indexOf('\0') != -1) raiseError("String contains null character");
else if (getText().length() > 1024) raiseError("String constant too long");
};

END_STRING : '"' -> skip, popMode;

UNTERMINATED_STRING : ENDL { raiseError("Unterminated string constant"); } ;

EOF_STRING : EOF { raiseError("EOF in string constant"); } ;
