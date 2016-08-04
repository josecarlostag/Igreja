package br.org.ijv.cadastro.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CaptureEvent;
import org.primefaces.event.FileUploadEvent;

import br.org.ijv.cadastro.model.EstadoCivil;
import br.org.ijv.cadastro.model.Funcao;
import br.org.ijv.cadastro.model.PerfilUsuario;
import br.org.ijv.cadastro.model.Pessoa;
import br.org.ijv.cadastro.model.Sexo;
import br.org.ijv.cadastro.model.Status;
import br.org.ijv.cadastro.repository.Cidades;
import br.org.ijv.cadastro.repository.Pessoas;
import br.org.ijv.cadastro.security.Seguranca;
import br.org.ijv.cadastro.service.CadastroPessoaService;
import br.org.ijv.cadastro.util.PhotoCamView;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

@Named
@ViewScoped
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private byte[] arquivo;
	
	public byte[] getArquivo() {
		
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	@Inject
	protected FacesMessages mensages;

	@Inject
	CadastroPessoaService cadastraPessoa;

	@Inject
	protected Pessoas pessoas;
	
	@Inject
	protected Cidades cidades;

	@Inject
	protected PhotoCamView fotografa;
	
	protected List<Pessoa> todasPessoas;
	protected List<Pessoa> discipuladores;
	protected List<Pessoa> discipuladoras;
	protected List<Pessoa> pastores;
	protected List<Pessoa> diaconos;
	protected List<Pessoa> conjuge;
	protected List<Pessoa> presidente;
	protected Pessoa pessoaEdicao = new Pessoa();
	protected Pessoa pessoaSelecionada;
	protected List<Pessoa> membros;

	
	public void prepararNovoCadastro() {
		pessoaEdicao = new Pessoa();
		consultar();
	}

	public void salvar() {
		cadastraPessoa.salvar(pessoaEdicao);
		consultar();
		RequestContext.getCurrentInstance().update(Arrays.asList("frm-pessoa:msgs", "frm-pessoa:pessoas-table"));
	}

	public Funcao[] getFuncoes() {
		return Funcao.values();
	}

	public Status[] getStatus() {
		return Status.values();
	}

	public EstadoCivil[] getEstadosCivil() {
		return EstadoCivil.values();
	}

	public void consultar() {
		todasPessoas = pessoas.todasPessoas();		
	}

	public List<Pessoa> getTodasPessoas() {
		return todasPessoas;
	}
	
	public List<Pessoa> getPresidente() {
		presidente = pessoas.presidente();
		return presidente;
	}

	public List<Pessoa> getPessoas() {
		consultar();
		return todasPessoas;
	}

	public List<Pessoa> getConjuge() {
		if (pessoaEdicao.getId() != null) {
			if (pessoaSelecionada.getSexo() == Sexo.MASCULINO) {
				conjuge = pessoas.esposas();
			} else {
				conjuge = pessoas.maridos();
			}

		} else {
			conjuge = pessoas.todasPessoas();
		}
		return conjuge;
	}

	public List<Pessoa> getDiscipuladores() {
		if (pessoaEdicao.getId() != null) {
			if (pessoaSelecionada.getSexo() == Sexo.MASCULINO) {
				discipuladores = pessoas.homensDiscipuladores();
			} else {
				discipuladores = pessoas.mulheresDiscipuladoras();
			}
			return discipuladores;
		}
		return null;
	}

	public List<Pessoa> getPastores() {
		pastores = pessoas.todosPastores();
		return pastores;
	}
		
	public List<Pessoa> getDiaconos() {
		diaconos = pessoas.todosDiaconos();
		return diaconos;
	}

	public List<Pessoa> getMembros() {
		membros = pessoas.todosMembros();
		return membros;
	}
	
	@Inject
	private Seguranca usuario;
		
	public List<Pessoa> getMembrosCongregacao() {
		
		if(usuario.getPerfil() == PerfilUsuario.ADMIN){
			
		  membros = pessoas.todasPessoas();	
			
		}else{
		
		membros = pessoas.membroCongregacao(usuario.getUsuarioLogado().getUsuario().getCongregacao());
		
		}
		return membros;
	}

	public Pessoa getPessoaEdicao() {
		return pessoaEdicao;
	}

	public Sexo[] getSexo() {
		return Sexo.values();
	}

	public void setPessoaEdicao(Pessoa pessoaEdicao) {
		this.pessoaEdicao = pessoaEdicao;
	}

	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

	public CadastroPessoaService getCadastraPessoa() {
		return cadastraPessoa;
	}

	public void excluir() {
		cadastraPessoa.excluir(pessoaSelecionada);
		consultar();
		RequestContext.getCurrentInstance()
				.update(Arrays.asList("frm-pessoa:msgs", "frm-pessoa:pessoas-table", "frm-pessoa.toolbar"));
	}

	public void oncapture(CaptureEvent captureEvent) {
		fotografa.oncapture(captureEvent, pessoaEdicao);
		RequestContext.getCurrentInstance().update(Arrays.asList("frm-pessoa:msgs", "frm-pessoa:pessoas-table", "frm-pessoa:painel-dialog:foto"));
	}

	public void fileUploadAction(FileUploadEvent event) {
    	try {
   
    	FacesContext aFacesContext = FacesContext.getCurrentInstance();
    	ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

    	String realPath = context.getRealPath("/");

    	// Aqui cria o diretorio caso não exista
    	File file = new File(realPath +"/resources/images/");
    	file.mkdirs();
    	
    	//Aqui informa o nome do arquivo
    	byte[] arquivo = event.getFile().getContents();
    	String caminho = realPath  +"/resources/images/" + pessoaEdicao.getId()+"foto"+".jpg";

    	// esse trecho grava o arquivo no diretório
    	FileOutputStream fos = new FileOutputStream(caminho);
  	
    	fos.write(arquivo);
    	
    	fos.close();
    	
    	} catch (Exception ex) {
    	System.out.println("Erro no upload de imagem" + ex);
    	}
    	}

	 
		
	
	
	
}