package br.org.ijv.cadastro.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.org.ijv.cadastro.model.Casa;
import br.org.ijv.cadastro.model.DiaSemana;
import br.org.ijv.cadastro.model.PerfilUsuario;
import br.org.ijv.cadastro.model.TipoCasa;
import br.org.ijv.cadastro.repository.Casas;
import br.org.ijv.cadastro.security.Seguranca;
import br.org.ijv.cadastro.service.CadastroCasaService;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CasasBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
	
	@Inject
	CadastroCasaService cadastraCasa;
	
	@Inject
	private Seguranca usuario;
		
	@Inject
	protected Casas casas;
			
	protected List<Casa> todasCasasBencaos;
	protected List<Casa> todasCasasCrescimento;
	protected Casa casaEdicao = new Casa();
	protected Casa casaSelecionada;
	
	public DiaSemana[] getDiaSemana() {
		return DiaSemana.values();
	}
	
	public TipoCasa[] getTipoCasa() {
		return TipoCasa.values();
	}

	public void prepararNovaCasaBencao() {
		casaEdicao = new Casa();
		casaEdicao.setTipo(TipoCasa.Benção);
	}
	
	public void prepararNovaCasaCrescimento() {
		casaEdicao = new Casa();
		casaEdicao.setTipo(TipoCasa.Crescimento);
	}
	
	public void salvar(){		
		
		cadastraCasa.salvar(casaEdicao);		
		consultar();
		if(casaEdicao.getTipo().equals(TipoCasa.Benção)){
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-casa-bencao:msgs", "frm-casa-bencao:casas-bencao-table"));
	}else{
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-casa-crescimento:msgs", "frm-casa-crescimento:casas-crescimento-table"));
		}
	}
	
				
	public void consultar() {			
		todasCasasBencaos = casas.todasCasasBencaos();
		todasCasasCrescimento = casas.todasCasasCrescimento();
	}
			
	public List<Casa> getTodasCasasBencaos() {
		consultar();
		return todasCasasBencaos;
	}
	
	

	public List<Casa> getTodasCasasCrescimento() {
		consultar();
		return todasCasasCrescimento;
	}
	
	public List<Casa> getCasasBencaosCongregacao() {
		
		if(usuario.getPerfil() == PerfilUsuario.ADMIN){
			
		  todasCasasBencaos = casas.todasCasasBencaos();	
			
		}else{
		
		todasCasasBencaos = casas.bencaoCongregacao(usuario.getUsuarioLogado().getUsuario().getCongregacao());
		
		}
		return todasCasasBencaos;
	}
	
		public List<Casa> getCasasCrescimentoCongregacao() {
		
		if(usuario.getPerfil() == PerfilUsuario.ADMIN){
			
		  todasCasasCrescimento = casas.todasCasasCrescimento();	
			
		}else{
		
		todasCasasCrescimento = casas.crescimentoCongregacao(usuario.getUsuarioLogado().getUsuario().getCongregacao());
		
		}
		return todasCasasCrescimento;
	}

	public Casas getCasas() {
		
	return casas;
	}

	public Casa getCasaEdicao() {
		return casaEdicao;
	}

	public void setCasaEdicao(Casa casaEdicao) {
		this.casaEdicao = casaEdicao;
	}

	public Casa getCasaSelecionada() {
		return casaSelecionada;
	}

	public void setCasaSelecionada(Casa casaSelecionada) {
		this.casaSelecionada = casaSelecionada;
	}

	public CadastroCasaService getCadastraCasa() {
		return cadastraCasa;
	}

	public void excluir(){				
		cadastraCasa.excluir(casaSelecionada);
		consultar();
		if(casaEdicao.getTipo()== TipoCasa.Benção){
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-casa-bencao:msgs", "frm-casa-bencao:casas-bencao-table", "frm-Casa-bencao.toolbar"));
		}else{
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm-casa-crescimento:msgs", "frm-casa-crescimento:casas-crescimento-table"));
			}
	}
	
}
