package cool.compiler.ast;

import cool.parser.CoolParser;

public class Complement extends Expression {
    private final CoolParser.UnaryContext context;
    private final Expression target;

    public Complement(CoolParser.UnaryContext context, Expression target) {
        this.context = context;
        this.target = target;
    }

    @Override
    public CoolParser.UnaryContext getContext() {
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
