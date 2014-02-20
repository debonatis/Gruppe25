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
@Table(name = "gruppeLevel6")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruppeLevel6.findAll", query = "SELECT g FROM GruppeLevel6 g"),
    @NamedQuery(name = "GruppeLevel6.findByGuID", query = "SELECT g FROM GruppeLevel6 g WHERE g.gruppeLevel6PK.guID = :guID"),
    @NamedQuery(name = "GruppeLevel6.findByGroupName", query = "SELECT g FROM GruppeLevel6 g WHERE g.gruppeLevel6PK.groupName = :groupName"),
    @NamedQuery(name = "GruppeLevel6.findByR", query = "SELECT g FROM GruppeLevel6 g WHERE g.r = :r"),
    @NamedQuery(name = "GruppeLevel6.findByW", query = "SELECT g FROM GruppeLevel6 g WHERE g.w = :w")})
public class GruppeLevel6 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GruppeLevel6PK gruppeLevel6PK;
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

    public GruppeLevel6() {
    }

    public GruppeLevel6(GruppeLevel6PK gruppeLevel6PK) {
        this.gruppeLevel6PK = gruppeLevel6PK;
    }

    public GruppeLevel6(GruppeLevel6PK gruppeLevel6PK, String r, String w) {
        this.gruppeLevel6PK = gruppeLevel6PK;
        this.r = r;
        this.w = w;
    }

    public GruppeLevel6(String guID, String groupName) {
        this.gruppeLevel6PK = new GruppeLevel6PK(guID, groupName);
    }

    public GruppeLevel6PK getGruppeLevel6PK() {
        return gruppeLevel6PK;
    }

    public void setGruppeLevel6PK(GruppeLevel6PK gruppeLevel6PK) {
        this.gruppeLevel6PK = gruppeLevel6PK;
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
        hash += (gruppeLevel6PK != null ? gruppeLevel6PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruppeLevel6)) {
            return false;
        }
        GruppeLevel6 other = (GruppeLevel6) object;
        if ((this.gruppeLevel6PK == null && other.gruppeLevel6PK != null) || (this.gruppeLevel6PK != null && !this.gruppeLevel6PK.equals(other.gruppeLevel6PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.entity.GruppeLevel6[ gruppeLevel6PK=" + gruppeLevel6PK + " ]";
    }
    
}
