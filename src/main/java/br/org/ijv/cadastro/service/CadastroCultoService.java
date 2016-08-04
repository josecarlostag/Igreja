package br.org.ijv.cadastro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.org.ijv.cadastro.controller.CultosBean;
import br.org.ijv.cadastro.model.Culto;
import br.org.ijv.cadastro.util.jpa.Transactional;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

public class CadastroCultoService extends CultosBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
				
	@Transactional
	public void salvar(Culto culto) {
	
				this.cultos.guardar(culto);

					mensages.info("A reunião da igreja "+culto.getCongregacao().getNomeCongregacao()+" foi cadastrada com sucesso.");
			}
	
	@Transactional 
	public void excluir(Culto cultoSelecionado) {
	 
		this.cultos.remover(cultoSelecionado);
		
				mensages.info("A congregação "+ cultoSelecionado.getReuniao()+ " foi excluida com sucesso.");
						
		this.cultoSelecionado = null;
		this.cultoEdicao = null;
		consultar();
	}	
}
