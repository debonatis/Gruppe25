/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entitys;

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
public class GroupaffiliationsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "GROUPNAMEMANY")
    private String groupnamemany;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "GROUPMAMBERSHIP")
    private String groupmambership;

    public GroupaffiliationsPK() {
    }

    public GroupaffiliationsPK(String groupnamemany, String groupmambership) {
        this.groupnamemany = groupnamemany;
        this.groupmambership = groupmambership;
    }

    public String getGroupnamemany() {
        return groupnamemany;
    }

    public void setGroupnamemany(String groupnamemany) {
        this.groupnamemany = groupnamemany;
    }

    public String getGroupmambership() {
        return groupmambership;
    }

    public void setGroupmambership(String groupmambership) {
        this.groupmambership = groupmambership;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupnamemany != null ? groupnamemany.hashCode() : 0);
        hash += (groupmambership != null ? groupmambership.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupaffiliationsPK)) {
            return false;
        }
        GroupaffiliationsPK other = (GroupaffiliationsPK) object;
        if ((this.groupnamemany == null && other.groupnamemany != null) || (this.groupnamemany != null && !this.groupnamemany.equals(other.groupnamemany))) {
            return false;
        }
        if ((this.groupmambership == null && other.groupmambership != null) || (this.groupmambership != null && !this.groupmambership.equals(other.groupmambership))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.GroupaffiliationsPK[ groupnamemany=" + groupnamemany + ", groupmambership=" + groupmambership + " ]";
    }
    
}
