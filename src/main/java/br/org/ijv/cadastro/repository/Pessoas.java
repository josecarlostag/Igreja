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

import br.org.ijv.cadastro.model.Cidade;
import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.model.Pessoa;

public class Pessoas  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
		
	public Pessoa porId(Long id) {
		return manager.find(Pessoa.class, id);
	}
	
	
	public List<Pessoa> todasPessoas() { 
		return manager.createQuery("FROM Pessoa ORDER BY nome_completo ASC", Pessoa.class).getResultList();		
	}
	
	public List<Pessoa> todosMembros() { 
		return manager.createQuery("FROM Pessoa WHERE status='ATIVO' ORDER BY nome ASC", Pessoa.class).getResultList();		
	}
	
	public List<Pessoa> presidente() { 
		return manager.createQuery("FROM Pessoa WHERE status='ATIVO' and funcao = 'Presidente' ", Pessoa.class).getResultList();		
	}
	
	public List<Pessoa> membrosAtivosCongregacao(Congregacao congregacao) { 	
		long id = congregacao.getId();
		return manager.createQuery("FROM Pessoa WHERE status='ATIVO' and congregacao_id = "+id, Pessoa.class).getResultList();		
	}
	
	public List<Pessoa> membroCongregacao(Congregacao congregacao) { 	
		long id = congregacao.getId();
		return manager.createQuery("FROM Pessoa WHERE congregacao_id = "+id, Pessoa.class).getResultList();		
	}
	
	public List<Pessoa> homemCongregacao(Congregacao congregacao) { 	
		long id = congregacao.getId();
		return manager.createQuery("FROM Pessoa  WHERE sexo = 'MASCULINO' and status='ATIVO' and congregacao_id = "+id, Pessoa.class).getResultList();		
	}
	
	public List<Pessoa> mulherCongregacao(Congregacao congregacao) { 	
		long id = congregacao.getId();
		return manager.createQuery("FROM Pessoa WHERE sexo = 'FEMININO'and status='ATIVO' and congregacao_id = "+id, Pessoa.class).getResultList();		
	}
	
	public List<Pessoa> colaboradorCongregacao(Congregacao congregacao) { 	
		long id = congregacao.getId();
		return manager.createQuery("FROM Pessoa WHERE funcao = 'Colaborador'and status='ATIVO' and congregacao_id = "+id, Pessoa.class).getResultList();		
	}
	
	
	
	public List<Pessoa> diaconoCongregacao(Congregacao congregacao) { 	
		long id = congregacao.getId();
		return manager.createQuery("FROM Pessoa WHERE funcao = 'Diácono'and status='ATIVO' and congregacao_id = "+id, Pessoa.class).getResultList();		
	}
	
	
	
	public List<Pessoa> pastorCongregacao(Congregacao congregacao) { 	
		long id = congregacao.getId();
		return manager.createQuery("FROM Pessoa WHERE funcao = 'Presidente'and congregacao_id = "+id+" or funcao = 'Pastor' and status='ATIVO' and congregacao_id = "+id, Pessoa.class).getResultList();		
	}
	
	public List<Pessoa> todosPastores() {
		return manager.createQuery("FROM Pessoa WHERE funcao = 'Presidente' or funcao = 'Pastor' and status='ATIVO' ORDER BY nome ASC", Pessoa.class).getResultList();
	}
	
	public List<Pessoa> intercessorCongregacao(Congregacao congregacao) { 	
		long id = congregacao.getId();
		return manager.createQuery("FROM Pessoa WHERE funcao = 'Intercessor'and status='ATIVO' and congregacao_id = "+id, Pessoa.class).getResultList();		
	}
	
	public List<Pessoa> louvorCongregacao(Congregacao congregacao) { 	
		long id = congregacao.getId();
		return manager.createQuery("FROM Pessoa WHERE funcao = 'Louvor' and status='ATIVO' and congregacao_id = "+id, Pessoa.class).getResultList();		
	}
	
	public List<Pessoa> todosDiaconos() {
		return manager.createQuery("FROM Pessoa WHERE funcao = 'Diácono' and status= 'ATIVO' ORDER BY nome_completo ASC", Pessoa.class).getResultList();
	}
	
	public List<Pessoa> todosDiscipuladores() {
		return manager.createQuery("FROM Pessoa WHERE status='ATIVO' ORDER BY nome_completo ASC", Pessoa.class).getResultList();
	}
	
	public List<Pessoa> homensDiscipuladores() {
		return manager.createQuery("FROM Pessoa WHERE sexo = 'MASCULINO'and status='ATIVO' ORDER BY nome_completo ASC", Pessoa.class).getResultList();
	}

	public List<Pessoa> mulheresDiscipuladoras() {
		return manager.createQuery("FROM Pessoa WHERE sexo = 'FEMININO' and status='ATIVO' ORDER BY nome_completo ASC", Pessoa.class).getResultList();
	}
	
	public List<Pessoa> esposas() {
		return manager.createQuery("FROM Pessoa WHERE sexo = 'FEMININO' and estado_civil != 'Solteiro' ORDER BY nome_completo ASC", Pessoa.class).getResultList();
	}

	public List<Pessoa> maridos() {
		return manager.createQuery("FROM Pessoa WHERE sexo = 'MASCULINO' and estado_civil != 'Solteiro' ORDER BY nome_completo ASC", Pessoa.class).getResultList();
	}
			
	public Pessoa guardar(Pessoa pessoa) {		
		return manager.merge(pessoa);
	}
	
	public void remover(Pessoa pessoa) {
		pessoa = porId(pessoa.getId());
		manager.remove(pessoa);
	}
	
	@InterceptorBinding 
	@Retention(RetentionPolicy.RUNTIME) 
	@Target
	({ ElementType.TYPE, ElementType.METHOD })
	public @interface Transactional { 
		
	}

	public int contarCidade(Cidade cidadeSelecionada) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Pessoa porSku(String sku) {
		try {
			return manager.createQuery("from Pessoa where upper(sku) = :sku", Pessoa.class)
				.setParameter("sku", sku.toUpperCase())
				.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	


}
