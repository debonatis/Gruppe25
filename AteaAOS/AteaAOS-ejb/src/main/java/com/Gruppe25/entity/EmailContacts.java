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
@Table(name = "emailContacts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmailContacts.findAll", query = "SELECT e FROM EmailContacts e"),
    @NamedQuery(name = "EmailContacts.findByContactsName", query = "SELECT e FROM EmailContacts e WHERE e.contactsName = :contactsName"),
    @NamedQuery(name = "EmailContacts.findByEmailAddress", query = "SELECT e FROM EmailContacts e WHERE e.emailAddress = :emailAddress")})
public class EmailContacts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contactsName")
    private String contactsName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "emailAddress")
    private String emailAddress;

    public EmailContacts() {
    }

    public EmailContacts(String contactsName) {
        this.contactsName = contactsName;
    }

    public EmailContacts(String contactsName, String emailAddress) {
        this.contactsName = contactsName;
        this.emailAddress = emailAddress;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactsName != null ? contactsName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmailContacts)) {
            return false;
        }
        EmailContacts other = (EmailContacts) object;
        if ((this.contactsName == null && other.contactsName != null) || (this.contactsName != null && !this.contactsName.equals(other.contactsName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.entity.EmailContacts[ contactsName=" + contactsName + " ]";
    }
    
}
