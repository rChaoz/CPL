package cool.compiler.ast;

import cool.parser.CoolParser;

public class Variable extends Expression {
    private final CoolParser.VarContext context;
    private final String id;

    public Variable(CoolParser.VarContext context, String id) {
        this.context = context;
        this.id = id;
    }

    @Override
    public CoolParser.VarContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print(id);
    }

    @Override
    protected void printChildren() {
    }
}
