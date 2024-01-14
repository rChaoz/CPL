package cool.compiler.ast.expression;

import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.Scope;
import cool.structures.VariableSymbol;

import java.util.ArrayList;
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

    public Expression getTarget() {
        return target;
    }

    public List<CaseBranch> getBranches() {
        return branches;
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

    @Override
    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        target.getExpressionType(scope);
        var branchTypes = new ArrayList<ClassSymbol>(branches.size());
        for (var branch : branches) {
            var branchType = branch.checkAndComputeType(scope);
            if (branchType != null) branchTypes.add(branchType);
        }
        return ClassSymbol.joinTypes(branchTypes, scope);
    }

    @Override
    public String toString() {
        return "case [ %s ]".formatted(getContentText(target));
    }
}
