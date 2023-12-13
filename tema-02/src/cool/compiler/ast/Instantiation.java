package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class Instantiation extends Expression {
    private final String type;

    public Instantiation(ParserRuleContext context, String type) {
        super(context);
        this.type = type;
    }

    @Override
    protected void printTitle() {
        print("new");
    }

    @Override
    protected void printChildren() {
        print(type);
    }
}
