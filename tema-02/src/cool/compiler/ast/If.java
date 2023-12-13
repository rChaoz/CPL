package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class If extends Expression {
    private final Expression condition, thenBranch, elseBranch;

    public If(ParserRuleContext context, Expression condition, Expression thenBranch, Expression elseBranch) {
        super(context);
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
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
