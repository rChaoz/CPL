package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public class CaseBranch extends ASTNode {
    private final String id, type;
    private final Expression body;

    public CaseBranch(Token token, String id, String type, Expression body) {
        super(token);
        this.id = id;
        this.type = type;
        this.body = body;
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
