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
public class GruppeLevel6PK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "guID")
    private String guID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "groupName")
    private String groupName;

    public GruppeLevel6PK() {
    }

    public GruppeLevel6PK(String guID, String groupName) {
        this.guID = guID;
        this.groupName = groupName;
    }

    public String getGuID() {
        return guID;
    }

    public void setGuID(String guID) {
        this.guID = guID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guID != null ? guID.hashCode() : 0);
        hash += (groupName != null ? groupName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruppeLevel6PK)) {
            return false;
        }
        GruppeLevel6PK other = (GruppeLevel6PK) object;
        if ((this.guID == null && other.guID != null) || (this.guID != null && !this.guID.equals(other.guID))) {
            return false;
        }
        if ((this.groupName == null && other.groupName != null) || (this.groupName != null && !this.groupName.equals(other.groupName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.GruppeLevel6PK[ guID=" + guID + ", groupName=" + groupName + " ]";
    }
    
}
