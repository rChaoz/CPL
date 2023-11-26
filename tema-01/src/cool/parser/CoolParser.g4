parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

tokens { NONE }

@header{
    package cool.parser;
}

program : class+ EOF ;

class : CLASS name=TYPE (INHERITS parent=TYPE)? LCURLY feature* RCURLY SEMICOLON;

feature
    : ID LPAREN (formal (COMMA formal)*)? RPAREN OF_TYPE TYPE
            LCURLY expr RCURLY SEMICOLON                        # method
    | ID OF_TYPE TYPE (ASSIGN expr)? SEMICOLON                  # attribute ;

formal : ID OF_TYPE TYPE ;

expr
    : ID ASSIGN expr                                                                # varAssign
    | obj=expr (AT type=TYPE)? DOT method=ID LPAREN
            (args+=expr (COMMA args+=expr)*)? RPAREN                                # methodCall
    | method=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN                     # selfMethodCall
    | IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI                     # if
    | WHILE cond=expr LOOP body=expr POOL                                           # while
    | LCURLY (body+=expr SEMICOLON)+ RCURLY                                         # block
    | LET vars+=ID OF_TYPE types+=TYPE (ASSIGN values+=expr)
            (COMMA ID OF_TYPE TYPE (ASSIGN values+=expr)?)* IN expr                 # let
    | CASE obj=expr OF (caseIds+=ID OF_TYPE caseTypes+=TYPE
            CASE_ARROW caseBodies+=expr SEMICOLON)+ ESAC                            # case
    | NEW TYPE                                                                      # instantiation
    | ISVOID expr                                                                   # isvoid
    | left=expr op=PLUS right=expr                                                  # arithmetic
    | left=expr op=MINUS right=expr                                                 # arithmetic
    | left=expr op=MULTIPLY right=expr                                              # arithmetic
    | left=expr op=DIVIDE right=expr                                                # arithmetic
    | op=COMPLEMENT expr                                                            # unary
    | left=expr op=LESS right=expr                                                  # comparison
    | left=expr op=LESS_EQ right=expr                                               # comparison
    | left=expr op=EQ right=expr                                                    # comparison
    | NOT expr                                                                      # negate
    | LPAREN expr RPAREN                                                            # expression
    | ID                                                                            # var
    | INTEGER                                                                       # literalInteger
    | STRING                                                                        # literalString
    | TRUE                                                                          # literalTrue
    | FALSE                                                                         # literalFalse ;

