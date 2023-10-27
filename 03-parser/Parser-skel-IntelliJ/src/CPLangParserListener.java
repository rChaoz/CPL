// Generated from D:/Facultate/CPL/Repo/03-parser/Parser-skel-IntelliJ/CPLangParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CPLangParser}.
 */
public interface CPLangParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CPLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(CPLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(CPLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link CPLangParser#formal}.
	 * @param ctx the parse tree
	 */
	void enterFormal(CPLangParser.FormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CPLangParser#formal}.
	 * @param ctx the parse tree
	 */
	void exitFormal(CPLangParser.FormalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code var_def}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterVar_def(CPLangParser.Var_defContext ctx);
	/**
	 * Exit a parse tree produced by the {@code var_def}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitVar_def(CPLangParser.Var_defContext ctx);
	/**
	 * Enter a parse tree produced by the {@code func_def}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 */
	void enterFunc_def(CPLangParser.Func_defContext ctx);
	/**
	 * Exit a parse tree produced by the {@code func_def}
	 * labeled alternative in {@link CPLangParser#definition}.
	 * @param ctx the parse tree
	 */
	void exitFunc_def(CPLangParser.Func_defContext ctx);
	/**
	 * Enter a parse tree produced by the {@code call}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCall(CPLangParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code call}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCall(CPLangParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unary_expr}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnary_expr(CPLangParser.Unary_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unary_expr}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnary_expr(CPLangParser.Unary_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paren_expr}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParen_expr(CPLangParser.Paren_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paren_expr}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParen_expr(CPLangParser.Paren_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comparison}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterComparison(CPLangParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comparison}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitComparison(CPLangParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code var}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVar(CPLangParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code var}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVar(CPLangParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code var_assign}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVar_assign(CPLangParser.Var_assignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code var_assign}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVar_assign(CPLangParser.Var_assignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmetic_2}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_2(CPLangParser.Arithmetic_2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmetic_2}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_2(CPLangParser.Arithmetic_2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmetic_1}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_1(CPLangParser.Arithmetic_1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmetic_1}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_1(CPLangParser.Arithmetic_1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code if}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIf(CPLangParser.IfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code if}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIf(CPLangParser.IfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literal}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(CPLangParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literal}
	 * labeled alternative in {@link CPLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(CPLangParser.LiteralContext ctx);
}