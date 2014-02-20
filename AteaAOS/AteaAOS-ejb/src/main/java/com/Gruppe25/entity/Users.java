/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25.entity;

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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "Users.findByTitle", query = "SELECT u FROM Users u WHERE u.title = :title"),
    @NamedQuery(name = "Users.findByITcontact", query = "SELECT u FROM Users u WHERE u.iTcontact = :iTcontact"),
    @NamedQuery(name = "Users.findByEmailAlias", query = "SELECT u FROM Users u WHERE u.emailAlias = :emailAlias"),
    @NamedQuery(name = "Users.findByDepartment", query = "SELECT u FROM Users u WHERE u.department = :department"),
    @NamedQuery(name = "Users.findByEmploymentNr", query = "SELECT u FROM Users u WHERE u.employmentNr = :employmentNr"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByMobile", query = "SELECT u FROM Users u WHERE u.mobile = :mobile")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "firstName")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "lastName")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Column(name = "ITcontact")
    private Boolean iTcontact;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "emailAlias")
    private String emailAlias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "department")
    private String department;
    @Basic(optional = false)
    @NotNull
    @Column(name = "employmentNr")
    private int employmentNr;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mobile")
    private int mobile;

    public Users() {
    }

    public Users(String username) {
        this.username = username;
    }

    public Users(String username, String firstName, String lastName, String title, String emailAlias, String department, int employmentNr, String email, int mobile) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.emailAlias = emailAlias;
        this.department = department;
        this.employmentNr = employmentNr;
        this.email = email;
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getITcontact() {
        return iTcontact;
    }

    public void setITcontact(Boolean iTcontact) {
        this.iTcontact = iTcontact;
    }

    public String getEmailAlias() {
        return emailAlias;
    }

    public void setEmailAlias(String emailAlias) {
        this.emailAlias = emailAlias;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getEmploymentNr() {
        return employmentNr;
    }

    public void setEmploymentNr(int employmentNr) {
        this.employmentNr = employmentNr;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.entity.Users[ username=" + username + " ]";
    }
    
}
