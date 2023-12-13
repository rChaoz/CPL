package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Case extends Expression {
    private final Expression target;
    private final List<CaseBranch> branches;

    public Case(ParserRuleContext context, Expression target, List<CaseBranch> branches) {
        super(context);
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
