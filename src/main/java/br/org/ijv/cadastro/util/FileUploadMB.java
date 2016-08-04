package br.org.ijv.cadastro.util;

import java.io.File;
import java.io.FileOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name="fileUploadMB")
@RequestScoped
public class FileUploadMB {

    public FileUploadMB() {   
    }

    public void doUpload(FileUploadEvent fileUploadEvent) { 
             UploadedFile uploadedFile = fileUploadEvent.getFile();  
             String fileNameUploaded = uploadedFile.getFileName(); 
             long fileSizeUploaded = uploadedFile.getSize(); 
             String infoAboutFile = "<br/> Arquivo recebido: <b>" +fileNameUploaded +"</b><br/>"+
                 "Tamanho do Arquivo: <b>"+fileSizeUploaded+"</b>";
             FacesContext facesContext = FacesContext.getCurrentInstance();
             facesContext.addMessage(null, new FacesMessage("Sucesso", infoAboutFile));
   }
    
    public void fileUploadAction(FileUploadEvent event) {
    	try {
   

    	FacesContext aFacesContext = FacesContext.getCurrentInstance();
    	ServletContext context = (ServletContext) aFacesContext.getExternalContext().getContext();

    	String realPath = context.getRealPath("/");

    	// Aqui cria o diretorio caso não exista
    	File file = new File(realPath +"/resources/fotos/");
    	file.mkdirs();

    	byte[] arquivo = event.getFile().getContents();
    	String caminho = realPath  +"/resources/fotos/" + event.getFile().getFileName();

    	// esse trecho grava o arquivo no diretório
    	FileOutputStream fos = new FileOutputStream(caminho);
    	fos.write(arquivo);
    	fos.close();


    	} catch (Exception ex) {
    	System.out.println("Erro no upload de imagem" + ex);
    	}
    	} 
}