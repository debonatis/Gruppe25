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
public class GroupsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "GROUPNAME")
    private String groupname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROJECTID")
    private String projectid;

    public GroupsPK() {
    }

    public GroupsPK(String groupname, String projectid) {
        this.groupname = groupname;
        this.projectid = projectid;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
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
        hash += (groupname != null ? groupname.hashCode() : 0);
        hash += (projectid != null ? projectid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupsPK)) {
            return false;
        }
        GroupsPK other = (GroupsPK) object;
        if ((this.groupname == null && other.groupname != null) || (this.groupname != null && !this.groupname.equals(other.groupname))) {
            return false;
        }
        if ((this.projectid == null && other.projectid != null) || (this.projectid != null && !this.projectid.equals(other.projectid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.GroupsPK[ groupname=" + groupname + ", projectid=" + projectid + " ]";
    }
    
}
