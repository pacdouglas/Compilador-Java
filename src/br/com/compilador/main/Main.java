package br.com.compilador.main;

import java.io.IOException;

import br.com.compilador.AnSintatico;

public class Main {

	public static void main(String[] args) throws IOException {
		FileLoader teste = new FileLoader("arquivo.txt");
		
		
		char t = teste.getNextChar();
		System.out.println(t+" "+teste.getElement());
		for (int i = 0; i < 20; i++) {
			t = teste.getNextChar();
			System.out.println(t+" "+teste.getElement());
		}
		
		AnSintatico anSintatico = new AnSintatico("arquivo.txt");
		anSintatico.executar();
	}

}
