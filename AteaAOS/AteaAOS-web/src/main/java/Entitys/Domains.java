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
@Table(name = "DOMAINS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Domains.findAll", query = "SELECT d FROM Domains d"),
    @NamedQuery(name = "Domains.findByDomainname", query = "SELECT d FROM Domains d WHERE d.domainname = :domainname"),
    @NamedQuery(name = "Domains.findByRegistrar", query = "SELECT d FROM Domains d WHERE d.registrar = :registrar"),
    @NamedQuery(name = "Domains.findByRegistrarcontact", query = "SELECT d FROM Domains d WHERE d.registrarcontact = :registrarcontact"),
    @NamedQuery(name = "Domains.findByDomainfunction", query = "SELECT d FROM Domains d WHERE d.domainfunction = :domainfunction"),
    @NamedQuery(name = "Domains.findByIppointer", query = "SELECT d FROM Domains d WHERE d.ippointer = :ippointer")})
public class Domains implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DOMAINNAME")
    private String domainname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "REGISTRAR")
    private String registrar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "REGISTRARCONTACT")
    private String registrarcontact;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DOMAINFUNCTION")
    private String domainfunction;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "IPPOINTER")
    private String ippointer;

    public Domains() {
    }

    public Domains(String domainname) {
        this.domainname = domainname;
    }

    public Domains(String domainname, String registrar, String registrarcontact, String domainfunction, String ippointer) {
        this.domainname = domainname;
        this.registrar = registrar;
        this.registrarcontact = registrarcontact;
        this.domainfunction = domainfunction;
        this.ippointer = ippointer;
    }

    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }

    public String getRegistrar() {
        return registrar;
    }

    public void setRegistrar(String registrar) {
        this.registrar = registrar;
    }

    public String getRegistrarcontact() {
        return registrarcontact;
    }

    public void setRegistrarcontact(String registrarcontact) {
        this.registrarcontact = registrarcontact;
    }

    public String getDomainfunction() {
        return domainfunction;
    }

    public void setDomainfunction(String domainfunction) {
        this.domainfunction = domainfunction;
    }

    public String getIppointer() {
        return ippointer;
    }

    public void setIppointer(String ippointer) {
        this.ippointer = ippointer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (domainname != null ? domainname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Domains)) {
            return false;
        }
        Domains other = (Domains) object;
        if ((this.domainname == null && other.domainname != null) || (this.domainname != null && !this.domainname.equals(other.domainname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Domains[ domainname=" + domainname + " ]";
    }
    
}
