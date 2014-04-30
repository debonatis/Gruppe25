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
@Table(name = "USERS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.usersPK.username = :username"),
    @NamedQuery(name = "Users.findByFirstname", query = "SELECT u FROM Users u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "Users.findByLastname", query = "SELECT u FROM Users u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "Users.findByTitle", query = "SELECT u FROM Users u WHERE u.title = :title"),
    @NamedQuery(name = "Users.findByItcontact", query = "SELECT u FROM Users u WHERE u.itcontact = :itcontact"),
    @NamedQuery(name = "Users.findByEmailalias", query = "SELECT u FROM Users u WHERE u.emailalias = :emailalias"),
    @NamedQuery(name = "Users.findByDepartment", query = "SELECT u FROM Users u WHERE u.department = :department"),
    @NamedQuery(name = "Users.findByEmploymentnr", query = "SELECT u FROM Users u WHERE u.employmentnr = :employmentnr"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByMobile", query = "SELECT u FROM Users u WHERE u.mobile = :mobile"),
    @NamedQuery(name = "Users.findByProjectid", query = "SELECT u FROM Users u WHERE u.usersPK.projectid = :projectid"),
    @NamedQuery(name = "Users.findByDn", query = "SELECT u FROM Users u WHERE u.dn = :dn")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsersPK usersPK;
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ITCONTACT")
    private String itcontact;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAILALIAS")
    private String emailalias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DEPARTMENT")
    private String department;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMPLOYMENTNR")
    private String employmentnr;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MOBILE")
    private int mobile;
    @Size(max = 100)
    @Column(name = "DN")
    private String dn;

    public Users() {
    }

    public Users(UsersPK usersPK) {
        this.usersPK = usersPK;
    }

    public Users(UsersPK usersPK, String firstname, String lastname, String title, String itcontact, String emailalias, String department, String employmentnr, String email, int mobile) {
        this.usersPK = usersPK;
        this.firstname = firstname;
        this.lastname = lastname;
        this.title = title;
        this.itcontact = itcontact;
        this.emailalias = emailalias;
        this.department = department;
        this.employmentnr = employmentnr;
        this.email = email;
        this.mobile = mobile;
    }

    public Users(String username, String projectid) {
        this.usersPK = new UsersPK(username, projectid);
    }

    public UsersPK getUsersPK() {
        return usersPK;
    }

    public void setUsersPK(UsersPK usersPK) {
        this.usersPK = usersPK;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItcontact() {
        return itcontact;
    }

    public void setItcontact(String itcontact) {
        this.itcontact = itcontact;
    }

    public String getEmailalias() {
        return emailalias;
    }

    public void setEmailalias(String emailalias) {
        this.emailalias = emailalias;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmploymentnr() {
        return employmentnr;
    }

    public void setEmploymentnr(String employmentnr) {
        this.employmentnr = employmentnr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usersPK != null ? usersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.usersPK == null && other.usersPK != null) || (this.usersPK != null && !this.usersPK.equals(other.usersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Users[ usersPK=" + usersPK + " ]";
    }
    
}
