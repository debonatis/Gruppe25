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
public class GroupusersPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USERGROUPNAME")
    private String usergroupname;

    public GroupusersPK() {
    }

    public GroupusersPK(String username, String usergroupname) {
        this.username = username;
        this.usergroupname = usergroupname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsergroupname() {
        return usergroupname;
    }

    public void setUsergroupname(String usergroupname) {
        this.usergroupname = usergroupname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        hash += (usergroupname != null ? usergroupname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupusersPK)) {
            return false;
        }
        GroupusersPK other = (GroupusersPK) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        if ((this.usergroupname == null && other.usergroupname != null) || (this.usergroupname != null && !this.usergroupname.equals(other.usergroupname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.GroupusersPK[ username=" + username + ", usergroupname=" + usergroupname + " ]";
    }
    
}
