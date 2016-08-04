package br.org.ijv.cadastro.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import br.org.ijv.cadastro.controller.FinanceirosBean;
import br.org.ijv.cadastro.model.Financeiro;
import br.org.ijv.cadastro.util.jpa.Transactional;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

public class CadastroFinanceiroService extends FinanceirosBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Inject
	protected FacesMessages mensages;
				
	@Transactional
	public void salvar(Financeiro financeiro) {
		
		Double totalOferta = financeiro.getAmor()+financeiro.getConstrucao()
		+financeiro.getJovens()+financeiro.getLouvor()+financeiro.getMissoes()+financeiro.getNovas()
		+financeiro.getPinas()+financeiro.getRedeMulheres();
		financeiro.setTotalOferta(totalOferta);
				
		Double totalGeral = totalOferta + financeiro.getDizimo();		
		financeiro.setTotalGeral(totalGeral);
		
		Double totalMoeda = financeiro.getDinheiro()+financeiro.getChequePredatado()+financeiro.getChequeVista()+financeiro.getCartao()+financeiro.getTicket()+
		financeiro.getMoeda()+financeiro.getDeposito();
		financeiro.setTotalMoeda(totalMoeda);
		
		Double totalPrometido = totalMoeda + financeiro.getPrometidos();
		financeiro.setTotalPrometido(totalPrometido);
		
		
		if(totalGeral.doubleValue() == totalMoeda.doubleValue())
		{
			this.financeiros.guardar(financeiro);
			
			 Date date = financeiro.getDataCaixa();		 
	         String dataz = "dd/MM/yyyy";
	         SimpleDateFormat formatas = new SimpleDateFormat(dataz );
	         String data = formatas.format(date );
					
			mensages.info("O caixa do dia "+data+ " foi cadastrado com sucesso.");
						
			}else{		
				mensages.error("Total geral diferente do total moeda.");
				
			}		
					
			}
	
	@Transactional 
	public void excluir(Financeiro financeiroSelecionado) {
	 
		this.financeiros.remover(financeiroSelecionado);
		
				mensages.info("A congregação "+ financeiroSelecionado.getId()+ " foi excluido com sucesso.");
				
		
		this.financeiroEdicao = null;
		this.financeiroSelecionado = null;
		consultar();
	}	
}
