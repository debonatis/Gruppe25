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
public class PrositeusersPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROJECTID")
    private String projectid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USERNAME")
    private String username;

    public PrositeusersPK() {
    }

    public PrositeusersPK(String projectid, String username) {
        this.projectid = projectid;
        this.username = username;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectid != null ? projectid.hashCode() : 0);
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrositeusersPK)) {
            return false;
        }
        PrositeusersPK other = (PrositeusersPK) object;
        if ((this.projectid == null && other.projectid != null) || (this.projectid != null && !this.projectid.equals(other.projectid))) {
            return false;
        }
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.PrositeusersPK[ projectid=" + projectid + ", username=" + username + " ]";
    }
    
}
