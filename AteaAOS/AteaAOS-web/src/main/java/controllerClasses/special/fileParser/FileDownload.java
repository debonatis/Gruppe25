/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.fileParser;

import java.io.InputStream;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
public class FileDownload {

    private StreamedContent file;
    private boolean onlyUsers = false;
    private boolean onlyGroups = false;
    private boolean csv = false;
    private boolean okDownload = false;

    public FileDownload() {
        InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream("/images/optimusprime.jpg");
        file = new DefaultStreamedContent(stream, "image/jpg", "downloaded_optimus.jpg");
    }
    
    public void setDown(boolean e){
        this.okDownload = e;
    }

    public StreamedContent getFile() {
        return file;
    }

    public boolean isOkDownload() {
        return okDownload;
    }

    public void setOkDownload(boolean okDownload) {
        this.okDownload = okDownload;
    }

    public boolean isOnlyUsers() {
        return onlyUsers;
    }

    public void setOnlyUsers(boolean onlyUsers) {
        this.onlyUsers = onlyUsers;
    }

    public boolean isOnlyGroups() {
        return onlyGroups;
    }

    public void setOnlyGroups(boolean onlyGroups) {
        this.onlyGroups = onlyGroups;
    }

    public boolean isCsv() {
        return csv;
    }

    public void setCsv(boolean csv) {
        this.csv = csv;
    }
    
    public void addMessage() {
        String summary = onlyUsers ? "Users Checked" : "Users Unchecked";
        String summary1 = onlyGroups ? "Groups Checked" : "Groups Unchecked";
        String summary2= csv ? "CSV Checked" : "CSV Unchecked";
 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(""+summary+"<br />"+summary1+"<br />"+summary2+""));
    }
    
}
