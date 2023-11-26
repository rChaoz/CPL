package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class Block extends Expression {
    private final List<Expression> expressions;

    public Block(Token token, List<Expression> expressions) {
        super(token);
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
