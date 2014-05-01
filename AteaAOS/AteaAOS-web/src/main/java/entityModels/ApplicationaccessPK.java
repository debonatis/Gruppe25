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
public class ApplicationaccessPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APPLICATIONID")
    private String applicationid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROJECTIDU")
    private String projectidu;

    public ApplicationaccessPK() {
    }

    public ApplicationaccessPK(String username, String applicationid, String projectidu) {
        this.username = username;
        this.applicationid = applicationid;
        this.projectidu = projectidu;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(String applicationid) {
        this.applicationid = applicationid;
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
        hash += (applicationid != null ? applicationid.hashCode() : 0);
        hash += (projectidu != null ? projectidu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationaccessPK)) {
            return false;
        }
        ApplicationaccessPK other = (ApplicationaccessPK) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        if ((this.applicationid == null && other.applicationid != null) || (this.applicationid != null && !this.applicationid.equals(other.applicationid))) {
            return false;
        }
        if ((this.projectidu == null && other.projectidu != null) || (this.projectidu != null && !this.projectidu.equals(other.projectidu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.ApplicationaccessPK[ username=" + username + ", applicationid=" + applicationid + ", projectidu=" + projectidu + " ]";
    }
    
}
