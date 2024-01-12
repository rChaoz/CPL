package cool.compiler.ast.expression;

import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.Scope;
import cool.structures.VariableSymbol;

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

    public List<Expression> getExpressions() {
        return expressions;
    }

    @Override
    protected void printTitle() {
        print("block");
    }

    @Override
    protected void printChildren() {
        print(expressions);
    }

    @Override
    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        for (var expr : expressions) expr.getExpressionType(scope);
        return expressions.get(expressions.size() - 1).getExpressionType(scope);
    }

    @Override
    public String toString() {
        return "block with %d expressions".formatted(expressions.size());
    }
}
