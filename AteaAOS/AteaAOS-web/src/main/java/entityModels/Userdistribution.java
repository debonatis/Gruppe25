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
@Table(name = "USERDISTRIBUTION", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userdistribution.findAll", query = "SELECT u FROM Userdistribution u"),
    @NamedQuery(name = "Userdistribution.findByUsername", query = "SELECT u FROM Userdistribution u WHERE u.userdistributionPK.username = :username"),
    @NamedQuery(name = "Userdistribution.findByDisplayname", query = "SELECT u FROM Userdistribution u WHERE u.userdistributionPK.displayname = :displayname"),
    @NamedQuery(name = "Userdistribution.findByProjectidu", query = "SELECT u FROM Userdistribution u WHERE u.userdistributionPK.projectidu = :projectidu"),
    @NamedQuery(name = "Userdistribution.findByProjectidd", query = "SELECT u FROM Userdistribution u WHERE u.projectidd = :projectidd")})
public class Userdistribution implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserdistributionPK userdistributionPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROJECTIDD")
    private String projectidd;

    public Userdistribution() {
    }

    public Userdistribution(UserdistributionPK userdistributionPK) {
        this.userdistributionPK = userdistributionPK;
    }

    public Userdistribution(UserdistributionPK userdistributionPK, String projectidd) {
        this.userdistributionPK = userdistributionPK;
        this.projectidd = projectidd;
    }

    public Userdistribution(String username, String displayname, String projectidu) {
        this.userdistributionPK = new UserdistributionPK(username, displayname, projectidu);
    }

    public UserdistributionPK getUserdistributionPK() {
        return userdistributionPK;
    }

    public void setUserdistributionPK(UserdistributionPK userdistributionPK) {
        this.userdistributionPK = userdistributionPK;
    }

    public String getProjectidd() {
        return projectidd;
    }

    public void setProjectidd(String projectidd) {
        this.projectidd = projectidd;
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
        return "entityModels.Userdistribution[ userdistributionPK=" + userdistributionPK + " ]";
    }
    
}
