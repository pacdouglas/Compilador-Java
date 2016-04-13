package br.com.compilador.main;

import java.io.IOException;

import br.com.compilador.AnSintatico;
import br.com.compilador.ErrorHandler;

public class Main {

	private static AnSintatico anSintatico;

	public static void main(String[] args) throws IOException {
		anSintatico = new AnSintatico("arquivo.txt");

		anSintatico.executar();

		ErrorHandler.getInstance().printErros();
	}

}
