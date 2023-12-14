package cool.compiler.ast;

import cool.parser.CoolParser;

public class Instantiation extends Expression {
    private final CoolParser.InstantiationContext context;
    private final String type;

    public Instantiation(CoolParser.InstantiationContext context, String type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public CoolParser.InstantiationContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print("new");
    }

    @Override
    protected void printChildren() {
        print(type);
    }
}
