package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public class Literal extends Expression {
    public enum Type {
        INTEGER, STRING, BOOLEAN
    }

    private final Type type;
    private final String content;

    public Literal(Token token, Type type, String content) {
        super(token);
        this.type = type;
        this.content = content;
    }

    @Override
    protected void printTitle() {
        print(content);
    }

    @Override
    protected void printChildren() {
    }
}
