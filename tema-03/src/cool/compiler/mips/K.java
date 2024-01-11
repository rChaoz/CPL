package cool.compiler.mips;

class K {
    // Basic

    static final String TAB    = "    ";
    static final String SEP = System.lineSeparator();

    static final String WORD   = "    .word   ";
    static final String ASCIIZ = "    .asciiz ";
    static final String ALIGN  = "    .align  ";

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
    static final String JAL = "    jal     ";
    static final String LW  = "    lw      ";
    static final String SW  = "    sw      ";
    static final String LA  = "    la      ";

    static void jal(StringBuilder builder, String address) {
        builder.append(JAL).append(address).append(SEP);
    }

    static void lw(StringBuilder builder, String register, String address) {
        builder.append(LW).append(register).append(' ').append(address).append(SEP);
    }

    static void sw(StringBuilder builder, String register, String address) {
        builder.append(SW).append(register).append(' ').append(address).append(SEP);
    }

    static void la(StringBuilder builder, String register, String address) {
        builder.append(LA).append(register).append(' ').append(address).append(SEP);
    }
}
