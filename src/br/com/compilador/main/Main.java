/*
 * Nome Alunos:
 * Douglas Martins
 * José Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */

package br.com.compilador.main;

import java.io.IOException;

import br.com.compilador.AnSintatico;
import br.com.compilador.ErrorHandler;
import br.com.compilador.TabSimbolos;

public class Main {

	private static AnSintatico anSintatico;

	public static void main(String[] args) throws IOException {
		anSintatico = new AnSintatico("arquivo.txt");

		anSintatico.executar();

		ErrorHandler.getInstance().printErros();

		TabSimbolos.getInstance().printToken();
		System.out.println("Fim");
	}

}
