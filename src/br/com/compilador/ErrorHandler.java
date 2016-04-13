package br.com.compilador;

import java.util.ArrayList;


/*
 * tratador de erros. 
 * Em geral, deve manter registro de todos os erros 
 * encontrados durante a compilação, e 
 * ser capaz de exibir um relatório formatado no final
 *
 */
public class ErrorHandler {
	public static ErrorHandler instance = new ErrorHandler();
	private ArrayList<Erro> listError = new ArrayList<Erro>();
	
	private ErrorHandler(){
		listError = new ArrayList<>();
	}
	
	public static ErrorHandler getInstance() {
		return ErrorHandler.instance;
	}
	
	public void gravaErro(Erro erro){
		listError.add(erro);
	}
	
	public void printErros(){
		if(listError.size() > 0){
			System.out.println("==================================================");
			System.out.println("                   Erros encontrados");
			System.out.println("==================================================");
			for (Erro erro : listError) {
				System.out.println("Tipo do erro: " + erro.getErroTipo());
	            System.out.println("Lexema: " + erro.getLexema());
	            System.out.println("Linha: " + erro.getLinha());
	            System.out.println("Coluna: "+erro.getColuna());
	            System.out.println("Descrição: " + erro.getDescricao());
	            System.out.println("==================================================");
			}
		}else {
			System.out.println("==================================================");
			System.out.println("            Nenhum Erro foi encontrado ");
			System.out.println("==================================================");
		}
	}
}	
