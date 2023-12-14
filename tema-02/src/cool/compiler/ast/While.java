package cool.compiler.ast;

import cool.parser.CoolParser;

public class While extends Expression {
    private final CoolParser.WhileContext context;
    private final Expression condition;
    private final Expression body;

    public While(CoolParser.WhileContext context, Expression condition, Expression body) {
        this.context = context;
        this.condition = condition;
        this.body = body;
    }

    @Override
    public CoolParser.WhileContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print("while");
    }

    @Override
    protected void printChildren() {
        print(condition);
        print(body);
    }
}
