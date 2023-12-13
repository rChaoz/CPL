package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public class Attribute extends Feature {
    private final Expression initializer;

    public Attribute(Token token, String id, String type, Expression initializer) {
        super(token, id, type);
        this.initializer = initializer;
    }

    @Override
    protected void printTitle() {
        print("attribute");
    }

    @Override
    protected void printChildren() {
        print(id);
        print(type);
        if (initializer != null) print(initializer);
    }
}
