/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entitys;

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
@Table(name = "GROUPLEVEL2", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grouplevel2.findAll", query = "SELECT g FROM Grouplevel2 g"),
    @NamedQuery(name = "Grouplevel2.findByGroupname", query = "SELECT g FROM Grouplevel2 g WHERE g.grouplevel2PK.groupname = :groupname"),
    @NamedQuery(name = "Grouplevel2.findByGuid", query = "SELECT g FROM Grouplevel2 g WHERE g.grouplevel2PK.guid = :guid"),
    @NamedQuery(name = "Grouplevel2.findByR", query = "SELECT g FROM Grouplevel2 g WHERE g.r = :r"),
    @NamedQuery(name = "Grouplevel2.findByW", query = "SELECT g FROM Grouplevel2 g WHERE g.w = :w")})
public class Grouplevel2 implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Grouplevel2PK grouplevel2PK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "R")
    private String r;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "W")
    private String w;

    public Grouplevel2() {
    }

    public Grouplevel2(Grouplevel2PK grouplevel2PK) {
        this.grouplevel2PK = grouplevel2PK;
    }

    public Grouplevel2(Grouplevel2PK grouplevel2PK, String r, String w) {
        this.grouplevel2PK = grouplevel2PK;
        this.r = r;
        this.w = w;
    }

    public Grouplevel2(String groupname, String guid) {
        this.grouplevel2PK = new Grouplevel2PK(groupname, guid);
    }

    public Grouplevel2PK getGrouplevel2PK() {
        return grouplevel2PK;
    }

    public void setGrouplevel2PK(Grouplevel2PK grouplevel2PK) {
        this.grouplevel2PK = grouplevel2PK;
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
        hash += (grouplevel2PK != null ? grouplevel2PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grouplevel2)) {
            return false;
        }
        Grouplevel2 other = (Grouplevel2) object;
        if ((this.grouplevel2PK == null && other.grouplevel2PK != null) || (this.grouplevel2PK != null && !this.grouplevel2PK.equals(other.grouplevel2PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Grouplevel2[ grouplevel2PK=" + grouplevel2PK + " ]";
    }
    
}
