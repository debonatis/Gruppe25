/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.security;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author simond
 */
@ManagedBean
@SessionScoped
public class logonAndMisc implements Serializable {

    /**
     * Creates a new instance of logonAndMisc
     */
    private String logon;
    private String csv;

    public String getCsv() {
        csv = "ok";
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

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
