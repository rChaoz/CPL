package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

import java.util.List;

public class Method extends Feature {
    private final List<Formal> formals;
    private final Expression body;

    public Method(Token token, String id, String type, List<Formal> formals, Expression body) {
        super(token, id, type);
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
