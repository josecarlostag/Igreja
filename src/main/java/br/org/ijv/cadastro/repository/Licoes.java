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

import br.org.ijv.cadastro.model.Licao;

public class Licoes implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
		

	public Licao porId(Long id) {
		return manager.find(Licao.class, id);
	}
	
	public List<Licao> todasLicoes() { 
		return manager.createQuery("from Licao ORDER BY serie, nomeLicao ASC", Licao.class).getResultList();				
	}
	
	public Licao guardar(Licao licao) {
		return manager.merge(licao);
	}
	
	public void remover(Licao licao) {
		licao = porId(licao.getId());
		manager.remove(licao);
	}

	public Licao cidade(Licao licao) {
	for (int i = 0; i < this.todasLicoes().size(); i++) {
	      if (licao == this.todasLicoes().get(i)) {
	        return this.todasLicoes().get(i);
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
