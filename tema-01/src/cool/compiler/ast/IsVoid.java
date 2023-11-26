package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public class IsVoid extends Expression {
    private final Expression target;

    public IsVoid(Token token, Expression target) {
        super(token);
        this.target = target;
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
