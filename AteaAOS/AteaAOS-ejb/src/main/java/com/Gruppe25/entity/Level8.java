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
@Table(name = "level8")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Level8.findAll", query = "SELECT l FROM Level8 l"),
    @NamedQuery(name = "Level8.findByMappeNavn", query = "SELECT l FROM Level8 l WHERE l.mappeNavn = :mappeNavn"),
    @NamedQuery(name = "Level8.findByGuID", query = "SELECT l FROM Level8 l WHERE l.guID = :guID"),
    @NamedQuery(name = "Level8.findByGuIDFK", query = "SELECT l FROM Level8 l WHERE l.guIDFK = :guIDFK")})
public class Level8 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mappeNavn")
    private String mappeNavn;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "guID")
    private String guID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "guIDFK")
    private String guIDFK;

    public Level8() {
    }

    public Level8(String guID) {
        this.guID = guID;
    }

    public Level8(String guID, String mappeNavn, String guIDFK) {
        this.guID = guID;
        this.mappeNavn = mappeNavn;
        this.guIDFK = guIDFK;
    }

    public String getMappeNavn() {
        return mappeNavn;
    }

    public void setMappeNavn(String mappeNavn) {
        this.mappeNavn = mappeNavn;
    }

    public String getGuID() {
        return guID;
    }

    public void setGuID(String guID) {
        this.guID = guID;
    }

    public String getGuIDFK() {
        return guIDFK;
    }

    public void setGuIDFK(String guIDFK) {
        this.guIDFK = guIDFK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guID != null ? guID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Level8)) {
            return false;
        }
        Level8 other = (Level8) object;
        if ((this.guID == null && other.guID != null) || (this.guID != null && !this.guID.equals(other.guID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.entity.Level8[ guID=" + guID + " ]";
    }
    
}
