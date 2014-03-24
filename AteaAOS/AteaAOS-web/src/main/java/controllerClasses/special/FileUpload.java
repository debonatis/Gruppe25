/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special;

import controllerClasses.special.cSVparser.readerCSV;
import java.io.IOException;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author simond
 */
@ManagedBean
@SessionScoped
public class FileUpload implements Serializable {

    /**
     * Creates a new instance of FileUpload
     */
    public FileUpload() {
    }
    private UploadedFile file;
    readerCSV reader;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if(file != null) {
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
                FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            reader = new readerCSV(file);
        } catch (IOException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        }
}
