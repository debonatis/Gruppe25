/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entitys;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @NamedQuery(name = "Emailcontacts.findByContactname", query = "SELECT e FROM Emailcontacts e WHERE e.contactname = :contactname"),
    @NamedQuery(name = "Emailcontacts.findByEmailaddress", query = "SELECT e FROM Emailcontacts e WHERE e.emailaddress = :emailaddress")})
public class Emailcontacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CONTACTNAME")
    private String contactname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAILADDRESS")
    private String emailaddress;

    public Emailcontacts() {
    }

    public Emailcontacts(String contactname) {
        this.contactname = contactname;
    }

    public Emailcontacts(String contactname, String emailaddress) {
        this.contactname = contactname;
        this.emailaddress = emailaddress;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
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
        hash += (contactname != null ? contactname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emailcontacts)) {
            return false;
        }
        Emailcontacts other = (Emailcontacts) object;
        if ((this.contactname == null && other.contactname != null) || (this.contactname != null && !this.contactname.equals(other.contactname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Emailcontacts[ contactname=" + contactname + " ]";
    }
    
}
