package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class Attribute extends Feature {
    private final Expression initializer;

    public Attribute(ParserRuleContext context, String id, String type, Expression initializer) {
        super(context, id, type);
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
