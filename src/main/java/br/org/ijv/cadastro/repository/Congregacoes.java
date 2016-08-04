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

import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.security.Seguranca;

public class Congregacoes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
		

	public Congregacao porId(Long id) {
		return manager.find(Congregacao.class, id);
	}
	
	public List<Congregacao> todasCongregacoes() { 
		return manager.createQuery("from Congregacao ORDER BY nomeCongregacao ASC", Congregacao.class).getResultList();				
	}
	
	public List<Congregacao> congregacoesUsuario(Seguranca usuario) {
		Congregacao congregacao  = usuario.getCongregacao();
		
		return manager.createQuery("from Congregacao WHERE id = "+congregacao.getId(), Congregacao.class).getResultList();
	}
	
	
	
	public Congregacao guardar(Congregacao congregacao) {
		return manager.merge(congregacao);
	}
	
	public void remover(Congregacao congregacao) {
		congregacao = porId(congregacao.getId());
		manager.remove(congregacao);
	}

	public Congregacao congregacao(Congregacao congregacao) {
	for (int i = 0; i < this.todasCongregacoes().size(); i++) {
	      if (congregacao == this.todasCongregacoes().get(i)) {
	        return this.todasCongregacoes().get(i);
	      }
	    }
	    return null;
	  }
	
	public boolean contem(Congregacao congregacao) {
	    for (int i = 0; i < this.todasCongregacoes().size(); i++) {
	      if (congregacao == this.todasCongregacoes().get(i)) {
	        return true;
	      }
	    }
	    return false;
	}
	
	public int contarCongregacao(){
		int total = 0;
		for (int i = 0; i < todasCongregacoes().size(); i++) {
			
				total +=1;
			
		}
		return total;
	}
	
	@InterceptorBinding 
	@Retention(RetentionPolicy.RUNTIME) 
	@Target
	({ ElementType.TYPE, ElementType.METHOD })
	public @interface Transactional { 
		
	}
	
	public Congregacao porSku(String sku) {
		try {
			return manager.createQuery("from Congregacao where upper(sku) = :sku", Congregacao.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	

	
	
}

