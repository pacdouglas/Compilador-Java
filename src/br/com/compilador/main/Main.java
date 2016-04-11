package br.com.compilador.main;

import java.io.IOException;

import br.com.compilador.TabSimbolos;
import br.com.compilador.Token;
import br.com.compilador.TokenTipo;

public class Main {

	public static void main(String[] args) throws IOException {
		FileLoader teste = new FileLoader("C:\\Users\\dougl\\workspace\\Compilador-Java\\arquivo.txt");
		
		
		char t = teste.getNextChar();
		System.out.println(t+" "+teste.getElement());
		for (int i = 0; i < 20; i++) {
			t = teste.getNextChar();
			System.out.println(t+" "+teste.getElement());
		}
		
		Token teste2 = new Token("Lexema", TokenTipo.BEGIN);
		
		System.out.println(teste2.getLexema());
		System.out.println(teste2.getTipoToken());
		
		TabSimbolos teste3 = TabSimbolos.getInstance();
		
		teste3.printTable();
	}

}
