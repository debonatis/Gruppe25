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
    private String username;
    public Security() {
        
    }

    public String getProjectID() {
        projectID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(projectID);
        if(projectID == null){
            projectID = "Project not choosen";
        }
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getUsername() {
        username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        if(projectID == null){
            projectID = "Not logged in";
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
