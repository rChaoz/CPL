package cool.compiler.ast;

import cool.parser.CoolParser;

import java.util.List;

public class SelfMethodCall extends Expression {
    private final CoolParser.SelfMethodCallContext context;
    private final String name;
    private final List<Expression> arguments;

    public SelfMethodCall(CoolParser.SelfMethodCallContext context, String name, List<Expression> arguments) {
        this.context = context;
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public CoolParser.SelfMethodCallContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print("implicit dispatch");
    }

    @Override
    protected void printChildren() {
        print(name);
        print(arguments);
    }
}
