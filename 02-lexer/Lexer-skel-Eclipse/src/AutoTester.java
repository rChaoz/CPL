import java.util.*;

import org.antlr.v4.runtime.*;
import org.junit.Assert;
import org.junit.Test;

public class AutoTester {

    @Test
    public void task0() {
        var input = CharStreams.fromString("""
            if 123 x 2.2e-5 /* abc /* xyz */
            efg */ "ab\\"c\"
        """);
        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);

        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();
        List<String> expectedTypes = List.of("'if'", "INT", "ID", "REAL", "STRING", "EOF");

        for(int i = 0; i < expectedTypes.size(); i++) {
            Assert.assertEquals(
                "Testing type of token at position " + i +
                ", found text \"" + tokens.get(i).getText() +
                "\", at line " + tokens.get(i).getLine() +
                " and col " + tokens.get(i).getCharPositionInLine() +
                " in sample",
                expectedTypes.get(i),
                CPLangLexer.VOCABULARY.getDisplayName(tokens.get(i).getType())
            );
        }

        Assert.assertEquals("Testing number of identified tokens", expectedTypes.size(), tokens.size());
    }

    @Test
    public void task1A() {
        var input = CharStreams.fromString("""
            /* Exemplu
               de program /* CPLang */
            */
                x;
                y = x;
                inc     x    x + 1  ;
            x = inc y ;
            print_float mult x  y  ;
                  mult       x        y    x + y *     ;
                 b = x == x;
            print_bool b ;
            x = if b then 5 else 7 fi;
            print_int x ;
        """);

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);

        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();
        List<String> expectedTypes = List.of(
            "ID", "';'",
            "ID", "'='", "ID", "';'",
            "ID", "ID", "ID", "'+'", "INT", "';'",
            "ID", "'='", "ID", "ID", "';'",
            "ID", "ID", "ID", "ID", "';'",
            "ID", "ID", "ID", "ID", "'+'", "ID", "'*'", "';'",
            "ID", "'='", "ID", "'=='", "ID", "';'",
            "ID", "ID", "';'",
            "ID", "'='", "'if'", "ID", "'then'", "INT", "'else'", "INT", "'fi'", "';'",
            "ID", "ID", "';'",
            "EOF"
        );

        for(int i = 0; i < expectedTypes.size(); i++) {
            Assert.assertEquals(
                "Testing type of token at position " + i +
                ", found text \"" + tokens.get(i).getText() +
                "\", at line " + tokens.get(i).getLine() +
                " and col " + tokens.get(i).getCharPositionInLine() +
                " in sample",
                expectedTypes.get(i),
                CPLangLexer.VOCABULARY.getDisplayName(tokens.get(i).getType())
            );
        }

        Assert.assertEquals("Testing number of identified tokens", expectedTypes.size(), tokens.size());
    }

    @Test
    public void task1B() {
        var input = CharStreams.fromString("""
                 negate      input    if input then false else true fi
                 checkRange     max      min      val
                if  val < max  then
                    if  min <= val  then
                        true
                    else
                        false
                    fi
                else
                    false
                fi
        """);

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);

        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();

        List<String> expectedTypes = List.of(
            "ID", "ID", "'if'", "ID", "'then'", "'false'","'else'", "'true'", "'fi'",
            "ID", "ID", "ID", "ID",
            "'if'", "ID", "'<'", "ID", "'then'",
            "'if'", "ID", "'<='", "ID", "'then'",
            "'true'",
            "'else'",
            "'false'",
            "'fi'",
            "'else'",
            "'false'",
            "'fi'",
            "EOF"
        );

        for(int i = 0; i < expectedTypes.size(); i++) {
            Assert.assertEquals(
                "Testing type of token at position " + i +
                ", found text \"" + tokens.get(i).getText() +
                "\", at line " + tokens.get(i).getLine() +
                " and col " + tokens.get(i).getCharPositionInLine() +
                " in sample",
                expectedTypes.get(i),
                CPLangLexer.VOCABULARY.getDisplayName(tokens.get(i).getType())
            );
        }

        Assert.assertEquals("Testing number of identified tokens", expectedTypes.size(), tokens.size());
    }

    @Test
    public void task2A() {
        var input = CharStreams.fromString("""
            /* Exemplu
               de program /* CPLang */
            */
            Int x;
            Int y = x;
            Int inc(Int x) { x + 1 };
            x = inc(y);
            print_float(mult(x, y));  // utilizare mult inainte de definire
            Float mult(Float x, Float y) { x + y *    };
            Bool b = x == x;
            print_bool(b);
            x = if b then 5 else 7 fi;
            print_int(x);
        """);

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);

        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();
        List<String> expectedTypes = List.of(
            "TYPE", "ID", "';'",
            "TYPE", "ID", "'='", "ID", "';'",
            "TYPE", "ID", "'('", "TYPE", "ID", "')'", "'{'", "ID", "'+'", "INT", "'}'", "';'",
            "ID", "'='", "ID", "'('", "ID", "')'", "';'",
            "ID", "'('", "ID", "'('", "ID", "','", "ID", "')'", "')'", "';'",
            "TYPE", "ID", "'('", "TYPE", "ID", "','", "TYPE", "ID", "')'", "'{'", "ID", "'+'", "ID", "'*'", "'}'", "';'",
            "TYPE", "ID", "'='", "ID", "'=='", "ID", "';'",
            "ID", "'('", "ID", "')'", "';'",
            "ID", "'='", "'if'", "ID", "'then'", "INT", "'else'", "INT", "'fi'", "';'",
            "ID", "'('", "ID", "')'", "';'",
            "EOF"
        );

        for(int i = 0; i < expectedTypes.size(); i++) {
            Assert.assertEquals(
                "Testing type of token at position " + i +
                ", found text \"" + tokens.get(i).getText() +
                "\", at line " + tokens.get(i).getLine() +
                " and col " + tokens.get(i).getCharPositionInLine() +
                " in sample",
                expectedTypes.get(i),
                CPLangLexer.VOCABULARY.getDisplayName(tokens.get(i).getType())
            );
        }

        Assert.assertEquals("Testing number of identified tokens", expectedTypes.size(), tokens.size());
    }

    @Test
    public void task2B() {
        var input = CharStreams.fromString("""
            Bool negate(Bool input) { if input then false else true fi};
            Bool checkRange(Int max, Int min, Int val) {
                if (val < max) then
                    if (min <= val) then
                        true
                    else
                        false
                    fi
                else
                    false
                fi
        """);

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);

        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();

        List<String> expectedTypes = List.of(
            "TYPE", "ID", "'('", "TYPE", "ID", "')'", "'{'", "'if'", "ID", "'then'", "'false'","'else'", "'true'", "'fi'", "'}'", "';'",
            "TYPE", "ID", "'('", "TYPE", "ID", "','", "TYPE", "ID", "','", "TYPE", "ID", "')'", "'{'",
            "'if'", "'('", "ID", "'<'", "ID", "')'", "'then'",
            "'if'", "'('", "ID", "'<='", "ID", "')'","'then'",
            "'true'",
            "'else'",
            "'false'",
            "'fi'",
            "'else'",
            "'false'",
            "'fi'",
            "EOF"
        );

        for(int i = 0; i < expectedTypes.size(); i++) {
            Assert.assertEquals(
                "Testing type of token at position " + i +
                ", found text \"" + tokens.get(i).getText() +
                "\", at line " + tokens.get(i).getLine() +
                " and col " + tokens.get(i).getCharPositionInLine() +
                " in sample",
                expectedTypes.get(i),
                CPLangLexer.VOCABULARY.getDisplayName(tokens.get(i).getType())
            );
        }

        Assert.assertEquals("Testing number of identified tokens", expectedTypes.size(), tokens.size());
    }

    @Test
    public void task3A() {
        var input = CharStreams.fromString("""
            /* Exemplu
               de program /* CPLang */
            */
            Int x;
            Int y = x;
            Int inc(Int x) { x + 1 };
            x = inc(y);
            print_float(mult(x, y));  // utilizare mult inainte de definire
            Float mult(Float x, Float y) { x + y * .5 };
            Bool b = x == x;
            print_bool(b);
            x = if b then 5 else 7 fi;
            print_int(x);
        """);

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);

        tokenStream.fill();
        List<Token> tokens = tokenStream.getTokens();
        List<String> expectedTypes = List.of(
            "TYPE", "ID", "';'",
            "TYPE", "ID", "'='", "ID", "';'",
            "TYPE", "ID", "'('", "TYPE", "ID", "')'", "'{'", "ID", "'+'", "INT", "'}'", "';'",
            "ID", "'='", "ID", "'('", "ID", "')'", "';'",
            "ID", "'('", "ID", "'('", "ID", "','", "ID", "')'", "')'", "';'",
            "TYPE", "ID", "'('", "TYPE", "ID", "','", "TYPE", "ID", "')'", "'{'", "ID", "'+'", "ID", "'*'", "REAL", "'}'", "';'",
            "TYPE", "ID", "'='", "ID", "'=='", "ID", "';'",
            "ID", "'('", "ID", "')'", "';'",
            "ID", "'='", "'if'", "ID", "'then'", "INT", "'else'", "INT", "'fi'", "';'",
            "ID", "'('", "ID", "')'", "';'",
            "EOF"
        );

        for(int i = 0; i < expectedTypes.size(); i++) {
            Assert.assertEquals(
                "Testing type of token at position " + i +
                ", found text \"" + tokens.get(i).getText() +
                "\", at line " + tokens.get(i).getLine() +
                " and col " + tokens.get(i).getCharPositionInLine() +
                " in sample",
                expectedTypes.get(i),
                CPLangLexer.VOCABULARY.getDisplayName(tokens.get(i).getType())
            );
        }

        Assert.assertEquals("Testing number of identified tokens", expectedTypes.size(), tokens.size());
    }
}
