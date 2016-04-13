package br.com.compilador;

public class Util {
	static boolean isLetter(char c) {
		int dec = (int) c;

		return (dec > 64 && dec < 91) || (dec > 96 && dec < 123) || (dec == 95);
	}
	
	static boolean isDigit(char c) {
		int dec = (int) c;

		return (dec > 47 && dec < 58);
	}

	static boolean isDot(char c) {
		int dec = (int) c;
		return dec == 46;
	}

	static boolean isQuote(char c) {
		int dec = (int) c;
		return dec == 39;
	}

	static boolean isDollar(char c) {
		int dec = (int) c;
		return dec == 36;
	}
}
