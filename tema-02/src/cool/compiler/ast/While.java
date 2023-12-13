package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class While extends Expression {
    private final Expression condition, body;

    public While(ParserRuleContext context, Expression condition, Expression body) {
        super(context);
        this.condition = condition;
        this.body = body;
    }

    @Override
    protected void printTitle() {
        print("while");
    }

    @Override
    protected void printChildren() {
        print(condition);
        print(body);
    }
}
