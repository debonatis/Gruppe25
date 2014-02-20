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
@Table(name = "gruppeLevel7")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruppeLevel7.findAll", query = "SELECT g FROM GruppeLevel7 g"),
    @NamedQuery(name = "GruppeLevel7.findByGuID", query = "SELECT g FROM GruppeLevel7 g WHERE g.gruppeLevel7PK.guID = :guID"),
    @NamedQuery(name = "GruppeLevel7.findByGroupName", query = "SELECT g FROM GruppeLevel7 g WHERE g.gruppeLevel7PK.groupName = :groupName"),
    @NamedQuery(name = "GruppeLevel7.findByR", query = "SELECT g FROM GruppeLevel7 g WHERE g.r = :r"),
    @NamedQuery(name = "GruppeLevel7.findByW", query = "SELECT g FROM GruppeLevel7 g WHERE g.w = :w")})
public class GruppeLevel7 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GruppeLevel7PK gruppeLevel7PK;
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

    public GruppeLevel7() {
    }

    public GruppeLevel7(GruppeLevel7PK gruppeLevel7PK) {
        this.gruppeLevel7PK = gruppeLevel7PK;
    }

    public GruppeLevel7(GruppeLevel7PK gruppeLevel7PK, String r, String w) {
        this.gruppeLevel7PK = gruppeLevel7PK;
        this.r = r;
        this.w = w;
    }

    public GruppeLevel7(String guID, String groupName) {
        this.gruppeLevel7PK = new GruppeLevel7PK(guID, groupName);
    }

    public GruppeLevel7PK getGruppeLevel7PK() {
        return gruppeLevel7PK;
    }

    public void setGruppeLevel7PK(GruppeLevel7PK gruppeLevel7PK) {
        this.gruppeLevel7PK = gruppeLevel7PK;
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
        hash += (gruppeLevel7PK != null ? gruppeLevel7PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruppeLevel7)) {
            return false;
        }
        GruppeLevel7 other = (GruppeLevel7) object;
        if ((this.gruppeLevel7PK == null && other.gruppeLevel7PK != null) || (this.gruppeLevel7PK != null && !this.gruppeLevel7PK.equals(other.gruppeLevel7PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.entity.GruppeLevel7[ gruppeLevel7PK=" + gruppeLevel7PK + " ]";
    }
    
}
