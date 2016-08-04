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

import br.org.ijv.cadastro.model.Grupo;



public class Grupos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
		

	public Grupo porId(Long id) {
		return manager.find(Grupo.class, id);
	}
	
	public List<Grupo> todosGrupos() { 
		return manager.createQuery("from Grupo ORDER BY nome ASC", Grupo.class).getResultList();				
	}
	
	@Transactional
	public Grupo guardar(Grupo grupo) {
		return manager.merge(grupo);
	}
	
	public void remover(Grupo grupo) {
		grupo = porId(grupo.getId());
		manager.remove(grupo);
	}

	public Grupo grupo(Grupo grupo) {
	for (int i = 0; i < this.todosGrupos().size(); i++) {
	      if (grupo == this.todosGrupos().get(i)) {
	        return this.todosGrupos().get(i);
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
