/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author simond
 */
@Entity
@Table(name = "gruppeTilhorighet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GruppeTilhorighet.findAll", query = "SELECT g FROM GruppeTilhorighet g"),
    @NamedQuery(name = "GruppeTilhorighet.findByGroupName", query = "SELECT g FROM GruppeTilhorighet g WHERE g.gruppeTilhorighetPK.groupName = :groupName"),
    @NamedQuery(name = "GruppeTilhorighet.findByGruppeMedlem", query = "SELECT g FROM GruppeTilhorighet g WHERE g.gruppeTilhorighetPK.gruppeMedlem = :gruppeMedlem")})
public class GruppeTilhorighet implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GruppeTilhorighetPK gruppeTilhorighetPK;

    public GruppeTilhorighet() {
    }

    public GruppeTilhorighet(GruppeTilhorighetPK gruppeTilhorighetPK) {
        this.gruppeTilhorighetPK = gruppeTilhorighetPK;
    }

    public GruppeTilhorighet(String groupName, String gruppeMedlem) {
        this.gruppeTilhorighetPK = new GruppeTilhorighetPK(groupName, gruppeMedlem);
    }

    public GruppeTilhorighetPK getGruppeTilhorighetPK() {
        return gruppeTilhorighetPK;
    }

    public void setGruppeTilhorighetPK(GruppeTilhorighetPK gruppeTilhorighetPK) {
        this.gruppeTilhorighetPK = gruppeTilhorighetPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gruppeTilhorighetPK != null ? gruppeTilhorighetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GruppeTilhorighet)) {
            return false;
        }
        GruppeTilhorighet other = (GruppeTilhorighet) object;
        if ((this.gruppeTilhorighetPK == null && other.gruppeTilhorighetPK != null) || (this.gruppeTilhorighetPK != null && !this.gruppeTilhorighetPK.equals(other.gruppeTilhorighetPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.GruppeTilhorighet[ gruppeTilhorighetPK=" + gruppeTilhorighetPK + " ]";
    }
    
}
