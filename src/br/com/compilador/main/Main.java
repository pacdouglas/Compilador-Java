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

import br.com.compilador.analisadores.Sintatico;

public class Main {

	private static Sintatico analisadorSintatico;

	public static void main(String[] args) throws IOException {
		analisadorSintatico = new Sintatico("arquivoTeste.txt");
		analisadorSintatico.executar();
	}

}
