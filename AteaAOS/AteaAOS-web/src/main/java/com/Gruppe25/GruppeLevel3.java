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
@Table(name = "gruppeLevel3")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruppeLevel3.findAll", query = "SELECT g FROM GruppeLevel3 g"),
    @NamedQuery(name = "GruppeLevel3.findByGuID", query = "SELECT g FROM GruppeLevel3 g WHERE g.gruppeLevel3PK.guID = :guID"),
    @NamedQuery(name = "GruppeLevel3.findByGroupName", query = "SELECT g FROM GruppeLevel3 g WHERE g.gruppeLevel3PK.groupName = :groupName"),
    @NamedQuery(name = "GruppeLevel3.findByR", query = "SELECT g FROM GruppeLevel3 g WHERE g.r = :r"),
    @NamedQuery(name = "GruppeLevel3.findByW", query = "SELECT g FROM GruppeLevel3 g WHERE g.w = :w")})
public class GruppeLevel3 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GruppeLevel3PK gruppeLevel3PK;
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

    public GruppeLevel3() {
    }

    public GruppeLevel3(GruppeLevel3PK gruppeLevel3PK) {
        this.gruppeLevel3PK = gruppeLevel3PK;
    }

    public GruppeLevel3(GruppeLevel3PK gruppeLevel3PK, String r, String w) {
        this.gruppeLevel3PK = gruppeLevel3PK;
        this.r = r;
        this.w = w;
    }

    public GruppeLevel3(String guID, String groupName) {
        this.gruppeLevel3PK = new GruppeLevel3PK(guID, groupName);
    }

    public GruppeLevel3PK getGruppeLevel3PK() {
        return gruppeLevel3PK;
    }

    public void setGruppeLevel3PK(GruppeLevel3PK gruppeLevel3PK) {
        this.gruppeLevel3PK = gruppeLevel3PK;
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
        hash += (gruppeLevel3PK != null ? gruppeLevel3PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruppeLevel3)) {
            return false;
        }
        GruppeLevel3 other = (GruppeLevel3) object;
        if ((this.gruppeLevel3PK == null && other.gruppeLevel3PK != null) || (this.gruppeLevel3PK != null && !this.gruppeLevel3PK.equals(other.gruppeLevel3PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.GruppeLevel3[ gruppeLevel3PK=" + gruppeLevel3PK + " ]";
    }
    
}
