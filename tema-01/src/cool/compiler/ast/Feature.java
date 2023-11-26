package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public abstract class Feature extends ASTNode {
    protected final String id, type;

    public Feature(Token token, String id, String type) {
        super(token);
        this.id = id;
        this.type = type;
    }
}
