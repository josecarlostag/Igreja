package br.org.ijv.cadastro.service;

import java.io.Serializable;

import br.org.ijv.cadastro.controller.PessoaBean;
import br.org.ijv.cadastro.model.Pessoa;
import br.org.ijv.cadastro.util.jpa.Transactional;

public class CadastroPessoaService extends PessoaBean implements Serializable{

	
	private static final long serialVersionUID = 1L;
				
	@Transactional
	public void salvar(Pessoa pessoa) {
		if(pessoa.getSexo() == null){
			mensages.error("Selecione o sexo");	
			}else{
				this.pessoas.guardar(pessoa);
				if(pessoa.getSexo().getDescricao().contains("Masculino")){
				mensages.info("O "+ pessoa.getNome()+ " foi cadastrado com sucesso.");
				}else {
					mensages.info("A "+ pessoa.getNome()+ " foi cadastrada com sucesso.");
					}
				}
			}
	
	@Transactional 
	public void excluir(Pessoa pessoaSelecionada) {
	 
		this.pessoas.remover(pessoaSelecionada);
		if(pessoaSelecionada.getSexo().getDescricao().contains("Masculino")){
			mensages.info("O "+ pessoaSelecionada.getNome()+ " foi excluido com sucesso.");
			}else {
				mensages.info("A "+ pessoaSelecionada.getNome()+ " foi excluida com sucesso.");
				}
		
		this.pessoaSelecionada = null;
		this.pessoaEdicao = null;
		consultar();
	}
	
		
}
