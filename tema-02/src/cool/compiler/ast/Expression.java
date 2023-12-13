package cool.compiler.ast;

import org.antlr.v4.runtime.ParserRuleContext;

public abstract class Expression extends ASTNode {
    protected Expression(ParserRuleContext context) {
        super(context);
    }
}
