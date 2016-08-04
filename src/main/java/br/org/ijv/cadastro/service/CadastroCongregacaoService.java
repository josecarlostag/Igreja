package br.org.ijv.cadastro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.org.ijv.cadastro.controller.CongregacoesBean;
import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.util.jpa.Transactional;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

public class CadastroCongregacaoService extends CongregacoesBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
				
	@Transactional
	public void salvar(Congregacao congregacao) {
	
				this.congregacoes.guardar(congregacao);

					mensages.info("A congregação "+ congregacao.getNomeCongregacao()+ " foi cadastrada com sucesso.");
			}
	
	@Transactional 
	public void excluir(Congregacao congregacaoSelecionada) {
	 
		this.congregacoes.remover(congregacaoSelecionada);
		
				mensages.info("A congregação "+ congregacaoSelecionada.getNomeCongregacao()+ " foi excluida com sucesso.");
				
		
		this.congregacaoSelecionada = null;
		this.congregacaoEdicao = null;
		consultar();
	}	
}
