package cool.compiler.mips;

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

    private static final String setSelf$a0 = "    move    $a0 $s0\n";

    private static final String methodBodyTail = """
                lw      $fp 12($sp)
                lw      $s0 8($sp)
                lw      $ra 4($sp)
                addiu   $sp $sp 12
            """;

    public static String generateMethodBody(Classes.Class cls, Classes classes, Literals literals, MethodSymbol method) {
        StringBuilder builder = new StringBuilder(K.SEP);
        K.label(builder, cls.getName() + "." + method.getName());

        builder.append(methodBodyHead);

        // Create method scope
        var methodScope = Expressions.createMethodScope(cls, method.getFormals());
        // Evaluate body
        new Expressions(builder, cls.getName() + "." + method.getName(), classes, literals, methodScope, cls).eval(method.getBody());

        builder.append(methodBodyTail);
        // Pop arguments off the stack and return
        K.pop(builder, method.getFormals().size());
        builder.append(K.ret).append(K.SEP);

        return builder.toString();
    }

    public static String generateInitMethodBody(Classes.Class cls, Classes classes, Literals literals) {
        StringBuilder builder = new StringBuilder(K.SEP);
        K.label(builder, cls.getName() + "_init");
        builder.append(methodBodyHead);

        // Call parent object initializer
        if (cls.getParent() != null) K.jal(builder, cls.getParent().getName() + "_init");

        // Initialize all attributes that have an initializer
        var attributeScope = Expressions.createAttributeScope(cls);
        for (VariableSymbol attr : cls.getSymbol().getAttributeScope().asCollection()) {
            if (attr.getInitializer() == null) continue;
            // Evaluate the attribute body and save its result in attribute
            new Expressions(builder, cls.getName() + "_init_", classes, literals, attributeScope, cls).eval(attr.getInitializer());
            K.sw(builder, "$a0", attributeScope.lookup(attr.getName()).getAddress());
        }

        builder.append(setSelf$a0).append(methodBodyTail).append(K.ret).append(K.SEP);
        return builder.toString();
    }
}
