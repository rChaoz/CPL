// Generated from D:/Facultate/CPL/Repo/03-parser/Parser-skel-IntelliJ/CPLangParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CPLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CPLangParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CPLangParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(CPLangParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link CPLangParser#formal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal(CPLangParser.FormalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code var_def}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_def(CPLangParser.Var_defContext ctx);
	/**
	 * Visit a parse tree produced by the {@code func_def}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_def(CPLangParser.Func_defContext ctx);
	/**
	 * Visit a parse tree produced by the {@code call}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(CPLangParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unary_expr}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary_expr(CPLangParser.Unary_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paren_expr}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParen_expr(CPLangParser.Paren_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comparison}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(CPLangParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by the {@code var}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(CPLangParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code var_assign}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_assign(CPLangParser.Var_assignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arithmetic_2}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic_2(CPLangParser.Arithmetic_2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code arithmetic_1}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmetic_1(CPLangParser.Arithmetic_1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code if}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf(CPLangParser.IfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literal}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(CPLangParser.LiteralContext ctx);
}