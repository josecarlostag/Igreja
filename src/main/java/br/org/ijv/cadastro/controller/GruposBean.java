package br.org.ijv.cadastro.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.org.ijv.cadastro.model.Grupo;
import br.org.ijv.cadastro.repository.Grupos;
import br.org.ijv.cadastro.service.CadastroGruposService;
import br.org.ijv.cadastro.util.jsf.FacesMessages;


	
	@Named
	@ViewScoped
	public class GruposBean implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		@Inject
		protected FacesMessages mensages;
		
		@Inject
		CadastroGruposService cadastraGrupos;
		
		@Inject
		protected Grupos grupos;
			
		protected List<Grupo> todosGrupos;
		protected Grupo grupoEdicao = new Grupo();
		protected Grupo grupoSelecionado = new Grupo();

		public void prepararNovoCadastro() {
			grupoEdicao = new Grupo();
		}
		
		public void salvar(){		
			
			cadastraGrupos.salvar(grupoEdicao);		
			consultar();		
			RequestContext.getCurrentInstance().update(
					Arrays.asList("cadastro-form:msgs", "usuario-form:usuarios-table"));
		}
					
		public void consultar() {			
			todosGrupos = grupos.todosGrupos();	
		}
				
		
		public List<Grupo> getTodosGrupos() {
			return todosGrupos;
		}


		public List<Grupo> getGrupos() {
			consultar();
			return todosGrupos;
		}

		public Grupo getGrupoEdicao() {
			return grupoEdicao;
		}
		
		public void setCongregacaoEdicao(Grupo grupoEdicao) {
			this.grupoEdicao = grupoEdicao;
		}
		
		public Grupo getGrupoSelecionado() {
			return grupoSelecionado;
		}

		public void setCongregacaoSelecionada(Grupo grupoSelecionado) {
			this.grupoSelecionado = grupoSelecionado;
		}

		public CadastroGruposService getCadastraGrupo() {
			return cadastraGrupos;
		}
		
		public void excluir(){				
			cadastraGrupos.excluir(grupoSelecionado);
			consultar();
			RequestContext.getCurrentInstance().update(
					Arrays.asList("frm-pessoa:msgs", "frm-pessoa:pessoas-table", "frm-Congregacao.toolbar"));
		}

}
