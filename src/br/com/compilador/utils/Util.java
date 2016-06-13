/*
 * Nome Alunos:
 * Douglas Martins
 * José Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */
package br.com.compilador.utils;

public class Util {
	public static boolean ehLetra(char c) {
		int dec = (int) c;

		return (dec > 64 && dec < 91) || (dec > 96 && dec < 123) || (dec == 95);
	}

	public static boolean ehDigito(char c) {
		int dec = (int) c;

		return (dec > 47 && dec < 58);
	}

	public static boolean ehPonto(char c) {
		int dec = (int) c;
		return dec == 46;
	}

	public static boolean ehAspa(char c) {
		int dec = (int) c;
		return dec == 39;
	}

	public static boolean ehDolar(char c) {
		int dec = (int) c;
		return dec == 36;
	}

	public static boolean isAllNumbersAfterDot(StringBuilder lexema) {
		String s = lexema.toString();
		char[] c = s.toCharArray();
		int dot = 0;
		boolean verif = true;
		boolean entrou = false;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '.') {
				dot = i;
			}
		}

		for (int i = dot + 1; i < c.length; i++) {
			if ((!Util.ehDigito(c[i]))) {
				verif = false;
				entrou = true;
			}
		}

		if (entrou == false) {
			verif = false;
		}
		return verif;
	}
	
	public static String completaStringComEspacos(String original, int tamanhoDesejado){
		String novaString = original;
		int tamanhoOriginal = original.length();
		
		for (int i = tamanhoOriginal; i < tamanhoDesejado-1; i++) {
			novaString += " ";
		}
		
		novaString += "|";
		return novaString;
	}
	
	
}
