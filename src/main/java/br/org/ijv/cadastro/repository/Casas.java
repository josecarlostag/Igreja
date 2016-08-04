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

import br.org.ijv.cadastro.model.Casa;
import br.org.ijv.cadastro.model.Congregacao;

public class Casas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
		

	public Casa porId(Long id) {
		return manager.find(Casa.class, id);
	}
	
	public List<Casa> todasCasas() { 
		return manager.createQuery("from Casa ORDER BY bairro ASC", Casa.class).getResultList();				
	}
	
	public List<Casa> todasCasasBencaos() { 
		return manager.createQuery("from Casa WHERE tipo = 'Benção' ORDER BY bairro ASC", Casa.class).getResultList();				
	}
	
	public List<Casa> todasCasasCrescimento(Congregacao congregacao) { 
		long id = congregacao.getId();
		return manager.createQuery("from Casa  WHERE tipo = 'Crescimento' and congregacao_id = "+id, Casa.class).getResultList();				
	}
	
	public List<Casa> todasCasasBencaos(Congregacao congregacao) { 
		long id = congregacao.getId();
		return manager.createQuery("from Casa  WHERE tipo = 'Benção' and congregacao_id = "+id , Casa.class).getResultList();				
	}
	
	public List<Casa> todasCasasCrescimento() { 
		return manager.createQuery("from Casa WHERE tipo = 'Crescimento' ORDER BY bairro ASC", Casa.class).getResultList();				
	}
	
	public List<Casa> bencaoCongregacao(Congregacao congregacao) {
		long id = congregacao.getId();
		return manager.createQuery("FROM Casa WHERE tipo = 'Benção' and  congregacao_id = "+id, Casa.class).getResultList();
	}
	
	public List<Casa> crescimentoCongregacao(Congregacao congregacao) {
		long id = congregacao.getId();
		return manager.createQuery("FROM Casa WHERE tipo = 'Crescimento' and  congregacao_id = "+id, Casa.class).getResultList();
	}
	
	public Casa guardar(Casa casa) {
		return manager.merge(casa);
	}
	
	public void remover(Casa casa) {
		casa = porId(casa.getId());
		manager.remove(casa);
	}

	public Casa cidade(Casa casaBencao) {
	for (int i = 0; i < this.todasCasasBencaos().size(); i++) {
	      if (casaBencao == this.todasCasasBencaos().get(i)) {
	        return this.todasCasasBencaos().get(i);
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
