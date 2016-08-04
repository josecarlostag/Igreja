package br.org.ijv.cadastro.util;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.ServletContext;

import org.primefaces.event.CaptureEvent;

import br.org.ijv.cadastro.model.Pessoa;
 
@ManagedBean
@ViewScoped
public class PhotoCamView implements Serializable{
     

	private static final long serialVersionUID = 1L;
	private String filename;
    
    private String getRandomImageName() {
        int i = (int) (Math.random() * 10000000);
         
        return String.valueOf(i);
    }
 
    public String getFilename() {
        return filename;
    }
     
    public void oncapture(CaptureEvent captureEvent, Pessoa pessoaEdicao) {
        filename = getRandomImageName();
        byte[] data = captureEvent.getData();
         
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String newFileName = servletContext.getRealPath("")+File.separator  + "resources" + 
                                    File.separator + "images" + File.separator + pessoaEdicao.getId()+"foto"+".jpg";
        
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        }
        catch(IOException e) {
            throw new FacesException("Error in writing captured image.", e);
        }
    }

}

