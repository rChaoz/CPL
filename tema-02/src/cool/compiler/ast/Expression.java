package cool.compiler.ast;

import org.antlr.v4.runtime.Token;

public abstract class Expression extends ASTNode {
    protected Expression(Token token) {
        super(token);
    }
}
