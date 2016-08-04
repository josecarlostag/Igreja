package br.org.ijv.cadastro.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.model.PerfilUsuario;
import br.org.ijv.cadastro.repository.Congregacoes;
import br.org.ijv.cadastro.security.Seguranca;
import br.org.ijv.cadastro.service.CadastroCongregacaoService;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CongregacoesBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
	
	@Inject
	private Seguranca usuario;
	
	@Inject
	CadastroCongregacaoService cadastraCongregacao;
	
	@Inject
	protected Congregacoes congregacoes;
		
	protected List<Congregacao> todasCongregacoes;
	protected Congregacao congregacaoEdicao = new Congregacao();
	protected Congregacao congregacaoSelecionada;

	public void prepararNovoCadastro() {
		congregacaoEdicao = new Congregacao();
	}
	
	public void salvar(){		
		
		cadastraCongregacao.salvar(congregacaoEdicao);		
		consultar();		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-congregacao:msgs", "frm-congregacao:congregacoes-table"));
	}
				
	public void consultar() {			
		todasCongregacoes = congregacoes.todasCongregacoes();	
	}
			
	
	public List<Congregacao> getTodasCongregacoes() {
		return todasCongregacoes;
	}


	public List<Congregacao> getCongregacoes() {
		consultar();
		return todasCongregacoes;
	}
	
	public List<Congregacao> getCongregacaoUsuario() {
		
		if(usuario.getPerfil() == PerfilUsuario.ADMIN){
			
			  todasCongregacoes = congregacoes.todasCongregacoes();	
				
			}else{
		
		todasCongregacoes = congregacoes.congregacoesUsuario(usuario);	
			}
		return todasCongregacoes;
	}

	public Congregacao getCongregacaoEdicao() {
		return congregacaoEdicao;
	}
	
	public void setCongregacaoEdicao(Congregacao congregacaoEdicao) {
		this.congregacaoEdicao = congregacaoEdicao;
	}
	
	public Congregacao getCongregacaoSelecionada() {
		return congregacaoSelecionada;
	}

	public void setCongregacaoSelecionada(Congregacao congregacaoSelecionada) {
		this.congregacaoSelecionada = congregacaoSelecionada;
	}

	public CadastroCongregacaoService getCadastraCongregacao() {
		return cadastraCongregacao;
	}
	
	public void excluir(){				
		cadastraCongregacao.excluir(congregacaoSelecionada);
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-congregacao:msgs", "frm-congregacao:congregacoes-table", "frm-Congregacao.toolbar"));
	}
	
}
