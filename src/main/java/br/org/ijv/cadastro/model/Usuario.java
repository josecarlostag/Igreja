package br.org.ijv.cadastro.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name = "Usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING) 
	private PerfilUsuario perfil;
	
	@NotEmpty
	@Column(name = "login", nullable = false, length = 18)
	private String login;
	
	@NotEmpty
	@Column(name = "senha", nullable = false, length = 10)
	private String senha;
	
	@NotEmpty
	@Column(name = "nomeUsuario", nullable = false, length = 30)
	private String nomeUsuario;
	
	@Email
	@Column(name = "email", nullable = false, length = 50)
	private String email;
	
	@Column(name = "rg", nullable = false, length = 20)
	private String rg;
	
	
	@Column(name = "telefone", nullable = false, length = 20)
	private String telefone;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_grupo", joinColumns = @JoinColumn(name="usuario_id"),
	inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private List<Grupo> grupos = new ArrayList<>();
		
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "congregacao_id")
	private Congregacao congregacao;
				
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PerfilUsuario getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilUsuario perfil) {
		this.perfil = perfil;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
	public Congregacao getCongregacao() {
		return congregacao;
	}
	public void setCongregacao(Congregacao congregacao) {
		this.congregacao = congregacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}