package cool.compiler.ast;

import cool.parser.CoolParser;

public class LetLocal extends ASTNode {
    private final CoolParser.LocalContext context;
    private final String id;
    private final String type;
    private final Expression initializer;

    public LetLocal(CoolParser.LocalContext context, String id, String type, Expression initializer) {
        this.context = context;
        this.id = id;
        this.type = type;
        this.initializer = initializer;
    }

    @Override
    public CoolParser.LocalContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print("local");
    }

    @Override
    protected void printChildren() {
        print(id);
        print(type);
        if (initializer != null) print(initializer);
    }
}
