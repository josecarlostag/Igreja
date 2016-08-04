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
import br.org.ijv.cadastro.model.Culto;

public class Cultos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
		

	public Culto porId(Long id) {
		return manager.find(Culto.class, id);
	}
	
	public List<Culto> todosCultos() { 
		return manager.createQuery("from Culto ORDER BY dataReuniao DESC", Culto.class).getResultList();				
	}
	
	public List<Culto> cultoCongregacao(Congregacao congregacao) {
		long id = congregacao.getId();
		return manager.createQuery("FROM Culto  WHERE  congregacao_id = "+id + "ORDER BY dataReuniao DESC", Culto.class).getResultList();

	}
	
	public Culto guardar(Culto culto) {
		return manager.merge(culto);
	}
	
	public void remover(Culto culto) {
		culto = porId(culto.getId());
		manager.remove(culto);
	}

	public Culto congregacao(Culto culto) {
	for (int i = 0; i < this.todosCultos().size(); i++) {
	      if (culto== this.todosCultos().get(i)) {
	        return this.todosCultos().get(i);
	      }
	    }
	    return null;
	  }
	
	public boolean contem(Culto culto) {
	    for (int i = 0; i < this.todosCultos().size(); i++) {
	      if (culto == this.todosCultos().get(i)) {
	        return true;
	      }
	    }
	    return false;
	}
	
	public int contarCulto(){
		int total = 0;
		for (int i = 0; i < todosCultos().size(); i++) {
			
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
	
	public Culto porSku(String sku) {
		try {
			return manager.createQuery("from Culto where upper(sku) = :sku", Culto.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	
	
}
