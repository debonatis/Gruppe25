/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25;

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
@Table(name = "gruppeLevel4")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruppeLevel4.findAll", query = "SELECT g FROM GruppeLevel4 g"),
    @NamedQuery(name = "GruppeLevel4.findByGuID", query = "SELECT g FROM GruppeLevel4 g WHERE g.gruppeLevel4PK.guID = :guID"),
    @NamedQuery(name = "GruppeLevel4.findByGroupName", query = "SELECT g FROM GruppeLevel4 g WHERE g.gruppeLevel4PK.groupName = :groupName"),
    @NamedQuery(name = "GruppeLevel4.findByR", query = "SELECT g FROM GruppeLevel4 g WHERE g.r = :r"),
    @NamedQuery(name = "GruppeLevel4.findByW", query = "SELECT g FROM GruppeLevel4 g WHERE g.w = :w")})
public class GruppeLevel4 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GruppeLevel4PK gruppeLevel4PK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "r")
    private String r;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "w")
    private String w;

    public GruppeLevel4() {
    }

    public GruppeLevel4(GruppeLevel4PK gruppeLevel4PK) {
        this.gruppeLevel4PK = gruppeLevel4PK;
    }

    public GruppeLevel4(GruppeLevel4PK gruppeLevel4PK, String r, String w) {
        this.gruppeLevel4PK = gruppeLevel4PK;
        this.r = r;
        this.w = w;
    }

    public GruppeLevel4(String guID, String groupName) {
        this.gruppeLevel4PK = new GruppeLevel4PK(guID, groupName);
    }

    public GruppeLevel4PK getGruppeLevel4PK() {
        return gruppeLevel4PK;
    }

    public void setGruppeLevel4PK(GruppeLevel4PK gruppeLevel4PK) {
        this.gruppeLevel4PK = gruppeLevel4PK;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gruppeLevel4PK != null ? gruppeLevel4PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruppeLevel4)) {
            return false;
        }
        GruppeLevel4 other = (GruppeLevel4) object;
        if ((this.gruppeLevel4PK == null && other.gruppeLevel4PK != null) || (this.gruppeLevel4PK != null && !this.gruppeLevel4PK.equals(other.gruppeLevel4PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.GruppeLevel4[ gruppeLevel4PK=" + gruppeLevel4PK + " ]";
    }
    
}
