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
@Table(catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grouplevel1.findAll", query = "SELECT g FROM Grouplevel1 g"),
    @NamedQuery(name = "Grouplevel1.findByGroupname", query = "SELECT g FROM Grouplevel1 g WHERE g.grouplevel1PK.groupname = :groupname"),
    @NamedQuery(name = "Grouplevel1.findByGuid", query = "SELECT g FROM Grouplevel1 g WHERE g.grouplevel1PK.guid = :guid"),
    @NamedQuery(name = "Grouplevel1.findByR", query = "SELECT g FROM Grouplevel1 g WHERE g.r = :r"),
    @NamedQuery(name = "Grouplevel1.findByW", query = "SELECT g FROM Grouplevel1 g WHERE g.w = :w")})
public class Grouplevel1 implements Serializable {
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
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Grouplevel1PK grouplevel1PK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String r;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String w;

    public Grouplevel1() {
    }

    public Grouplevel1(Grouplevel1PK grouplevel1PK) {
        this.grouplevel1PK = grouplevel1PK;
    }

    public Grouplevel1(Grouplevel1PK grouplevel1PK, String r, String w) {
        this.grouplevel1PK = grouplevel1PK;
        this.r = r;
        this.w = w;
    }

    public Grouplevel1(String groupname, String guid) {
        this.grouplevel1PK = new Grouplevel1PK(groupname, guid);
    }

    public Grouplevel1PK getGrouplevel1PK() {
        return grouplevel1PK;
    }

    public void setGrouplevel1PK(Grouplevel1PK grouplevel1PK) {
        this.grouplevel1PK = grouplevel1PK;
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
        hash += (grouplevel1PK != null ? grouplevel1PK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grouplevel1)) {
            return false;
        }
        Grouplevel1 other = (Grouplevel1) object;
        if ((this.grouplevel1PK == null && other.grouplevel1PK != null) || (this.grouplevel1PK != null && !this.grouplevel1PK.equals(other.grouplevel1PK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Grouplevel1[ grouplevel1PK=" + grouplevel1PK + " ]";
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
    
}
