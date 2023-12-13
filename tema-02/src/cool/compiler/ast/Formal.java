package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class Formal extends ASTNode {
    private final String id, type;

    public Formal(ParserRuleContext context, String id, String type) {
        super(context);
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
