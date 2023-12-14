package cool.compiler.ast;

import cool.parser.CoolParser;

public class Assign extends Expression {
    private final CoolParser.VarAssignContext context;
    private final String id;
    private final Expression expression;

    public Assign(CoolParser.VarAssignContext context, String id, Expression expression) {
        this.context = context;
        this.id = id;
        this.expression = expression;
    }

    @Override
    public CoolParser.VarAssignContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print("<-");
    }

    @Override
    protected void printChildren() {
        print(id);
        print(expression);
    }
}
