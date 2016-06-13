/*
 * Nome Alunos:
 * Douglas Martins
 * José Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */

package br.com.compilador.utils;

public class Token {
	private String lexema;
	private TokenTipo tipoToken;
	private int linha, coluna;
	private boolean foiDeclarado;

	public Token(String lexema, TokenTipo tipoToken, int linha, int coluna) {
		this.lexema = lexema;
		this.tipoToken = tipoToken;
		this.linha = linha;
		this.coluna = coluna;
		this.foiDeclarado = false;
	}

	public Token(String lexema, TokenTipo tipoToken) {
		super();
		this.lexema = lexema;
		this.tipoToken = tipoToken;
	}

	public String getLexema() {
		return lexema;
	}

	public void setLexema(String lexema) {
		this.lexema = lexema;
	}

	public TokenTipo getTipoToken() {
		return tipoToken;
	}

	public void setTipoToken(TokenTipo tipoToken) {
		this.tipoToken = tipoToken;
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

	public boolean foiDeclarado() {
		return foiDeclarado;
	}

	public void setFoiDeclarado(boolean foiDeclarado) {
		this.foiDeclarado = foiDeclarado;
	}

}
