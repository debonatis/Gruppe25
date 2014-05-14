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
 * @author Martin
 */
@Entity
@Table(name = "SHAREDRESOURCESUSERS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sharedresourcesusers.findAll", query = "SELECT s FROM Sharedresourcesusers s"),
    @NamedQuery(name = "Sharedresourcesusers.findByDisplaynameshared", query = "SELECT s FROM Sharedresourcesusers s WHERE s.sharedresourcesusersPK.displaynameshared = :displaynameshared"),
    @NamedQuery(name = "Sharedresourcesusers.findByUsername", query = "SELECT s FROM Sharedresourcesusers s WHERE s.sharedresourcesusersPK.username = :username"),
    @NamedQuery(name = "Sharedresourcesusers.findByProjectiddisp", query = "SELECT s FROM Sharedresourcesusers s WHERE s.sharedresourcesusersPK.projectiddisp = :projectiddisp")})
public class Sharedresourcesusers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SharedresourcesusersPK sharedresourcesusersPK;

    public Sharedresourcesusers() {
    }

    public Sharedresourcesusers(SharedresourcesusersPK sharedresourcesusersPK) {
        this.sharedresourcesusersPK = sharedresourcesusersPK;
    }

    public Sharedresourcesusers(String displaynameshared, String username, String projectiddisp) {
        this.sharedresourcesusersPK = new SharedresourcesusersPK(displaynameshared, username, projectiddisp);
    }

    public SharedresourcesusersPK getSharedresourcesusersPK() {
        return sharedresourcesusersPK;
    }

    public void setSharedresourcesusersPK(SharedresourcesusersPK sharedresourcesusersPK) {
        this.sharedresourcesusersPK = sharedresourcesusersPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sharedresourcesusersPK != null ? sharedresourcesusersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sharedresourcesusers)) {
            return false;
        }
        Sharedresourcesusers other = (Sharedresourcesusers) object;
        if ((this.sharedresourcesusersPK == null && other.sharedresourcesusersPK != null) || (this.sharedresourcesusersPK != null && !this.sharedresourcesusersPK.equals(other.sharedresourcesusersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Sharedresourcesusers[ sharedresourcesusersPK=" + sharedresourcesusersPK + " ]";
    }
    
}
