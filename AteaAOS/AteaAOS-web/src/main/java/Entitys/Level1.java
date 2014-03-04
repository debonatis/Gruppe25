/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entitys;

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
@Table(name = "LEVEL1", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Level1.findAll", query = "SELECT l FROM Level1 l"),
    @NamedQuery(name = "Level1.findByMappenavn", query = "SELECT l FROM Level1 l WHERE l.mappenavn = :mappenavn"),
    @NamedQuery(name = "Level1.findByGuid", query = "SELECT l FROM Level1 l WHERE l.guid = :guid")})
public class Level1 implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "MAPPENAVN")
    private String mappenavn;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "GUID")
    private String guid;

    public Level1() {
    }

    public Level1(String guid) {
        this.guid = guid;
    }

    public Level1(String guid, String mappenavn) {
        this.guid = guid;
        this.mappenavn = mappenavn;
    }

    public String getMappenavn() {
        return mappenavn;
    }

    public void setMappenavn(String mappenavn) {
        this.mappenavn = mappenavn;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guid != null ? guid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Level1)) {
            return false;
        }
        Level1 other = (Level1) object;
        if ((this.guid == null && other.guid != null) || (this.guid != null && !this.guid.equals(other.guid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Level1[ guid=" + guid + " ]";
    }
    
}
