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
@Table(name = "Licao")
public class Licao  implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	@Column(name = "nomeLicao", nullable = false, length = 60)
	private String nomeLicao;
	
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "serie_id")
	private Serie serie;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeLicao() {
		return nomeLicao;
	}

	public void setNomeLicao(String nomeLicao) {
		this.nomeLicao = nomeLicao;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
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
		Licao other = (Licao) obj;
		if (id == null) { if (other.id != null)
			return false;
		} else if (!id.equals(other.id)) return false; 
		return true;
	}
}

