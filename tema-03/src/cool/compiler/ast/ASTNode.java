package cool.compiler.ast;

import cool.compiler.mips.Literals;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.List;

public abstract class ASTNode {
    public abstract ParserRuleContext getContext();

    public static String getContentText(ASTNode node) {
        return getContentText(node.getContext());
    }

    public static String getContentText(ParserRuleContext context) {
        if (context.children == null) return "";
        return String.join(" ", context.children.stream().map(child -> {
            if (child instanceof ParserRuleContext) return getContentText((ParserRuleContext) child);
            else return Literals.escape(child.getText());
        }).toList());
    }

    // Printing stuff
    private int indent;

    public void printTree() {
        printTree(0);
    }

    protected void printTree(int indent) {
        this.indent = indent;
        printTitle();
        ++this.indent;
        printChildren();
    }

    protected abstract void printTitle();

    protected abstract void printChildren();

    // Print helpers
    protected <T extends ASTNode> void print(List<T> nodes) {
        for (var node : nodes) node.printTree(indent);
    }

    protected void print(ASTNode node) {
        node.printTree(indent);
    }

    protected void print(String message) {
        for (int i = 0; i < indent; ++i) System.out.print("  ");
        System.out.println(message);
    }
}
