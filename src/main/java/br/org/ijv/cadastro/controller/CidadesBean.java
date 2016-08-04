package br.org.ijv.cadastro.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.org.ijv.cadastro.model.Cidade;
import br.org.ijv.cadastro.model.Estado;
import br.org.ijv.cadastro.repository.Cidades;
import br.org.ijv.cadastro.service.CadastroCidadeService;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CidadesBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroCidadeService cadastraCidade;
	
	@Inject
	private Cidades cidades;
	
	@Inject
	protected FacesMessages mensages;
	
	
			
	private List<Cidade> todasCidades;
	private Cidade cidadeEdicao = new Cidade();
	private Cidade cidadeSelecionada;
		
	public void prepararNovoCadastro() {
		cidadeEdicao = new Cidade();
	}
		
	public Estado[] getEstados() { 
		return Estado.values(); 
	}
	
	public void salvar(){		
		cadastraCidade.salvar(cidadeEdicao);		
		consultar();		
		mensages.info("Cidade "+cidadeEdicao.getNomeCidade()+" "+cidadeEdicao.getEstado()+" salvo com sucesso.");
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-cidade:msgs", "frm-cidade:cidades-table"));
	}
	
	public void consultar() {			
		todasCidades = cidades.todasCidades();	
	}
		
	public void excluir(){				
		cadastraCidade.excluir(cidadeSelecionada);
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-cidade:msgs", "frm-cidade:cidades-table"));
	}
	
	public List<Cidade> getTodasCidades() {			
		return todasCidades;
	}
			
	public List<Cidade> getCidades() {
		consultar();
		return todasCidades;
	}

	public Cidade getCidadeEdicao() {
		return cidadeEdicao;
	}
	
	public void setCidadeEdicao(Cidade cidadeEdicao) {
		this.cidadeEdicao = cidadeEdicao;
	}

	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}

	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}	
	
	
}