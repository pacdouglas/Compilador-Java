/*
 * Nome Alunos:
 * Douglas Martins
 * José Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */
package br.com.compilador.analisadores;

import java.io.IOException;
import br.com.compilador.utils.Erro;
import br.com.compilador.utils.ErroHandler;
import br.com.compilador.utils.MapaDerivacao;
import br.com.compilador.utils.Mensagens;
import br.com.compilador.utils.TabelaSimbolos;
import br.com.compilador.utils.TipoDeErro;
import br.com.compilador.utils.Token;
import br.com.compilador.utils.TokenTipo;

public class Sintatico {

	Lexico analisadorLexico;
	private Token token;
	private MapaDerivacao mapaDerivacao;

	public Sintatico(String path) throws IOException {
		analisadorLexico = new Lexico(path);
		mapaDerivacao = new MapaDerivacao();
	}

	public void executar() {
		derivaS();
		ErroHandler.getInstance().exibeErros();
	}

	public void derivaS() {
		token = analisadorLexico.getNextToken();

		if (!token.getTipoToken().equals(TokenTipo.PROGRAM))
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_FALTA_PROGRAM, true);
		else
			token = analisadorLexico.getNextToken();

		if (!token.getTipoToken().equals(TokenTipo.ID))
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_FALTA_PROGRAM, true);
		else
			token = analisadorLexico.getNextToken();

		if (!token.getTipoToken().equals(TokenTipo.TERM)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_FALTA_PONTOVIRGULA_NOME_PROGRAM, true);
			derivaBloco();
			token = analisadorLexico.getNextToken();
		} else {
			token = analisadorLexico.getNextToken();
			derivaBloco();
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.END_PROGRAM)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_FALTOU_ENDPROGR, true);
		} else {
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.TERM)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_FALTOU_TERM, true);
		} else {
			token = analisadorLexico.getNextToken();
		}
	}

	public void derivaBloco() {
		if (token.getTipoToken().equals(TokenTipo.BEGIN)) {
			token = analisadorLexico.getNextToken();
			derivaCmds();
			token = analisadorLexico.getNextToken();

			if (!token.getTipoToken().equals(TokenTipo.END)) {
				this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_END_NAO_ENCONTRADO, true);
			}
		} else {
			if (!mapaDerivacao.derivacaoCmd.verificaSeContem(token.getTipoToken())) {
				this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_BEGIN_CMD_NAO_ENCONTRADO, true);
			} else {
				derivaCmd();
			}
		}
	}

	public void derivaCmds() {
		if (token.getTipoToken().equals(TokenTipo.DECLARE)) {
			token = analisadorLexico.getNextToken();
			derivaDcflw();
		} else if (token.getTipoToken().equals(TokenTipo.IF)) {
			token = analisadorLexico.getNextToken();
			derivaIfflw();
		} else if (mapaDerivacao.derivacaoRepf.verificaSeContem(token.getTipoToken())) {
			derivaRepf();
			token = analisadorLexico.getNextToken();
			derivaCmds();
		} else if (mapaDerivacao.derivacaoRepw.verificaSeContem(token.getTipoToken())) {
			derivaRepw();
			token = analisadorLexico.getNextToken();
			derivaCmds();
		} else if (token.getTipoToken().equals(TokenTipo.ID)) {
			if (!TabelaSimbolos.getInstance().get(token.getLexema()).foiDeclarado())
				this.gravaErro(TipoDeErro.SEMANTICO, token, Mensagens.MSG_VARIAVEL_NAO_DECLARADA + token.getLexema(),
						false);

			token = analisadorLexico.getNextToken();
			derivaIdflw();
		} else {
			analisadorLexico.armazenaToken(token);
		}
	}

	public void derivaIfflw() {
		if (!token.getTipoToken().equals(TokenTipo.L_PAR)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_PARENTESES_ABRE, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExpl();
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.R_PAR)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_PARENTESES_FECHA, true);
		} else {
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.THEN)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_IF_SEM_THEN, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaBloco();
			token = analisadorLexico.getNextToken();
			derivaCmds();
		}
	}

	public void derivaIdflw() {
		if (!token.getTipoToken().equals(TokenTipo.ATTRIB_OP)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_VARIAVEL_SEM_ATRIBUICAO, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExp();
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.TERM)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_FALTOU_TERM, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaCmds();
		}
	}

	public void derivaDcflw() {
		if (!token.getTipoToken().equals(TokenTipo.ID)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_DECLARE_SEM_VARIAVEL, true);
		} else {
			if (token.foiDeclarado()) {
				this.gravaErro(TipoDeErro.SEMANTICO, token, Mensagens.MSG_VARIAVEL_REDECLARADA + token.getLexema(),
						false);
			}

			token.setFoiDeclarado(true);
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.TYPE)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_VARIAVEL_SEM_TIPO + token.getLexema(), true);
		} else {
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.TERM)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_PONTOVIRGULA_TIPO_VARIAVEL, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaCmds();
		}
	}

	public void derivaCmd() {
		if (mapaDerivacao.derivacaoDecl.verificaSeContem(token.getTipoToken())) {
			derivaDecl();
		} else if (mapaDerivacao.derivacaoCond.verificaSeContem(token.getTipoToken())) {
			derivaCond();
		} else if (mapaDerivacao.derivacaoRep.verificaSeContem(token.getTipoToken())) {
			derivaRep();
		} else if (mapaDerivacao.derivacaoAtrib.verificaSeContem(token.getTipoToken())) {
			derivaAtrib();
		} else {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_CMD_NAO_ENCONTRADO, true);
		}
	}

	public void derivaDecl() {
		if (!token.getTipoToken().equals(TokenTipo.DECLARE)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_VARIAVEL_SEM_DECLARE, true);
		} else {
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.ID)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_DECLARE_SEM_VARIAVEL, true);
		} else {
			if (token.foiDeclarado()) {
				this.gravaErro(TipoDeErro.SEMANTICO, token, Mensagens.MSG_VARIAVEL_REDECLARADA, false);
			}

			token = analisadorLexico.getNextToken();
			token.setFoiDeclarado(true);
		}

		if (!token.getTipoToken().equals(TokenTipo.TYPE)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_VARIAVEL_SEM_TIPO, true);
		} else {
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.TERM)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_PONTOVIRGULA_TIPO_VARIAVEL, true);
		}
	}

	public void derivaCond() {
		if (!token.getTipoToken().equals(TokenTipo.IF)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_CONDICAO_SEM_IF, true);
		} else {
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.L_PAR)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_PARENTESES_ABRE, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExpl();
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.R_PAR)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_PARENTESES_FECHA, true);
		} else {
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.THEN)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_IF_SEM_THEN, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaBloco();
			token = analisadorLexico.getNextToken();
			derivaCndb();
		}
	}

	public void derivaCndb() {
		if (!token.getTipoToken().equals(TokenTipo.ELSE)) {
			analisadorLexico.armazenaToken(token);
		} else {
			token = analisadorLexico.getNextToken();
			derivaBloco();
		}
	}

	public void derivaAtrib() {
		if (!token.getTipoToken().equals(TokenTipo.ID)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_VARIAVEL_NAO_ENCONTRADA, true);
		} else {
			if (!TabelaSimbolos.getInstance().get(token.getLexema()).foiDeclarado())
				this.gravaErro(TipoDeErro.SEMANTICO, token, Mensagens.MSG_VARIAVEL_NAO_DECLARADA + token.getLexema(),
						false);
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.ATTRIB_OP)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_VARIAVEL_SEM_ATRIBUICAO, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExp();
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.TERM)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_FALTOU_PONTOVIRGULA, true);
		}
	}

	public void derivaExp() {
		if (token.getTipoToken().equals(TokenTipo.LOGIC_VAL)) {
			token = analisadorLexico.getNextToken();
			derivaLogflw();
		} else if (token.getTipoToken().equals(TokenTipo.ID)) {
			if (!TabelaSimbolos.getInstance().get(token.getLexema()).foiDeclarado())
				this.gravaErro(TipoDeErro.SEMANTICO, token, Mensagens.MSG_VARIAVEL_NAO_DECLARADA + token.getLexema(),
						false);
			token = analisadorLexico.getNextToken();
			derivaGenflw();
		} else if (token.getTipoToken().equals(TokenTipo.NUM_INT)) {
			token = analisadorLexico.getNextToken();
			derivaGenflw1();
		} else if (token.getTipoToken().equals(TokenTipo.NUM_FLOAT)) {
			derivaGenflw1();
		} else if (token.getTipoToken().equals(TokenTipo.L_PAR)) {
			token = analisadorLexico.getNextToken();
			derivaExp();
			token = analisadorLexico.getNextToken();
			if (token.getTipoToken().equals(TokenTipo.R_PAR)) {
				token = analisadorLexico.getNextToken();
				derivaGenflw1();
			} else {
				this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_PARENTESES_FECHA, true);
			}
		} else {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXP_FALTANDO, true);
		}
	}

	public void derivaExpl() {

		if (token.getTipoToken().equals(TokenTipo.LOGIC_VAL)) {
			token = analisadorLexico.getNextToken();
			derivaLogflw();
		} else if (token.getTipoToken().equals(TokenTipo.ID)) {
			if (!TabelaSimbolos.getInstance().get(token.getLexema()).foiDeclarado())
				this.gravaErro(TipoDeErro.SEMANTICO, token, Mensagens.MSG_VARIAVEL_NAO_DECLARADA + token.getLexema(),
						false);
			token = analisadorLexico.getNextToken();
			derivaGenflw();
		} else if (token.getTipoToken().equals(TokenTipo.NUM_INT)) {
			token = analisadorLexico.getNextToken();
			derivaGenflw1();
		} else if (token.getTipoToken().equals(TokenTipo.NUM_FLOAT)) {
			token = analisadorLexico.getNextToken();
			derivaGenflw1();
		} else if (token.getTipoToken().equals(TokenTipo.LOGIC_VAL)) {
			derivaLogflw();
		} else if (token.getTipoToken().equals(TokenTipo.L_PAR)) {
			token = analisadorLexico.getNextToken();
			derivaExp();
			token = analisadorLexico.getNextToken();
			if (token.getTipoToken().equals(TokenTipo.R_PAR)) {
				token = analisadorLexico.getNextToken();
				derivaGenflw1();
			} else {
				this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_PARENTESES_FECHA, true);
			}
		} else {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPL_FALTANDO, true);
		}
	}

	public void derivaLogflw() {
		if (!token.getTipoToken().equals(TokenTipo.LOGIC_OP)) {
			analisadorLexico.armazenaToken(token);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExpl();
		}
	}

	public void derivaGenflw() {
		if (token.getTipoToken().equals(TokenTipo.LOGIC_OP)) {
			token = analisadorLexico.getNextToken();
			derivaExpl();
		} else if (mapaDerivacao.derivacaoGenflw1.verificaSeContem(token.getTipoToken())) {
			derivaGenflw1();
		} else {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_GENFLW_FALTANDO, true);
		}
	}

	public void derivaGenflw1() {
		if (!mapaDerivacao.derivacaoTermon1.verificaSeContem(token.getTipoToken())) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_TERMON1_FALTANDO, true);
		} else {
			derivaTermon1();
		}

		if (!mapaDerivacao.derivacaoExpn1.verificaSeContem(token.getTipoToken())) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPN1_FALTANDO, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExpn1();
		}

		if (!mapaDerivacao.derivacaoGenflw2.verificaSeContem(token.getTipoToken())) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_GENFLW2_FALTANDO, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaGenflw2();
		}
	}

	public void derivaGenflw2() {
		if (!token.getTipoToken().equals(TokenTipo.REL_OP)) {
			analisadorLexico.armazenaToken(token);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExpn();
			token = analisadorLexico.getNextToken();
			derivaGenflw3();
		}
	}

	public void derivaGenflw3() {
		if (!token.getTipoToken().equals(TokenTipo.LOGIC_OP)) {
			analisadorLexico.armazenaToken(token);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExpr();
		}
	}

	public void derivaExpr() {
		if (!mapaDerivacao.derivacaoExpn.verificaSeContem(token.getTipoToken())) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPN_FALTANDO, true);
		} else {
			derivaExpn();
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.REL_OP)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_OPERADOR_RELACAO, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExpn();
		}
	}

	public void derivaExpn() {
		if (!mapaDerivacao.derivacaoTermon.verificaSeContem(token.getTipoToken())) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_TERMON_FALTANDO, true);
		} else {
			derivaTermon();
			derivaExpn1();
		}
	}

	public void derivaExpn1() {
		if (!token.getTipoToken().equals(TokenTipo.ADDSUB_OP)) {
			analisadorLexico.armazenaToken(token);
		} else {
			token = analisadorLexico.getNextToken();
			derivaTermon();
			derivaExpn1();
		}
	}

	public void derivaTermon() {
		if (!mapaDerivacao.derivacaoValn.verificaSeContem(token.getTipoToken())) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_VALN_FALTANDO, true);
		} else {
			derivaValn();
			token = analisadorLexico.getNextToken();
			derivaTermon1();
		}
	}

	public void derivaTermon1() {
		if (!token.getTipoToken().equals(TokenTipo.MULTDIV_OP)) {
			analisadorLexico.armazenaToken(token);
		} else {
			token = analisadorLexico.getNextToken();
			derivaValn();
			token = analisadorLexico.getNextToken();
			derivaTermon1();
		}
	}

	public void derivaValn() {
		if (token.getTipoToken().equals(TokenTipo.ID)) {
			if (!TabelaSimbolos.getInstance().get(token.getLexema()).foiDeclarado())
				this.gravaErro(TipoDeErro.SEMANTICO, token, Mensagens.MSG_VARIAVEL_NAO_DECLARADA + token.getLexema(),
						false);
		} else if (token.getTipoToken().equals(TokenTipo.L_PAR)) {
			derivaExpn();
			token = analisadorLexico.getNextToken();
			if (!token.getTipoToken().equals(TokenTipo.R_PAR)) {
				this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_PARENTESES_FECHA, true);
			}
		} else if ((!token.getTipoToken().equals(TokenTipo.NUM_INT))
				&& (!token.getTipoToken().equals(TokenTipo.NUM_FLOAT))) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_VALN_FALTANDO, true);
		}
	}

	public void derivaRep() {
		if (mapaDerivacao.derivacaoRepf.verificaSeContem(token.getTipoToken())) {
			derivaRepf();
			token = analisadorLexico.getNextToken();
			derivaTermon1();
		} else if (mapaDerivacao.derivacaoRepw.verificaSeContem(token.getTipoToken())) {
			derivaRepw();
		} else {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_REP_FALTANDO, true);
		}
	}

	public void derivaRepf() {
		if (!token.getTipoToken().equals(TokenTipo.FOR)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_REPETICAO_FOR_FALTANDO, true);
		} else {
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.ID)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_VARIAVEL_NAO_DECLARADA, true);
		} else {
			if (!TabelaSimbolos.getInstance().get(token.getLexema()).foiDeclarado())
				this.gravaErro(TipoDeErro.SEMANTICO, token, Mensagens.MSG_VARIAVEL_NAO_DECLARADA, false);
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.ATTRIB_OP)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_OPERADOR_ATRIBUICAO, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExpn();
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.TO)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_REPETICAO_FOR_TO, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExpn();
			token = analisadorLexico.getNextToken();
			derivaBloco();
		}
	}

	public void derivaRepw() {
		if (!token.getTipoToken().equals(TokenTipo.WHILE)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_REPETICAO_WHILE_FALTANDO, true);
		} else {
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.L_PAR)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_PARENTESES_ABRE, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaExpl();
			token = analisadorLexico.getNextToken();
		}

		if (!token.getTipoToken().equals(TokenTipo.R_PAR)) {
			this.gravaErro(TipoDeErro.SINTATICO, token, Mensagens.MSG_EXPR_PARENTESES_FECHA, true);
		} else {
			token = analisadorLexico.getNextToken();
			derivaBloco();
		}
	}

	public void gravaErro(TipoDeErro tipoDeErro, Token token, String descricao, boolean guardarToken) {
		Erro erro = new Erro(tipoDeErro, token.getLexema(), token.getLinha(), token.getColuna(), descricao);
		ErroHandler.gravaErro(erro);
	}
}
