/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author simond
 */
@Entity
@Table(name = "sharedResources")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SharedResources.findAll", query = "SELECT s FROM SharedResources s"),
    @NamedQuery(name = "SharedResources.findByDisplayNameShared", query = "SELECT s FROM SharedResources s WHERE s.displayNameShared = :displayNameShared"),
    @NamedQuery(name = "SharedResources.findByEmailAlias", query = "SELECT s FROM SharedResources s WHERE s.emailAlias = :emailAlias"),
    @NamedQuery(name = "SharedResources.findByAccess", query = "SELECT s FROM SharedResources s WHERE s.access = :access"),
    @NamedQuery(name = "SharedResources.findByExternalEmail", query = "SELECT s FROM SharedResources s WHERE s.externalEmail = :externalEmail")})
public class SharedResources implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "displayNameShared")
    private String displayNameShared;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "emailAlias")
    private String emailAlias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "access")
    private String access;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "externalEmail")
    private String externalEmail;

    public SharedResources() {
    }

    public SharedResources(String displayNameShared) {
        this.displayNameShared = displayNameShared;
    }

    public SharedResources(String displayNameShared, String emailAlias, String access, String externalEmail) {
        this.displayNameShared = displayNameShared;
        this.emailAlias = emailAlias;
        this.access = access;
        this.externalEmail = externalEmail;
    }

    public String getDisplayNameShared() {
        return displayNameShared;
    }

    public void setDisplayNameShared(String displayNameShared) {
        this.displayNameShared = displayNameShared;
    }

    public String getEmailAlias() {
        return emailAlias;
    }

    public void setEmailAlias(String emailAlias) {
        this.emailAlias = emailAlias;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getExternalEmail() {
        return externalEmail;
    }

    public void setExternalEmail(String externalEmail) {
        this.externalEmail = externalEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (displayNameShared != null ? displayNameShared.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SharedResources)) {
            return false;
        }
        SharedResources other = (SharedResources) object;
        if ((this.displayNameShared == null && other.displayNameShared != null) || (this.displayNameShared != null && !this.displayNameShared.equals(other.displayNameShared))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.SharedResources[ displayNameShared=" + displayNameShared + " ]";
    }
    
}
