package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Feature extends ASTNode {
    protected final String id, type;

    public Feature(ParserRuleContext context, String id, String type) {
        super(context);
        this.id = id;
        this.type = type;
    }
}
