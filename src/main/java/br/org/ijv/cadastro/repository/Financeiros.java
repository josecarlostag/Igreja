package br.org.ijv.cadastro.repository;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import javax.inject.Inject;
import javax.interceptor.InterceptorBinding;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.org.ijv.cadastro.controller.FinanceirosBean;
import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.model.Financeiro;


public class Financeiros extends FinanceirosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	

	public Financeiro porId(Long id) {
		return manager.find(Financeiro.class, id);
	}
	
	public List<Financeiro> todosFinanceiros() { 
		return manager.createQuery("from Financeiro ORDER BY dataCaixa DESC", Financeiro.class).getResultList();				
	}
		
	public List<Financeiro> financeiroCongregacao(Congregacao congregacao) {
		long id = congregacao.getId();
		return manager.createQuery("FROM Financeiro WHERE congregacao_id = "+id +" ORDER BY dataCaixa DESC", Financeiro.class).getResultList();

	}
	
	public Financeiro guardar(Financeiro financeiro) {
		return manager.merge(financeiro);
	}
	
	public void remover(Financeiro financeiro) {
		financeiro = porId(financeiro.getId());
		manager.remove(financeiro);
	}

	public Financeiro congregacao(Financeiro financeiro) {
	for (int i = 0; i < this.todosFinanceiros().size(); i++) {
	      if (financeiro== this.todosFinanceiros().get(i)) {
	        return this.todosFinanceiros().get(i);
	      }
	    }
	    return null;
	  }
	
	public boolean contem(Financeiro financeiro) {
	    for (int i = 0; i < this.todosFinanceiros().size(); i++) {
	      if (financeiro == this.todosFinanceiros().get(i)) {
	        return true;
	      }
	    }
	    return false;
	}
	
	
	public Double dizimoTotal(){
		Double total = 0.00;
		Financeiro financeiro;
		for (int i = 0; i < this.todosFinanceiros().size(); i++) {			
				financeiro = this.todosFinanceiros().get(i);
				if(financeiro.getDizimo() != null){
				total = total+financeiro.getDizimo();
				}
		}
		return total;
	}
	
	public double amorTotal(){
		double total = 0.0;
		Financeiro financeiro;
		for (int i = 0; i < this.todosFinanceiros().size(); i++) {
				financeiro = this.todosFinanceiros().get(i);
				if(financeiro.getAmor() != null){
				total = total+financeiro.getAmor();
				}
		}
		return total;
	}
	
	public double missoesTotal(){
		double  missoesTotal = 0.0;
		Financeiro financeiro;
		for (int i = 0; i < this.todosFinanceiros().size(); i++) {
			financeiro = this.todosFinanceiros().get(i);
			if(financeiro.getMissoes() != null){
				missoesTotal += financeiro.getMissoes();	
			}
		}
		return missoesTotal;		
	}
	
	public double novasIgrejasTotal(){
		double novasIgrejasTotal = 0.0;
		Financeiro financeiro;
		for (int i = 0; i < this.todosFinanceiros().size(); i++) {
				financeiro = this.todosFinanceiros().get(i);
				if(financeiro.getNovas() != null){
			novasIgrejasTotal += financeiro.getNovas();	
				}
		}
		return novasIgrejasTotal;
	}
	
	public Double jovensTotal(){
		Double jovensTotal = 0.0;
		Financeiro financeiro;
		for (int i = 0; i < this.todosFinanceiros().size(); i++) {
				financeiro = this.todosFinanceiros().get(i);
				if(financeiro.getJovens() != null){
				jovensTotal += financeiro.getJovens();
			}			
		}
		return jovensTotal;
	}
	
	public double construcaoTotal(){
		double  construcaoTotal = 0.0;
		Financeiro financeiro;
		for (int i = 0; i < this.todosFinanceiros().size(); i++) {
			financeiro = this.todosFinanceiros().get(i);
			if(financeiro.getConstrucao() != null){
				construcaoTotal += financeiro.getConstrucao();	
			}
		}		
		return construcaoTotal;		
	}
	
	public Double pinasTotal(){
		Double  pinasTotal = 0.0;
		Financeiro financeiro;
		for (int i = 0; i < this.todosFinanceiros().size(); i++) {
			financeiro = this.todosFinanceiros().get(i);
			if(financeiro.getPinas() != null){
				pinasTotal += financeiro.getPinas();
			}
		}
		return pinasTotal;		
	}
	
	public double louvorTotal(){
		double  louvorTotal = 0.0;
		Financeiro financeiro;
		for (int i = 0; i < this.todosFinanceiros().size(); i++) {
			financeiro = this.todosFinanceiros().get(i);
			if(financeiro.getLouvor() != null){
				louvorTotal += financeiro.getLouvor();
			}
		}
		return louvorTotal;		
	}
	
	public double bazarTotal(){
		double  bazarTotal = 0.0;
		Financeiro financeiro;
		for (int i = 0; i < this.todosFinanceiros().size(); i++) {
			financeiro = this.todosFinanceiros().get(i);
			if(financeiro.getBazar() != null){
				bazarTotal += financeiro.getBazar();
			}
		}
		return bazarTotal;		
	}
	
	public double redeMulheresTotal(){
		double  redeTotal = 0.0;
		Financeiro financeiro;
		for (int i = 0; i < this.todosFinanceiros().size(); i++) {
			financeiro = this.todosFinanceiros().get(i);
			if(financeiro.getRedeMulheres() != null){
				redeTotal += financeiro.getRedeMulheres();	
			}
		}
		return redeTotal;		
	}
	
	@InterceptorBinding 
	@Retention(RetentionPolicy.RUNTIME) 
	@Target
	({ ElementType.TYPE, ElementType.METHOD })
	public @interface Transactional { 
		
	}
	
	public Financeiro porSku(String sku) {
		try {
			return manager.createQuery("from Financeiro where upper(sku) = :sku", Financeiro.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

		
}