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
public class ApplicationaccessPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APPLICATIONNAME")
    private String applicationname;

    public ApplicationaccessPK() {
    }

    public ApplicationaccessPK(String username, String applicationname) {
        this.username = username;
        this.applicationname = applicationname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getApplicationname() {
        return applicationname;
    }

    public void setApplicationname(String applicationname) {
        this.applicationname = applicationname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        hash += (applicationname != null ? applicationname.hashCode() : 0);
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
        if ((this.applicationname == null && other.applicationname != null) || (this.applicationname != null && !this.applicationname.equals(other.applicationname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.ApplicationaccessPK[ username=" + username + ", applicationname=" + applicationname + " ]";
    }
    
}
