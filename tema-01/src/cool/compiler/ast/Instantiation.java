package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public class Instantiation extends Expression {
    private final String type;

    public Instantiation(Token token, String type) {
        super(token);
        this.type = type;
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
