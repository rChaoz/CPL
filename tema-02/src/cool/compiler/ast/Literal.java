package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public class Literal extends Expression {
    public enum Type {
        INTEGER, STRING, BOOLEAN
    }

    private final ParserRuleContext context;
    private final Type type;
    private final String content;

    public Literal(ParserRuleContext context, Type type, String content) {
        this.context = context;
        this.type = type;
        this.content = content;
    }

    @Override
    public ParserRuleContext getContext() {
        return context;
    }

    public Type getType() {
        return type;
    }

    @Override
    protected void printTitle() {
        print(content);
    }

    @Override
    protected void printChildren() {
    }
}
