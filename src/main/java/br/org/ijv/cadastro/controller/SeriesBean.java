package br.org.ijv.cadastro.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.org.ijv.cadastro.model.Serie;
import br.org.ijv.cadastro.repository.Series;
import br.org.ijv.cadastro.service.CadastroSerieService;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

@Named
@ViewScoped
public class SeriesBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroSerieService cadastraSerie;
	
	@Inject
	protected Series series;
	
	@Inject
	protected FacesMessages mensages;
				
	protected List<Serie> todasSeries;
	protected Serie serieEdicao = new Serie();
	protected Serie serieSelecionada;
		
	public void prepararNovoCadastro() {
		serieEdicao = new Serie();
	}
			
	public void salvar(){		
		cadastraSerie.salvar(serieEdicao);		
		consultar();		
		mensages.info("Serie "+serieEdicao.getNomeSerie()+"foi salva com sucesso.");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-serie:msgs", "frm-serie:series-table"));
	}
	
	public void consultar() {			
		todasSeries = series.todasSerie();	
	}
		
	public void excluir(){				
		cadastraSerie.excluir(serieSelecionada);
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-serie:msgs", "frm-serie:series-table"));
	}
				
	public List<Serie> getTodasSeries() {
		consultar();
		return todasSeries;
	}
	
	public Serie getSerieEdicao() {
		return serieEdicao;
	}

	public void setSerieEdicao(Serie serieEdicao) {
		this.serieEdicao = serieEdicao;
	}

	public Serie getSerieSelecionada() {
		return serieSelecionada;
	}

	public void setSerieSelecionada(Serie serieSelecionada) {
		this.serieSelecionada = serieSelecionada;
	}

	public Series getSeries() {
		return series;
	}	
}
