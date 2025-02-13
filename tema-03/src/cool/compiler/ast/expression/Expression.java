package cool.compiler.ast.expression;

import cool.compiler.ast.ASTNode;
import cool.structures.ClassSymbol;
import cool.structures.Scope;
import cool.structures.SymbolTable;
import cool.structures.VariableSymbol;
import org.antlr.v4.runtime.Token;

public abstract class Expression extends ASTNode {
    private boolean typeComputed = false;
    private ClassSymbol expressionType = null;

    protected abstract ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope);

    @Override
    public abstract String toString();

    public ClassSymbol getExpressionType(Scope<VariableSymbol> scope) {
        if (!typeComputed) {
            expressionType = checkAndComputeType(scope);
            typeComputed = true;
        }
        return expressionType;
    }

    protected void ensureOperandInt(Scope<VariableSymbol> scope, Expression operand, String operation, Token token) {
        ClassSymbol type = operand.getExpressionType(scope);
        if (type != null && type != SymbolTable.Int)
            SymbolTable.error(this, token, "Operand of %s has type %s instead of Int".formatted(operation, type.getName()));
    }

    protected void ensureConditionBool(Scope<VariableSymbol> scope, Expression operand, String instruction, Token token) {
        ClassSymbol type = operand.getExpressionType(scope);
        if (type != null && type != SymbolTable.Bool)
            SymbolTable.error(this, token, "%s condition has type %s instead of Bool".formatted(instruction, type.getName()));
    }
}
