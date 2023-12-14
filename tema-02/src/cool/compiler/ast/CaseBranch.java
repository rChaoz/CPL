package cool.compiler.ast;

import cool.parser.CoolParser;

public class CaseBranch extends ASTNode {
    private final CoolParser.Case_branchContext context;
    private final String id;
    private final String type;
    private final Expression body;

    public CaseBranch(CoolParser.Case_branchContext context, String id, String type, Expression body) {
        this.context = context;
        this.id = id;
        this.type = type;
        this.body = body;
    }

    @Override
    public CoolParser.Case_branchContext getContext() {
        return context;
    }

    @Override
    protected void printTitle() {
        print("case branch");
    }

    @Override
    protected void printChildren() {
        print(id);
        print(type);
        print(body);
    }
}
