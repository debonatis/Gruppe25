/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityModels;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author simond
 */
@Entity
@Table(name = "GROUPUSERS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupusers.findAll", query = "SELECT g FROM Groupusers g"),
    @NamedQuery(name = "Groupusers.findByUsername", query = "SELECT g FROM Groupusers g WHERE g.groupusersPK.username = :username"),
    @NamedQuery(name = "Groupusers.findByUsergroupname", query = "SELECT g FROM Groupusers g WHERE g.groupusersPK.usergroupname = :usergroupname")})
public class Groupusers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GroupusersPK groupusersPK;

    public Groupusers() {
    }

    public Groupusers(GroupusersPK groupusersPK) {
        this.groupusersPK = groupusersPK;
    }

    public Groupusers(String username, String usergroupname) {
        this.groupusersPK = new GroupusersPK(username, usergroupname);
    }

    public GroupusersPK getGroupusersPK() {
        return groupusersPK;
    }

    public void setGroupusersPK(GroupusersPK groupusersPK) {
        this.groupusersPK = groupusersPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupusersPK != null ? groupusersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupusers)) {
            return false;
        }
        Groupusers other = (Groupusers) object;
        if ((this.groupusersPK == null && other.groupusersPK != null) || (this.groupusersPK != null && !this.groupusersPK.equals(other.groupusersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Groupusers[ groupusersPK=" + groupusersPK + " ]";
    }
    
}
