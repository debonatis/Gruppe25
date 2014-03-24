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
@Table(name = "USERPROJECTS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userprojects.findAll", query = "SELECT u FROM Userprojects u"),
    @NamedQuery(name = "Userprojects.findByUserid", query = "SELECT u FROM Userprojects u WHERE u.userprojectsPK.userid = :userid"),
    @NamedQuery(name = "Userprojects.findByProjectid", query = "SELECT u FROM Userprojects u WHERE u.userprojectsPK.projectid = :projectid")})
public class Userprojects implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserprojectsPK userprojectsPK;

    public Userprojects() {
    }

    public Userprojects(UserprojectsPK userprojectsPK) {
        this.userprojectsPK = userprojectsPK;
    }

    public Userprojects(String userid, String projectid) {
        this.userprojectsPK = new UserprojectsPK(userid, projectid);
    }

    public UserprojectsPK getUserprojectsPK() {
        return userprojectsPK;
    }

    public void setUserprojectsPK(UserprojectsPK userprojectsPK) {
        this.userprojectsPK = userprojectsPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userprojectsPK != null ? userprojectsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userprojects)) {
            return false;
        }
        Userprojects other = (Userprojects) object;
        if ((this.userprojectsPK == null && other.userprojectsPK != null) || (this.userprojectsPK != null && !this.userprojectsPK.equals(other.userprojectsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Userprojects[ userprojectsPK=" + userprojectsPK + " ]";
    }
    
}
