package cool.compiler.ast;

import cool.parser.CoolParser;

import java.util.List;

public class MethodCall extends Expression {
    private final CoolParser.MethodCallContext context;
    private final Expression targetObject;
    private final String targetType, name;
    private final List<Expression> arguments;

    public MethodCall(CoolParser.MethodCallContext context, Expression targetObject, String targetType, String name, List<Expression> arguments) {
        this.context = context;
        this.targetObject = targetObject;
        this.targetType = targetType;
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public CoolParser.MethodCallContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print(".");
    }

    @Override
    protected void printChildren() {
        print(targetObject);
        if (targetType != null) print(targetType);
        print(name);
        print(arguments);
    }
}
