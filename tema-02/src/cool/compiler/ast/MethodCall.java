package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class MethodCall extends Expression {
    private final Expression targetObject;
    private final String targetType, name;
    private final List<Expression> arguments;

    public MethodCall(ParserRuleContext context, Expression targetObject, String targetType, String name, List<Expression> arguments) {
        super(context);
        this.targetObject = targetObject;
        this.targetType = targetType;
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    protected void printTitle() {
        print(targetObject == null ? "implicit dispatch" : ".");
    }

    @Override
    protected void printChildren() {
        if (targetObject != null) {
            print(targetObject);
            if (targetType != null) print(targetType);
        }
        print(name);
        print(arguments);
    }
}
