package br.org.ijv.cadastro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.org.ijv.cadastro.controller.LicoesBean;
import br.org.ijv.cadastro.model.Licao;
import br.org.ijv.cadastro.util.jpa.Transactional;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

public class CadastroLicaoService extends LicoesBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
				
	@Transactional
	public void salvar(Licao licao) {
	
				this.licoes.guardar(licao);
					
				
					mensages.info("A lição "+licao.getNomeLicao()+" foi cadastrada com sucesso.");
					
			}
	
	@Transactional 
	public void excluir(Licao licaoSelecionada) {
	 
		this.licoes.remover(licaoSelecionada);
				
				mensages.info("A lição "+ licaoSelecionada.getNomeLicao()+ " foi excluida com sucesso.");
				
		this.licaoSelecionada = null;
		this.licaoEdicao = null;
		consultar();
	}	
}

