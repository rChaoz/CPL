package cool.compiler.mips;

import cool.compiler.ast.expression.Literal;
import cool.structures.SymbolTable;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Literals {
    private final Map<Integer, Integer> intLiterals = new LinkedHashMap<>();
    private final Map<String, Integer> stringLiterals = new LinkedHashMap<>();
    private final Map<Boolean, Integer> booleanLiterals = new LinkedHashMap<>();

    private void addLiteral(Integer literal) {
        intLiterals.putIfAbsent(literal, intLiterals.size());
    }

    private void addLiteral(String literal) {
        stringLiterals.putIfAbsent(literal, stringLiterals.size());
        var length = Integer.valueOf(literal.length());
        intLiterals.putIfAbsent(length, intLiterals.size());
    }

    public Literals(Collection<Literal> literals) {
        // Ensure the basic literals exists
        addLiteral(0);
        addLiteral("");
        booleanLiterals.put(Boolean.FALSE, 0);
        booleanLiterals.put(Boolean.TRUE, 1);

        for (var literal : literals) {
            switch (literal.getType()) {
                case INTEGER -> addLiteral(Integer.valueOf(literal.getContent()));
                case STRING -> addLiteral(literal.getContent());
            }
        }
    }

    public void addClassNames(Classes classes) {
        for (var cls : classes) addLiteral(cls.getName());
    }

    public String generateCode() {
        StringBuilder builder = new StringBuilder();

        // It's fine to use a new blank object as all Classes objects have the same tags for basic classes
        Classes classes = new Classes();
        var stringCls = classes.get(SymbolTable.String);
        var intCls = classes.get(SymbolTable.Int);
        var boolCls = classes.get(SymbolTable.Bool);

        // String constants
        for (var entry : stringLiterals.entrySet()) {
            String str = entry.getKey();

            builder.append("str_const").append(entry.getValue()).append(':').append(K.SEP);
            K.word(builder, stringCls.getTag());
            K.word(builder, 4 + (str.length() + 4) / 4);
            K.word(builder, stringCls.getDispTab());
            K.word(builder, "int_const" + intLiterals.get(str.length()));
            builder.append(K.ASCIIZ).append('"');

            // MIPS backslash escapes
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                switch (c) {
                    case '\n':
                        builder.append("\\n");
                        break;
                    case '\r':
                        builder.append("\\r");
                        break;
                    case '\t':
                        builder.append("\\t");
                        break;
                    case '\b':
                        builder.append("\\b");
                        break;
                    case '\f':
                        builder.append("\\f");
                        break;
                    case '\\':
                        builder.append("\\\\");
                        break;
                    case '"':
                        builder.append("\\\"");
                        break;
                    case '\'':
                        builder.append("\\'");
                        break;
                    default:
                        builder.append(c);
                        break;
                }
            }

            builder.append('"').append(K.SEP);
            K.align(builder, 2);
        }

        // Int constants
        for (var entry : intLiterals.entrySet()) {
            Integer i = entry.getKey();

            builder.append("int_const").append(entry.getValue()).append(':').append(K.SEP);
            K.word(builder, 2);
            K.word(builder, 4);
            K.word(builder, intCls.getDispTab());
            K.word(builder, i);
        }

        // Boolean constants
        for (var entry : booleanLiterals.entrySet()) {
            Boolean b = entry.getKey();

            builder.append("bool_const").append(entry.getValue()).append(':').append(K.SEP);
            K.word(builder, 4);
            K.word(builder, 4);
            K.word(builder, boolCls.getDispTab());
            K.word(builder, b ? 1 : 0);
        }

        return builder.toString();
    }

    public int getIndex(int literal) {
        return intLiterals.get(literal);
    }

    public int getIndex(String literal) {
        return stringLiterals.get(literal);
    }

    public int getIndex(boolean literal) {
        return booleanLiterals.get(literal);
    }
}
