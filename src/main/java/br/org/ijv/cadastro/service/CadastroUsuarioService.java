package br.org.ijv.cadastro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.org.ijv.cadastro.model.Usuario;
import br.org.ijv.cadastro.repository.Usuarios;
import br.org.ijv.cadastro.util.jpa.Transactional;
import br.org.ijv.cadastro.util.jsf.FacesMessages;


public class CadastroUsuarioService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	protected FacesMessages mensages;
						
	@Transactional
	public void salvar(Usuario usuario) {
		this.usuarios.guardar(usuario);
		mensages.info("Usuario " +usuario.getLogin()+ " salvo com sucesso.");
	}
		
	@Transactional
	public void excluir(Usuario usuarioSelecionado) {
		 {
		usuarios.remover(usuarioSelecionado);
		mensages.info("Usuário "+ usuarioSelecionado.getNomeUsuario()+" excluido com sucesso.");
		usuarioSelecionado = null;
	}

	}

	public boolean autorizado(Usuario usuario) {
		boolean cadastrado = false;
		cadastrado = usuarios.autorizado(usuario);
		return cadastrado;
	}	
}

