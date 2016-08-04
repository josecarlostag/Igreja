package br.org.ijv.cadastro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@Entity 
@Table(name = "Congregacao")
public class Congregacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	@Column(name = "nomeCongregacao", nullable = false, length = 30)
	private String nomeCongregacao;
	
	@Column(name = "endereco", nullable = false, length = 60)
	private String endereco;
	
	@NotEmpty
	@Column(name = "telefone", nullable = false, length = 20)
	private String telefone;
	
	@Column(name = "email", nullable = true, length = 30)
	private String email;
	
	@NotNull
	@ManyToOne(optional = false) 
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCongregacao() {
		return nomeCongregacao;
	}

	public void setNomeCongregacao(String nomeCongregacao) {
		this.nomeCongregacao = nomeCongregacao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
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
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Congregacao other = (Congregacao) obj;
		if (id == null) { if (other.id != null)
			return false;
		} else if (!id.equals(other.id)) return false; 
		return true;
	}

}
