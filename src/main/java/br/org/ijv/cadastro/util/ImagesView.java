package br.org.ijv.cadastro.util;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class ImagesView {
     
    private List<String> images;
     
    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 12; i++) {
            images.add("foto" + i + ".jpg");
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}