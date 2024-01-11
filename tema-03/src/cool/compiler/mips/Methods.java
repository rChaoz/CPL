package cool.compiler.mips;

import cool.compiler.ast.Method;
import cool.compiler.ast.expression.Expression;
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

    static String generateMethodBody(Classes.Class cls, Method method) {
        return cls.getName() + "." + method.getId() + ":" + K.SEP +
                methodBodyHead + Expressions.generateBody(cls, method.getBody()) + methodBodyTail;
    }

    public static String generateInitMethodBody(Classes.Class cls) {
        StringBuilder builder = new StringBuilder(cls.getName()).append("_init:").append(K.SEP);
        builder.append(methodBodyHead);

        // Call parent object initializer
        if (cls.getParent() != null) builder.append(K.JAL).append(cls.getParent().getName()).append("_init").append(K.SEP);

        // Initialize all attributes that have an initializer
        for (VariableSymbol attr : cls.getSymbol().getAttributeScope().asCollection()) {
            Expression initializer = attr.getInitializer();
            if (initializer == null) continue;
            builder.append(Expressions.generateBody(cls, initializer));
            // TODO set attribute to $a0
        }

        builder.append(returnSelf).append(methodBodyTail);
        return builder.toString();
    }
}
