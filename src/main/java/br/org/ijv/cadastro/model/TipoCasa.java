package br.org.ijv.cadastro.model;

public enum TipoCasa {
	
	Benção("Casa de Benção"),
	Crescimento("Casa de Crescimento");
	
	
	private String descricao;

	TipoCasa(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
