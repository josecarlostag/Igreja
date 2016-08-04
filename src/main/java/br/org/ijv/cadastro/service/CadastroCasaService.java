package br.org.ijv.cadastro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.org.ijv.cadastro.controller.CasasBean;
import br.org.ijv.cadastro.model.Casa;
import br.org.ijv.cadastro.model.TipoCasa;
import br.org.ijv.cadastro.util.jpa.Transactional;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

public class CadastroCasaService extends CasasBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
				
	@Transactional
	public void salvar(Casa casaBencao) {
	
				this.casas.guardar(casaBencao);
				if(casaBencao.getTipo().equals(TipoCasa.Benção)){
					mensages.info("A casa de benção do  "+casaBencao.getAnfitriao()+" foi cadastrada com sucesso.");
				}else{
					mensages.info("A casa de Crescimento do  "+casaBencao.getAnfitriao()+" foi cadastrada com sucesso.");
				}
			}
	
	@Transactional 
	public void excluir(Casa casaSelecionada) {
	 
		this.casas.remover(casaSelecionada);
				if(casaSelecionada.getTipo()== TipoCasa.Benção){
				mensages.info("A casa de benção do "+ casaSelecionada.getAnfitriao()+ " foi excluida com sucesso.");
				}else{
					mensages.info("A casa de Crescimento do "+ casaSelecionada.getAnfitriao()+ " foi excluida com sucesso.");	
				}
		this.casaSelecionada = null;
		this.casaEdicao = null;
		consultar();
	}	
}

