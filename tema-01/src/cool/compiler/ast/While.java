package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public class While extends Expression {
    private final Expression condition, body;

    public While(Token token, Expression condition, Expression body) {
        super(token);
        this.condition = condition;
        this.body = body;
    }

    @Override
    protected void printTitle() {
        print("while");
    }

    @Override
    protected void printChildren() {
        print(condition);
        print(body);
    }
}
