parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

tokens { NONE }

@header{
    package cool.parser;
}

program : class+ ;

class : CLASS TYPE (INHERITS TYPE)? LCURLY feature* RCURLY ;

feature
    : ID LPAREN (formal (COMMA formal)*)? RPAREN    # field
    | ID OF_TYPE TYPE (ASSIGN expr)                 # method ;

formal : ID OF_TYPE TYPE ;

none: ;

expr
    : ID ASSIGN                                                                     # varAssign
    | obj=ID (AT type=TYPE) DOT method=ID LPAREN
            (args+=expr (COMMA args+=expr)*)? RPAREN                                # methodCall
    | method=ID LPAREN (args+=expr (COMMA args+=expr)*)? RPAREN                     # selfMethodCall
    | IF cond=expr THEN thenBranch=expr ELSE elseBranch=expr FI                     # if
    | WHILE cond=expr LOOP body=expr POOL                                           # while
    | LCURLY (body+=expr)+ RCURLY                                                   # block
    | LET vars+=ID OF_TYPE types+=TYPE (ASSIGN values+=expr)
            (COMMA ID OF_TYPE TYPE (ASSIGN values+=expr)?)* IN expr                 # let
    | CASE obj=expr OF
            (caseIds+=ID OF_TYPE caseTypes+=TYPE CASE_ARROW caseBodies+=expr)+ ESAC # case
    | NEW TYPE                                                                      # instantiation
    | ISVOID expr                                                                   # isvoid
    | left=expr op=PLUS right=expr                                                  # arithmetic
    | left=expr op=MINUS right=expr                                                 # arithmetic
    | left=expr op=MULTIPLY right=expr                                              # arithmetic
    | left=expr op=DIVIDE right=expr                                                # arithmetic
    | COMPLEMENT expr                                                               # unary
    | left=expr LESS right=expr                                                     # comparison
    | left=expr LESS_EQ right=expr                                                  # comparison
    | left=expr EQ right=expr                                                       # comparison
    | NOT expr                                                                      # negate
    | LPAREN expr RPAREN                                                            # expression
    | ID                                                                            # var
    | INTEGER                                                                       # literalInteger
    | STRING                                                                        # literalString
    | TRUE                                                                          # literalTrue
    | FALSE                                                                         # literalFalse ;

