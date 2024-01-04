package cool.compiler.ast;

public abstract class Feature extends ASTNode {
    protected final String id, type;

    public Feature(String id, String type) {
        this.id = id;
        this.type = type;
    }
}
