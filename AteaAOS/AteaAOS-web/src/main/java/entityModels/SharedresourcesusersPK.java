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
 * @author Martin
 */
@Embeddable
public class SharedresourcesusersPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DISPLAYNAMESHARED")
    private String displaynameshared;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROJECTIDDISP")
    private String projectiddisp;

    public SharedresourcesusersPK() {
    }

    public SharedresourcesusersPK(String displaynameshared, String username, String projectiddisp) {
        this.displaynameshared = displaynameshared;
        this.username = username;
        this.projectiddisp = projectiddisp;
    }

    public String getDisplaynameshared() {
        return displaynameshared;
    }

    public void setDisplaynameshared(String displaynameshared) {
        this.displaynameshared = displaynameshared;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProjectiddisp() {
        return projectiddisp;
    }

    public void setProjectiddisp(String projectiddisp) {
        this.projectiddisp = projectiddisp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (displaynameshared != null ? displaynameshared.hashCode() : 0);
        hash += (username != null ? username.hashCode() : 0);
        hash += (projectiddisp != null ? projectiddisp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SharedresourcesusersPK)) {
            return false;
        }
        SharedresourcesusersPK other = (SharedresourcesusersPK) object;
        if ((this.displaynameshared == null && other.displaynameshared != null) || (this.displaynameshared != null && !this.displaynameshared.equals(other.displaynameshared))) {
            return false;
        }
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        if ((this.projectiddisp == null && other.projectiddisp != null) || (this.projectiddisp != null && !this.projectiddisp.equals(other.projectiddisp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.SharedresourcesusersPK[ displaynameshared=" + displaynameshared + ", username=" + username + ", projectiddisp=" + projectiddisp + " ]";
    }
    
}
