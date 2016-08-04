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

import br.org.ijv.cadastro.model.Serie;

public class Series implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
		

	public Serie porId(Long id) {
		return manager.find(Serie.class, id);
	}
	
	public List<Serie> todasSerie() { 
		return manager.createQuery("from Serie ORDER BY nomeSerie ASC", Serie.class).getResultList();				
	}
	
		
	public Serie guardar(Serie serie) {
		return manager.merge(serie);
	}
	
	public void remover(Serie serie) {
		serie = porId(serie.getId());
		manager.remove(serie);
	}

	public Serie cidade(Serie serie) {
	for (int i = 0; i < this.todasSerie().size(); i++) {
	      if (serie == this.todasSerie().get(i)) {
	        return this.todasSerie().get(i);
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