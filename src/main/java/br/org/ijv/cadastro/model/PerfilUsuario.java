package br.org.ijv.cadastro.model;

public enum PerfilUsuario {
	
	ADMIN("Administrador do sistema"),
	USUÁRIO("Usuário do sistema"),
	PASTOR("Pastor");
	
	private String descricao;

	PerfilUsuario(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
