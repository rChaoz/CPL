package cool.compiler.ast;

import cool.parser.CoolParser;

public class Negate extends Expression {
    private final CoolParser.NegateContext context;
    private final Expression target;

    public Negate(CoolParser.NegateContext context, Expression target) {
        this.context = context;
        this.target = target;
    }

    @Override
    public CoolParser.NegateContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print("~");
    }

    @Override
    protected void printChildren() {
        print(target);
    }
}
