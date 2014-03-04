/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entitys;

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
@Table(catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userdistribution.findAll", query = "SELECT u FROM Userdistribution u"),
    @NamedQuery(name = "Userdistribution.findByUsername", query = "SELECT u FROM Userdistribution u WHERE u.userdistributionPK.username = :username"),
    @NamedQuery(name = "Userdistribution.findByDisplayname", query = "SELECT u FROM Userdistribution u WHERE u.userdistributionPK.displayname = :displayname")})
public class Userdistribution implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserdistributionPK userdistributionPK;

    public Userdistribution() {
    }

    public Userdistribution(UserdistributionPK userdistributionPK) {
        this.userdistributionPK = userdistributionPK;
    }

    public Userdistribution(String username, String displayname) {
        this.userdistributionPK = new UserdistributionPK(username, displayname);
    }

    public UserdistributionPK getUserdistributionPK() {
        return userdistributionPK;
    }

    public void setUserdistributionPK(UserdistributionPK userdistributionPK) {
        this.userdistributionPK = userdistributionPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userdistributionPK != null ? userdistributionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userdistribution)) {
            return false;
        }
        Userdistribution other = (Userdistribution) object;
        if ((this.userdistributionPK == null && other.userdistributionPK != null) || (this.userdistributionPK != null && !this.userdistributionPK.equals(other.userdistributionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Userdistribution[ userdistributionPK=" + userdistributionPK + " ]";
    }
    
}
