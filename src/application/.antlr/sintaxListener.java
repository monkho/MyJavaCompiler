// Generated from c:/Users/Obgoh/OneDrive - Instituto Tecnológico de Morelia/Documentos/08_Semestre/03 - Lenguajes y automatas II/practicas/tst/src/application/sintax.g by ANTLR 4.13.1

package application;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link sintaxParser}.
 */
public interface sintaxListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link sintaxParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(sintaxParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(sintaxParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#contar}.
	 * @param ctx the parse tree
	 */
	void enterContar(sintaxParser.ContarContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#contar}.
	 * @param ctx the parse tree
	 */
	void exitContar(sintaxParser.ContarContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#clase}.
	 * @param ctx the parse tree
	 */
	void enterClase(sintaxParser.ClaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#clase}.
	 * @param ctx the parse tree
	 */
	void exitClase(sintaxParser.ClaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#miembro}.
	 * @param ctx the parse tree
	 */
	void enterMiembro(sintaxParser.MiembroContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#miembro}.
	 * @param ctx the parse tree
	 */
	void exitMiembro(sintaxParser.MiembroContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#propiedad}.
	 * @param ctx the parse tree
	 */
	void enterPropiedad(sintaxParser.PropiedadContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#propiedad}.
	 * @param ctx the parse tree
	 */
	void exitPropiedad(sintaxParser.PropiedadContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#metodo}.
	 * @param ctx the parse tree
	 */
	void enterMetodo(sintaxParser.MetodoContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#metodo}.
	 * @param ctx the parse tree
	 */
	void exitMetodo(sintaxParser.MetodoContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void enterInstruccion(sintaxParser.InstruccionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#instruccion}.
	 * @param ctx the parse tree
	 */
	void exitInstruccion(sintaxParser.InstruccionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void enterAsignacion(sintaxParser.AsignacionContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#asignacion}.
	 * @param ctx the parse tree
	 */
	void exitAsignacion(sintaxParser.AsignacionContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#declaracion_local}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion_local(sintaxParser.Declaracion_localContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#declaracion_local}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion_local(sintaxParser.Declaracion_localContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#declaracion_args}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracion_args(sintaxParser.Declaracion_argsContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#declaracion_args}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracion_args(sintaxParser.Declaracion_argsContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#llamada}.
	 * @param ctx the parse tree
	 */
	void enterLlamada(sintaxParser.LlamadaContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#llamada}.
	 * @param ctx the parse tree
	 */
	void exitLlamada(sintaxParser.LlamadaContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(sintaxParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(sintaxParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(sintaxParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#multExpr}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(sintaxParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(sintaxParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(sintaxParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#metodo_principal}.
	 * @param ctx the parse tree
	 */
	void enterMetodo_principal(sintaxParser.Metodo_principalContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#metodo_principal}.
	 * @param ctx the parse tree
	 */
	void exitMetodo_principal(sintaxParser.Metodo_principalContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#modificAcceso}.
	 * @param ctx the parse tree
	 */
	void enterModificAcceso(sintaxParser.ModificAccesoContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#modificAcceso}.
	 * @param ctx the parse tree
	 */
	void exitModificAcceso(sintaxParser.ModificAccesoContext ctx);
	/**
	 * Enter a parse tree produced by {@link sintaxParser#tipo}.
	 * @param ctx the parse tree
	 */
	void enterTipo(sintaxParser.TipoContext ctx);
	/**
	 * Exit a parse tree produced by {@link sintaxParser#tipo}.
	 * @param ctx the parse tree
	 */
	void exitTipo(sintaxParser.TipoContext ctx);
}