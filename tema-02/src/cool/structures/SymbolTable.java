package cool.structures;

import cool.compiler.Compiler;
import cool.compiler.ast.ASTNode;
import cool.parser.CoolParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.io.File;

public class SymbolTable {
    private static final Scope<ClassSymbol> globals = new DefaultScope<>(null);

    private static boolean semanticErrors;
    static final ClassSymbol objectSymbol = new ClassSymbol(null);

    public static void defineBasicClasses() {
        globals.add(objectSymbol);
        semanticErrors = false;

        // Populate global scope

        ClassSymbol ioSymbol = new ClassSymbol("IO");
        ClassSymbol intSymbol = new ClassSymbol("Int");
        ClassSymbol stringSymbol = new ClassSymbol("String");
        ClassSymbol boolSymbol = new ClassSymbol("Bool");
        globals.add(ioSymbol);
        globals.add(intSymbol);
        globals.add(stringSymbol);
        globals.add(boolSymbol);

        // Object: Define methods
        var methods = objectSymbol.getMethodScope();
        methods.add(new MethodSymbol("abort", objectSymbol));
        methods.add(new MethodSymbol("type_name", stringSymbol));
        methods.add(new MethodSymbol("copy", null));

        // IO: Define methods
        methods = ioSymbol.getMethodScope();
        methods.add(new MethodSymbol("out_string", null, new VariableSymbol("x", stringSymbol)));
        methods.add(new MethodSymbol("out_int", null, new VariableSymbol("x", intSymbol)));
        methods.add(new MethodSymbol("in_string", stringSymbol));
        methods.add(new MethodSymbol("in_int", intSymbol));

        // String: Define methods
        methods = stringSymbol.getMethodScope();
        methods.add(new MethodSymbol("length", intSymbol));
        methods.add(new MethodSymbol("concat", stringSymbol, new VariableSymbol("s", stringSymbol)));
        methods.add(new MethodSymbol("substr", stringSymbol, new VariableSymbol("i", intSymbol), new VariableSymbol("l", intSymbol)));
    }

    public static boolean defineClass(String name) {
        return globals.add(new ClassSymbol(name));
    }

    public static ClassSymbol lookupClass(String name) {
        return globals.lookup(name);
    }

    /**
     * Displays a semantic error message.
     *
     * @param node Used to determine the enclosing class context of this error,
     *             which knows the file name in which the class was defined,
     *             as well as for line and column information.
     * @param str  The error message.
     */
    public static void error(ASTNode node, String str) {
        ParserRuleContext ctx = node.getContext();
        Token info = ctx.start;
        while (!(ctx.getParent() instanceof CoolParser.ProgramContext))
            ctx = ctx.getParent();

        String message = "\"" + new File(Compiler.fileNames.get(ctx)).getName()
                + "\", line " + info.getLine()
                + ":" + (info.getCharPositionInLine() + 1)
                + ", Semantic error: " + str;

        System.err.println(message);

        semanticErrors = true;
    }

    public static void error(String str) {
        String message = "Semantic error: " + str;

        System.err.println(message);

        semanticErrors = true;
    }

    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }
}
