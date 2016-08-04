package br.org.ijv.cadastro.util;

import java.io.File;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import org.primefaces.model.CroppedImage;
 
@ManagedBean
public class CropperView {
     
    private CroppedImage croppedImage;
     
    private String newImageName;
 
    public CroppedImage getCroppedImage() {
        return croppedImage;
    }
 
    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }
 
    public void crop() {
        if(croppedImage == null) {
            return;
        }
         
        setNewImageName(getRandomImageName());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources"  +
                    File.separator + "images"  + File.separator + getNewImageName() + ".jpeg";
         
        FileImageOutputStream imageOutput;
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
            return;
        }
         
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cropping finished."));
    }
     
    private String getRandomImageName() {
        int i = (int) (Math.random() * 1000000);
         
        return String.valueOf(i);
    }
     
    public String getNewImageName() {
        return newImageName;
    }
 
    public void setNewImageName(String newImageName) {
        this.newImageName = newImageName;
    }
}
