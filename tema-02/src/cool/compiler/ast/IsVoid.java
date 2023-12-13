package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class IsVoid extends Expression {
    private final Expression target;

    public IsVoid(ParserRuleContext context, Expression target) {
        super(context);
        this.target = target;
    }

    @Override
    protected void printTitle() {
        print("isvoid");
    }

    @Override
    protected void printChildren() {
        print(target);
    }
}
