package br.org.ijv.cadastro.model;

public enum EstadoCivil {
	Casado("Casado"),
	Divorciado("Divorciado"),
	Solteiro("Solteiro"),
	Uniao("União Estável"),
	Separado("Separado"),
	Viúvo("Viúvo");
		
	private String descricao;
	
	EstadoCivil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	
}
