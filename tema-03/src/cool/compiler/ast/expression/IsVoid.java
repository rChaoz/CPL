package cool.compiler.ast.expression;

import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.Scope;
import cool.structures.SymbolTable;
import cool.structures.VariableSymbol;

public class IsVoid extends Expression {
    private final CoolParser.IsvoidContext context;
    private final Expression target;

    public IsVoid(CoolParser.IsvoidContext context, Expression target) {
        this.context = context;
        this.target = target;
    }

    @Override
    public CoolParser.IsvoidContext getContext() {
        return context;
    }

    public Expression getTarget() {
        return target;
    }

    @Override
    protected void printTitle() {
        print("isvoid");
    }

    @Override
    protected void printChildren() {
        print(target);
    }

    @Override
    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        target.getExpressionType(scope);
        return SymbolTable.Bool;
    }

    @Override
    public String toString() {
        return "isvoid " + getContentText(target);
    }
}
