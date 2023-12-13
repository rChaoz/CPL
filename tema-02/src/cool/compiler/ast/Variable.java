package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class Variable extends Expression {
    private final String id;

    public Variable(ParserRuleContext context, String id) {
        super(context);
        this.id = id;
    }

    @Override
    protected void printTitle() {
        print(id);
    }

    @Override
    protected void printChildren() {
    }
}
