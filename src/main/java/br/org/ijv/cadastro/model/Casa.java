package br.org.ijv.cadastro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;

@Entity 
@Table(name = "Casa")
public class Casa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCasa tipo;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "lider_id")
	private Pessoa lider;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "ministrante_id")
	private Pessoa ministrante;
	
	@NotEmpty
	@Column(name = "anfitriao", nullable = false, length = 50)
	private String anfitriao;
	
	@Column(name = "endereco", nullable = false, length = 80)
	private String endereco;
	
	@Column(name = "bairro", nullable = true, length = 40)
	private String bairro;
	
	
	@Column(name = "telefone_resid", nullable = true, length = 20)
	private String telefoneResid; 
	
	
	@Column(name = "telefone_trab", nullable = true, length = 20)
	private String telefoneTrab;
		
	@Column(name = "celular", nullable = true, length = 20)
	private String celular; 
	
	@Column(name = "email", nullable = true, length = 40)
	private String email;
	
	@NotNull
	@ManyToOne(optional = false) 
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private DiaSemana dia_semana;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "serie_id")
	private Serie serie;
	
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "congregacao_id")
	private Congregacao congregacao;
	
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "horario")
	private Date horario;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoCasa getTipo() {
		return tipo;
	}

	public void setTipo(TipoCasa tipo) {
		this.tipo = tipo;
	}

	public Pessoa getLider() {
		return lider;
	}

	public void setLider(Pessoa lider) {
		this.lider = lider;
	}

	public Pessoa getMinistrante() {
		return ministrante;
	}

	public void setMinistrante(Pessoa ministrante) {
		this.ministrante = ministrante;
	}

	public String getAnfitriao() {
		return anfitriao;
	}

	public void setAnfitriao(String anfitriao) {
		this.anfitriao = anfitriao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getTelefoneResid() {
		return telefoneResid;
	}

	public void setTelefoneResid(String telefoneResid) {
		this.telefoneResid = telefoneResid;
	}

	public String getTelefoneTrab() {
		return telefoneTrab;
	}

	public void setTelefoneTrab(String telefoneTrab) {
		this.telefoneTrab = telefoneTrab;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
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

	public DiaSemana getDia_semana() {
		return dia_semana;
	}

	public void setDia_semana(DiaSemana dia_semana) {
		this.dia_semana = dia_semana;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
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
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Casa other = (Casa) obj;
		if (id == null) { if (other.id != null)
			return false;
		} else if (!id.equals(other.id)) return false; 
		return true;
	}

}
