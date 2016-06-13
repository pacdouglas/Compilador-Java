/*
 * Nome Alunos:
 * Douglas Martins
 * José Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */

package br.com.compilador.derivacao;

import java.util.ArrayList;

import br.com.compilador.utils.TokenTipo;

public class DerivacaoBase implements ListaDerivacaoTokens {

	protected final ArrayList<TokenTipo> listaDeTokens;

	public DerivacaoBase() {
		listaDeTokens = new ArrayList<>();
	}

	@Override
	public void adiciona(TokenTipo token) {
		this.listaDeTokens.add(token);
	}

	@Override
	public boolean verificaSeContem(TokenTipo token) {
		return this.listaDeTokens.contains(token);
	}

}
