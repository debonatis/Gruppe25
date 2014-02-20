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
@Table(name = "gruppeLevel8")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruppeLevel8.findAll", query = "SELECT g FROM GruppeLevel8 g"),
    @NamedQuery(name = "GruppeLevel8.findByGuID", query = "SELECT g FROM GruppeLevel8 g WHERE g.gruppeLevel8PK.guID = :guID"),
    @NamedQuery(name = "GruppeLevel8.findByGroupName", query = "SELECT g FROM GruppeLevel8 g WHERE g.gruppeLevel8PK.groupName = :groupName"),
    @NamedQuery(name = "GruppeLevel8.findByR", query = "SELECT g FROM GruppeLevel8 g WHERE g.r = :r"),
    @NamedQuery(name = "GruppeLevel8.findByW", query = "SELECT g FROM GruppeLevel8 g WHERE g.w = :w")})
public class GruppeLevel8 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GruppeLevel8PK gruppeLevel8PK;
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

    public GruppeLevel8() {
    }

    public GruppeLevel8(GruppeLevel8PK gruppeLevel8PK) {
        this.gruppeLevel8PK = gruppeLevel8PK;
    }

    public GruppeLevel8(GruppeLevel8PK gruppeLevel8PK, String r, String w) {
        this.gruppeLevel8PK = gruppeLevel8PK;
        this.r = r;
        this.w = w;
    }

    public GruppeLevel8(String guID, String groupName) {
        this.gruppeLevel8PK = new GruppeLevel8PK(guID, groupName);
    }

    public GruppeLevel8PK getGruppeLevel8PK() {
        return gruppeLevel8PK;
    }

    public void setGruppeLevel8PK(GruppeLevel8PK gruppeLevel8PK) {
        this.gruppeLevel8PK = gruppeLevel8PK;
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
        hash += (gruppeLevel8PK != null ? gruppeLevel8PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruppeLevel8)) {
            return false;
        }
        GruppeLevel8 other = (GruppeLevel8) object;
        if ((this.gruppeLevel8PK == null && other.gruppeLevel8PK != null) || (this.gruppeLevel8PK != null && !this.gruppeLevel8PK.equals(other.gruppeLevel8PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.entity.GruppeLevel8[ gruppeLevel8PK=" + gruppeLevel8PK + " ]";
    }
    
}
