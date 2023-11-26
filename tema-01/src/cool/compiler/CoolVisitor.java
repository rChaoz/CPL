package cool.compiler;

import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;

public class CoolVisitor extends CoolParserBaseVisitor<Void> {
    // Helper code to print with indents
    int indent = 0;

    private void print(Object any) {
        for (int i = 0; i < indent; ++i) System.out.print("  ");
        System.out.println(any);
    }

    // Program structure: classes, methods, attributes

    @Override
    public Void visitProgram(CoolParser.ProgramContext ctx) {
        print("program");
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitClass(CoolParser.ClassContext ctx) {
        print("class");
        ++indent;
        print(ctx.name.getText());
        if (ctx.parent != null) print(ctx.parent.getText());
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitMethod(CoolParser.MethodContext ctx) {
        print("method");
        ++indent;
        print(ctx.ID().getText());
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitFormal(CoolParser.FormalContext ctx) {
        print("formal");
        ++indent;
        print(ctx.ID().getText());
        print(ctx.TYPE().getText());
        --indent;
        return null;
    }

    @Override
    public Void visitAttribute(CoolParser.AttributeContext ctx) {
        print("attribute");
        ++indent;
        print(ctx.ID().getText());
        print(ctx.TYPE().getText());
        visitChildren(ctx);
        --indent;
        return null;
    }

    // Complex expressions

    @Override
    public Void visitIf(CoolParser.IfContext ctx) {
        print("if");
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitWhile(CoolParser.WhileContext ctx) {
        print("while");
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitLet(CoolParser.LetContext ctx) {
        print("let");
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitCase(CoolParser.CaseContext ctx) {
        print("case");
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    // Arithmetic & comparison

    @Override
    public Void visitArithmetic(CoolParser.ArithmeticContext ctx) {
        print(ctx.op.getText());
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitUnary(CoolParser.UnaryContext ctx) {
        print(ctx.op.getText());
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitComparison(CoolParser.ComparisonContext ctx) {
        print(ctx.op.getText());
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    // Variable stuff

    @Override
    public Void visitVar(CoolParser.VarContext ctx) {
        print(ctx.getText());
        return null;
    }

    @Override
    public Void visitVarAssign(CoolParser.VarAssignContext ctx) {
        print(ctx.ID().getText());
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    // Literals

    @Override
    public Void visitLiteralInteger(CoolParser.LiteralIntegerContext ctx) {
        print(ctx.getText());
        return null;
    }

    @Override
    public Void visitLiteralString(CoolParser.LiteralStringContext ctx) {
        print(ctx.getText());
        return null;
    }

    @Override
    public Void visitLiteralTrue(CoolParser.LiteralTrueContext ctx) {
        print(ctx.getText());
        return null;
    }

    @Override
    public Void visitLiteralFalse(CoolParser.LiteralFalseContext ctx) {
        print(ctx.getText());
        return null;
    }

    // Single-argument expressions

    @Override
    public Void visitIsvoid(CoolParser.IsvoidContext ctx) {
        print("isvoid");
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitNegate(CoolParser.NegateContext ctx) {
        print("not");
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitInstantiation(CoolParser.InstantiationContext ctx) {
        print("new");
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    // Others

    @Override
    public Void visitBlock(CoolParser.BlockContext ctx) {
        print("block");
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitSelfMethodCall(CoolParser.SelfMethodCallContext ctx) {
        print("implicit dispatch");
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }

    @Override
    public Void visitMethodCall(CoolParser.MethodCallContext ctx) {
        print("dispatch");
        ++indent;
        visitChildren(ctx);
        --indent;
        return null;
    }
}
