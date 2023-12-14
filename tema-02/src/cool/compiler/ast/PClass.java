package cool.compiler.ast;

import cool.parser.CoolParser;

import java.util.List;

public class PClass extends ASTNode {
    private final CoolParser.ClassContext context;
    private final String name;
    private final String parent;
    private final List<Feature> features;

    public PClass(CoolParser.ClassContext context, String name, String parent, List<Feature> features) {
        this.context = context;
        this.name = name;
        this.parent = parent;
        this.features = features;
    }

    @Override
    public CoolParser.ClassContext getContext() {
        return context;
    }

    public String getName() {
        return name;
    }

    public String getParent() {
        return parent;
    }

    public List<Feature> getFeatures() {
        return features;
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
