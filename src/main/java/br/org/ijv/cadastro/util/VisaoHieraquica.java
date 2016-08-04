package br.org.ijv.cadastro.util;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

import br.org.ijv.cadastro.controller.PessoaBean;
import br.org.ijv.cadastro.model.Cidade;
import br.org.ijv.cadastro.model.Congregacao;
import br.org.ijv.cadastro.model.Pessoa;
import br.org.ijv.cadastro.repository.Congregacoes;

@Named(value ="visaoHierarquica")
@ViewScoped
public class VisaoHieraquica extends PessoaBean implements Serializable{
        
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Congregacoes congregacoes;

	private DefaultDiagramModel model;
	
	private MindmapNode root;
    
    private MindmapNode selectedNode;

   
	public MindmapNode getRoot() {
		return root;
	}

	public MindmapNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(MindmapNode selectedNode) {
		this.selectedNode = selectedNode;
	}
	
	List<Pessoa> membros;
    List<Cidade> todasCidades;
    List<Congregacao> todasCongregacoes;
	
		 
    @PostConstruct
    public void init() {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);
        
        membros = pessoas.todosMembros();
        todasCidades = cidades.todasCidades();
        todasCongregacoes = congregacoes.todasCongregacoes();
        
             root = new DefaultMindmapNode("Jesus Vive", "Jesus Vive WebSite", "FFCC00", false);
          
        //Presidente
        for (int i = 0; i < pessoas.presidente().size(); i++) {	
        	Pessoa presi = new Pessoa();
        	presi = pessoas.presidente().get(i);
        	MindmapNode pres = new DefaultMindmapNode(presi.getNome(), "numeros", "6e9ebf", true);
        	root.addNode(pres);
        	
        	//Pastores      	
            for (int j = 0; j < membros.size(); j++) {
            	if(pessoas.todosMembros().get(j).getDiscipulador() == presi){
            		Pessoa pastor = new Pessoa();	
            		pastor = membros.get(j);
            		MindmapNode pas = new DefaultMindmapNode(pastor.getNome(), "numeros", "6e9ebf", true);
                	pres.addNode(pas);
	
                	//Diáconos
            		for (int k = 0; k < membros.size(); k++) {
            				
        				if(membros.get(k).getDiscipulador() == pastor){
        					Pessoa diacono = new Pessoa();            				
        					diacono = membros.get(k);
        					MindmapNode dia = new DefaultMindmapNode(diacono.getNome(), "numeros", "6e9ebf", true);
        	            	pas.addNode(dia);
        	            	
                	//Discipulos
             		for (int l = 0; l < membros.size(); l++) {
        				
            			if(membros.get(l).getDiscipulador() == diacono){
            				Pessoa discipulo = new Pessoa();
            				discipulo = membros.get(l);              		
            				MindmapNode dis = new DefaultMindmapNode(discipulo.getNome(), "numeros", "6e9ebf", true);
                        	dia.addNode(dis);
                    	
        	     	//Membros	
        			for (int m = 0; m < membros.size(); m++) {
        				
        				if(membros.get(m).getDiscipulador() == discipulo){
        					Pessoa membro = new Pessoa();
        					membro = membros.get(m);               
            				MindmapNode mem = new DefaultMindmapNode(membro.getNome(), "numeros", "6e9ebf", true);
                        	dis.addNode(mem);
                        	
                        	//Pessoas
                         	for (int n = 0; n < membros.size(); n++) {
                    				
                				if(membros.get(n).getDiscipulador() == membro){
                    				Pessoa pessoa = new Pessoa();
                    				pessoa = membros.get(n);
                    				MindmapNode pes = new DefaultMindmapNode(pessoa.getNome(), "numeros", "6e9ebf", true);
                                	mem.addNode(pes);
                 					}                       				
                         			}       			
        					}
                			}								
        					}
                    		}
					}
            		}     	
            	}               	        
        	}
        }
  
        
       
        for (int i = 0; i < todasCidades.size(); i++) {	
        	Cidade cidade = new Cidade();
        	cidade = todasCidades.get(i);
        	 MindmapNode cid = new DefaultMindmapNode(cidade.getNomeCidade(), "numeros", "6e9ebf", true);
        	root.addNode(cid);
       
    	for (int n = 0; n < todasCongregacoes.size(); n++) {
			
			if(todasCongregacoes.get(n).getCidade() == cidade){
				Congregacao congregacao = new Congregacao();
				congregacao = todasCongregacoes.get(n);			
				MindmapNode con = new DefaultMindmapNode(congregacao.getNomeCongregacao(), "numeros", "6e9ebf", true);
				cid.addNode(con);
        	
    	for (int k = 0; k < membros.size(); k++) {
				
			if(membros.get(k).getCongregacao() == congregacao){
				Pessoa pessoa = new Pessoa();
				pessoa = membros.get(k);			
				MindmapNode pes = new DefaultMindmapNode(pessoa.getNome(), "numeros", "6e9ebf", true);
            	con.addNode(pes);
            	
    	for (int l = 0; l < membros.size(); l++) {
    				
			if(membros.get(l).getDiscipulador() == pessoa){
				Pessoa discipulo = new Pessoa();
				discipulo = membros.get(l);				
				MindmapNode dis = new DefaultMindmapNode(discipulo.getNome(), "numeros", "6e9ebf", true);
            	pes.addNode(dis);				
			}	                          				
			}             				
			}	                          				
			}	
			}	                          				
			}
        }
        
    }
    
    public DiagramModel getModel() {
        return model;
    }
    
    public void onNodeSelect(SelectEvent event) {
		MindmapNode node = (MindmapNode) event.getObject();

		// populate if not already loaded
		if (node.getChildren().isEmpty()) {
			Object label = node.getLabel();
			
			for (int j = 0; j < pessoas.presidente().size(); j++) {
					
			if (label.equals(pessoas.presidente().get(j).getNome())) {
									
				Pessoa presidente = pessoas.presidente().get(j);
				node.addNode(new DefaultMindmapNode(presidente.getNome(), "Jesus Vive", "82c542", true));		
				}	
			}
			
			for (int j = 0; j < todasCidades.size(); j++) {
				
				if (label.equals(todasCidades.get(j).getNomeCidade())) {
										
					Cidade cidade = todasCidades.get(j);
					node.addNode(new DefaultMindmapNode(cidade.getNomeCidade(), "Jesus Vive", "82c542", true));					
					}	
				}
		}
		
		}
	
	     
	    public void onNodeDblselect(SelectEvent event) {
	        this.selectedNode = (MindmapNode) event.getObject();        
	    }
	    
	    
}
