package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public class Method extends Feature {
    private final List<Formal> formals;
    private final Expression body;

    public Method(ParserRuleContext context, String id, String type, List<Formal> formals, Expression body) {
        super(context, id, type);
        this.formals = formals;
        this.body = body;
    }

    @Override
    protected void printTitle() {
        print("method");
    }

    @Override
    protected void printChildren() {
        print(id);
        print(formals);
        print(type);
        print(body);
    }
}
