/*
 * Nome Alunos:
 * Douglas Martins
 * José Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */
package br.com.compilador.main;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;

import br.com.compilador.Util;

/*
 *  classe usada pelo AnLexico, 
 *  responsável pela leitura do arquivo de entrada. 
 *  Possui métodos para a leitura de caracteres e controle posicional 
 * 
 */

public class FileLoader {
	public BufferedReader buffer;

	private int linha;
	private int coluna;
	private int lineBreak;

	private boolean finalLinha = false;

	public final int EOF_CHAR = -1;

	private static char lastChar;

	public FileLoader(String path) throws IOException {
		this.linha = 1;
		this.coluna = 0;
		this.buffer = new BufferedReader(new FileReader(path));
	}

	public char getNextChar() throws IOException {
		this.buffer.mark(1);
		int aux = this.buffer.read();

		if (aux == EOF_CHAR && !Util.isDigit(FileLoader.getLastChar()) && !Util.isLetter(FileLoader.getLastChar())) {
			throw new EOFException();
		}

		char result = (char) aux;

		this.controlLineColumn(result);

		FileLoader.setLastChar(result);

		return result;
	}

	public void controlLineColumn(char c) {
		if (this.finalLinha) {
			this.linha++;
			this.coluna = 0;
			this.finalLinha = false;
		}

		this.setUltimaColuna(this.coluna);

		if (c == '\n') {
			this.finalLinha = true;
		}
		this.coluna++;
	}

	public int getLine() {
		return linha;
	}

	public int getColumn() {
		return coluna;
	}

	public String getElement() {
		return "[" + this.getLine() + "," + this.getColumn() + "]";
	}

	public int getUltimaColuna() {
		return lineBreak;
	}

	public void setUltimaColuna(int ultimaColuna) {
		this.lineBreak = ultimaColuna;
	}

	public void rollbackChar() throws IOException {
		buffer.reset();
	}

	public static char getLastChar() {
		return lastChar;
	}

	public static void setLastChar(char lastChar) {
		FileLoader.lastChar = lastChar;
	}
}
