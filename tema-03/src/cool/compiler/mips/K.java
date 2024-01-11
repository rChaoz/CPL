package cool.compiler.mips;

import cool.structures.DefaultScope;
import cool.structures.Scope;

import java.util.List;

class K {
    // Basic

    static final String TAB    = "    ";
    static final String SEP = System.lineSeparator();

    static final String WORD   = "    .word   ";
    static final String ASCIIZ = "    .asciiz ";
    static final String ALIGN  = "    .align  ";

    static void label(StringBuilder builder, String label) {
        builder.append(label).append(':').append(SEP);
    }

    static void word(StringBuilder builder, int value) {
        builder.append(WORD).append(value).append(SEP);
    }

    static void word(StringBuilder builder, String value) {
        builder.append(WORD).append(value).append(SEP);
    }

    static void align(StringBuilder builder, int n) {
        builder.append(ALIGN).append(n).append(SEP);
    }

    // Instructions
    static final String ADD   = "    add     ";
    static final String SUB   = "    sub     ";
    static final String MUL   = "    mul     ";
    static final String DIV   = "    div     ";

    static final String JAL   = "    jal     ";
    static final String J     = "    j       ";
    static final String JR    = "    jr      ";

    static final String LW    = "    lw      ";
    static final String SW    = "    sw      ";
    static final String LI    = "    li      ";
    static final String MOVE  = "    move    ";
    static final String LA    = "    la      ";
    static final String ADDIU = "    addiu   ";

    static final String BEQ   = "    beq     ";
    static final String BNE   = "    bne     ";
    static final String BGT   = "    bgt     ";
    static final String BGE   = "    bge     ";
    static final String BLT   = "    blt     ";
    static final String BLE   = "    ble     ";

    static void jal(StringBuilder builder, String address) {
        builder.append(JAL).append(address).append(SEP);
    }

    static void j(StringBuilder builder, String address) {
        builder.append(J).append(address).append(SEP);
    }

    static void jr(StringBuilder builder, String register) {
        builder.append(JR).append(register).append(SEP);
    }

    static void lw(StringBuilder builder, String register, String address) {
        builder.append(LW).append(register).append(' ').append(address).append(SEP);
    }

    static void sw(StringBuilder builder, String register, String address) {
        builder.append(SW).append(register).append(' ').append(address).append(SEP);
    }

    static void li(StringBuilder builder, String register, int immediate) {
        builder.append(LI).append(register).append(' ').append(immediate).append(SEP);
    }

    static void li(StringBuilder builder, String register, String immediate) {
        builder.append(LI).append(register).append(' ').append(immediate).append(SEP);
    }

    static void move(StringBuilder builder, String target, String source) {
        builder.append(MOVE).append(target).append(' ').append(source).append(SEP);
    }

    static void la(StringBuilder builder, String register, String address) {
        builder.append(LA).append(register).append(' ').append(address).append(SEP);
    }

    enum Condition {
        Equal(BEQ), NotEqual(BNE), Greater(BGT), GreaterEqual(BGE), Less(BLT), LessEqual(BLE);

        private final String instruction;

        Condition(String instruction) {
            this.instruction = instruction;
        }
    }

    static void branch(StringBuilder builder, String register1, Condition condition, String register2, String address) {
        builder.append(condition.instruction).append(' ').append(register1).append(' ').append(register2).append(' ').append(address).append(SEP);
    }

    // Memory management
    static void push(StringBuilder builder, String register) {
        sw(builder, register, "0($sp)");
        builder.append(ADDIU).append(-4).append(SEP);
    }

    static void pop(StringBuilder builder, String register) {
        builder.append(ADDIU).append(4).append(SEP);
        lw(builder, register, "0($sp)");
    }

    static Scope<AddressSymbol> allocScope(StringBuilder builder, Scope<AddressSymbol> scope, List<AddressSymbol> variables) {
        builder.append(ADDIU).append(-4 * variables.size()).append(SEP);
        Scope<AddressSymbol> newScope = new DefaultScope<>(scope);
        for (var v : variables) newScope.add(v);
        return newScope;
    }

    static Scope<AddressSymbol> freeScope(StringBuilder builder, Scope<AddressSymbol> scope) {
        builder.append(ADDIU).append(4 * scope.asCollection().size()).append(SEP);
        return scope.getParent();
    }
}
