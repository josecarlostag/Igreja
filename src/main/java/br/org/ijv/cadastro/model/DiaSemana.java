package br.org.ijv.cadastro.model;

public enum DiaSemana {
	
	DOMINGO("Domingo"),
	SEGUNDA("Segunda feira"),
	TERÇA("Terça feira"),
	QUARTA("Quarta feira"),
	QUINTA("Quinta feira"),
	SEXTA("Sexta feira"),
	SABADO("sABADO");
	
	private String descricao;

	private DiaSemana(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
