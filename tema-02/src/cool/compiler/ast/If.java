package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public class If extends Expression {
    private final Expression condition, thenBranch, elseBranch;

    public If(Token token, Expression condition, Expression thenBranch, Expression elseBranch) {
        super(token);
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
