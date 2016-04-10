package br.com.compilador.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	private int ultimaColuna;

	private boolean finalLinha = false;

	// Construtor
	public FileLoader(String path) throws IOException {
		this.linha = 1;
		this.coluna = 0;
		this.buffer = new BufferedReader(new FileReader(path));
	}
	
	// Retorna o proximo char
	public char getNextChar() throws IOException {
		this.buffer.mark(1);
		char aux = (char) this.buffer.read();
		this.controlLineColumn(aux);
		return aux;
	}

	// Controla contadores das linhas e colunas
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

	// Retorna Linha Atual
	public int getLine() {
		return linha;
	}

	// Retorna Coluna Atual
	public int getColumn() {
		return coluna;
	}

	// Retornar o elemento atual em String
	public String getElement() {
		return "[" + this.getLine() + "," + this.getColumn() + "]";
	}

	public int getUltimaColuna() {
		return ultimaColuna;
	}

	public void setUltimaColuna(int ultimaColuna) {
		this.ultimaColuna = ultimaColuna;
	}

}
