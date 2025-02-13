package cool.compiler.ast.expression;

import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.Scope;
import cool.structures.VariableSymbol;

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

    public String getName() {
        return name;
    }

    public List<Expression> getArguments() {
        return arguments;
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

    @Override
    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        return MethodCall.checkMethodCall(scope, scope.getCurrentClass(), null, name, arguments, this, context.ID().getSymbol());
    }

    @Override
    public String toString() {
        return "dynamic call self.%s(%d arguments)".formatted(name, arguments.size());
    }
}
