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

import br.org.ijv.cadastro.model.Cidade;



public class Cidades implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
		

	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}
	
	public List<Cidade> todasCidades() { 
		return manager.createQuery("from Cidade ORDER BY nomeCidade ASC", Cidade.class).getResultList();				
	}
	
	public Cidade guardar(Cidade cidade) {
		return manager.merge(cidade);
	}
	
	public void remover(Cidade cidade) {
		cidade = porId(cidade.getId());
		manager.remove(cidade);
	}

	public Cidade cidade(Cidade cidade) {
	for (int i = 0; i < this.todasCidades().size(); i++) {
	      if (cidade == this.todasCidades().get(i)) {
	        return this.todasCidades().get(i);
	      }
	    }
	    return null;
	  }
	
	@InterceptorBinding 
	@Retention(RetentionPolicy.RUNTIME) 
	@Target
	({ ElementType.TYPE, ElementType.METHOD })
	public @interface Transactional { 
		
	}

}
