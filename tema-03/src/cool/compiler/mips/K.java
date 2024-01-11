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
}
