package br.com.compilador;
/*
 *  Representa um s�mbolo v�lido para a linguagem. 
 *  Seu estado deve possuir, minimamente, 
 *  o c�digo do token (estabelece o tipo de token) e 
 *  o lexema extra�do do arquivo de entrada 
 */


public class Token {
	private String lexema;
	private TokenTipo tipoToken;
	
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
}
