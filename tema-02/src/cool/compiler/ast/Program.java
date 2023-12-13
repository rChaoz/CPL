package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class Program extends ASTNode {
    private final List<PClass> classes;

    public Program(Token token, List<PClass> classes) {
        super(token);
        this.classes = classes;
    }

    @Override
    protected void printTitle() {
        print("program");
    }

    @Override
    protected void printChildren() {
        print(classes);
    }
}
