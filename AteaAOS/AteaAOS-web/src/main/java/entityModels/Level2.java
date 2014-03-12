/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityModels;

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
@Table(name = "LEVEL2", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Level2.findAll", query = "SELECT l FROM Level2 l"),
    @NamedQuery(name = "Level2.findByMappenavn", query = "SELECT l FROM Level2 l WHERE l.mappenavn = :mappenavn"),
    @NamedQuery(name = "Level2.findByGuid", query = "SELECT l FROM Level2 l WHERE l.guid = :guid"),
    @NamedQuery(name = "Level2.findByGuidfk", query = "SELECT l FROM Level2 l WHERE l.guidfk = :guidfk")})
public class Level2 implements Serializable {
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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "GUIDFK")
    private String guidfk;

    public Level2() {
    }

    public Level2(String guid) {
        this.guid = guid;
    }

    public Level2(String guid, String mappenavn, String guidfk) {
        this.guid = guid;
        this.mappenavn = mappenavn;
        this.guidfk = guidfk;
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

    public String getGuidfk() {
        return guidfk;
    }

    public void setGuidfk(String guidfk) {
        this.guidfk = guidfk;
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
        if (!(object instanceof Level2)) {
            return false;
        }
        Level2 other = (Level2) object;
        if ((this.guid == null && other.guid != null) || (this.guid != null && !this.guid.equals(other.guid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Level2[ guid=" + guid + " ]";
    }
    
}
