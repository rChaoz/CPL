package cool.compiler.ast;

import cool.parser.CoolParser;

import java.util.List;

public class Block extends Expression {
    private final CoolParser.BlockContext context;
    private final List<Expression> expressions;

    public Block(CoolParser.BlockContext context, List<Expression> expressions) {
        this.context = context;
        this.expressions = expressions;
    }

    @Override
    public CoolParser.BlockContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print("block");
    }

    @Override
    protected void printChildren() {
        print(expressions);
    }
}
