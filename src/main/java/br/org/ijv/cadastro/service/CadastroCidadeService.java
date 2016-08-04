package br.org.ijv.cadastro.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.org.ijv.cadastro.model.Cidade;
import br.org.ijv.cadastro.repository.Cidades;
import br.org.ijv.cadastro.repository.Pessoas;
import br.org.ijv.cadastro.util.jpa.Transactional;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

public class CadastroCidadeService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private FacesMessages mensages;
	
	@Inject
	private Cidades cidades;
	
	@Inject
	private Pessoas pessoas;
	
			
	@Transactional
	public void salvar(Cidade cidade) {
		this.cidades.guardar(cidade);
	}
	
	@Transactional 
	public void excluir(Cidade cidadeSelecionada){
			
		if(pessoas.contarCidade(cidadeSelecionada) >= 1){
			mensages.error("Não é permitido excluir cidade usada por membro");
		}else {
		
		this.cidades.remover(cidadeSelecionada);
		mensages.info("Cidade "+cidadeSelecionada.getNomeCidade()+" "+cidadeSelecionada.getEstado()+" removida com sucesso!");
		cidadeSelecionada = null;
		} 
	}
}