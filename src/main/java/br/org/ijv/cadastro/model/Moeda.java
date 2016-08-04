package br.org.ijv.cadastro.model;

public enum Moeda {
	
	DINHEIRO("Dínheiro"),
	MOEDAS("Moedas"),
	CHEQUE("Cheque"),
	CARTÃO("Cartão"),
	DEPÓSITO("Depósito");
	
	private String descricao;

	Moeda(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
