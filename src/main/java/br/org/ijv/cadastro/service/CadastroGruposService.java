package br.org.ijv.cadastro.service;

import java.io.Serializable;

import br.org.ijv.cadastro.controller.GruposBean;
import br.org.ijv.cadastro.model.Grupo;
import br.org.ijv.cadastro.util.jpa.Transactional;

public class CadastroGruposService extends GruposBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
				
	@Transactional
	public void salvar(Grupo grupo) {
	
				this.grupos.guardar(grupo);

					mensages.info("O "+ grupo.getNome()+ " foi cadastrado com sucesso.");
			}
	
	@Transactional 
	public void excluir(Grupo grupoSelecionado) {
	 
		this.grupos.remover(grupoSelecionado);
		
				mensages.info("A "+ grupoSelecionado.getNome()+ " foi excluido com sucesso.");
				
		
		this.grupoSelecionado = null;
		this.grupoEdicao = null;
		consultar();
	}
}