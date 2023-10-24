import java.util.*;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;

import org.antlr.v4.runtime.*;

public class AutoTester {
    @org.junit.Test
    public void task2A() {
        var input = CharStreams.fromString("""
            Int inc(Int x) { x + 1 };
            Float mult(Float x, Float y) { x + y * .5 };
            Float avg(Int x, Int y, Int z, Int w) { (x + y + z + w) / 4 };
        """);

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);
        var parser = new CPLangParser(tokenStream);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tree = parser.prog();
        var listener = Test.task12setup();
        var walker = new ParseTreeWalker();
        walker.walk(listener, tree);
        Assert.assertEquals("Test cu definitii de functii", 3, Test.funcDefs);
    }

    @org.junit.Test
    public void task2B() {
        var input = CharStreams.fromString("""
            Int x;
            Int y = x;
            Bool b = x == x;
            Int z = -x + 1;
            Int w = (7 + 2) * 3 / x;
            Bool var = 2 < y + 1 == false;
        """);

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);
        var parser = new CPLangParser(tokenStream);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tree = parser.prog();
        var listener = Test.task12setup();
        var walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        Assert.assertEquals("Test cu variabile globale",6, Test.varDefs);
    }

    @org.junit.Test
    public void task2C() {
        var input = CharStreams.fromString("""
            Int inc(Int x) { x + 1 };
            Float mult(Float x, Float y) { x + y * .5 };
            Float avg(Int x, Int y, Int z, Int w) { (x + y + z + w) / 4 };
        """);

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);
        var parser = new CPLangParser(tokenStream);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tree = parser.prog();
        var listener = Test.task12setup();
        var walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        Assert.assertEquals("Test cu parametri formali", 7, Test.varDefs);
    }

    @org.junit.Test
    public void task2D() {
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
            Int z = -x + 1;
            Int w = (7 + 2) * 3 / x;
            Bool var = 2 < y + 1 == false;
            print_bool(var);
            Float avg(Int x, Int y, Int z, Int w) { (x + y + z + w) / 4 };
            print_float(avg(x, y, z, w));
        """);

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);
        var parser = new CPLangParser(tokenStream);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tree = parser.prog();
        var listener = Test.task12setup();
        var walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        Assert.assertEquals(13, Test.varDefs);
        Assert.assertEquals(3, Test.funcDefs);
    }

    @org.junit.Test
    public void task3A() {
        var input = CharStreams.fromString("""
3 + 6 + 2;
3 * 2;
""");

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);
        var parser = new CPLangParser(tokenStream);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var visitor = Test.task3setup();
        var tree = parser.prog();
        visitor.visit(tree);

        List<Integer> expectedVals = List.of(11, 6);

        Assert.assertEquals("Testing number of identified expressions", expectedVals.size(), Test.task3list.size());
        for(int i = 0; i < expectedVals.size(); i++) {
            Assert.assertEquals(
                    "Expression number " + i +
                            ", expected result of " + expectedVals.get(i) +
                            " but found " + Test.task3list.get(i),
                    expectedVals.get(i),
                    Test.task3list.get(i)
            );
        }
    }

    @org.junit.Test
    public void task3B() {
        var input = CharStreams.fromString("""
3 - 1 + 14;
""");

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);
        var parser = new CPLangParser(tokenStream);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var visitor = Test.task3setup();
        var tree = parser.prog();
        visitor.visit(tree);

        List<Integer> expectedVals = List.of(16);

        Assert.assertEquals("Testing number of identified expressions", expectedVals.size(), Test.task3list.size());
        for(int i = 0; i < expectedVals.size(); i++) {
            Assert.assertEquals(
                    "Expression number " + i +
                            ", expected result of " + expectedVals.get(i) +
                            " but found " + Test.task3list.get(i),
                    expectedVals.get(i),
                    Test.task3list.get(i)
            );
        }
    }

    @org.junit.Test
    public void task3C() {
        var input = CharStreams.fromString("""
3 + 6 * 2;
3 * 6 + 2;
3 + 3 * 2 + 20;
3 - 3 / 3;
""");

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);
        var parser = new CPLangParser(tokenStream);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var visitor = Test.task3setup();
        var tree = parser.prog();
        visitor.visit(tree);

        List<Integer> expectedVals = List.of(15, 20, 29, 2);

        Assert.assertEquals("Testing number of identified expressions", expectedVals.size(), Test.task3list.size());
        for(int i = 0; i < expectedVals.size(); i++) {
            Assert.assertEquals(
                    "Expression number " + i +
                            ", expected result of " + expectedVals.get(i) +
                            " but found " + Test.task3list.get(i),
                    expectedVals.get(i),
                    Test.task3list.get(i)
            );
        }
    }

    @org.junit.Test
    public void task3D() {
        var input = CharStreams.fromString("""
(20 + 30) * 40 / 20;
(2 + 2 / 2);
1 + 2 * (3 + 4) / (6 - 5);
1 + 13 * (15 + 5) / (20 - 16);
""");

        var lexer = new CPLangLexer(input);
        lexer.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var tokenStream = new CommonTokenStream(lexer);
        var parser = new CPLangParser(tokenStream);
        parser.removeErrorListener(ConsoleErrorListener.INSTANCE);
        var visitor = Test.task3setup();
        var tree = parser.prog();
        visitor.visit(tree);

        List<Integer> expectedVals = List.of(100, 3, 15, 66);

        Assert.assertEquals("Testing number of identified expressions", expectedVals.size(), Test.task3list.size());
        for(int i = 0; i < expectedVals.size(); i++) {
            Assert.assertEquals(
                    "Expression number " + i +
                            ", expected result of " + expectedVals.get(i) +
                            " but found " + Test.task3list.get(i),
                    expectedVals.get(i),
                    Test.task3list.get(i)
            );
        }
    }
}
