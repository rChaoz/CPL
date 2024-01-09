package cool.compiler.ast;

import cool.parser.CoolParser;

import java.util.ArrayList;
import java.util.List;

public class PClass extends ASTNode {
    private final CoolParser.ClassContext context;
    private final String name;
    private final String parent;
    private final List<Feature> features;
    private final List<Attribute> attributes;
    private final List<Method> methods;

    public PClass(CoolParser.ClassContext context, String name, String parent, List<Feature> features) {
        this.context = context;
        this.name = name;
        this.parent = parent;
        this.features = features;
        this.attributes = new ArrayList<>();
        this.methods = new ArrayList<>();
        for (Feature f : features) {
            if (f instanceof Attribute a) attributes.add(a);
            else if (f instanceof Method m) methods.add(m);
            else throw new RuntimeException("Unknown feature: " + f);
        }
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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public List<Method> getMethods() {
        return methods;
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
