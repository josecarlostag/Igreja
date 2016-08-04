package br.org.ijv.cadastro.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.model.PerfilUsuario;

@Named
@RequestScoped
public class Seguranca {

	public String getNomeUsuario() {
		String nome = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getNomeUsuario();
		}
		
		return nome;
	}
	
	public Congregacao getCongregacao() {
		Congregacao nome = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			nome = usuarioLogado.getUsuario().getCongregacao();
		}
		
		return nome;
	}
	
	public PerfilUsuario getPerfil() {
		PerfilUsuario perfil = null;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			perfil = usuarioLogado.getUsuario().getPerfil();
		}
		
		return perfil;
	}
		
	public UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	}

	public long getId() {
		long id = 0;
		
		UsuarioSistema usuarioLogado = getUsuarioLogado();
		
		if (usuarioLogado != null) {
			id = usuarioLogado.getUsuario().getId();
			
		}
		return id;
		
		
	}

}
