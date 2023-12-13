package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Binary extends Expression {
    public enum Operation {
        ADD("+"), SUBTRACT("-"), MULTIPLY("*"), DIVIDE("/"),
        LESS("<"), LESS_OR_EQUAL("<="), EQUAL("=");

        private final String symbol;

        Operation(String symbol) {
            this.symbol = symbol;
        }

        /**
         * @throws NoSuchElementException If no Operation with given symbol exists
         */
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        public static Operation findBySymbol(String symbol) {
            return Arrays.stream(values()).filter(op -> op.symbol.equals(symbol)).findFirst().get();
        }
    }

    private final Operation operation;
    private final Expression left, right;

    public Binary(Token token, Operation operation, Expression left, Expression right) {
        super(token);
        this.operation = operation;
        this.left = left;
        this.right = right;
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
