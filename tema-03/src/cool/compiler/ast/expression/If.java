package cool.compiler.ast.expression;

import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.Scope;
import cool.structures.VariableSymbol;

public class If extends Expression {
    private final CoolParser.IfContext context;
    private final Expression condition;
    private final Expression thenBranch;
    private final Expression elseBranch;

    public If(CoolParser.IfContext context, Expression condition, Expression thenBranch, Expression elseBranch) {
        this.context = context;
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public CoolParser.IfContext getContext() {
        return context;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getThenBranch() {
        return thenBranch;
    }

    public Expression getElseBranch() {
        return elseBranch;
    }

    @Override
    protected void printTitle() {
        print("if");
    }

    @Override
    protected void printChildren() {
        print(condition);
        print(thenBranch);
        print(elseBranch);
    }

    @Override
    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        ensureConditionBool(scope, condition, "If", context.cond.start);
        var thenType = thenBranch.getExpressionType(scope);
        var elseType = elseBranch.getExpressionType(scope);
        if (thenType == null || elseType == null) return null;
        return ClassSymbol.joinTypes(thenType, elseType, scope);
    }

    @Override
    public String toString() {
        return "if [ %s ] then [ %s ] else [ %s ]".formatted(getContentText(condition), getContentText(thenBranch), getContentText(elseBranch));
    }
}
