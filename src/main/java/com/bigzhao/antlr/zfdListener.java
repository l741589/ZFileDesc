// Generated from E:/IntellijWS/FileDecoder/src/main/grammar\zfd.g4 by ANTLR 4.5.1

package com.bigzhao.antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link zfdParser}.
 */
public interface zfdListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link zfdParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode(zfdParser.CodeContext ctx);
	/**
	 * Exit a parse tree produced by {@link zfdParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode(zfdParser.CodeContext ctx);
	/**
	 * Enter a parse tree produced by {@link zfdParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(zfdParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link zfdParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(zfdParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link zfdParser#fieldDef}.
	 * @param ctx the parse tree
	 */
	void enterFieldDef(zfdParser.FieldDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link zfdParser#fieldDef}.
	 * @param ctx the parse tree
	 */
	void exitFieldDef(zfdParser.FieldDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link zfdParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void enterTypeDef(zfdParser.TypeDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link zfdParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void exitTypeDef(zfdParser.TypeDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link zfdParser#structDef}.
	 * @param ctx the parse tree
	 */
	void enterStructDef(zfdParser.StructDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link zfdParser#structDef}.
	 * @param ctx the parse tree
	 */
	void exitStructDef(zfdParser.StructDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link zfdParser#structBody}.
	 * @param ctx the parse tree
	 */
	void enterStructBody(zfdParser.StructBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link zfdParser#structBody}.
	 * @param ctx the parse tree
	 */
	void exitStructBody(zfdParser.StructBodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code configDefSingleLine}
	 * labeled alternative in {@link zfdParser#configDef}.
	 * @param ctx the parse tree
	 */
	void enterConfigDefSingleLine(zfdParser.ConfigDefSingleLineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code configDefSingleLine}
	 * labeled alternative in {@link zfdParser#configDef}.
	 * @param ctx the parse tree
	 */
	void exitConfigDefSingleLine(zfdParser.ConfigDefSingleLineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code configDefBlock}
	 * labeled alternative in {@link zfdParser#configDef}.
	 * @param ctx the parse tree
	 */
	void enterConfigDefBlock(zfdParser.ConfigDefBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code configDefBlock}
	 * labeled alternative in {@link zfdParser#configDef}.
	 * @param ctx the parse tree
	 */
	void exitConfigDefBlock(zfdParser.ConfigDefBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link zfdParser#configDefLine}.
	 * @param ctx the parse tree
	 */
	void enterConfigDefLine(zfdParser.ConfigDefLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link zfdParser#configDefLine}.
	 * @param ctx the parse tree
	 */
	void exitConfigDefLine(zfdParser.ConfigDefLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link zfdParser#className}.
	 * @param ctx the parse tree
	 */
	void enterClassName(zfdParser.ClassNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link zfdParser#className}.
	 * @param ctx the parse tree
	 */
	void exitClassName(zfdParser.ClassNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link zfdParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(zfdParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link zfdParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(zfdParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link zfdParser#ref}.
	 * @param ctx the parse tree
	 */
	void enterRef(zfdParser.RefContext ctx);
	/**
	 * Exit a parse tree produced by {@link zfdParser#ref}.
	 * @param ctx the parse tree
	 */
	void exitRef(zfdParser.RefContext ctx);
	/**
	 * Enter a parse tree produced by {@link zfdParser#refSeg}.
	 * @param ctx the parse tree
	 */
	void enterRefSeg(zfdParser.RefSegContext ctx);
	/**
	 * Exit a parse tree produced by {@link zfdParser#refSeg}.
	 * @param ctx the parse tree
	 */
	void exitRefSeg(zfdParser.RefSegContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenthesizedExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(zfdParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenthesizedExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(zfdParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExpression(zfdParser.AdditiveExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AdditiveExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExpression(zfdParser.AdditiveExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExpression(zfdParser.RelationalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RelationalExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExpression(zfdParser.RelationalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterTernaryExpression(zfdParser.TernaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TernaryExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitTernaryExpression(zfdParser.TernaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalAndExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExpression(zfdParser.LogicalAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalAndExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExpression(zfdParser.LogicalAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitNotExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitNotExpression(zfdParser.BitNotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitNotExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitNotExpression(zfdParser.BitNotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalOrExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExpression(zfdParser.LogicalOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalOrExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExpression(zfdParser.LogicalOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LogicalNotExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLogicalNotExpression(zfdParser.LogicalNotExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LogicalNotExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLogicalNotExpression(zfdParser.LogicalNotExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryMinusExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryMinusExpression(zfdParser.UnaryMinusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryMinusExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryMinusExpression(zfdParser.UnaryMinusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitAndExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitAndExpression(zfdParser.BitAndExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitAndExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitAndExpression(zfdParser.BitAndExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ValueExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterValueExpression(zfdParser.ValueExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ValueExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitValueExpression(zfdParser.ValueExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitOrExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitOrExpression(zfdParser.BitOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitOrExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitOrExpression(zfdParser.BitOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryPlusExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterUnaryPlusExpression(zfdParser.UnaryPlusExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryPlusExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitUnaryPlusExpression(zfdParser.UnaryPlusExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualityExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExpression(zfdParser.EqualityExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualityExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExpression(zfdParser.EqualityExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitXOrExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitXOrExpression(zfdParser.BitXOrExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitXOrExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitXOrExpression(zfdParser.BitXOrExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExpression(zfdParser.MultiplicativeExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultiplicativeExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExpression(zfdParser.MultiplicativeExpressionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitShiftExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBitShiftExpression(zfdParser.BitShiftExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitShiftExpression}
	 * labeled alternative in {@link zfdParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBitShiftExpression(zfdParser.BitShiftExpressionContext ctx);
}