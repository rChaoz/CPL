package cool.compiler.ast.expression;

import cool.compiler.ast.ASTNode;
import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.Scope;
import cool.structures.SymbolTable;
import cool.structures.VariableSymbol;

public class LetLocal extends ASTNode {
    private final CoolParser.LocalContext context;
    private final String id;
    private final String type;
    private final Expression initializer;

    public LetLocal(CoolParser.LocalContext context, String id, String type, Expression initializer) {
        this.context = context;
        this.id = id;
        this.type = type;
        this.initializer = initializer;
    }

    @Override
    public CoolParser.LocalContext getContext() {
        return context;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Expression getInitializer() {
        return initializer;
    }

    public void checkTypes(Scope<VariableSymbol> scope) {

        if (id.equals("self")) {
            SymbolTable.error(this, context.ID().getSymbol(), "Let variable has illegal name self");
            return;
        }

        ClassSymbol declaredType = type.equals("SELF_TYPE") ? scope.getCurrentClass() : SymbolTable.lookupClass(type);

        if (!type.equals("SELF_TYPE")) {
            SymbolTable.error(this, context.TYPE().getSymbol(),
                    "Let variable %s has undefined type %s".formatted(id, type));
            return;
        }

        if (initializer == null) return;

        initializer.checkTypes(scope);

        ClassSymbol expressionType = initializer.getExpressionType(scope);
        if (expressionType != null && !declaredType.isSuperTypeOf(expressionType))
            SymbolTable.error(this, context.TYPE().getSymbol(),
                    "Type %s of initialization expression of identifier %s is incompatible with declared type %s"
                            .formatted(expressionType.getName(), id, declaredType.getName()));
    }

    @Override
    protected void printTitle() {
        print("local");
    }

    @Override
    protected void printChildren() {
        print(id);
        print(type);
        if (initializer != null) print(initializer);
    }
}
