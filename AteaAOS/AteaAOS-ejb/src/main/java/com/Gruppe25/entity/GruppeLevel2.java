/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25.entity;

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
@Table(name = "gruppeLevel2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruppeLevel2.findAll", query = "SELECT g FROM GruppeLevel2 g"),
    @NamedQuery(name = "GruppeLevel2.findByGuID", query = "SELECT g FROM GruppeLevel2 g WHERE g.gruppeLevel2PK.guID = :guID"),
    @NamedQuery(name = "GruppeLevel2.findByGroupName", query = "SELECT g FROM GruppeLevel2 g WHERE g.gruppeLevel2PK.groupName = :groupName"),
    @NamedQuery(name = "GruppeLevel2.findByR", query = "SELECT g FROM GruppeLevel2 g WHERE g.r = :r"),
    @NamedQuery(name = "GruppeLevel2.findByW", query = "SELECT g FROM GruppeLevel2 g WHERE g.w = :w")})
public class GruppeLevel2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GruppeLevel2PK gruppeLevel2PK;
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

    public GruppeLevel2() {
    }

    public GruppeLevel2(GruppeLevel2PK gruppeLevel2PK) {
        this.gruppeLevel2PK = gruppeLevel2PK;
    }

    public GruppeLevel2(GruppeLevel2PK gruppeLevel2PK, String r, String w) {
        this.gruppeLevel2PK = gruppeLevel2PK;
        this.r = r;
        this.w = w;
    }

    public GruppeLevel2(String guID, String groupName) {
        this.gruppeLevel2PK = new GruppeLevel2PK(guID, groupName);
    }

    public GruppeLevel2PK getGruppeLevel2PK() {
        return gruppeLevel2PK;
    }

    public void setGruppeLevel2PK(GruppeLevel2PK gruppeLevel2PK) {
        this.gruppeLevel2PK = gruppeLevel2PK;
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
        hash += (gruppeLevel2PK != null ? gruppeLevel2PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruppeLevel2)) {
            return false;
        }
        GruppeLevel2 other = (GruppeLevel2) object;
        if ((this.gruppeLevel2PK == null && other.gruppeLevel2PK != null) || (this.gruppeLevel2PK != null && !this.gruppeLevel2PK.equals(other.gruppeLevel2PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.entity.GruppeLevel2[ gruppeLevel2PK=" + gruppeLevel2PK + " ]";
    }
    
}
