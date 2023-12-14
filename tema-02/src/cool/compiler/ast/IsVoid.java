package cool.compiler.ast;

import cool.parser.CoolParser;

public class IsVoid extends Expression {
    private final CoolParser.IsvoidContext context;
    private final Expression target;

    public IsVoid(CoolParser.IsvoidContext context, Expression target) {
        this.context = context;
        this.target = target;
    }

    @Override
    public CoolParser.IsvoidContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print("isvoid");
    }

    @Override
    protected void printChildren() {
        print(target);
    }
}
