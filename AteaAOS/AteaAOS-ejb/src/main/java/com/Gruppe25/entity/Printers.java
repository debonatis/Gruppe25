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
@Table(name = "printers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Printers.findAll", query = "SELECT p FROM Printers p"),
    @NamedQuery(name = "Printers.findByPrinterName", query = "SELECT p FROM Printers p WHERE p.printerName = :printerName"),
    @NamedQuery(name = "Printers.findByIPaddr", query = "SELECT p FROM Printers p WHERE p.iPaddr = :iPaddr"),
    @NamedQuery(name = "Printers.findByLocation", query = "SELECT p FROM Printers p WHERE p.location = :location"),
    @NamedQuery(name = "Printers.findByModel", query = "SELECT p FROM Printers p WHERE p.model = :model"),
    @NamedQuery(name = "Printers.findByDriver", query = "SELECT p FROM Printers p WHERE p.driver = :driver"),
    @NamedQuery(name = "Printers.findByScanToMail", query = "SELECT p FROM Printers p WHERE p.scanToMail = :scanToMail"),
    @NamedQuery(name = "Printers.findByAccessories", query = "SELECT p FROM Printers p WHERE p.accessories = :accessories")})
public class Printers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "printerName")
    private String printerName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "IPaddr")
    private String iPaddr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "model")
    private String model;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "driver")
    private String driver;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "scanToMail")
    private String scanToMail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "accessories")
    private String accessories;

    public Printers() {
    }

    public Printers(String printerName) {
        this.printerName = printerName;
    }

    public Printers(String printerName, String iPaddr, String location, String model, String driver, String scanToMail, String accessories) {
        this.printerName = printerName;
        this.iPaddr = iPaddr;
        this.location = location;
        this.model = model;
        this.driver = driver;
        this.scanToMail = scanToMail;
        this.accessories = accessories;
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName;
    }

    public String getIPaddr() {
        return iPaddr;
    }

    public void setIPaddr(String iPaddr) {
        this.iPaddr = iPaddr;
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

    public String getScanToMail() {
        return scanToMail;
    }

    public void setScanToMail(String scanToMail) {
        this.scanToMail = scanToMail;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (printerName != null ? printerName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Printers)) {
            return false;
        }
        Printers other = (Printers) object;
        if ((this.printerName == null && other.printerName != null) || (this.printerName != null && !this.printerName.equals(other.printerName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.entity.Printers[ printerName=" + printerName + " ]";
    }
    
}
