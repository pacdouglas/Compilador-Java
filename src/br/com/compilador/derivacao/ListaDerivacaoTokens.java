/*
 * Nome Alunos:
 * Douglas Martins
 * Jos� Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */

package br.com.compilador.derivacao;

import br.com.compilador.utils.TokenTipo;

public interface ListaDerivacaoTokens {
	public void adiciona(TokenTipo token);
	public boolean verificaSeContem(TokenTipo token);
}
