package br.org.ijv.cadastro.model;


public enum Status {
	
	ATIVO("Ativo"),
	INATIVO("Inativo"),
	FALECIDO("Falecido");
	
	
	private String descricao;

	Status(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}

