/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author simond
 */
@Embeddable
public class ApplicationAccessPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "appUsername")
    private String appUsername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "userApplicationName")
    private String userApplicationName;

    public ApplicationAccessPK() {
    }

    public ApplicationAccessPK(String appUsername, String userApplicationName) {
        this.appUsername = appUsername;
        this.userApplicationName = userApplicationName;
    }

    public String getAppUsername() {
        return appUsername;
    }

    public void setAppUsername(String appUsername) {
        this.appUsername = appUsername;
    }

    public String getUserApplicationName() {
        return userApplicationName;
    }

    public void setUserApplicationName(String userApplicationName) {
        this.userApplicationName = userApplicationName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appUsername != null ? appUsername.hashCode() : 0);
        hash += (userApplicationName != null ? userApplicationName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationAccessPK)) {
            return false;
        }
        ApplicationAccessPK other = (ApplicationAccessPK) object;
        if ((this.appUsername == null && other.appUsername != null) || (this.appUsername != null && !this.appUsername.equals(other.appUsername))) {
            return false;
        }
        if ((this.userApplicationName == null && other.userApplicationName != null) || (this.userApplicationName != null && !this.userApplicationName.equals(other.userApplicationName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.entity.ApplicationAccessPK[ appUsername=" + appUsername + ", userApplicationName=" + userApplicationName + " ]";
    }
    
}
