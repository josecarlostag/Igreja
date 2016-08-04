package br.org.ijv.cadastro.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


@Entity 
@Table(name = "Culto")
public class Culto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "congregacao_id")
	private Congregacao congregacao;
	
	@NotNull
	@Column(name = "reuniao", nullable = false, length = 30)
	private String reuniao;
	
	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_reuniao")
	private Date dataReuniao;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_inicio")
	private Date horaInicio;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "hora_fim")
	private Date horaFim;
		
	@ManyToOne(optional = true) 
	@JoinColumn(name = "recepicao_id")
	private Pessoa recepicao;
	
	@ManyToOne(optional = true) 
	@JoinColumn(name = "anfitriao_id")
	private Pessoa anfitriao;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "recepicao_inicio")
	private Date recepicaoInicio;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "recepicao_fim")
	private Date recepicaoFim;
	
	
	@ManyToOne(optional = true) 
	@JoinColumn(name = "intersessor_id")
	private Pessoa intercessor;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "intercessao_inicio")
	private Date intercessaoInicio;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "intercessao_fim")
	private Date intercessaoFim;
	
	@NotNull
	@ManyToOne(optional = false) 
	@JoinColumn(name = "palavra_id")
	private Pessoa palavra;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "palavra_inicio")
	private Date palavraInicio;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "palavra_fim")
	private Date palavraFim;
	
	
	@ManyToOne(optional = true) 
	@JoinColumn(name = "louvor_id")
	private Pessoa louvor;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "louvor_inicio")
	private Date louvorInicio;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "louvor_fim")
	private Date louvorFim;
	
	
	@ManyToOne(optional = true) 
	@JoinColumn(name = "oferta_id")
	private Pessoa oferta;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "oferta_inicio")
	private Date ofertaInicio;
	
	@Past
	@Temporal(TemporalType.TIME)
	@Column(name = "oferta_fim")
	private Date ofertaFim;
	
	@ManyToOne(optional = true) 
	@JoinColumn(name = "diacono_id")
	private Pessoa diacono;
	
	
	@ManyToOne(optional = true) 
	@JoinColumn(name = "midia_id")
	private Pessoa midia;
		
	@ManyToOne(optional = true) 
	@JoinColumn(name = "som_id")
	private Pessoa som;
		
	@ManyToOne(optional = true) 
	@JoinColumn(name = "apoio_id")
	private Pessoa apoio;
	
	@Column(name = "testemunho", nullable = true, length = 500)
	private String testemunho;
	
	@Column(name = "quan_pessoas", nullable = true, length = 4)
	private Long quantidadePessoas;
	
	@Column(name = "quan_pre", nullable = true, length = 4)
	private Long quantidadePreAdolescente;
	
	@Column(name = "quan_adolescente", nullable = true, length = 4)
	private Long quantidadeAdolescente;
	
	@Column(name = "quan_crianca_9", nullable = true, length = 4)
	private Long quantidadeCriancas9;
	
	@Column(name = "quan_crianca_6", nullable = true, length = 4)
	private Long quantidadeCriancas6;
	
	@Column(name = "quan_crianca_4", nullable = true, length = 4)
	private Long quantidadeCriancas4;
	
	@Column(name = "quan_crianca_2", nullable = true, length = 4)
	private Long quantidadeCriancas2;
	
	@Column(name = "professores", nullable = false, length = 500)
	private String professores;
	
	
	@Column(name = "consolidacao", nullable = false, length = 4)
	private Long consolidacao;
	
	@Column(name = "observacao", nullable = false, length = 500)
	private String observacao;
	
	@ManyToOne(optional = true) 
	@JoinColumn(name = "pastor_id")
	private Pessoa pastor;
	
	
	@ManyToOne(optional = true) 
	@JoinColumn(name = "auxiliar_id")
	private Pessoa auxiliar;
	
	@NotNull
	@ManyToOne(optional = false) 
	@JoinColumn(name = "preenchido_id")
	private Pessoa preenchido;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Congregacao getCongregacao() {
		return congregacao;
	}

	public void setCongregacao(Congregacao congregacao) {
		this.congregacao = congregacao;
	}

	public String getReuniao() {
		return reuniao;
	}

	public void setReuniao(String reuniao) {
		this.reuniao = reuniao;
	}

	public Date getDataReuniao() {
		return dataReuniao;
	}

	public void setDataReuniao(Date dataReuniao) {
		this.dataReuniao = dataReuniao;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public Pessoa getRecepicao() {
		return recepicao;
	}

	public void setRecepicao(Pessoa recepicao) {
		this.recepicao = recepicao;
	}

	public Pessoa getAnfitriao() {
		return anfitriao;
	}

	public void setAnfitriao(Pessoa anfitriao) {
		this.anfitriao = anfitriao;
	}

	public Date getRecepicaoInicio() {
		return recepicaoInicio;
	}

	public void setRecepicaoInicio(Date recepicaoInicio) {
		this.recepicaoInicio = recepicaoInicio;
	}

	public Date getRecepicaoFim() {
		return recepicaoFim;
	}

	public void setRecepicaoFim(Date recepicaoFim) {
		this.recepicaoFim = recepicaoFim;
	}

	public Pessoa getIntercessor() {
		return intercessor;
	}

	public void setIntercessor(Pessoa intercessor) {
		this.intercessor = intercessor;
	}

	public Date getIntercessaoInicio() {
		return intercessaoInicio;
	}

	public void setIntercessaoInicio(Date intercessaoInicio) {
		this.intercessaoInicio = intercessaoInicio;
	}

	public Date getIntercessaoFim() {
		return intercessaoFim;
	}

	public void setIntercessaoFim(Date intercessaoFim) {
		this.intercessaoFim = intercessaoFim;
	}

	public Pessoa getPalavra() {
		return palavra;
	}

	public void setPalavra(Pessoa palavra) {
		this.palavra = palavra;
	}

	public Date getPalavraInicio() {
		return palavraInicio;
	}

	public void setPalavraInicio(Date palavraInicio) {
		this.palavraInicio = palavraInicio;
	}

	public Date getPalavraFim() {
		return palavraFim;
	}

	public void setPalavraFim(Date palavraFim) {
		this.palavraFim = palavraFim;
	}

	public Pessoa getLouvor() {
		return louvor;
	}

	public void setLouvor(Pessoa louvor) {
		this.louvor = louvor;
	}

	public Date getLouvorInicio() {
		return louvorInicio;
	}

	public void setLouvorInicio(Date louvorInicio) {
		this.louvorInicio = louvorInicio;
	}

	public Date getLouvorFim() {
		return louvorFim;
	}

	public void setLouvorFim(Date louvorFim) {
		this.louvorFim = louvorFim;
	}

	public Pessoa getOferta() {
		return oferta;
	}

	public void setOferta(Pessoa oferta) {
		this.oferta = oferta;
	}

	public Date getOfertaInicio() {
		return ofertaInicio;
	}

	public void setOfertaInicio(Date ofertaInicio) {
		this.ofertaInicio = ofertaInicio;
	}

	public Date getOfertaFim() {
		return ofertaFim;
	}

	public void setOfertaFim(Date ofertaFim) {
		this.ofertaFim = ofertaFim;
	}

	public Pessoa getDiacono() {
		return diacono;
	}

	public void setDiacono(Pessoa diacono) {
		this.diacono = diacono;
	}

	public Pessoa getMidia() {
		return midia;
	}

	public void setMidia(Pessoa midia) {
		this.midia = midia;
	}

	public Pessoa getSom() {
		return som;
	}

	public void setSom(Pessoa som) {
		this.som = som;
	}

	public Pessoa getApoio() {
		return apoio;
	}

	public void setApoio(Pessoa apoio) {
		this.apoio = apoio;
	}

	public String getTestemunho() {
		return testemunho;
	}

	public void setTestemunho(String testemunho) {
		this.testemunho = testemunho;
	}

	public Long getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public void setQuantidadePessoas(Long quantidadePessoas) {
		this.quantidadePessoas = quantidadePessoas;
	}

	public Long getQuantidadePreAdolescente() {
		return quantidadePreAdolescente;
	}

	public void setQuantidadePreAdolescente(Long quantidadePreAdolescente) {
		this.quantidadePreAdolescente = quantidadePreAdolescente;
	}

	public Long getQuantidadeAdolescente() {
		return quantidadeAdolescente;
	}

	public void setQuantidadeAdolescente(Long quantidadeAdolescente) {
		this.quantidadeAdolescente = quantidadeAdolescente;
	}

	public Long getQuantidadeCriancas9() {
		return quantidadeCriancas9;
	}

	public void setQuantidadeCriancas9(Long quantidadeCriancas9) {
		this.quantidadeCriancas9 = quantidadeCriancas9;
	}

	public Long getQuantidadeCriancas6() {
		return quantidadeCriancas6;
	}

	public void setQuantidadeCriancas6(Long quantidadeCriancas6) {
		this.quantidadeCriancas6 = quantidadeCriancas6;
	}

	public Long getQuantidadeCriancas4() {
		return quantidadeCriancas4;
	}

	public void setQuantidadeCriancas4(Long quantidadeCriancas4) {
		this.quantidadeCriancas4 = quantidadeCriancas4;
	}

	public Long getQuantidadeCriancas2() {
		return quantidadeCriancas2;
	}

	public void setQuantidadeCriancas2(Long quantidadeCriancas2) {
		this.quantidadeCriancas2 = quantidadeCriancas2;
	}

	public String getProfessores() {
		return professores;
	}

	public void setProfessores(String professores) {
		this.professores = professores;
	}

	public Long getConsolidacao() {
		return consolidacao;
	}

	public void setConsolidacao(Long consolidacao) {
		this.consolidacao = consolidacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Pessoa getPastor() {
		return pastor;
	}

	public void setPastor(Pessoa pastor) {
		this.pastor = pastor;
	}

	public Pessoa getAuxiliar() {
		return auxiliar;
	}

	public void setAuxiliar(Pessoa auxiliar) {
		this.auxiliar = auxiliar;
	}

	public Pessoa getPreenchido() {
		return preenchido;
	}

	public void setPreenchido(Pessoa preenchido) {
		this.preenchido = preenchido;
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
		Culto other = (Culto) obj;
		if (id == null) { if (other.id != null)
			return false;
		} else if (!id.equals(other.id)) return false; 
		return true;
	}

}

