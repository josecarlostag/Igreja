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
@Table(name = "Pessoa")
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue
	private Long id;
		
	@NotEmpty
	@Column(name = "nome_completo", nullable = false, length = 50)
	private String nomeCompleto;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private EstadoCivil estado_civil;
	
	@Enumerated(EnumType.STRING)
	private Funcao funcao;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	private Date dataNascimento;
	
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "data_conversao")
	private Date dataConversao;
	
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "data_batismo")
	private Date dataBatismo;
	
	@Column(name = "endereco", nullable = false, length = 80)
	private String endereco;
			
	@Column(name = "bairro", nullable = false, length = 40)
	private String bairro;
	
	@Column(name = "celular", nullable = false, length = 20)
	private String celular;
	
	@Column(name = "email", nullable = false, length = 40)
	private String email;
	
	@Column(name = "telefone_resid", nullable = false, length = 20)
	private String telefoneResid; 
		
	@Column(name = "telefone_trab", nullable = false, length = 20)
	private String telefoneTrab;
	
	@NotNull
	@ManyToOne(optional = false) 
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "congregacao_id")
	private Congregacao congregacao;
	
	@ManyToOne(optional = true) 
	@JoinColumn(name = "conjuge_id")
	private Pessoa conjuge; 
	
	@ManyToOne(optional = true) 
	@JoinColumn(name = "discipulador_id")
	private Pessoa discipulador;
	
	@ManyToOne(optional = true) 
	@JoinColumn(name = "pastor_id")
	private Pessoa pastor;
	
	
		
	@NotEmpty
	@Column(name = "nome", nullable = false, length = 20)
	private String nome;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public EstadoCivil getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(EstadoCivil estado_civil) {
		this.estado_civil = estado_civil;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Date getDataConversao() {
		return dataConversao;
	}

	public void setDataConversao(Date dataConversao) {
		this.dataConversao = dataConversao;
	}

	public Date getDataBatismo() {
		return dataBatismo;
	}

	public void setDataBatismo(Date dataBatismo) {
		this.dataBatismo = dataBatismo;
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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Congregacao getCongregacao() {
		return congregacao;
	}

	public void setCongregacao(Congregacao congregacao) {
		this.congregacao = congregacao;
	}

	public Pessoa getConjuge() {
		return conjuge;
	}

	public void setConjuge(Pessoa conjuge) {
		this.conjuge = conjuge;
	}

	public Pessoa getDiscipulador() {
		return discipulador;
	}

	public void setDiscipulador(Pessoa discipulador) {
		this.discipulador = discipulador;
	}

	public Pessoa getPastor() {
		return pastor;
	}

	public void setPastor(Pessoa pastor) {
		this.pastor = pastor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		Pessoa other = (Pessoa) obj;
		if (id == null) { if (other.id != null)
			return false;
		} else if (!id.equals(other.id)) return false; 
		return true;
	}
}
