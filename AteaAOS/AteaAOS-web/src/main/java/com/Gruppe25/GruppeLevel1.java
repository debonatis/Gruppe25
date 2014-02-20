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
@Table(name = "gruppeLevel1")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruppeLevel1.findAll", query = "SELECT g FROM GruppeLevel1 g"),
    @NamedQuery(name = "GruppeLevel1.findByGuID", query = "SELECT g FROM GruppeLevel1 g WHERE g.gruppeLevel1PK.guID = :guID"),
    @NamedQuery(name = "GruppeLevel1.findByGroupName", query = "SELECT g FROM GruppeLevel1 g WHERE g.gruppeLevel1PK.groupName = :groupName"),
    @NamedQuery(name = "GruppeLevel1.findByR", query = "SELECT g FROM GruppeLevel1 g WHERE g.r = :r"),
    @NamedQuery(name = "GruppeLevel1.findByW", query = "SELECT g FROM GruppeLevel1 g WHERE g.w = :w")})
public class GruppeLevel1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GruppeLevel1PK gruppeLevel1PK;
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

    public GruppeLevel1() {
    }

    public GruppeLevel1(GruppeLevel1PK gruppeLevel1PK) {
        this.gruppeLevel1PK = gruppeLevel1PK;
    }

    public GruppeLevel1(GruppeLevel1PK gruppeLevel1PK, String r, String w) {
        this.gruppeLevel1PK = gruppeLevel1PK;
        this.r = r;
        this.w = w;
    }

    public GruppeLevel1(String guID, String groupName) {
        this.gruppeLevel1PK = new GruppeLevel1PK(guID, groupName);
    }

    public GruppeLevel1PK getGruppeLevel1PK() {
        return gruppeLevel1PK;
    }

    public void setGruppeLevel1PK(GruppeLevel1PK gruppeLevel1PK) {
        this.gruppeLevel1PK = gruppeLevel1PK;
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
        hash += (gruppeLevel1PK != null ? gruppeLevel1PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruppeLevel1)) {
            return false;
        }
        GruppeLevel1 other = (GruppeLevel1) object;
        if ((this.gruppeLevel1PK == null && other.gruppeLevel1PK != null) || (this.gruppeLevel1PK != null && !this.gruppeLevel1PK.equals(other.gruppeLevel1PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.GruppeLevel1[ gruppeLevel1PK=" + gruppeLevel1PK + " ]";
    }
    
}
