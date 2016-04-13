package br.com.compilador.main;

import java.io.IOException;

import br.com.compilador.AnLexico;
import br.com.compilador.AnSintatico;
import br.com.compilador.Token;

public class Main {

	public static void main(String[] args) throws IOException {
		FileLoader teste = new FileLoader("arquivo.txt");
		
		
		char t = teste.getNextChar();
		System.out.println(t+" "+teste.getElement());
		for (int i = 0; i < 20; i++) {
			t = teste.getNextChar();
			System.out.println(t+" "+teste.getElement());
		}
		
		
		
		AnLexico anLexico = new AnLexico("arquivo.txt");
		Token teste5 = anLexico.nextToken();
		
		System.out.println(teste5.getLexema());
		System.out.println(teste5.getTipoToken());
		System.out.println(teste5.getLinha());
		System.out.println(teste5.getColuna());

	}

}
