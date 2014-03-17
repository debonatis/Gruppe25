/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.security;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author simond
 */
@Named(value = "logonAndMisc")
@SessionScoped
public class logonAndMisc implements Serializable {

    /**
     * Creates a new instance of logonAndMisc
     */
    
    private String logon;
    public logonAndMisc() {
    }

    public String getLogon() {
        logon = "ok";
        return logon;
    }

    public void setLogon(String logon) {
        this.logon = logon;
    }

    
}
