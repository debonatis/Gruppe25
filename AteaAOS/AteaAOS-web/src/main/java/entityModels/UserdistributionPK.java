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
public class UserdistributionPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DISPLAYNAME")
    private String displayname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROJECTIDU")
    private String projectidu;

    public UserdistributionPK() {
    }

    public UserdistributionPK(String username, String displayname, String projectidu) {
        this.username = username;
        this.displayname = displayname;
        this.projectidu = projectidu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getProjectidu() {
        return projectidu;
    }

    public void setProjectidu(String projectidu) {
        this.projectidu = projectidu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        hash += (displayname != null ? displayname.hashCode() : 0);
        hash += (projectidu != null ? projectidu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserdistributionPK)) {
            return false;
        }
        UserdistributionPK other = (UserdistributionPK) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        if ((this.displayname == null && other.displayname != null) || (this.displayname != null && !this.displayname.equals(other.displayname))) {
            return false;
        }
        if ((this.projectidu == null && other.projectidu != null) || (this.projectidu != null && !this.projectidu.equals(other.projectidu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.UserdistributionPK[ username=" + username + ", displayname=" + displayname + ", projectidu=" + projectidu + " ]";
    }
    
}
