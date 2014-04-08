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
 * @author Martin
 */
@Entity
@Table(name = "APPLICATIONS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Applications.findAll", query = "SELECT a FROM Applications a"),
    @NamedQuery(name = "Applications.findByApplicationname", query = "SELECT a FROM Applications a WHERE a.applicationname = :applicationname"),
    @NamedQuery(name = "Applications.findByApplicationid", query = "SELECT a FROM Applications a WHERE a.applicationid = :applicationid"),
    @NamedQuery(name = "Applications.findByVersion", query = "SELECT a FROM Applications a WHERE a.version = :version"),
    @NamedQuery(name = "Applications.findByApplanguage", query = "SELECT a FROM Applications a WHERE a.applanguage = :applanguage"),
    @NamedQuery(name = "Applications.findByLicense", query = "SELECT a FROM Applications a WHERE a.license = :license"),
    @NamedQuery(name = "Applications.findBySubcontractor", query = "SELECT a FROM Applications a WHERE a.subcontractor = :subcontractor"),
    @NamedQuery(name = "Applications.findByContractinformation", query = "SELECT a FROM Applications a WHERE a.contractinformation = :contractinformation"),
    @NamedQuery(name = "Applications.findByApplicationownercustomer", query = "SELECT a FROM Applications a WHERE a.applicationownercustomer = :applicationownercustomer"),
    @NamedQuery(name = "Applications.findBySizefile", query = "SELECT a FROM Applications a WHERE a.sizefile = :sizefile"),
    @NamedQuery(name = "Applications.findBySizedatabase", query = "SELECT a FROM Applications a WHERE a.sizedatabase = :sizedatabase")})
public class Applications implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APPLICATIONNAME")
    private String applicationname;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APPLICATIONID")
    private String applicationid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "VERSION")
    private String version;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APPLANGUAGE")
    private String applanguage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LICENSE")
    private String license;
    @Size(max = 100)
    @Column(name = "SUBCONTRACTOR")
    private String subcontractor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CONTRACTINFORMATION")
    private String contractinformation;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "APPLICATIONOWNERCUSTOMER")
    private String applicationownercustomer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SIZEFILE")
    private String sizefile;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SIZEDATABASE")
    private String sizedatabase;

    public Applications() {
    }

    public Applications(String applicationid) {
        this.applicationid = applicationid;
    }

    public Applications(String applicationid, String applicationname, String version, String applanguage, String license, String contractinformation, String applicationownercustomer, String sizefile, String sizedatabase) {
        this.applicationid = applicationid;
        this.applicationname = applicationname;
        this.version = version;
        this.applanguage = applanguage;
        this.license = license;
        this.contractinformation = contractinformation;
        this.applicationownercustomer = applicationownercustomer;
        this.sizefile = sizefile;
        this.sizedatabase = sizedatabase;
    }

    public String getApplicationname() {
        return applicationname;
    }

    public void setApplicationname(String applicationname) {
        this.applicationname = applicationname;
    }

    public String getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(String applicationid) {
        this.applicationid = applicationid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApplanguage() {
        return applanguage;
    }

    public void setApplanguage(String applanguage) {
        this.applanguage = applanguage;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getSubcontractor() {
        return subcontractor;
    }

    public void setSubcontractor(String subcontractor) {
        this.subcontractor = subcontractor;
    }

    public String getContractinformation() {
        return contractinformation;
    }

    public void setContractinformation(String contractinformation) {
        this.contractinformation = contractinformation;
    }

    public String getApplicationownercustomer() {
        return applicationownercustomer;
    }

    public void setApplicationownercustomer(String applicationownercustomer) {
        this.applicationownercustomer = applicationownercustomer;
    }

    public String getSizefile() {
        return sizefile;
    }

    public void setSizefile(String sizefile) {
        this.sizefile = sizefile;
    }

    public String getSizedatabase() {
        return sizedatabase;
    }

    public void setSizedatabase(String sizedatabase) {
        this.sizedatabase = sizedatabase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationid != null ? applicationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applications)) {
            return false;
        }
        Applications other = (Applications) object;
        if ((this.applicationid == null && other.applicationid != null) || (this.applicationid != null && !this.applicationid.equals(other.applicationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Applications[ applicationid=" + applicationid + " ]";
    }
    
}
