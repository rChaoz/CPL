package cool.compiler.ast;

import cool.parser.CoolParser;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Comparison extends Expression {
    public enum Op {
        LESS("<"), LESS_OR_EQUAL("<="), EQUAL("=");

        private final String symbol;

        Op(String symbol) {
            this.symbol = symbol;
        }

        /**
         * @throws NoSuchElementException If no Op with given symbol exists
         */
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        public static Op findBySymbol(String symbol) {
            return Arrays.stream(values()).filter(op -> op.symbol.equals(symbol)).findFirst().get();
        }
    }

    private final CoolParser.ComparisonContext context;
    private final Op operation;

    private final Expression left, right;

    public Comparison(CoolParser.ComparisonContext context, Op operation, Expression left, Expression right) {
        this.context = context;
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    @Override
    public CoolParser.ComparisonContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print(operation.symbol);
    }

    @Override
    protected void printChildren() {
        print(left);
        print(right);
    }
}
