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
@Table(name = "PRINTERS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Printers.findAll", query = "SELECT p FROM Printers p"),
    @NamedQuery(name = "Printers.findByPrintername", query = "SELECT p FROM Printers p WHERE p.printername = :printername"),
    @NamedQuery(name = "Printers.findByIpaddr", query = "SELECT p FROM Printers p WHERE p.ipaddr = :ipaddr"),
    @NamedQuery(name = "Printers.findByLocation", query = "SELECT p FROM Printers p WHERE p.location = :location"),
    @NamedQuery(name = "Printers.findByModel", query = "SELECT p FROM Printers p WHERE p.model = :model"),
    @NamedQuery(name = "Printers.findByDriver", query = "SELECT p FROM Printers p WHERE p.driver = :driver"),
    @NamedQuery(name = "Printers.findByScantomail", query = "SELECT p FROM Printers p WHERE p.scantomail = :scantomail"),
    @NamedQuery(name = "Printers.findByAccessories", query = "SELECT p FROM Printers p WHERE p.accessories = :accessories"),
    @NamedQuery(name = "Printers.findByProjectid", query = "SELECT p FROM Printers p WHERE p.projectid = :projectid")})
public class Printers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PRINTERNAME")
    private String printername;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "IPADDR")
    private String ipaddr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "LOCATION")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MODEL")
    private String model;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DRIVER")
    private String driver;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SCANTOMAIL")
    private String scantomail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ACCESSORIES")
    private String accessories;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROJECTID")
    private String projectid;

    public Printers() {
    }

    public Printers(String printername) {
        this.printername = printername;
    }

    public Printers(String printername, String ipaddr, String location, String model, String driver, String scantomail, String accessories, String projectid) {
        this.printername = printername;
        this.ipaddr = ipaddr;
        this.location = location;
        this.model = model;
        this.driver = driver;
        this.scantomail = scantomail;
        this.accessories = accessories;
        this.projectid = projectid;
    }

    public String getPrintername() {
        return printername;
    }

    public void setPrintername(String printername) {
        this.printername = printername;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getScantomail() {
        return scantomail;
    }

    public void setScantomail(String scantomail) {
        this.scantomail = scantomail;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (printername != null ? printername.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Printers)) {
            return false;
        }
        Printers other = (Printers) object;
        if ((this.printername == null && other.printername != null) || (this.printername != null && !this.printername.equals(other.printername))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Printers[ printername=" + printername + " ]";
    }
    
}
