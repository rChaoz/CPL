package cool.compiler.ast;

import cool.parser.CoolParser;

import java.util.List;

public class Case extends Expression {
    private final CoolParser.CaseContext context;
    private final Expression target;
    private final List<CaseBranch> branches;

    public Case(CoolParser.CaseContext context, Expression target, List<CaseBranch> branches) {
        this.context = context;
        this.target = target;
        this.branches = branches;
    }

    @Override
    public CoolParser.CaseContext getContext() {
        return context;
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
