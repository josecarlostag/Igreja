package br.org.ijv.cadastro.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.org.ijv.cadastro.model.Culto;
import br.org.ijv.cadastro.model.PerfilUsuario;
import br.org.ijv.cadastro.repository.Cultos;
import br.org.ijv.cadastro.security.Seguranca;
import br.org.ijv.cadastro.service.CadastroCultoService;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

@Named
@ViewScoped
public class CultosBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
	
	@Inject
	private Seguranca usuario;
	
	@Inject
	CadastroCultoService cadastraCulto;
	
	@Inject
	protected Cultos cultos;
		
	protected List<Culto> todosCultos;
	protected Culto cultoEdicao = new Culto();
	protected Culto cultoSelecionado;

	public void prepararNovoCadastro() {
		cultoEdicao = new Culto();
	}
	
	public void salvar(){		
		
		cadastraCulto.salvar(cultoEdicao);		
		consultar();		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-culto:msgs", "frm-culto:cultos-table"));
	}
				
	public void consultar() {			
		todosCultos = cultos.todosCultos();	
	}
			
	public List<Culto> getTodosCultos() {
		return todosCultos;
	}
	
	public List<Culto> getCultoCongregacao() {
		
		if(usuario.getPerfil() == PerfilUsuario.ADMIN){
			
		  todosCultos = cultos.todosCultos();	
			
		}else{
		
		todosCultos = cultos.cultoCongregacao(usuario.getUsuarioLogado().getUsuario().getCongregacao());
		
		}
		return todosCultos;
	}


	public List<Culto> getCultos() {
		consultar();
		return todosCultos;
	}

	public Culto getCultoEdicao() {
		return cultoEdicao;
	}
	
	public void setCultoEdicao(Culto cultoEdicao) {
		this.cultoEdicao = cultoEdicao;
	}
	
	public Culto getCultoSelecionado() {
		return cultoSelecionado;
	}

	public void setCultoSelecionado(Culto cultoSelecionado) {
		this.cultoSelecionado = cultoSelecionado;
	}

	public CadastroCultoService getCadastraCulto() {
		return cadastraCulto;
	}
	
	public void excluir(){				
		cadastraCulto.excluir(cultoSelecionado);
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-culto:msgs", "frm-culto:cultos-table", "frm-Culto.toolbar"));
	}
	
}
