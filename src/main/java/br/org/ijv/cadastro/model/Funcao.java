package br.org.ijv.cadastro.model;

public enum Funcao {
	
	Coreografo("Coreografo"),
	Colaborador("Colaborador"),
	Crianças("Crianças"),
	Dança("Dança"),
	Diácono("Diácono"),
	Intercessor("Intercessor"),
	Lider("Líder de célula"),
	Adolescente("Adolescente"),
	Louvor("Louvor"),
	Membro("Membro"),
	Missões("Missões"),
	Pastor("Pastor"),
	Presidente("Pastor Presidente");
		
	private String descricao;
	
	Funcao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	
}