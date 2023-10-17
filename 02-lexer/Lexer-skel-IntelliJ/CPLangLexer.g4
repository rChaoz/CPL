lexer grammar CPLangLexer;

/* Reguli de funcționare:
 * 
 * * Se ia în considerare cel mai lung lexem recunoscut, indiferent de ordinea
 *   regulilor din specificație (maximal munch).
 * 
 * * Dacă există mai multe cele mai lungi lexeme, se ia în considerare prima
 *   regulă din specificație.
 */

/* Cuvânt cheie.
 */
IF : 'if';

/* Număr întreg.
 * 
 * fragment spune că acea categorie este utilizată doar în interiorul
 * analizorului lexical, nefiind trimisă mai departe analizorului sintactic.
 */
fragment DIGIT : [0-9];
INT : DIGIT+;

/* Identificator.
 */
fragment LETTER: [a-zA-Z];
ID : (LETTER | '_')(LETTER | '_' | DIGIT)*;

/* Număr real.
 */
fragment DIGITS : DIGIT+;
fragment FRACTION : ('.' DIGITS?)?;
fragment EXPONENT : ('e' ('+' | '-')? DIGITS)?;
REAL : DIGITS FRACTION EXPONENT;

/* Șir de caractere.
 * 
 * Poate conține caracterul '"', doar precedat de backslash.
 * . reprezintă orice caracter în afară de EOF.
 * *? este operatorul non-greedy, care încarcă să consume caractere cât timp
 * nu a fost întâlnit caracterul ulterior, '"'.
 * 
 * Acoladele de la final pot conține secvențe arbitrare de cod Java,
 * care vor fi executate la întâlnirea acestui token.
 */
STRING : '"' ('\\"' | .)*? '"'
    { System.out.println("there are no strings in CPLang, but shhh.."); };

BLOCK_COMMENT : '/*' (BLOCK_COMMENT | .)*? '*/' -> skip;

/* Spații albe.
 * 
 * skip spune că nu este creat niciun token pentru lexemul depistat.
 */
WS : [ \n\r\t]+ -> skip;

/* Modalitate alternativă de recunoaștere a șirurilor de caractere, folosind
 * moduri lexicale.
 * 
 * Un mod lexical, precum cel implicit (DEFAULT_MODE) sau IN_STR, de mai jos,
 * reprezintă stări ale analizorului. Când analizorul se află într-un anumit
 * mod, numai regulile din acel mod se pot activa.
 * 
 * Folosim pushMode și popMode pentru intra și ieși din modurile lexicale,
 * în regim de stivă.
 * 
 * more spune că deocamdată nu este construit un token, dar lexemul identificat
 * va face parte, cumulativ, din lexemul recunoscut de următoarea regulă.
 * 
 * De-abia la recunoașterea caracterului '"' de sfârșit de șir de către regula
 * STR, se va construi un token cu categoria STR și întregul conținut al șirului
 * drept lexem.
 */
/*
STR_OPEN : '"' -> pushMode(IN_STR), more;

mode IN_STR;

STR : '"' -> popMode;
CHAR : ('\\"' | ~'"') -> more;  // ~ = complement
*/
