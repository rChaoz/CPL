package cool.compiler.mips;

import cool.compiler.ast.expression.*;
import cool.structures.DefaultScope;
import cool.structures.MethodSymbol;
import cool.structures.Scope;
import cool.structures.VariableSymbol;

import java.util.List;

public class Expressions {
    public static Scope<AddressSymbol> createAttributeScope(Classes.Class cls) {
        var attributeScope = new DefaultScope<AddressSymbol>();
        int index = 0;
        for (var attribute : attributeScope) attributeScope.add(new AddressSymbol(attribute.getName(), index++, AddressSymbol.Type.ATTRIBUTE));
        return attributeScope;
    }

    public static Scope<AddressSymbol> createMethodScope(Classes.Class cls, List<VariableSymbol> formals) {
        // Attribute scope
        var attributeScope = createAttributeScope(cls);

        // Method scope
        var methodScope = new DefaultScope<>(attributeScope);
        int index = 0;
        for (var formal : formals) methodScope.add(new AddressSymbol(formal.getName(), index++, AddressSymbol.Type.FORMAL));

        return methodScope;
    }

    public static void generateAttributeBody(StringBuilder builder, Classes.Class cls, Literals literals, Expression attributeBody) {
        // Create attribute scope
        var attributeScope = createAttributeScope(cls);

        // Generate code
        new Expressions(builder, literals, attributeScope, attributeScope).expression(attributeBody);
    }

    public static void generateMethodBody(StringBuilder builder, Classes.Class cls, Literals literals, MethodSymbol method) {
        // Create attribute & method scopes
        var methodScope = createMethodScope(cls, method.getFormals());
        var attributeScope = methodScope.getParent();

        // Generate code
        new Expressions(builder, literals, attributeScope, methodScope).expression(method.getBody());
    }

    private final StringBuilder builder;
    private final Literals literals;
    private final Scope<AddressSymbol> attributeScope, mainScope;

    public Expressions(StringBuilder builder, Literals literals, Scope<AddressSymbol> attributeScope, Scope<AddressSymbol> mainScope) {
        this.builder = builder;
        this.literals = literals;
        this.attributeScope = attributeScope;
        this.mainScope = mainScope;
    }

    private void expression(Expression expr) {
        if (expr instanceof Arithmetic);
        else if (expr instanceof Assign);
        else if (expr instanceof Block);
        else if (expr instanceof Case);
        else if (expr instanceof Comparison);
        else if (expr instanceof Complement);
        else if (expr instanceof If);
        else if (expr instanceof Instantiation);
        else if (expr instanceof IsVoid);
        else if (expr instanceof Let);
        else if (expr instanceof Literal) literal((Literal) expr);
        else if (expr instanceof MethodCall);
        else if (expr instanceof SelfMethodCall);
        else if (expr instanceof Variable);
        else if (expr instanceof While);
        else throw new RuntimeException("Unknown expression " + expr.getClass().getCanonicalName() + ": " + expr);
    }

    private void literal(Literal literal) {
        K.la(builder, "$a0", switch (literal.getType()) {
            case INTEGER -> literals.getName(Integer.parseInt(literal.getContent()));
            case STRING -> literals.getName(literal.getContent());
            case BOOLEAN -> literals.getName(Boolean.parseBoolean(literal.getContent()));
        });
    }
}
