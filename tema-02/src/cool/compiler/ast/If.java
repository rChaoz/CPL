package cool.compiler.ast;

import cool.parser.CoolParser;

public class If extends Expression {
    private final CoolParser.IfContext context;
    private final Expression condition;
    private final Expression thenBranch;
    private final Expression elseBranch;

    public If(CoolParser.IfContext context, Expression condition, Expression thenBranch, Expression elseBranch) {
        this.context = context;
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public CoolParser.IfContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print("if");
    }

    @Override
    protected void printChildren() {
        print(condition);
        print(thenBranch);
        print(elseBranch);
    }
}
