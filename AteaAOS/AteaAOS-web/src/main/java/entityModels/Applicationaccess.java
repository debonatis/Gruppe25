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
@Table(name = "APPLICATIONACCESS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Applicationaccess.findAll", query = "SELECT a FROM Applicationaccess a"),
    @NamedQuery(name = "Applicationaccess.findByUsername", query = "SELECT a FROM Applicationaccess a WHERE a.applicationaccessPK.username = :username"),
    @NamedQuery(name = "Applicationaccess.findByApplicationid", query = "SELECT a FROM Applicationaccess a WHERE a.applicationaccessPK.applicationid = :applicationid")})
public class Applicationaccess implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ApplicationaccessPK applicationaccessPK;

    public Applicationaccess() {
    }

    public Applicationaccess(ApplicationaccessPK applicationaccessPK) {
        this.applicationaccessPK = applicationaccessPK;
    }

    public Applicationaccess(String username, String applicationid) {
        this.applicationaccessPK = new ApplicationaccessPK(username, applicationid);
    }

    public ApplicationaccessPK getApplicationaccessPK() {
        return applicationaccessPK;
    }

    public void setApplicationaccessPK(ApplicationaccessPK applicationaccessPK) {
        this.applicationaccessPK = applicationaccessPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationaccessPK != null ? applicationaccessPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applicationaccess)) {
            return false;
        }
        Applicationaccess other = (Applicationaccess) object;
        if ((this.applicationaccessPK == null && other.applicationaccessPK != null) || (this.applicationaccessPK != null && !this.applicationaccessPK.equals(other.applicationaccessPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Applicationaccess[ applicationaccessPK=" + applicationaccessPK + " ]";
    }
    
}
