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
@Table(name = "distributionGroups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DistributionGroups.findAll", query = "SELECT d FROM DistributionGroups d"),
    @NamedQuery(name = "DistributionGroups.findByDisplayName", query = "SELECT d FROM DistributionGroups d WHERE d.displayName = :displayName"),
    @NamedQuery(name = "DistributionGroups.findByEmailAlias", query = "SELECT d FROM DistributionGroups d WHERE d.emailAlias = :emailAlias"),
    @NamedQuery(name = "DistributionGroups.findByExternalEmail", query = "SELECT d FROM DistributionGroups d WHERE d.externalEmail = :externalEmail")})
public class DistributionGroups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "displayName")
    private String displayName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "emailAlias")
    private String emailAlias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "externalEmail")
    private String externalEmail;

    public DistributionGroups() {
    }

    public DistributionGroups(String displayName) {
        this.displayName = displayName;
    }

    public DistributionGroups(String displayName, String emailAlias, String externalEmail) {
        this.displayName = displayName;
        this.emailAlias = emailAlias;
        this.externalEmail = externalEmail;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailAlias() {
        return emailAlias;
    }

    public void setEmailAlias(String emailAlias) {
        this.emailAlias = emailAlias;
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
        hash += (displayName != null ? displayName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistributionGroups)) {
            return false;
        }
        DistributionGroups other = (DistributionGroups) object;
        if ((this.displayName == null && other.displayName != null) || (this.displayName != null && !this.displayName.equals(other.displayName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.DistributionGroups[ displayName=" + displayName + " ]";
    }
    
}
