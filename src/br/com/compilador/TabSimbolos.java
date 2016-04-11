package br.com.compilador;

import java.util.HashMap;

/*
 *  define a Tabela de Símbolos, 
 *  usada para armazenar dados dos Identificadores 
 *  (preferencialmente, um Singleton) 
 */

@SuppressWarnings("serial")
public class TabSimbolos extends HashMap<String, Token>{
	private static TabSimbolos uniqueInstance;
	
	private TabSimbolos(){
		this.configPalavraReservada(new Token("true", TokenTipo.LOGIC_VAL));
		this.configPalavraReservada(new Token("false", TokenTipo.LOGIC_VAL));
		this.configPalavraReservada(new Token("not", TokenTipo.LOGIC_OP));
		this.configPalavraReservada(new Token("and", TokenTipo.LOGIC_OP));
		this.configPalavraReservada(new Token("or", TokenTipo.LOGIC_OP));
		this.configPalavraReservada(new Token("bool", TokenTipo.TYPE));
		this.configPalavraReservada(new Token("text", TokenTipo.TYPE));
		this.configPalavraReservada(new Token("int", TokenTipo.TYPE));
		this.configPalavraReservada(new Token("float", TokenTipo.TYPE));
		this.configPalavraReservada(new Token("program", TokenTipo.PROGRAM));
		this.configPalavraReservada(new Token("end_prog", TokenTipo.END_PROGRAM));
		this.configPalavraReservada(new Token("begin", TokenTipo.BEGIN));
		this.configPalavraReservada(new Token("end", TokenTipo.END));
		this.configPalavraReservada(new Token("if", TokenTipo.IF));
		this.configPalavraReservada(new Token("then", TokenTipo.THEN));
		this.configPalavraReservada(new Token("else", TokenTipo.ELSE));
		this.configPalavraReservada(new Token("for", TokenTipo.FOR));
		this.configPalavraReservada(new Token("while", TokenTipo.WHILE));
		this.configPalavraReservada(new Token("declare", TokenTipo.DECLARE));
		this.configPalavraReservada(new Token("to", TokenTipo.TO));
		
	}
	
	public static synchronized TabSimbolos getInstance(){
		if(uniqueInstance == null){
			uniqueInstance = new TabSimbolos();
		}
		return uniqueInstance;
	}
	
	public void configPalavraReservada(Token token){
		this.put(token.getLexema(), token);
	}
	
	public void printTable(){
		System.out.println("===========================================");
		System.out.println("    Tabela Simbolos (TabSimbolos.class) ");
		System.out.println("===========================================");
		System.out.println("==LEXEMA== ==TOKEN==");
		for(Entry<String, Token> entry : getInstance().entrySet()){
			String key = entry.getKey();
			Token value = entry.getValue();
			
			System.out.println(value.getLexema()+" | "+value.getTipoToken());
			System.out.println("_________________________________________");
		}
		System.out.println("===========================================");
	}
	
}
