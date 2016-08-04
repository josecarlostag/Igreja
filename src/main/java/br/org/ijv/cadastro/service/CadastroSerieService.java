package br.org.ijv.cadastro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.org.ijv.cadastro.controller.SeriesBean;
import br.org.ijv.cadastro.model.Serie;
import br.org.ijv.cadastro.util.jpa.Transactional;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

public class CadastroSerieService extends SeriesBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
				
	@Transactional
	public void salvar(Serie serie) {
	
				this.series.guardar(serie);
				
					mensages.info("A série  "+serie.getNomeSerie()+" foi cadastrada com sucesso.");
				
			}
	
	@Transactional 
	public void excluir(Serie serieSelecionada) {
	 
		this.series.remover(serieSelecionada);
				
				mensages.info("A série "+ serieSelecionada.getNomeSerie()+ " foi excluida com sucesso.");
				
		this.serieSelecionada = null;
		this.serieEdicao = null;
		consultar();
	}	
}

