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
@Table(name = "PROSITEUSERS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prositeusers.findAll", query = "SELECT p FROM Prositeusers p"),
    @NamedQuery(name = "Prositeusers.findByProjectid", query = "SELECT p FROM Prositeusers p WHERE p.prositeusersPK.projectid = :projectid"),
    @NamedQuery(name = "Prositeusers.findByUsername", query = "SELECT p FROM Prositeusers p WHERE p.prositeusersPK.username = :username")})
public class Prositeusers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PrositeusersPK prositeusersPK;

    public Prositeusers() {
    }

    public Prositeusers(PrositeusersPK prositeusersPK) {
        this.prositeusersPK = prositeusersPK;
    }

    public Prositeusers(String projectid, String username) {
        this.prositeusersPK = new PrositeusersPK(projectid, username);
    }

    public PrositeusersPK getPrositeusersPK() {
        return prositeusersPK;
    }

    public void setPrositeusersPK(PrositeusersPK prositeusersPK) {
        this.prositeusersPK = prositeusersPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prositeusersPK != null ? prositeusersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prositeusers)) {
            return false;
        }
        Prositeusers other = (Prositeusers) object;
        if ((this.prositeusersPK == null && other.prositeusersPK != null) || (this.prositeusersPK != null && !this.prositeusersPK.equals(other.prositeusersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Prositeusers[ prositeusersPK=" + prositeusersPK + " ]";
    }
    
}
