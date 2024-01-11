package cool.compiler.mips;

import cool.compiler.ast.expression.Expression;
import cool.structures.MethodSymbol;
import cool.structures.VariableSymbol;

public class Methods {
    private static final String methodBodyHead = """
                addiu   $sp $sp -12
                sw      $fp 12($sp)
                sw      $s0 8($sp)
                sw      $ra 4($sp)
                addiu   $fp $sp 4
                move    $s0 $a0
            """;

    private static final String returnSelf = "    move    $a0 $s0\n";

    private static final String methodBodyTail = """
                lw      $fp 12($sp)
                lw      $s0 8($sp)
                lw      $ra 4($sp)
                addiu   $sp $sp 12
                jr      $ra
            """;

    public static String generateMethodBody(Classes.Class cls, Literals literals, MethodSymbol method) {
        StringBuilder builder = new StringBuilder(cls.getName()).append('.').append(method.getName()).append(':').append(K.SEP);

        builder.append(methodBodyHead);
        Expressions.generateMethodBody(builder, cls, literals, method);
        builder.append(methodBodyTail);

        return builder.toString();
    }

    public static String generateInitMethodBody(Classes.Class cls, Literals literals) {
        StringBuilder builder = new StringBuilder(cls.getName()).append("_init:").append(K.SEP);
        builder.append(methodBodyHead);

        // Call parent object initializer
        if (cls.getParent() != null) K.jal(builder, cls.getParent().getName() + "_init");

        // Initialize all attributes that have an initializer
        var attributeScope = Expressions.createAttributeScope(cls);
        for (VariableSymbol attr : cls.getSymbol().getAttributeScope().asCollection()) {
            Expression initializer = attr.getInitializer();
            if (initializer == null) continue;
            Expressions.generateAttributeBody(builder, cls, literals, initializer);
            K.sw(builder, "$a0", attributeScope.lookup(attr.getName()).getAddress());
        }

        builder.append(returnSelf).append(methodBodyTail);
        return builder.toString();
    }
}
