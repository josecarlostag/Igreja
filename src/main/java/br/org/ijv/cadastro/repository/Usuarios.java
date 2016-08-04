package br.org.ijv.cadastro.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.org.ijv.cadastro.model.Usuario;
import br.org.ijv.cadastro.util.jpa.Transactional;

public class Usuarios implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Usuario porId(Long id) {
		return this.manager.find(Usuario.class, id);
	}
	
	public List<Usuario> todosUsuarios() { 
		return manager.createQuery("from Usuario ORDER BY nomeUsuario ASC ", Usuario.class).getResultList();		
	}
	
	public List<Usuario> autentico(Usuario logado) {
		Long id = logado.getId();
		String sql= "from Usuario where usuario = "+id;
		return manager.createQuery(sql, Usuario.class).getResultList();
	}
	
	public List<Usuario> pastores() {
		// TODO filtrar apenas pastores (por um grupo específico)
		return this.manager.createQuery("from Usuario", Usuario.class)
				.getResultList();
	}

	public Usuario porEmail(String email) {
		Usuario usuario = null;
		
		try {
			usuario = this.manager.createQuery("from Usuario where lower(email) = :email", Usuario.class)
				.setParameter("email", email.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}
		
		return usuario;
	}
	
	@Transactional
	public Usuario guardar(Usuario usuario) {
		return manager.merge(usuario);
	}

	public Usuario porLogin(String login) {
		Usuario usuario = null;
		
		try {
			usuario = this.manager.createQuery("from Usuario where lower(login) = :login", Usuario.class)
				.setParameter("login", login.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// nenhum usuário encontrado com o e-mail informado
		}
		
		return usuario;
	}

	

	public void remover(Usuario usuario) {
		usuario = porId(usuario.getId());
		manager.remove(usuario);
	}
	
	public Usuario autenticado(Usuario usuarioSelecionado) {
		Usuario usuario = new Usuario();
		for (int i = 0; i <todosUsuarios().size(); i++) {
				
			if( (todosUsuarios().get(i).getLogin().equals(usuarioSelecionado.getLogin()) == true) && (todosUsuarios().get(i).getSenha().equals(usuarioSelecionado.getSenha())== true )){			
				usuario = todosUsuarios().get(i);
				return usuario;			
			}
		}
		return null;
	}
	
	public boolean autorizado(Usuario usuarioSelecionado) {
		for (int i = 0; i <todosUsuarios().size(); i++) {
			
			if( (todosUsuarios().get(i).getLogin().equals(usuarioSelecionado.getLogin()) == true) && (todosUsuarios().get(i).getSenha().equals(usuarioSelecionado.getSenha())== true )){	
				
				return true;			
			}
		}
		return false;
	}
		
}
