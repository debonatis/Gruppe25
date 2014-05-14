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
@Table(name = "EMAILCONTACTS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emailcontacts.findAll", query = "SELECT e FROM Emailcontacts e"),
    @NamedQuery(name = "Emailcontacts.findByContactname", query = "SELECT e FROM Emailcontacts e WHERE e.emailcontactsPK.contactname = :contactname"),
    @NamedQuery(name = "Emailcontacts.findByEmailaddress", query = "SELECT e FROM Emailcontacts e WHERE e.emailaddress = :emailaddress"),
    @NamedQuery(name = "Emailcontacts.findByProjectid", query = "SELECT e FROM Emailcontacts e WHERE e.emailcontactsPK.projectid = :projectid")})
public class Emailcontacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmailcontactsPK emailcontactsPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAILADDRESS")
    private String emailaddress;

    public Emailcontacts() {
    }

    public Emailcontacts(EmailcontactsPK emailcontactsPK) {
        this.emailcontactsPK = emailcontactsPK;
    }

    public Emailcontacts(EmailcontactsPK emailcontactsPK, String emailaddress) {
        this.emailcontactsPK = emailcontactsPK;
        this.emailaddress = emailaddress;
    }

    public Emailcontacts(String contactname, String projectid) {
        this.emailcontactsPK = new EmailcontactsPK(contactname, projectid);
    }

    public EmailcontactsPK getEmailcontactsPK() {
        return emailcontactsPK;
    }

    public void setEmailcontactsPK(EmailcontactsPK emailcontactsPK) {
        this.emailcontactsPK = emailcontactsPK;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emailcontactsPK != null ? emailcontactsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emailcontacts)) {
            return false;
        }
        Emailcontacts other = (Emailcontacts) object;
        if ((this.emailcontactsPK == null && other.emailcontactsPK != null) || (this.emailcontactsPK != null && !this.emailcontactsPK.equals(other.emailcontactsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Emailcontacts[ emailcontactsPK=" + emailcontactsPK + " ]";
    }
    
}
