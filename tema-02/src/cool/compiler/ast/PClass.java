package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class PClass extends ASTNode {
    private final String name, parent;
    private final List<Feature> features;

    public PClass(Token token, String name, String parent, List<Feature> features) {
        super(token);
        this.name = name;
        this.parent = parent;
        this.features = features;
    }

    @Override
    protected void printTitle() {
        print("class");
    }

    @Override
    protected void printChildren() {
        print(name);
        if (parent != null) print(parent);
        print(features);
    }
}
