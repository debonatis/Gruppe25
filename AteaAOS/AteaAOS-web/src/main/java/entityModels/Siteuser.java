/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityModels;

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
@Table(name = "SITEUSER", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Siteuser.findAll", query = "SELECT s FROM Siteuser s"),
    @NamedQuery(name = "Siteuser.findByUsername", query = "SELECT s FROM Siteuser s WHERE s.username = :username"),
    @NamedQuery(name = "Siteuser.findByPassword", query = "SELECT s FROM Siteuser s WHERE s.password = :password"),
    @NamedQuery(name = "Siteuser.findByFirstname", query = "SELECT s FROM Siteuser s WHERE s.firstname = :firstname"),
    @NamedQuery(name = "Siteuser.findByLastname", query = "SELECT s FROM Siteuser s WHERE s.lastname = :lastname"),
    @NamedQuery(name = "Siteuser.findByEmail", query = "SELECT s FROM Siteuser s WHERE s.email = :email"),
    @NamedQuery(name = "Siteuser.findByMobile", query = "SELECT s FROM Siteuser s WHERE s.mobile = :mobile"),
    @NamedQuery(name = "Siteuser.findByAdress", query = "SELECT s FROM Siteuser s WHERE s.adress = :adress"),
    @NamedQuery(name = "Siteuser.findByPostalcode", query = "SELECT s FROM Siteuser s WHERE s.postalcode = :postalcode")})
public class Siteuser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LASTNAME")
    private String lastname;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MOBILE")
    private String mobile;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ADRESS")
    private String adress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "POSTALCODE")
    private String postalcode;

    public Siteuser() {
    }

    public Siteuser(String username) {
        this.username = username;
    }

    public Siteuser(String username, String password, String firstname, String lastname, String email, String mobile, String adress, String postalcode) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobile = mobile;
        this.adress = adress;
        this.postalcode = postalcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Siteuser)) {
            return false;
        }
        Siteuser other = (Siteuser) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Siteuser[ username=" + username + " ]";
    }
    
}
