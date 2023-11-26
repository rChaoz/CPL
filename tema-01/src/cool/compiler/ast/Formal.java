package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public class Formal extends ASTNode {
    private final String id, type;

    public Formal(Token token, String id, String type) {
        super(token);
        this.id = id;
        this.type = type;
    }

    @Override
    protected void printTitle() {
        print("formal");
    }

    @Override
    protected void printChildren() {
        print(id);
        print(type);
    }
}
