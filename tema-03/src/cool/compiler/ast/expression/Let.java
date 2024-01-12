package cool.compiler.ast.expression;

import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.DefaultScope;
import cool.structures.Scope;
import cool.structures.VariableSymbol;

import java.util.List;

public class Let extends Expression {
    private final CoolParser.LetContext context;
    private final List<LetLocal> locals;
    private final Expression body;

    public Let(CoolParser.LetContext context, List<LetLocal> locals, Expression body) {
        this.context = context;
        this.locals = locals;
        this.body = body;
    }

    @Override
    public CoolParser.LetContext getContext() {
        return context;
    }

    public List<LetLocal> getLocals() {
        return locals;
    }

    public Expression getBody() {
        return body;
    }

    @Override
    protected void printTitle() {
        print("let");
    }

    @Override
    protected void printChildren() {
        print(locals);
        print(body);
    }

    @Override
    public ClassSymbol checkAndComputeType(Scope<VariableSymbol> scope) {
        var letScope = new DefaultScope<>(scope);
        for (var local : locals) {
            ClassSymbol localType = local.checkAndComputeType(letScope);
            if (local.getId().equals("self")) continue;
            letScope.add(new VariableSymbol(local.getId(), localType, local.getInitializer()));
        }
        return body.getExpressionType(letScope);
    }

    @Override
    public String toString() {
        return "let " + String.join(", ", locals.stream().map(local -> local.getId() + ": " + local.getType()).toList());
    }
}
