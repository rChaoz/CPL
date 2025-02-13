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

    public void addLiteral(Integer literal) {
        intLiterals.putIfAbsent(literal, intLiterals.size());
    }

    public void addLiteral(String literal) {
        stringLiterals.putIfAbsent(literal, stringLiterals.size());
        var length = Integer.valueOf(literal.length());
        intLiterals.putIfAbsent(length, intLiterals.size());
    }

    public void addClassNames(Classes classes) {
        for (var cls : classes) addLiteral(cls.getName());
    }

    public static String escape(String s) {
        StringBuilder builder = new StringBuilder();

        // MIPS backslash escapes
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
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

        return builder.toString();
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

            K.label(builder, "str_const" + entry.getValue());
            K.word(builder, stringCls.getTag());
            K.word(builder, 4 + (str.length() + 4) / 4);
            K.word(builder, stringCls.getDispTab());
            K.word(builder, "int_const" + intLiterals.get(str.length()));
            builder.append(K.ASCIIZ).append('"').append(escape(str)).append('"').append(K.SEP);
            K.align(builder, 2);
        }

        // Int constants
        for (var entry : intLiterals.entrySet()) {
            Integer i = entry.getKey();

            K.label(builder, "int_const" + entry.getValue());
            K.word(builder, 2);
            K.word(builder, 4);
            K.word(builder, intCls.getDispTab());
            K.word(builder, i);
        }

        // Boolean constants
        for (var entry : booleanLiterals.entrySet()) {
            Boolean b = entry.getKey();

            K.label(builder, "bool_const" + entry.getValue());
            K.word(builder, 4);
            K.word(builder, 4);
            K.word(builder, boolCls.getDispTab());
            K.word(builder, b ? 1 : 0);
        }

        return builder.toString();
    }

    public String getLabel(int literal) {
        return "int_const" + intLiterals.get(literal);
    }

    public String getLabel(String literal) {
        return "str_const" + stringLiterals.get(literal);
    }

    public String getLabel(boolean literal) {
        return "bool_const" + booleanLiterals.get(literal);
    }
}
