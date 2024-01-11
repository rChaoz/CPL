package cool.compiler.mips;

import cool.structures.ClassSymbol;
import cool.structures.MethodSymbol;
import cool.structures.SymbolTable;
import cool.structures.VariableSymbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Classes implements Iterable<Classes.Class> {
    public static class Class implements Comparable<Class> {
        private final int tag;
        private final ClassSymbol symbol;
        private final Class parent;
        private final String dispTab;

        public Class(int tag, ClassSymbol symbol, Class parent) {
            this.tag = tag;
            this.symbol = symbol;
            this.parent = parent;
            this.dispTab = symbol.getName() + "_dispTab";
        }

        public String getName() {
            return symbol.getName();
        }

        public int getTag() {
            return tag;
        }

        public String getDispTab() {
            return dispTab;
        }

        public Class getParent() {
            return parent;
        }

        public ClassSymbol getSymbol() {
            return symbol;
        }

        @Override
        public int compareTo(Class o) {
            return Integer.compare(tag, o.tag);
        }

        public String generateProtObject(Literals literals) {
            String defaultString = "string_const" + literals.getIndex("");
            String defaultInt = "int_const" + literals.getIndex(0);
            String defaultBool = "bool_const" + literals.getIndex(false);

            // Deal with basic classes as our general code can't handle the special attributes of these classes
            // (ASCII, int32)
            if (symbol == SymbolTable.String) return "String_protObj:\n" +
                    "    .word   " + tag + "\n" +
                    "    .word   5\n" +
                    "    .word   String_dispTab\n" +
                    "    .word   int_const" + literals.getIndex(0) + "\n" +
                    "    .asciiz \"\"\n" +
                    "    .align 2\n";
            else if (symbol == SymbolTable.Int) return "Int_protObj:\n" +
                    "    .word   " + tag + "\n" +
                    "    .word   4\n" +
                    "    .word   Int_dispTab\n" +
                    "    .word   0\n";
            else if (symbol == SymbolTable.Bool) return "Bool_protObj:\n" +
                    "    .word   " + tag + "\n" +
                    "    .word   4\n" +
                    "    .word   Bool_dispTab\n" +
                    "    .word   0\n";

            // Calculate size - 3 words for header + number of attributes
            int size = 3 + symbol.getAttributeScope().asCollection().size();

            StringBuilder builder = new StringBuilder(getName()).append("_protObj:").append(K.SEP);
            K.word(builder, tag);
            K.word(builder, size);
            K.word(builder, getName() + "_dispTab");

            for (VariableSymbol attribute : symbol.getAttributeScope()) {
                var type = attribute.getType();
                if (type == SymbolTable.String) K.word(builder, defaultString);
                else if (type == SymbolTable.Int) K.word(builder, defaultInt);
                else if (type == SymbolTable.Bool) K.word(builder, defaultBool);
                else K.word(builder, 0);
            }

            return builder.toString();
        }

        public String generateDispTab() {
            StringBuilder builder = new StringBuilder(getName()).append("_dispTab:").append(K.SEP);

            for (MethodSymbol method : symbol.getMethodScope())
                K.word(builder, method.getOwnerClass().getName() + "." + method.getName());

            return builder.toString();
        }
    }

    private int tag = 0;

    public Classes() {
        defineClass(SymbolTable.Object);
        defineClass(SymbolTable.IO);
        defineClass(SymbolTable.Int);
        defineClass(SymbolTable.String);
        defineClass(SymbolTable.Bool);
    }

    private final Map<ClassSymbol, Class> classes = new HashMap<>();

    public String generateBasicTags() {
        return "_int_tag:" + K.SEP +
                K.WORD + get(SymbolTable.Int).tag + K.SEP +
                "_string_tag:" + K.SEP +
                K.WORD + get(SymbolTable.String).tag + K.SEP +
                "_bool_tag:" + K.SEP +
                K.WORD + get(SymbolTable.Bool).tag + K.SEP;
    }

    public Class defineClass(ClassSymbol cls) {
        if (classes.containsKey(cls)) return classes.get(cls);
        Class parent = cls.getParent() == null ? null : defineClass(cls.getParent());
        Class c = new Class(tag++, cls, parent);
        classes.put(cls, c);
        return c;
    }

    public Class get(ClassSymbol cls) {
        return classes.get(cls);
    }

    @Override
    public Iterator<Class> iterator() {
        return classes.values().iterator();
    }

    public String generateCode(Literals literals) {
        StringBuilder nameTab = new StringBuilder("class_nameTab:" + K.SEP);
        StringBuilder objTab = new StringBuilder("class_objTab:" + K.SEP);
        StringBuilder protObjects = new StringBuilder();
        StringBuilder dispTabs = new StringBuilder();

        var sorted = new ArrayList<>(classes.values());
        sorted.sort(null);

        for (var cls : sorted) {
            K.word(nameTab, "strConst" + literals.getIndex(cls.getName()));
            K.word(objTab, cls.getName() + "_protObj");
            K.word(objTab, cls.getName() + "_init");
            protObjects.append(cls.generateProtObject(literals));
            dispTabs.append(cls.generateDispTab());
        }

        //noinspection UnnecessaryToStringCall
        return nameTab.toString() + objTab.toString() + protObjects.toString() + dispTabs.toString();
    }
}
