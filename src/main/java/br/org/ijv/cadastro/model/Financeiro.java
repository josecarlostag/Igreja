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
@Table(name = "Financeiro")
public class Financeiro  implements Serializable{ 
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Past
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_caixa")
	private Date dataCaixa;
	
	@NotNull
	@Column(name = "dizimo", nullable = false, length = 10)
	private Double dizimo;
	
	
	@Column(name = "total_oferta", nullable = true, length = 10)
	private Double totalOferta;
	
	
	@Column(name = "total_geral", nullable = true, length = 10)
	private Double totalGeral;
	
	
	@Column(name = "total_moeda", nullable = true, length = 10)
	private Double totalMoeda;
	
	
	@Column(name = "total_prometido", nullable = true, length = 10)
	private Double totalPrometido;
	
	@NotNull
	@Column(name = "amor", nullable = true, length = 10)
	private Double amor;
	
	@NotNull
	@Column(name = "missoes", nullable = true, length = 10)
	private Double missoes;
	
	@NotNull
	@Column(name = "novas", nullable = true, length = 10)
	private Double novas;
	
	@NotNull
	@Column(name = "jovens", nullable = true, length = 10)
	private Double jovens;
	
	@NotNull
	@Column(name = "contrucao", nullable = true, length = 10)
	private Double construcao;
	
	@NotNull
	@Column(name = "pinas", nullable = true, length = 10)
	private Double pinas;
	
	@NotNull
	@Column(name = "louvor", nullable = true, length = 10)
	private Double louvor;
	
	@NotNull
	@Column(name = "bazar", nullable = true, length = 10)
	private Double bazar;
	
	@NotNull
	@Column(name = "rede_mulheres", nullable = true, length = 10)
	private Double redeMulheres;
	
	@NotNull
	@Column(name = "cheque_vista", nullable = true, length = 10)
	private Double chequeVista;
	
	@NotNull
	@Column(name = "cheque_predatado", nullable = true, length = 10)
	private Double chequePredatado;
	
	@NotNull
	@Column(name = "dinheiro", nullable = true, length = 10)
	private Double dinheiro;
	
	@NotNull
	@Column(name = "moeda", nullable = true, length = 10)
	private Double moeda;
	
	@NotNull
	@Column(name = "ticket", nullable = true, length = 10)
	private Double ticket;
	
	@NotNull
	@Column(name = "cartao", nullable = true, length = 10)
	private Double cartao;
	
	@NotNull
	@Column(name = "deposito", nullable = true, length = 10)
	private Double deposito;
	
	@NotNull
	@Column(name = "prometidos", nullable = true, length = 10)
	private Double prometidos;
	
	@NotNull
	@Column(name = "envelopes", nullable = true, length = 10)
	private Long envelopes;
	
	@NotNull
	@Column(name = "envelope_prometido", nullable = true, length = 10)
	private Long envelopePrometido;
	
		
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
	
	public Date getDataCaixa() {
		return dataCaixa;
	}

	public void setDataCaixa(Date dataCaixa) {
		this.dataCaixa = dataCaixa;
	}
	
	public Double getDizimo() {
		return dizimo;
	}

	public void setDizimo(Double dizimo) {
		this.dizimo = dizimo;
	}

	public Double getTotalOferta() {
		return totalOferta;
	}

	public void setTotalOferta(Double totalOferta) {
		this.totalOferta = totalOferta;
	}

	public Double getTotalGeral() {
		return totalGeral;
	}

	public void setTotalGeral(Double totalGeral) {
		this.totalGeral = totalGeral;
	}

	public Double getTotalMoeda() {
		return totalMoeda;
	}

	public void setTotalMoeda(Double totalMoeda) {
		this.totalMoeda = totalMoeda;
	}

	public Double getTotalPrometido() {
		return totalPrometido;
	}

	public void setTotalPrometido(Double totalPrometido) {
		this.totalPrometido = totalPrometido;
	}

	public Double getAmor() {
		return amor;
	}

	public void setAmor(Double amor) {
		this.amor = amor;
	}

	public Double getMissoes() {
		return missoes;
	}

	public void setMissoes(Double missoes) {
		this.missoes = missoes;
	}

	public Double getNovas() {
		return novas;
	}

	public void setNovas(Double novas) {
		this.novas = novas;
	}

	public Double getJovens() {
		return jovens;
	}

	public void setJovens(Double jovens) {
		this.jovens = jovens;
	}

	public Double getConstrucao() {
		return construcao;
	}

	public void setConstrucao(Double construcao) {
		this.construcao = construcao;
	}

	public Double getPinas() {
		return pinas;
	}

	public void setPinas(Double pinas) {
		this.pinas = pinas;
	}

	public Double getLouvor() {
		return louvor;
	}

	public void setLouvor(Double louvor) {
		this.louvor = louvor;
	}

	public Double getBazar() {
		return bazar;
	}

	public void setBazar(Double bazar) {
		this.bazar = bazar;
	}

	public Double getRedeMulheres() {
		return redeMulheres;
	}

	public void setRedeMulheres(Double redeMulheres) {
		this.redeMulheres = redeMulheres;
	}

	public Double getChequeVista() {
		return chequeVista;
	}

	public void setChequeVista(Double chequeVista) {
		this.chequeVista = chequeVista;
	}

	public Double getChequePredatado() {
		return chequePredatado;
	}

	public void setChequePredatado(Double chequePredatado) {
		this.chequePredatado = chequePredatado;
	}

	public Double getDinheiro() {
		return dinheiro;
	}

	public void setDinheiro(Double dinheiro) {
		this.dinheiro = dinheiro;
	}

	public Double getMoeda() {
		return moeda;
	}

	public void setMoeda(Double moeda) {
		this.moeda = moeda;
	}

	public Double getTicket() {
		return ticket;
	}

	public void setTicket(Double ticket) {
		this.ticket = ticket;
	}

	public Double getCartao() {
		return cartao;
	}

	public void setCartao(Double cartao) {
		this.cartao = cartao;
	}

	public Double getDeposito() {
		return deposito;
	}

	public void setDeposito(Double deposito) {
		this.deposito = deposito;
	}

	public Double getPrometidos() {
		return prometidos;
	}

	public void setPrometidos(Double prometidos) {
		this.prometidos = prometidos;
	}

	public Congregacao getCongregacao() {
		return congregacao;
	}

	public void setCongregacao(Congregacao congregacao) {
		this.congregacao = congregacao;
	}
	
	public Long getEnvelopes() {
		return envelopes;
	}

	public void setEnvelopes(Long envelopes) {
		this.envelopes = envelopes;
	}

	public Long getEnvelopePrometido() {
		return envelopePrometido;
	}

	public void setEnvelopePrometido(Long envelopePrometido) {
		this.envelopePrometido = envelopePrometido;
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
		Financeiro other = (Financeiro) obj;
		if (id == null) { if (other.id != null)
			return false;
		} else if (!id.equals(other.id)) return false; 
		return true;
	}
}
