package cool.compiler.ast.expression;

import cool.compiler.ast.ASTNode;
import cool.parser.CoolParser;
import cool.structures.*;
import org.antlr.v4.runtime.Token;

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

    public Expression getTargetObject() {
        return targetObject;
    }

    public String getTargetType() {
        return targetType;
    }

    public String getName() {
        return name;
    }

    public List<Expression> getArguments() {
        return arguments;
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

    private ClassSymbol targetClassType;

    @Override
    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        ClassSymbol cls = targetObject.getExpressionType(scope);
        if (cls == null) return null;

        // Resolve method
        ClassSymbol targetCls;
        if (targetType != null) {
            // Static dispatch
            if (targetType.equals("SELF_TYPE")) {
                SymbolTable.error(this, context.TYPE().getSymbol(),
                        "Type of static dispatch cannot be SELF_TYPE");
                return null;
            }
            targetClassType = targetCls = SymbolTable.lookupClass(targetType);
            if (targetCls == null) {
                SymbolTable.error(this, context.TYPE().getSymbol(),
                        "Type %s of static dispatch is undefined".formatted(targetType));
                return null;
            } else if (!targetCls.isSuperTypeOf(cls)) {
                SymbolTable.error(this, context.TYPE().getSymbol(),
                        "Type %s of static dispatch is not a superclass of type %s".formatted(targetType, cls.getName()));
                return null;
            }
        } else {
            // Dynamic dispatch
            targetCls = cls == SymbolTable.SelfType ? scope.getCurrentClass() : cls;
        }

        return checkMethodCall(scope, targetCls, cls, name, arguments, this, context.ID().getSymbol());
    }

    public ClassSymbol getTargetClassType() {
        return targetClassType;
    }

    static ClassSymbol checkMethodCall(Scope<VariableSymbol> scope, ClassSymbol methodClass, ClassSymbol objectClass,
                                       String name, List<Expression> arguments, ASTNode node, Token token) {
        for (var arg : arguments) arg.getExpressionType(scope);

        MethodSymbol method = methodClass.getMethodScope().lookup(name);
        if (method == null) {
            SymbolTable.error(node, token, "Undefined method %s in class %s".formatted(name, methodClass.getName()));
            return null;
        }
        if (method.getFormals().size() != arguments.size())
            SymbolTable.error(node, token, "Method %s of class %s is applied to wrong number of arguments".formatted(name, methodClass.getName()));
        // Check types of all formals
        for (int i = 0; i < arguments.size(); ++i) {
            var arg = arguments.get(i);
            var formal = method.getFormals().get(i);
            ClassSymbol argType = arg.getExpressionType(scope);
            ClassSymbol formalType = formal.getType();
            if (argType == null || formalType == null) continue;
            if (!argType.canBeAssignedTo(formalType, scope.getCurrentClass()))
                SymbolTable.error(node, arg.getContext().start,
                        "In call to method %s of class %s, actual type %s of formal parameter %s is incompatible with declared type %s"
                                .formatted(name, methodClass.getName(), argType.getName(), formal.getName(), formalType.getName()));
        }
        if (objectClass == null) return method.getReturnType();
        else return method.getReturnType(objectClass);
    }

    @Override
    public String toString() {
        if (targetType == null) return "dynamic call [ %s ].%s(%d arguments)".formatted(getContentText(targetObject), name, arguments.size());
        else return "static call @%s [ %s ].%s(%d arguments)".formatted(targetType, getContentText(targetObject), name, arguments.size());
    }
}
