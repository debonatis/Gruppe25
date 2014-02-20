/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25;

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
@Table(name = "applications")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Applications.findAll", query = "SELECT a FROM Applications a"),
    @NamedQuery(name = "Applications.findByApplicationName", query = "SELECT a FROM Applications a WHERE a.applicationName = :applicationName"),
    @NamedQuery(name = "Applications.findByVersion", query = "SELECT a FROM Applications a WHERE a.version = :version"),
    @NamedQuery(name = "Applications.findByAppLanguage", query = "SELECT a FROM Applications a WHERE a.appLanguage = :appLanguage"),
    @NamedQuery(name = "Applications.findByLicense", query = "SELECT a FROM Applications a WHERE a.license = :license"),
    @NamedQuery(name = "Applications.findBySubContractor", query = "SELECT a FROM Applications a WHERE a.subContractor = :subContractor"),
    @NamedQuery(name = "Applications.findByContactInformation", query = "SELECT a FROM Applications a WHERE a.contactInformation = :contactInformation"),
    @NamedQuery(name = "Applications.findByApplicationOwnerCustomer", query = "SELECT a FROM Applications a WHERE a.applicationOwnerCustomer = :applicationOwnerCustomer"),
    @NamedQuery(name = "Applications.findBySizeFile", query = "SELECT a FROM Applications a WHERE a.sizeFile = :sizeFile"),
    @NamedQuery(name = "Applications.findBySizeDatabase", query = "SELECT a FROM Applications a WHERE a.sizeDatabase = :sizeDatabase")})
public class Applications implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "applicationName")
    private String applicationName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "version")
    private String version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "appLanguage")
    private String appLanguage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "license")
    private String license;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "subContractor")
    private String subContractor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contactInformation")
    private String contactInformation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "applicationOwnerCustomer")
    private String applicationOwnerCustomer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sizeFile")
    private String sizeFile;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "sizeDatabase")
    private String sizeDatabase;

    public Applications() {
    }

    public Applications(String applicationName) {
        this.applicationName = applicationName;
    }

    public Applications(String applicationName, String version, String appLanguage, String license, String subContractor, String contactInformation, String applicationOwnerCustomer, String sizeFile, String sizeDatabase) {
        this.applicationName = applicationName;
        this.version = version;
        this.appLanguage = appLanguage;
        this.license = license;
        this.subContractor = subContractor;
        this.contactInformation = contactInformation;
        this.applicationOwnerCustomer = applicationOwnerCustomer;
        this.sizeFile = sizeFile;
        this.sizeDatabase = sizeDatabase;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppLanguage() {
        return appLanguage;
    }

    public void setAppLanguage(String appLanguage) {
        this.appLanguage = appLanguage;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getSubContractor() {
        return subContractor;
    }

    public void setSubContractor(String subContractor) {
        this.subContractor = subContractor;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getApplicationOwnerCustomer() {
        return applicationOwnerCustomer;
    }

    public void setApplicationOwnerCustomer(String applicationOwnerCustomer) {
        this.applicationOwnerCustomer = applicationOwnerCustomer;
    }

    public String getSizeFile() {
        return sizeFile;
    }

    public void setSizeFile(String sizeFile) {
        this.sizeFile = sizeFile;
    }

    public String getSizeDatabase() {
        return sizeDatabase;
    }

    public void setSizeDatabase(String sizeDatabase) {
        this.sizeDatabase = sizeDatabase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationName != null ? applicationName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applications)) {
            return false;
        }
        Applications other = (Applications) object;
        if ((this.applicationName == null && other.applicationName != null) || (this.applicationName != null && !this.applicationName.equals(other.applicationName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.Applications[ applicationName=" + applicationName + " ]";
    }
    
}
