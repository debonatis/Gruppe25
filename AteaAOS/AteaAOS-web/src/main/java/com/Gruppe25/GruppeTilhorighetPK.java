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
public class GruppeTilhorighetPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "groupName")
    private String groupName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "gruppeMedlem")
    private String gruppeMedlem;

    public GruppeTilhorighetPK() {
    }

    public GruppeTilhorighetPK(String groupName, String gruppeMedlem) {
        this.groupName = groupName;
        this.gruppeMedlem = gruppeMedlem;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGruppeMedlem() {
        return gruppeMedlem;
    }

    public void setGruppeMedlem(String gruppeMedlem) {
        this.gruppeMedlem = gruppeMedlem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupName != null ? groupName.hashCode() : 0);
        hash += (gruppeMedlem != null ? gruppeMedlem.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruppeTilhorighetPK)) {
            return false;
        }
        GruppeTilhorighetPK other = (GruppeTilhorighetPK) object;
        if ((this.groupName == null && other.groupName != null) || (this.groupName != null && !this.groupName.equals(other.groupName))) {
            return false;
        }
        if ((this.gruppeMedlem == null && other.gruppeMedlem != null) || (this.gruppeMedlem != null && !this.gruppeMedlem.equals(other.gruppeMedlem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.GruppeTilhorighetPK[ groupName=" + groupName + ", gruppeMedlem=" + gruppeMedlem + " ]";
    }
    
}
