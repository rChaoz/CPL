package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public class LetLocal extends ASTNode {
    private final String id, type;
    private final Expression initializer;

    public LetLocal(Token token, String id, String type, Expression initializer) {
        super(token);
        this.id = id;
        this.type = type;
        this.initializer = initializer;
    }

    @Override
    protected void printTitle() {
        print("local");
    }

    @Override
    protected void printChildren() {
        print(id);
        print(type);
        if (initializer != null) print(initializer);
    }
}
