package cool.compiler.ast;

import cool.parser.CoolParser;

import java.util.List;

public class Let extends Expression {
    private final CoolParser.LetContext context;
    private final List<LetLocal> locals;
    private final Expression body;

    public Let(CoolParser.LetContext context, List<LetLocal> locals, Expression body) {
        this.context = context;
        this.locals = locals;
        this.body = body;
    }

    @Override
    public CoolParser.LetContext getContext() {
        return context;
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
