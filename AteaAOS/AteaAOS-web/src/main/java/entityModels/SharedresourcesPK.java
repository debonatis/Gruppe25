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
public class SharedresourcesPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DISPLAYNAMESHARED")
    private String displaynameshared;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROJECTID")
    private String projectid;

    public SharedresourcesPK() {
    }

    public SharedresourcesPK(String displaynameshared, String projectid) {
        this.displaynameshared = displaynameshared;
        this.projectid = projectid;
    }

    public String getDisplaynameshared() {
        return displaynameshared;
    }

    public void setDisplaynameshared(String displaynameshared) {
        this.displaynameshared = displaynameshared;
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
        hash += (displaynameshared != null ? displaynameshared.hashCode() : 0);
        hash += (projectid != null ? projectid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SharedresourcesPK)) {
            return false;
        }
        SharedresourcesPK other = (SharedresourcesPK) object;
        if ((this.displaynameshared == null && other.displaynameshared != null) || (this.displaynameshared != null && !this.displaynameshared.equals(other.displaynameshared))) {
            return false;
        }
        if ((this.projectid == null && other.projectid != null) || (this.projectid != null && !this.projectid.equals(other.projectid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.SharedresourcesPK[ displaynameshared=" + displaynameshared + ", projectid=" + projectid + " ]";
    }
    
}
