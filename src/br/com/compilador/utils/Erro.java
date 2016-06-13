/*
 * Nome Alunos:
 * Douglas Martins
 * José Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */
package br.com.compilador.utils;

public class Erro {
	private TipoDeErro erroTipo;
	private String lexema;
	private int linha;
	private int coluna;
	private String descricao;

	public Erro(TipoDeErro tipo, String lexema, int linha, int coluna, String descricao) {
		super();
		this.erroTipo = tipo;
		this.lexema = lexema;
		this.linha = linha;
		this.coluna = coluna;
		this.descricao = descricao;
	}

	public TipoDeErro getErroTipo() {
		return erroTipo;
	}

	public void setErroTipo(TipoDeErro erroTipo) {
		this.erroTipo = erroTipo;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
