package br.org.ijv.cadastro.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.org.ijv.cadastro.model.PerfilUsuario;
import br.org.ijv.cadastro.model.Usuario;
import br.org.ijv.cadastro.repository.Usuarios;
import br.org.ijv.cadastro.service.CadastroUsuarioService;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

@Named
@ViewScoped
public class UsuariosBean implements Serializable {

	private static final long serialVersionUID = 1L;
			
	private Usuario usuarioSelecionado;
	
	@Inject
	protected Usuarios usuarios;
	
	private List<Usuario> todosUsuarios;
	
	@Inject
	protected FacesMessages mensages;
	
	public void consultar() {			
		todosUsuarios = usuarios.todosUsuarios();	
	}
	
	protected Usuario usuarioEdicao = new Usuario();
	
	@Inject
	private CadastroUsuarioService cadastraUsuario;
	
	public void prepararNovoCadastro() {
		usuarioEdicao = new Usuario();
	}

	public void salvar(){
		cadastraUsuario.salvar(usuarioEdicao);		
		consultar();		
		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-usuario:msgs", "frm-usuario:usuarios-table"));
	}
	
	public void excluir(){
		cadastraUsuario.excluir(usuarioSelecionado);				
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-usuario:msgs", "frm-usuario:usuarios-table"));
	}
	
	public PerfilUsuario[] getPerfilUsuario() {
		return PerfilUsuario.values();
	}
	
	
	
	public List<Usuario> getTodosUsuarios() {
		return todosUsuarios;
	}
	
	
	
	public FacesMessages getMensages() {
		return mensages;
	}

	public void setMensages(FacesMessages mensages) {
		this.mensages = mensages;
	}

	public Usuario getUsuarioEdicao() {
		return usuarioEdicao;
	}

	public void setUsuarioEdicao(Usuario usuarioEdicao) {
		this.usuarioEdicao = usuarioEdicao;
	}

	public List<Usuario> getUsuarios() {
		consultar();
		return todosUsuarios;
	}
	
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	
	public Usuario[] getGrupo() {
		return getGrupo();
	}
	
	
}
