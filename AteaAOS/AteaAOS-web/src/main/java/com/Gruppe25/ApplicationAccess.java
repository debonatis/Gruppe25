/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author simond
 */
@Entity
@Table(name = "applicationAccess")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicationAccess.findAll", query = "SELECT a FROM ApplicationAccess a"),
    @NamedQuery(name = "ApplicationAccess.findByAppUsername", query = "SELECT a FROM ApplicationAccess a WHERE a.applicationAccessPK.appUsername = :appUsername"),
    @NamedQuery(name = "ApplicationAccess.findByUserApplicationName", query = "SELECT a FROM ApplicationAccess a WHERE a.applicationAccessPK.userApplicationName = :userApplicationName")})
public class ApplicationAccess implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ApplicationAccessPK applicationAccessPK;

    public ApplicationAccess() {
    }

    public ApplicationAccess(ApplicationAccessPK applicationAccessPK) {
        this.applicationAccessPK = applicationAccessPK;
    }

    public ApplicationAccess(String appUsername, String userApplicationName) {
        this.applicationAccessPK = new ApplicationAccessPK(appUsername, userApplicationName);
    }

    public ApplicationAccessPK getApplicationAccessPK() {
        return applicationAccessPK;
    }

    public void setApplicationAccessPK(ApplicationAccessPK applicationAccessPK) {
        this.applicationAccessPK = applicationAccessPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationAccessPK != null ? applicationAccessPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationAccess)) {
            return false;
        }
        ApplicationAccess other = (ApplicationAccess) object;
        if ((this.applicationAccessPK == null && other.applicationAccessPK != null) || (this.applicationAccessPK != null && !this.applicationAccessPK.equals(other.applicationAccessPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.ApplicationAccess[ applicationAccessPK=" + applicationAccessPK + " ]";
    }
    
}
