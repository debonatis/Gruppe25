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
@Table(name = "gruppeLevel5")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruppeLevel5.findAll", query = "SELECT g FROM GruppeLevel5 g"),
    @NamedQuery(name = "GruppeLevel5.findByGuID", query = "SELECT g FROM GruppeLevel5 g WHERE g.gruppeLevel5PK.guID = :guID"),
    @NamedQuery(name = "GruppeLevel5.findByGroupName", query = "SELECT g FROM GruppeLevel5 g WHERE g.gruppeLevel5PK.groupName = :groupName"),
    @NamedQuery(name = "GruppeLevel5.findByR", query = "SELECT g FROM GruppeLevel5 g WHERE g.r = :r"),
    @NamedQuery(name = "GruppeLevel5.findByW", query = "SELECT g FROM GruppeLevel5 g WHERE g.w = :w")})
public class GruppeLevel5 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GruppeLevel5PK gruppeLevel5PK;
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

    public GruppeLevel5() {
    }

    public GruppeLevel5(GruppeLevel5PK gruppeLevel5PK) {
        this.gruppeLevel5PK = gruppeLevel5PK;
    }

    public GruppeLevel5(GruppeLevel5PK gruppeLevel5PK, String r, String w) {
        this.gruppeLevel5PK = gruppeLevel5PK;
        this.r = r;
        this.w = w;
    }

    public GruppeLevel5(String guID, String groupName) {
        this.gruppeLevel5PK = new GruppeLevel5PK(guID, groupName);
    }

    public GruppeLevel5PK getGruppeLevel5PK() {
        return gruppeLevel5PK;
    }

    public void setGruppeLevel5PK(GruppeLevel5PK gruppeLevel5PK) {
        this.gruppeLevel5PK = gruppeLevel5PK;
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
        hash += (gruppeLevel5PK != null ? gruppeLevel5PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruppeLevel5)) {
            return false;
        }
        GruppeLevel5 other = (GruppeLevel5) object;
        if ((this.gruppeLevel5PK == null && other.gruppeLevel5PK != null) || (this.gruppeLevel5PK != null && !this.gruppeLevel5PK.equals(other.gruppeLevel5PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.GruppeLevel5[ gruppeLevel5PK=" + gruppeLevel5PK + " ]";
    }
    
}
