/*
 * Nome Alunos:
 * Douglas Martins
 * José Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */
package br.com.compilador.utils;

import br.com.compilador.derivacao.DerivacaoBase;

public class MapaDerivacao {

	public DerivacaoBase derivacaoS = new DerivacaoBase();
	public DerivacaoBase derivacaoBloco = new DerivacaoBase();
	public DerivacaoBase derivacaoCmds = new DerivacaoBase();
	public DerivacaoBase derivacaoIfflw = new DerivacaoBase();
	public DerivacaoBase derivacaoIdflw = new DerivacaoBase();
	public DerivacaoBase derivacaoDcflw = new DerivacaoBase();
	public DerivacaoBase derivacaoCmd = new DerivacaoBase();
	public DerivacaoBase derivacaoDecl = new DerivacaoBase();
	public DerivacaoBase derivacaoCond = new DerivacaoBase();
	public DerivacaoBase derivacaoCndb = new DerivacaoBase();
	public DerivacaoBase derivacaoAtrib = new DerivacaoBase();
	public DerivacaoBase derivacaoExp = new DerivacaoBase();
	public DerivacaoBase derivacaoExpl = new DerivacaoBase();
	public DerivacaoBase derivacaoLogflw = new DerivacaoBase();
	public DerivacaoBase derivacaoGenflw = new DerivacaoBase();
	public DerivacaoBase derivacaoGenflw1 = new DerivacaoBase();
	public DerivacaoBase derivacaoGenflw2 = new DerivacaoBase();
	public DerivacaoBase derivacaoGenflw3 = new DerivacaoBase();
	public DerivacaoBase derivacaoExpr = new DerivacaoBase();
	public DerivacaoBase derivacaoExpn = new DerivacaoBase();
	public DerivacaoBase derivacaoExpn1 = new DerivacaoBase();
	public DerivacaoBase derivacaoTermon = new DerivacaoBase();
	public DerivacaoBase derivacaoTermon1 = new DerivacaoBase();
	public DerivacaoBase derivacaoValn = new DerivacaoBase();
	public DerivacaoBase derivacaoRep = new DerivacaoBase();
	public DerivacaoBase derivacaoRepf = new DerivacaoBase();
	public DerivacaoBase derivacaoRepw = new DerivacaoBase();
	
	public MapaDerivacao(){
		derivacaoS.adiciona(TokenTipo.PROGRAM);

		derivacaoBloco.adiciona(TokenTipo.WHILE);
		derivacaoBloco.adiciona(TokenTipo.ID);
		derivacaoBloco.adiciona(TokenTipo.FOR);
		derivacaoBloco.adiciona(TokenTipo.IF);
		derivacaoBloco.adiciona(TokenTipo.DECLARE);
		derivacaoBloco.adiciona(TokenTipo.BEGIN);

		derivacaoCmds.adiciona(TokenTipo.WHILE);
		derivacaoCmds.adiciona(TokenTipo.ID);
		derivacaoCmds.adiciona(TokenTipo.FOR);
		derivacaoCmds.adiciona(TokenTipo.IF);
		derivacaoCmds.adiciona(TokenTipo.DECLARE);
		derivacaoCmds.adiciona(TokenTipo.END);

		derivacaoIfflw.adiciona(TokenTipo.L_PAR);
		derivacaoIfflw.adiciona(TokenTipo.WHILE);
		derivacaoIfflw.adiciona(TokenTipo.FOR);
		derivacaoIfflw.adiciona(TokenTipo.ID);

		derivacaoIdflw.adiciona(TokenTipo.ATTRIB_OP);

		derivacaoDcflw.adiciona(TokenTipo.ID);

		derivacaoCmd.adiciona(TokenTipo.WHILE);
		derivacaoCmd.adiciona(TokenTipo.ID);
		derivacaoCmd.adiciona(TokenTipo.FOR);
		derivacaoCmd.adiciona(TokenTipo.IF);
		derivacaoCmd.adiciona(TokenTipo.DECLARE);

		derivacaoDecl.adiciona(TokenTipo.DECLARE);

		derivacaoCond.adiciona(TokenTipo.IF);

		derivacaoCndb.adiciona(TokenTipo.WHILE);
		derivacaoCndb.adiciona(TokenTipo.ID);
		derivacaoCndb.adiciona(TokenTipo.FOR);
		derivacaoCndb.adiciona(TokenTipo.ELSE);
		derivacaoCndb.adiciona(TokenTipo.IF);
		derivacaoCndb.adiciona(TokenTipo.DECLARE);
		derivacaoCndb.adiciona(TokenTipo.END);
		derivacaoCndb.adiciona(TokenTipo.END_PROGRAM);

		derivacaoAtrib.adiciona(TokenTipo.ID);

		derivacaoExp.adiciona(TokenTipo.L_PAR);
		derivacaoExp.adiciona(TokenTipo.ID);
		derivacaoExp.adiciona(TokenTipo.NUM_FLOAT);
		derivacaoExp.adiciona(TokenTipo.NUM_INT);
		derivacaoExp.adiciona(TokenTipo.LOGIC_VAL);
		derivacaoExp.adiciona(TokenTipo.LITERAL);

		derivacaoExpl.adiciona(TokenTipo.L_PAR);
		derivacaoExpl.adiciona(TokenTipo.ID);
		derivacaoExpl.adiciona(TokenTipo.NUM_FLOAT);
		derivacaoExpl.adiciona(TokenTipo.NUM_INT);
		derivacaoExpl.adiciona(TokenTipo.LOGIC_VAL);

		derivacaoLogflw.adiciona(TokenTipo.R_PAR);
		derivacaoLogflw.adiciona(TokenTipo.TERM);
		derivacaoLogflw.adiciona(TokenTipo.LOGIC_OP);

		derivacaoGenflw.adiciona(TokenTipo.R_PAR);
		derivacaoGenflw.adiciona(TokenTipo.TERM);
		derivacaoGenflw.adiciona(TokenTipo.LOGIC_OP);

		derivacaoGenflw1.adiciona(TokenTipo.R_PAR);
		derivacaoGenflw1.adiciona(TokenTipo.WHILE);
		derivacaoGenflw1.adiciona(TokenTipo.TO);
		derivacaoGenflw1.adiciona(TokenTipo.ID);
		derivacaoGenflw1.adiciona(TokenTipo.MULTDIV_OP);
		derivacaoGenflw1.adiciona(TokenTipo.ADDSUB_OP);
		derivacaoGenflw1.adiciona(TokenTipo.REL_OP);
		derivacaoGenflw1.adiciona(TokenTipo.TERM);
		derivacaoGenflw1.adiciona(TokenTipo.IF);
		derivacaoGenflw1.adiciona(TokenTipo.DECLARE);
		derivacaoGenflw1.adiciona(TokenTipo.BEGIN);
		derivacaoGenflw1.adiciona(TokenTipo.LOGIC_OP);

		derivacaoGenflw2.adiciona(TokenTipo.R_PAR);
		derivacaoGenflw2.adiciona(TokenTipo.REL_OP);
		derivacaoGenflw2.adiciona(TokenTipo.TERM);

		derivacaoGenflw3.adiciona(TokenTipo.R_PAR);
		derivacaoGenflw3.adiciona(TokenTipo.LOGIC_OP);
		derivacaoGenflw3.adiciona(TokenTipo.TERM);

		derivacaoExpr.adiciona(TokenTipo.L_PAR);
		derivacaoExpr.adiciona(TokenTipo.ID);
		derivacaoExpr.adiciona(TokenTipo.NUM_FLOAT);
		derivacaoExpr.adiciona(TokenTipo.NUM_INT);

		derivacaoExpn.adiciona(TokenTipo.L_PAR);
		derivacaoExpn.adiciona(TokenTipo.ID);
		derivacaoExpn.adiciona(TokenTipo.NUM_FLOAT);
		derivacaoExpn.adiciona(TokenTipo.NUM_INT);

		derivacaoExpn1.adiciona(TokenTipo.R_PAR);
		derivacaoExpn1.adiciona(TokenTipo.ID);
		derivacaoExpn1.adiciona(TokenTipo.WHILE);
		derivacaoExpn1.adiciona(TokenTipo.TO);
		derivacaoExpn1.adiciona(TokenTipo.FOR);
		derivacaoExpn1.adiciona(TokenTipo.ADDSUB_OP);
		derivacaoExpn1.adiciona(TokenTipo.REL_OP);
		derivacaoExpn1.adiciona(TokenTipo.TERM);
		derivacaoExpn1.adiciona(TokenTipo.IF);
		derivacaoExpn1.adiciona(TokenTipo.DECLARE);
		derivacaoExpn1.adiciona(TokenTipo.BEGIN);
		derivacaoExpn1.adiciona(TokenTipo.LOGIC_OP);

		derivacaoTermon.adiciona(TokenTipo.L_PAR);
		derivacaoTermon.adiciona(TokenTipo.ID);
		derivacaoTermon.adiciona(TokenTipo.NUM_FLOAT);
		derivacaoTermon.adiciona(TokenTipo.NUM_INT);

		derivacaoTermon1.adiciona(TokenTipo.R_PAR);
		derivacaoTermon1.adiciona(TokenTipo.ID);
		derivacaoTermon1.adiciona(TokenTipo.WHILE);
		derivacaoTermon1.adiciona(TokenTipo.TO);
		derivacaoTermon1.adiciona(TokenTipo.FOR);
		derivacaoTermon1.adiciona(TokenTipo.MULTDIV_OP);
		derivacaoTermon1.adiciona(TokenTipo.ADDSUB_OP);
		derivacaoTermon1.adiciona(TokenTipo.REL_OP);
		derivacaoTermon1.adiciona(TokenTipo.IF);
		derivacaoTermon1.adiciona(TokenTipo.TERM);
		derivacaoTermon1.adiciona(TokenTipo.BEGIN);
		derivacaoTermon1.adiciona(TokenTipo.DECLARE);
		derivacaoTermon1.adiciona(TokenTipo.LOGIC_OP);

		derivacaoValn.adiciona(TokenTipo.L_PAR);
		derivacaoValn.adiciona(TokenTipo.ID);
		derivacaoValn.adiciona(TokenTipo.NUM_FLOAT);
		derivacaoValn.adiciona(TokenTipo.NUM_INT);

		derivacaoRep.adiciona(TokenTipo.WHILE);
		derivacaoRep.adiciona(TokenTipo.FOR);

		derivacaoRepf.adiciona(TokenTipo.FOR);

		derivacaoRepw.adiciona(TokenTipo.WHILE);
	}
	
}
