package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class Assign extends Expression {
    private final String id;
    private final Expression expression;

    public Assign(ParserRuleContext context, String id, Expression expression) {
        super(context);
        this.id = id;
        this.expression = expression;
    }

    @Override
    protected void printTitle() {
        print("<-");
    }

    @Override
    protected void printChildren() {
        print(id);
        print(expression);
    }
}
