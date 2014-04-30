/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityModels;

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
public class DistributiongroupsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DISPLAYNAME")
    private String displayname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROJECTID")
    private String projectid;

    public DistributiongroupsPK() {
    }

    public DistributiongroupsPK(String displayname, String projectid) {
        this.displayname = displayname;
        this.projectid = projectid;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (displayname != null ? displayname.hashCode() : 0);
        hash += (projectid != null ? projectid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DistributiongroupsPK)) {
            return false;
        }
        DistributiongroupsPK other = (DistributiongroupsPK) object;
        if ((this.displayname == null && other.displayname != null) || (this.displayname != null && !this.displayname.equals(other.displayname))) {
            return false;
        }
        if ((this.projectid == null && other.projectid != null) || (this.projectid != null && !this.projectid.equals(other.projectid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.DistributiongroupsPK[ displayname=" + displayname + ", projectid=" + projectid + " ]";
    }
    
}
