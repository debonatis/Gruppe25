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
@Table(name = "domains")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Domains.findAll", query = "SELECT d FROM Domains d"),
    @NamedQuery(name = "Domains.findByDomainName", query = "SELECT d FROM Domains d WHERE d.domainName = :domainName"),
    @NamedQuery(name = "Domains.findByRegistrar", query = "SELECT d FROM Domains d WHERE d.registrar = :registrar"),
    @NamedQuery(name = "Domains.findByRegistrarContact", query = "SELECT d FROM Domains d WHERE d.registrarContact = :registrarContact"),
    @NamedQuery(name = "Domains.findByDomainFunction", query = "SELECT d FROM Domains d WHERE d.domainFunction = :domainFunction"),
    @NamedQuery(name = "Domains.findByIPpointer", query = "SELECT d FROM Domains d WHERE d.iPpointer = :iPpointer")})
public class Domains implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "domainName")
    private String domainName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "registrar")
    private String registrar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "registrarContact")
    private String registrarContact;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "domainFunction")
    private String domainFunction;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "IPpointer")
    private String iPpointer;

    public Domains() {
    }

    public Domains(String domainName) {
        this.domainName = domainName;
    }

    public Domains(String domainName, String registrar, String registrarContact, String domainFunction, String iPpointer) {
        this.domainName = domainName;
        this.registrar = registrar;
        this.registrarContact = registrarContact;
        this.domainFunction = domainFunction;
        this.iPpointer = iPpointer;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public String getRegistrarContact() {
        return registrarContact;
    }

    public void setRegistrarContact(String registrarContact) {
        this.registrarContact = registrarContact;
    }

    public String getDomainFunction() {
        return domainFunction;
    }

    public void setDomainFunction(String domainFunction) {
        this.domainFunction = domainFunction;
    }

    public String getIPpointer() {
        return iPpointer;
    }

    public void setIPpointer(String iPpointer) {
        this.iPpointer = iPpointer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (domainName != null ? domainName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domains)) {
            return false;
        }
        Domains other = (Domains) object;
        if ((this.domainName == null && other.domainName != null) || (this.domainName != null && !this.domainName.equals(other.domainName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.entity.Domains[ domainName=" + domainName + " ]";
    }
    
}
