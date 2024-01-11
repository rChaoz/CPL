package cool.compiler.mips;

import cool.compiler.ast.expression.Expression;
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
        // TODO
    }

    private void literal()
}
