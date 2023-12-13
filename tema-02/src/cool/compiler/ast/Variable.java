package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public class Variable extends Expression {
    private final String id;

    public Variable(Token token, String id) {
        super(token);
        this.id = id;
    }

    @Override
    protected void printTitle() {
        print(id);
    }

    @Override
    protected void printChildren() {
    }
}
