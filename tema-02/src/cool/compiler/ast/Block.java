package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Block extends Expression {
    private final List<Expression> expressions;

    public Block(ParserRuleContext context, List<Expression> expressions) {
        super(context);
        this.expressions = expressions;
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
