package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class Case extends Expression {
    private final Expression target;
    private final List<CaseBranch> branches;

    public Case(Token token, Expression target, List<CaseBranch> branches) {
        super(token);
        this.target = target;
        this.branches = branches;
    }

    @Override
    protected void printTitle() {
        print("case");
    }

    @Override
    protected void printChildren() {
        print(target);
        print(branches);
    }
}
