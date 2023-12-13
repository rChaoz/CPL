package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Program extends ASTNode {
    private final List<PClass> classes;

    public Program(ParserRuleContext context, List<PClass> classes) {
        super(context);
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

    public List<PClass> getClasses() {
        return classes;
    }
}
