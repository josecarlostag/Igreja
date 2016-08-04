package br.org.ijv.cadastro.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.org.ijv.cadastro.model.Financeiro;
import br.org.ijv.cadastro.model.Moeda;
import br.org.ijv.cadastro.model.PerfilUsuario;
import br.org.ijv.cadastro.repository.Financeiros;
import br.org.ijv.cadastro.security.Seguranca;
import br.org.ijv.cadastro.service.CadastroFinanceiroService;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

@Named
@ViewScoped
public class FinanceirosBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroFinanceiroService cadastraFinanceiro;
	
	@Inject
	protected Financeiros financeiros;
	
	@Inject
	private Seguranca usuario;
	
	@Inject
	protected FacesMessages mensages;
				
	private List<Financeiro> todosFinanceiros;
	protected Financeiro financeiroEdicao = new Financeiro();
	protected Financeiro financeiroSelecionado;
		
	public void prepararNovoCadastro() {
		financeiroEdicao = new Financeiro();
	}
		
	public Moeda[] getMoedas() { 
		return Moeda.values(); 
	}
	
	public void salvar(){		
		cadastraFinanceiro.salvar(financeiroEdicao);		
		consultar();		
		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-financeiro:msgs", "frm-financeiro:financeiros-table"));
	}
	
	public void consultar() {			
		todosFinanceiros = financeiros.todosFinanceiros();	
	}
		
	public void excluir(){				
		cadastraFinanceiro.excluir(financeiroSelecionado);
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-financeiro:msgs", "frm-financeiro:financeiros-table"));
	}
	
	public List<Financeiro> getTodosFinanceiros() {			
		return todosFinanceiros;
	}
	
public List<Financeiro> getFinanceiroCongregacao() {
		
		if(usuario.getPerfil() == PerfilUsuario.ADMIN){
			
		  todosFinanceiros = financeiros.todosFinanceiros();	
			
		}else{
		
		todosFinanceiros = financeiros.financeiroCongregacao(usuario.getUsuarioLogado().getUsuario().getCongregacao());
		
		}
		return todosFinanceiros;
	}
			
	public List<Financeiro> getFinanceiros() {
		consultar();
		return todosFinanceiros;
	}

	public Financeiro getFinanceiroEdicao() {
		return financeiroEdicao;
	}

	public void setFinanceiroEdicao(Financeiro financeiroEdicao) {
		this.financeiroEdicao = financeiroEdicao;
	}

	public Financeiro getFinanceiroSelecionado() {
		return financeiroSelecionado;
	}

	public void setFinanceiroSelecionado(Financeiro financeiroSelecionado) {
		this.financeiroSelecionado = financeiroSelecionado;
	}

	public void setFinanceiros(Financeiros financeiros) {
		this.financeiros = financeiros;
	}
	
	public Double getDizimoTotal() {
		
		return  financeiros.dizimoTotal();
	}
	
	public Double getAmorTotal() {
		
		return financeiros.amorTotal();
	}
	
	public Double getMissoesTotal() {
		
		return financeiros.missoesTotal();
	}
	
	public Double getNovasIgrejasTotal() {
		
		return financeiros.novasIgrejasTotal();
	}

	public Double getJovensTotal() {
		
		return financeiros.jovensTotal();
	}
	
	

	public Double getConstrucaoTotal() {
		
		return financeiros.construcaoTotal();
	}

	public Double getPinasTotal() {
		
		return financeiros.pinasTotal();
	}

	public Double getLouvorTotal() {
		
		return financeiros.louvorTotal();
	}

	public Double getBazarTotal() {
		
		return financeiros.bazarTotal();
	}

	public Double getRedeTotal() {
		
		return financeiros.redeMulheresTotal();
	}
	
	private Double diferenca = 0.0;
	
	
	
	public Double getDiferenca() {
		calcula();
		return diferenca;
	}

	public void  calcula() {
		this.diferenca = 0.0;
		if(financeiroEdicao.getId() != null){
		this.diferenca = financeiroEdicao.getTotalGeral().doubleValue() - financeiroEdicao.getTotalMoeda().doubleValue();
		}
		
	}
	
}
