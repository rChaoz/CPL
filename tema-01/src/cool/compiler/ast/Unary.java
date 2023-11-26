package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Unary extends Expression {
    public enum Operation {
        COMPLEMENT("~"), NOT("not");

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
    private final Expression target;

    public Unary(Token token, Operation operation, Expression target) {
        super(token);
        this.operation = operation;
        this.target = target;
    }

    @Override
    protected void printTitle() {
        print(operation.symbol);
    }

    @Override
    protected void printChildren() {
        print(target);
    }
}
