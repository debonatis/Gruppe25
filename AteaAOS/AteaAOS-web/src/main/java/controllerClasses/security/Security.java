/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.security;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

/**
 *
 * @author simond
 */
@Named(value = "security")
@SessionScoped
public class Security implements Serializable {

    /**
     * Creates a new instance of Security
     */
    
    private String projectID;
    public Security() {
        projectID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(projectID);
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }
    
}
