/*
 * Nome Alunos:
 * Douglas Martins
 * José Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */
package br.com.compilador.utils;

import java.util.ArrayList;
import java.util.List;

public class ErroHandler {

	public static ErroHandler instance = new ErroHandler();
	private ArrayList<Erro> erros = new ArrayList<Erro>();

	private ErroHandler() {
	}

	public static ErroHandler getInstance() {
		return ErroHandler.instance;
	}

	public static void gravaErro(Erro erro) {
		ErroHandler.getInstance().erros.add(erro);
	}

	public void exibeErros() {
		List<String> errosEmString = new ArrayList<String>();
		
		for (int i = 0; i < getInstance().erros.size(); i++) {
			switch (erros.get(i).getErroTipo()){
				case LEXICO:
					errosEmString.add("(" + erros.get(i).getLinha() + "," + erros.get(i).getColuna() + ")" + " - " + erros.get(i).getDescricao());
				break;
					
				case SINTATICO:
				case SEMANTICO:
					errosEmString.add("| " + "Linha: " + erros.get(i).getLinha() + " / " + erros.get(i).getDescricao());
				break;
			}
		}
		
		if (errosEmString.isEmpty()){
			System.out.println("======================================================================================");
			System.out.println("|                            CÓDIGO COMPILADO COM SUCESSO                            |");
			System.out.println("======================================================================================");			
		}
		else {
			System.out.println("======================================================================================");
			System.out.println("|                           RELATÓRIO DE ERROS ENCONTRADOS                           |");
			System.out.println("======================================================================================");
			
			for (int i = 0; i < errosEmString.size(); i++) {
				System.out.println(Util.completaStringComEspacos(errosEmString.get(i), 86));
			}
			
			System.out.println("======================================================================================");
		}
	}
}
