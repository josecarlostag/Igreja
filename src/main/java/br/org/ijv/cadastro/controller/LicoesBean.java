package br.org.ijv.cadastro.controller;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;

import br.org.ijv.cadastro.model.Licao;
import br.org.ijv.cadastro.model.Serie;
import br.org.ijv.cadastro.repository.Licoes;
import br.org.ijv.cadastro.service.CadastroLicaoService;
import br.org.ijv.cadastro.util.jsf.FacesMessages;

@Named
@ViewScoped
public class LicoesBean extends SeriesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLicaoService cadastraLicao;
	
	@Inject
	protected Licoes licoes;
	
	@Inject
	protected FacesMessages mensages;
				
	protected List<Licao> todasLicoes;
	protected Licao licaoEdicao = new Licao();
	protected Licao licaoSelecionada;
	
		
	public void prepararNovoCadastro() {
		licaoEdicao = new Licao();
	}
			
	public void salvar(){		
		cadastraLicao.salvar(licaoEdicao);		
		consultar();		
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-licao:msgs", "frm-licao:licoes-table"));
	}
	
	public void consultar() {			
		todasLicoes = licoes.todasLicoes();
		todasSeries = series.todasSerie();
	}
		
	public void excluir(){				
		cadastraLicao.excluir(licaoSelecionada);
		consultar();
		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm-licao:msgs", "frm-licao:licoes-table"));
	}
				
	public List<Licao> getTodasLicoes() {
		consultar();
		return todasLicoes;
	}
	
	public Licao getLicaoEdicao() {
		return licaoEdicao;
	}

	public void setLicaoEdicao(Licao licaoEdicao) {
		this.licaoEdicao = licaoEdicao;
	}

	public Licao getLicaoSelecionada() {
		return licaoSelecionada;
	}

	public void setLicaoSelecionada(Licao licaoSelecionada) {
		this.licaoSelecionada = licaoSelecionada;
	}


	public Licoes getLicoes() {
		return licoes;
	}
      	
	private StreamedContent file;
    
    public void FileDownload() {  
    //	if(licaoSelecionada != null){
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/casaBencao/"+licaoSelecionada.getSerie().getNomeSerie()+"/"+licaoSelecionada.getNomeLicao()+".pdf");
        file = new DefaultStreamedContent(stream, "casaBencao/pdf", "download.pdf");
    //	}
    }
 
    public StreamedContent getFile() {
    	
    	FileDownload();
        return file;
    }
    
   
   // private String arquivo;
    
   	 
    private TreeNode root;
    
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Série", null);
        consultar();
        Serie serie = new Serie();
        Licao licao = new Licao();
        for (int i = 0; i < series.todasSerie().size(); i++) {
        	serie = todasSeries.get(i);
        	
        	 TreeNode series = new DefaultTreeNode(serie.getNomeSerie(), root);
        	 series.getChildren().add(new DefaultTreeNode(serie.getNomeSerie()));
        	 for (int j = 0; j < licoes.todasLicoes().size(); j++) {
        		 licao = todasLicoes.get(j);
             	 if(licao.getSerie() == serie){
             		 TreeNode licoes = new DefaultTreeNode(licao.getNomeLicao(), series);
             		 	licoes.getChildren().add(new DefaultTreeNode(licao.getNomeLicao()));
             	 	} 
        	 	} 
        }
    }
 
    public TreeNode getRoot() {
        return root;
    }
    
    
 /*      
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
*/
}