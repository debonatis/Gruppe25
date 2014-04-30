/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityModels;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author simond
 */
@Entity
@Table(name = "GROUPS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g"),
    @NamedQuery(name = "Groups.findByGroupname", query = "SELECT g FROM Groups g WHERE g.groupsPK.groupname = :groupname"),
    @NamedQuery(name = "Groups.findByDescription", query = "SELECT g FROM Groups g WHERE g.description = :description"),
    @NamedQuery(name = "Groups.findByGroupowner", query = "SELECT g FROM Groups g WHERE g.groupowner = :groupowner"),
    @NamedQuery(name = "Groups.findByFunctions", query = "SELECT g FROM Groups g WHERE g.functions = :functions"),
    @NamedQuery(name = "Groups.findByProjectid", query = "SELECT g FROM Groups g WHERE g.groupsPK.projectid = :projectid"),
    @NamedQuery(name = "Groups.findByDn", query = "SELECT g FROM Groups g WHERE g.dn = :dn")})
public class Groups implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GroupsPK groupsPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPTION")
    private String description;
    @Size(max = 100)
    @Column(name = "GROUPOWNER")
    private String groupowner;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FUNCTIONS")
    private String functions;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DN")
    private String dn;

    public Groups() {
    }

    public Groups(GroupsPK groupsPK) {
        this.groupsPK = groupsPK;
    }

    public Groups(GroupsPK groupsPK, String description, String functions, String dn) {
        this.groupsPK = groupsPK;
        this.description = description;
        this.functions = functions;
        this.dn = dn;
    }

    public Groups(String groupname, String projectid) {
        this.groupsPK = new GroupsPK(groupname, projectid);
    }

    public GroupsPK getGroupsPK() {
        return groupsPK;
    }

    public void setGroupsPK(GroupsPK groupsPK) {
        this.groupsPK = groupsPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGroupowner() {
        return groupowner;
    }

    public void setGroupowner(String groupowner) {
        this.groupowner = groupowner;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupsPK != null ? groupsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.groupsPK == null && other.groupsPK != null) || (this.groupsPK != null && !this.groupsPK.equals(other.groupsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Groups[ groupsPK=" + groupsPK + " ]";
    }
    
}
