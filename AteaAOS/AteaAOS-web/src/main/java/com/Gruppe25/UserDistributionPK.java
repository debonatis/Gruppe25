/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25;

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
public class UserDistributionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "distUsername")
    private String distUsername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "distDisplayName")
    private String distDisplayName;

    public UserDistributionPK() {
    }

    public UserDistributionPK(String distUsername, String distDisplayName) {
        this.distUsername = distUsername;
        this.distDisplayName = distDisplayName;
    }

    public String getDistUsername() {
        return distUsername;
    }

    public void setDistUsername(String distUsername) {
        this.distUsername = distUsername;
    }

    public String getDistDisplayName() {
        return distDisplayName;
    }

    public void setDistDisplayName(String distDisplayName) {
        this.distDisplayName = distDisplayName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (distUsername != null ? distUsername.hashCode() : 0);
        hash += (distDisplayName != null ? distDisplayName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDistributionPK)) {
            return false;
        }
        UserDistributionPK other = (UserDistributionPK) object;
        if ((this.distUsername == null && other.distUsername != null) || (this.distUsername != null && !this.distUsername.equals(other.distUsername))) {
            return false;
        }
        if ((this.distDisplayName == null && other.distDisplayName != null) || (this.distDisplayName != null && !this.distDisplayName.equals(other.distDisplayName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.UserDistributionPK[ distUsername=" + distUsername + ", distDisplayName=" + distDisplayName + " ]";
    }
    
}
