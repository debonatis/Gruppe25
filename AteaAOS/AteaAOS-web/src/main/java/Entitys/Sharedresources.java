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
@Table(catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sharedresources.findAll", query = "SELECT s FROM Sharedresources s"),
    @NamedQuery(name = "Sharedresources.findByDisplaynameshared", query = "SELECT s FROM Sharedresources s WHERE s.displaynameshared = :displaynameshared"),
    @NamedQuery(name = "Sharedresources.findByEmailalias", query = "SELECT s FROM Sharedresources s WHERE s.emailalias = :emailalias"),
    @NamedQuery(name = "Sharedresources.findByAccessresources", query = "SELECT s FROM Sharedresources s WHERE s.accessresources = :accessresources"),
    @NamedQuery(name = "Sharedresources.findByExternalemail", query = "SELECT s FROM Sharedresources s WHERE s.externalemail = :externalemail")})
public class Sharedresources implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DISPLAYNAMESHARED")
    private String displaynameshared;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EMAILALIAS")
    private String emailalias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ACCESSRESOURCES")
    private String accessresources;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "EXTERNALEMAIL")
    private String externalemail;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String displaynameshared;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String emailalias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String accessresources;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String externalemail;

    public Sharedresources() {
    }

    public Sharedresources(String displaynameshared) {
        this.displaynameshared = displaynameshared;
    }

    public Sharedresources(String displaynameshared, String emailalias, String accessresources, String externalemail) {
        this.displaynameshared = displaynameshared;
        this.emailalias = emailalias;
        this.accessresources = accessresources;
        this.externalemail = externalemail;
    }

    public String getDisplaynameshared() {
        return displaynameshared;
    }

    public void setDisplaynameshared(String displaynameshared) {
        this.displaynameshared = displaynameshared;
    }

    public String getEmailalias() {
        return emailalias;
    }

    public void setEmailalias(String emailalias) {
        this.emailalias = emailalias;
    }

    public String getAccessresources() {
        return accessresources;
    }

    public void setAccessresources(String accessresources) {
        this.accessresources = accessresources;
    }

    public String getExternalemail() {
        return externalemail;
    }

    public void setExternalemail(String externalemail) {
        this.externalemail = externalemail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (displaynameshared != null ? displaynameshared.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sharedresources)) {
            return false;
        }
        Sharedresources other = (Sharedresources) object;
        if ((this.displaynameshared == null && other.displaynameshared != null) || (this.displaynameshared != null && !this.displaynameshared.equals(other.displaynameshared))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Sharedresources[ displaynameshared=" + displaynameshared + " ]";
    }

    public Sharedresources(String displaynameshared) {
        this.displaynameshared = displaynameshared;
    }

    public Sharedresources(String displaynameshared, String emailalias, String accessresources, String externalemail) {
        this.displaynameshared = displaynameshared;
        this.emailalias = emailalias;
        this.accessresources = accessresources;
        this.externalemail = externalemail;
    }

    public String getDisplaynameshared() {
        return displaynameshared;
    }

    public void setDisplaynameshared(String displaynameshared) {
        this.displaynameshared = displaynameshared;
    }

    public String getEmailalias() {
        return emailalias;
    }

    public void setEmailalias(String emailalias) {
        this.emailalias = emailalias;
    }

    public String getAccessresources() {
        return accessresources;
    }

    public void setAccessresources(String accessresources) {
        this.accessresources = accessresources;
    }

    public String getExternalemail() {
        return externalemail;
    }

    public void setExternalemail(String externalemail) {
        this.externalemail = externalemail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (displaynameshared != null ? displaynameshared.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sharedresources)) {
            return false;
        }
        Sharedresources other = (Sharedresources) object;
        if ((this.displaynameshared == null && other.displaynameshared != null) || (this.displaynameshared != null && !this.displaynameshared.equals(other.displaynameshared))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Sharedresources[ displaynameshared=" + displaynameshared + " ]";
    }
    
}
