package br.com.compilador;

import java.io.EOFException;
import java.io.IOException;

import br.com.compilador.main.FileLoader;

/*
* classe que implementa o Analisador Léxico 
* para a linguagem estabelecida. 
* Deve possuir o método nextToken, que contém 
* a lógica de processamento do analisador léxico 
*/

public class AnLexico {
	private FileLoader buffer;
	private StringBuilder lexema = new StringBuilder();
	private int linha;
	private int coluna;

	public AnLexico(String path) throws IOException {
		buffer = new FileLoader(path);
	}

	public Token nextToken() {
		Token token = null;

		try {
			token = this.processa();
		} catch (EOFException eof) {
			token = new Token("Final", TokenTipo.EOF);
		} catch (Exception e) {
			ErrorHandler.getInstance().gravaErro(new Erro("Erro Não tratado", "", 0, 0, "Erro Não tratado"));
		}
		return token;
	}

	private Token processa() throws EOFException, IOException {
		Token tk = null;

		char caractere = buffer.getNextChar();
		lexema.append(caractere);
		linha = buffer.getLine();
		coluna = buffer.getColumn();

		if (Util.isLetter(caractere)) {
			tk = this.tokenId();
		} else if (Util.isDigit(caractere)) {
			tk = this.tokenNumInt();
		} else if ((Character.isWhitespace(caractere))) {
			lexema = new StringBuilder();
			tk = this.processa();
		} else {
			switch (caractere) {
			case ':':
				caractere = buffer.getNextChar();
				if (caractere == '[') {
					buffer.rollbackChar();
					this.descartaComentario();
					tk = this.processa();
				} else {
					buffer.rollbackChar();
					tk = this.tokenAttribOp();
				}
				lexema = new StringBuilder();
				break;
			case '$':
				tk = this.tokenRelOp();
				lexema = new StringBuilder();
				break; 
			case '\'':
				tk = this.tokenLiteral();
				lexema = new StringBuilder();
				break;
			case '+':
				tk = new Token(this.lexema.toString(), TokenTipo.ADDSUB_OP, this.linha, this.coluna);
				this.lexema = new StringBuilder();
				break;
			case '-':
				tk = new Token(this.lexema.toString(), TokenTipo.ADDSUB_OP, this.linha, this.coluna);
				this.lexema = new StringBuilder();
				break;
			case '*':
				tk = new Token(this.lexema.toString(), TokenTipo.MULTDIV_OP, this.linha, this.coluna);
				this.lexema = new StringBuilder();
				break;
			case '/':
				tk = new Token(this.lexema.toString(), TokenTipo.MULTDIV_OP, this.linha, this.coluna);
				this.lexema = new StringBuilder();
				break;
			case ';':
				tk = new Token(this.lexema.toString(), TokenTipo.TERM, this.linha, this.coluna);
				this.lexema = new StringBuilder();
				break;
			case '(':
				tk = new Token(this.lexema.toString(), TokenTipo.L_PAR, this.linha, this.coluna);
				this.lexema = new StringBuilder();
				break;
			case ')':
				tk = new Token(this.lexema.toString(), TokenTipo.R_PAR, this.linha, this.coluna);
				this.lexema = new StringBuilder();
				break;
			default:
				ErrorHandler.getInstance().gravaErro(
						new Erro("Lexico", this.lexema.toString(), this.linha, this.coluna, "Caractere Inválido!"));
				this.lexema = new StringBuilder();
			}
		}
		return tk;
	}
	
	private void descartaComentario() throws IOException {
		char c;
		while (true) {
			c = buffer.getNextChar();
			lexema.append(c);
			if (c == ']') {
				lexema.append(c);
				if (c == ':') {
					lexema.append(c);
					break;
				}
			}
		}
	}
	
	private Token tokenLiteral() {
		// TODO Auto-generated method stub
		return null;
	}

	private Token tokenRelOp() {
		// TODO Auto-generated method stub
		return null;
	}

	private Token tokenNumInt() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Token tokenAttribOp() {
		// TODO Auto-generated method stub
		return null;
	}

	

	private Token tokenId() throws IOException {
		Token tkId = null;
		char c;

		while (true) {
			c = buffer.getNextChar();
			if ((Util.isLetter(c)) || (Util.isDigit(c))) {
				this.lexema.append(c);
			} else {
				buffer.rollbackChar();
				tkId = TabSimbolos.getInstance().instalaToken(lexema.toString(), linha, coluna);
				lexema = new StringBuilder();
				break;
			}
		}
		return tkId;
	}
}
