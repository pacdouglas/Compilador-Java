/*
 * Nome Alunos:
 * Douglas Martins
 * Jos� Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */
package br.com.compilador;

/*
 * nesta etapa, esta classe ser� apenas um "boneco" para testar o AnLexico. 
 * Classe execut�vel (m�todo main) que deve ser capaz de acionar todas 
 * as partes do Analisador, desempenhando as fun��es abaixo: 
 * - receber o nome de um arquivo-fonte como argumento 
 * - criar inst�ncia de AnLexico, passando o nome desse arquivo como par�metro 
 * - chamar o m�todo nextToken() at� que um token EOF seja produzido 
 * - escrever o nome de cada token e seu lexema (quando existir) na sa�da padr�o, 
 * em ordem de ocorr�ncia 
 * - exibir o relat�rio de erros, se existir algum 
 * 
 */

import java.io.IOException;

public class AnSintatico {
	AnLexico anLexico;
	private Token token;
	public AnSintatico(String path) throws IOException {
		anLexico = new AnLexico(path);
	}

	public void executar() {
		System.out.println("=========================");
		System.out.println("     Analixador L�xico");
		System.out.println("=========================");
		do {
			token = anLexico.nextToken();
			if (token.getTipoToken() != TokenTipo.ERROR) {
				System.out.println("Lexema: " + token.getLexema());
				System.out.println("Tipo Token: " + token.getTipoToken());
				if (token.getTipoToken() != TokenTipo.EOF) {
					System.out.println("Linha: " + token.getLinha());
					System.out.println("Coluna: " + token.getColuna());
				}
				System.out.println("=========================");
			}
		} while (token.getTipoToken() != TokenTipo.EOF);
	}

}
