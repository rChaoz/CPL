package cool.compiler.ast.expression;

import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.Scope;
import cool.structures.SymbolTable;
import cool.structures.VariableSymbol;

public class While extends Expression {
    private final CoolParser.WhileContext context;
    private final Expression condition;
    private final Expression body;

    public While(CoolParser.WhileContext context, Expression condition, Expression body) {
        this.context = context;
        this.condition = condition;
        this.body = body;
    }

    @Override
    public CoolParser.WhileContext getContext() {
        return context;
    }

    public Expression getCondition() {
        return condition;
    }

    public Expression getBody() {
        return body;
    }

    @Override
    protected void printTitle() {
        print("while");
    }

    @Override
    protected void printChildren() {
        print(condition);
        print(body);
    }

    @Override
    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        ensureConditionBool(scope, condition, "While", context.cond.start);
        body.getExpressionType(scope);
        return SymbolTable.Object;
    }

    @Override
    public String toString() {
        return "while [ %s ] do [ %s ]".formatted(getContentText(condition), getContentText(body));
    }
}
