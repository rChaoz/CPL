package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Let extends Expression {
    private final List<LetLocal> locals;
    private final Expression body;

    public Let(ParserRuleContext context, List<LetLocal> locals, Expression body) {
        super(context);
        this.locals = locals;
        this.body = body;
    }

    @Override
    protected void printTitle() {
        print("let");
    }

    @Override
    protected void printChildren() {
        print(locals);
        print(body);
    }
}
