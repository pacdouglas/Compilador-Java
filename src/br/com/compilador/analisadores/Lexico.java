/*
 * Nome Alunos:
 * Douglas Martins
 * José Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */
package br.com.compilador.analisadores;

import java.io.EOFException;
import java.io.IOException;

import br.com.compilador.exception.CaractereInvalidoException;
import br.com.compilador.main.FileLoader;
import br.com.compilador.utils.Erro;
import br.com.compilador.utils.ErroHandler;
import br.com.compilador.utils.Mensagens;
import br.com.compilador.utils.TabelaSimbolos;
import br.com.compilador.utils.TipoDeErro;
import br.com.compilador.utils.Token;
import br.com.compilador.utils.TokenTipo;
import br.com.compilador.utils.Util;

public class Lexico {

	private FileLoader leitorDeArquivo;
	private StringBuilder lexema = new StringBuilder();
	private int linha;
	private int coluna;
	private Token tokenArmazenado;

	public Lexico(String path) throws IOException {
		leitorDeArquivo = new FileLoader(path);
	}

	public Token getNextToken() {
		Token tk = null;

		if (tokenArmazenado != null) {
			tk = tokenArmazenado;
			tokenArmazenado = null;
			return tk;
		}

		try {
			tk = this.processa();
		} catch (CaractereInvalidoException e) {
			tk = this.getNextToken();
		} catch (IOException eof) {
			if (lexema.length() > 0) {
				this.gravaErro(TipoDeErro.LEXICO, tk, Mensagens.MSG_FIM_INESPERADO_ARQUIVO, false);
			}
			tk = new Token("EndOfFile", TokenTipo.EOF);
		}

		return tk;
	}

	private Token processa() throws CaractereInvalidoException, IOException {
		Token tk = null;
		char caractere;
		lexema = new StringBuilder();
		do {
			caractere = leitorDeArquivo.getNextChar();
		} while (Character.isWhitespace(caractere));

		lexema.append(caractere);
		linha = leitorDeArquivo.getLine();
		coluna = leitorDeArquivo.getColumn();

		if (Util.ehLetra(caractere)) {
			tk = this.tokenId();
		} else if (Util.ehDigito(caractere)) {
			tk = this.tokenNumInt();
		} else {
			switch (caractere) {
			case ':':
				caractere = leitorDeArquivo.getNextChar();
				if (caractere == '[') {
					leitorDeArquivo.rollbackChar();
					this.descartaComentario();
					tk = this.processa();
				} else {
					leitorDeArquivo.rollbackChar();
					tk = this.tokenAttribOp();
				}
				break;
			case '$':
				tk = this.tokenRelOp();
				break;
			case '\'':
				tk = this.tokenLiteral();
				break;
			case '+':
				tk = new Token(this.lexema.toString(), TokenTipo.ADDSUB_OP, this.linha, this.coluna);
				break;
			case '-':
				tk = new Token(this.lexema.toString(), TokenTipo.ADDSUB_OP, this.linha, this.coluna);
				break;
			case '*':
				tk = new Token(this.lexema.toString(), TokenTipo.MULTDIV_OP, this.linha, this.coluna);
				break;
			case '/':
				tk = new Token(this.lexema.toString(), TokenTipo.MULTDIV_OP, this.linha, this.coluna);
				break;
			case ';':
				tk = new Token(this.lexema.toString(), TokenTipo.TERM, this.linha, this.coluna);
				break;
			case '(':
				tk = new Token(this.lexema.toString(), TokenTipo.L_PAR, this.linha, this.coluna);
				break;
			case ')':
				tk = new Token(this.lexema.toString(), TokenTipo.R_PAR, this.linha, this.coluna);
				break;
			default:
				this.gravaErro(TipoDeErro.LEXICO, tk, Mensagens.MSG_LEXICO_CARACTERE_INVALIDO, false);
				throw new CaractereInvalidoException(Mensagens.MSG_LEXICO_CARACTERE_INVALIDO);
			}
		}
		return tk;
	}

	private void descartaComentario() throws IOException, CaractereInvalidoException {
		int comentarios = 0;
		char caracter = leitorDeArquivo.getNextChar();

		try {
			lexema.append(caracter);
			if (caracter == '[') {
				while (true) {
					caracter = leitorDeArquivo.getNextChar();
					lexema.append(caracter);

					if (caracter == ':') {
						caracter = leitorDeArquivo.getNextChar();
						lexema.append(caracter);
						if (caracter == '[')
							comentarios++;
					}

					if (caracter == ']') {
						caracter = leitorDeArquivo.getNextChar();
						lexema.append(caracter);
						if (caracter == ':') {
							if (comentarios == 0)
								break;
							else
								comentarios--;
						}
					}
				}
			} else {
				ErroHandler.getInstance();
				ErroHandler.gravaErro(new Erro(TipoDeErro.LEXICO, lexema.toString(), linha, coluna,
						Mensagens.MSG_LEXICO_FALTA_FECHAR_COMENTARIO));
				throw new CaractereInvalidoException(Mensagens.MSG_LEXICO_FALTA_FECHAR_COMENTARIO);
			}
		} catch (EOFException eof) {
			ErroHandler.getInstance();
			ErroHandler.gravaErro(new Erro(TipoDeErro.LEXICO, lexema.toString(), linha, coluna,
					Mensagens.MSG_LEXICO_COMENTARIO_MAL_FECHADO));
			throw new CaractereInvalidoException(Mensagens.MSG_LEXICO_COMENTARIO_MAL_FECHADO);
		}
	}

	private Token tokenLiteral() throws IOException {
		Token tkLiteral = null;
		char c;
		boolean achou = false;
		try {
			while (true) {
				c = leitorDeArquivo.getNextChar();
				this.lexema.append(c);
				if (c == '\'') {
					tkLiteral = new Token(this.lexema.toString(), TokenTipo.LITERAL, this.linha, this.coluna);
					achou = true;
					break;
				}
			}
			if (achou == false) {
				throw new EOFException();
			}
		} catch (EOFException e) {
			Token tk = new Token(this.lexema.toString(), TokenTipo.LITERAL, this.linha, this.coluna);
			this.gravaErro(TipoDeErro.LEXICO, tk, Mensagens.MSG_LEXICO_ASPAS_FECHAMENTO_FALTANDO, false);
			tkLiteral = new Token(this.lexema.toString(), TokenTipo.ERROR);
		}

		return tkLiteral;
	}

	private Token tokenRelOp() throws IOException {
		Token tkRelOp = null;
		char c;
		c = leitorDeArquivo.getNextChar();
		boolean validar = false;

		if (c == 'g') {
			this.lexema.append(c);
			c = leitorDeArquivo.getNextChar();
			if (c == 't' || c == 'e') {
				this.lexema.append(c);
				c = leitorDeArquivo.getNextChar();
				if (c == '$') {
					this.lexema.append(c);
					tkRelOp = new Token(this.lexema.toString(), TokenTipo.REL_OP, this.linha, this.coluna);
					validar = true;
				}
			}
		}

		if (validar == false && c == 'l') {
			this.lexema.append(c);
			c = leitorDeArquivo.getNextChar();
			if (c == 't' || c == 'e') {
				this.lexema.append(c);
				c = leitorDeArquivo.getNextChar();
				if (c == '$') {
					this.lexema.append(c);
					tkRelOp = new Token(this.lexema.toString(), TokenTipo.REL_OP, this.linha, this.coluna);
					validar = true;
				}
			}
		}

		if (validar == false && c == 'e') {
			this.lexema.append(c);
			c = leitorDeArquivo.getNextChar();
			if (c == 'q') {
				this.lexema.append(c);
				c = leitorDeArquivo.getNextChar();
				if (c == '$') {
					this.lexema.append(c);
					tkRelOp = new Token(this.lexema.toString(), TokenTipo.REL_OP, this.linha, this.coluna);
					validar = true;
				}
			}
		}

		if (validar == false && c == 'd') {
			this.lexema.append(c);
			c = leitorDeArquivo.getNextChar();
			if (c == 'f') {
				this.lexema.append(c);
				c = leitorDeArquivo.getNextChar();
				if (c == '$') {
					this.lexema.append(c);
					tkRelOp = new Token(this.lexema.toString(), TokenTipo.REL_OP, this.linha, this.coluna);
					validar = true;
				}
			}
		}

		if (validar == false) {
			if (!Character.isWhitespace(c))
				this.lexema.append(c);

			Token tk = new Token(this.lexema.toString(), TokenTipo.REL_OP, this.linha, this.coluna);
			this.gravaErro(TipoDeErro.LEXICO, tk, Mensagens.MSG_LEXICO_OPERADOR_RELACIONAL_INVALIDO, false);

			tkRelOp = new Token("Error", TokenTipo.ERROR);
		}

		return tkRelOp;
	}

	private Token tokenAttribOp() throws IOException {
		Token tkAttribOp = null;
		char c;
		c = leitorDeArquivo.getNextChar();
		if (c == '=') {
			this.lexema.append(c);
			tkAttribOp = new Token(this.lexema.toString(), TokenTipo.ATTRIB_OP, this.linha, this.coluna);
		} else {
			Token tk = new Token(this.lexema.toString(), TokenTipo.ATTRIB_OP, this.linha, this.coluna);
			this.gravaErro(TipoDeErro.LEXICO, tk, Mensagens.MSG_LEXICO_CARACTERE_INVALIDO, false);

			tkAttribOp = new Token(this.lexema.toString(), TokenTipo.ERROR);
		}

		return tkAttribOp;
	}

	private Token tokenNumInt() throws IOException {
		Token tkNumInt = null;
		char c;

		try {
			while (true) {
				c = leitorDeArquivo.getNextChar();
				if (Util.ehDigito(c)) {
					lexema.append(c);
				} else if (Util.ehPonto(c)) {
					this.lexema.append(c);
					tkNumInt = this.tokenNumFloat();
					break;
				} else if (c == 'e' || c == 'E') {
					this.lexema.append(c);
					tkNumInt = this.tokenNumCientifico(TokenTipo.NUM_INT);
					break;
				} else {
					leitorDeArquivo.rollbackChar();
					tkNumInt = new Token(this.lexema.toString(), TokenTipo.NUM_INT, this.linha, this.coluna);
					break;
				}
			}
		} catch (Exception e) {
			Token tk = new Token(this.lexema.toString(), TokenTipo.NUM_INT, this.linha, this.coluna);
			this.gravaErro(TipoDeErro.LEXICO, tk, Mensagens.MSG_LEXICO_ERRO_NAO_TRATADO, false);

		} finally {
			this.lexema = new StringBuilder();
		}

		return tkNumInt;
	}

	private Token tokenNumFloat() throws IOException {
		Token tkNumFloat = null;
		char c = leitorDeArquivo.getNextChar();
		boolean first = false;
		boolean cient = false;
		try {
			while (Util.ehDigito(c)) {
				this.lexema.append(c);
				c = leitorDeArquivo.getNextChar();
				first = true;
			}
			if (Character.isWhitespace(c) && !first) {
				throw new EOFException();
			}
			if ((!Util.ehDigito(c) && c != 'e' && c != 'E' && !first) || ((c == 'e' || c == 'E') && first == false)) {
				if (Util.ehLetra(c) || Util.ehPonto(c) || Util.ehDolar(c) || Util.ehAspa(c)) {
					this.lexema.append(c);
				}
				throw new EOFException();
			}
			if (c == 'e' || c == 'E') {
				this.lexema.append(c);
				tkNumFloat = this.tokenNumCientifico(TokenTipo.NUM_FLOAT);
				cient = true;
			}

		} catch (EOFException e) {
			Token tk = new Token(this.lexema.toString(), TokenTipo.NUM_FLOAT, this.linha, this.coluna);
			this.gravaErro(TipoDeErro.LEXICO, tk, Mensagens.MSG_LEXICO_CARACTERE_INVALIDO_APOS_PONTO, false);

			tkNumFloat = new Token("Erro", TokenTipo.ERROR);
			cient = true;
		}

		if (cient == false) {
			tkNumFloat = new Token(this.lexema.toString(), TokenTipo.NUM_FLOAT, this.linha, this.coluna);
			leitorDeArquivo.rollbackChar();
		}
		return tkNumFloat;
	}

	private Token tokenNumCientifico(TokenTipo tokenTipo) throws IOException {
		Token tkNumCientifico = null;
		char c;
		boolean firstTime = true;
		try {
			c = leitorDeArquivo.getNextChar();
			if (c == '+' || c == '-') {
				this.lexema.append(c);
				firstTime = false;
				c = leitorDeArquivo.getNextChar();
			}
			if (!Util.ehDigito(c) && !firstTime) {
				if (Util.ehLetra(c) || Util.ehPonto(c) || Util.ehDolar(c) || Util.ehAspa(c)) {
					this.lexema.append(c);
				}
				throw new EOFException();
			}
			while (true) {
				if (Util.ehDigito(c)) {
					this.lexema.append(c);
					c = leitorDeArquivo.getNextChar();
				} else {
					break;
				}
			}
			tkNumCientifico = new Token(this.lexema.toString(), tokenTipo, this.linha, this.coluna);
		} catch (EOFException e) {
			Token tk = new Token(this.lexema.toString(), tokenTipo, this.linha, this.coluna);
			this.gravaErro(TipoDeErro.LEXICO, tk, Mensagens.MSG_LEXICO_CARACTERE_INVALIDO_APOS_E, false);

			tkNumCientifico = new Token("Erro", TokenTipo.ERROR);
		}
		leitorDeArquivo.rollbackChar();
		return tkNumCientifico;
	}

	private Token tokenId() throws IOException {
		Token tkId = null;
		char c;

		while (true) {
			c = leitorDeArquivo.getNextChar();
			if ((Util.ehLetra(c)) || (Util.ehDigito(c)) || (c == '_')) {
				this.lexema.append(c);
			} else {
				leitorDeArquivo.rollbackChar();
				tkId = TabelaSimbolos.getInstance().instalaToken(lexema.toString(), linha, coluna);
				lexema = new StringBuilder();
				break;
			}
		}
		return tkId;
	}

	public void gravaErro(TipoDeErro tipoDeErro, Token token, String descricao, boolean guardarToken) {
		Erro erro = new Erro(tipoDeErro, token.getLexema(), token.getLinha(), token.getColuna(), descricao);
		ErroHandler.gravaErro(erro);
	}

	public void armazenaToken(Token t) {
		this.tokenArmazenado = t;
	}

}
