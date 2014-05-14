/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityModels;

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
@Table(name = "SHAREDRESOURCES", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sharedresources.findAll", query = "SELECT s FROM Sharedresources s"),
    @NamedQuery(name = "Sharedresources.findByDisplaynameshared", query = "SELECT s FROM Sharedresources s WHERE s.sharedresourcesPK.displaynameshared = :displaynameshared"),
    @NamedQuery(name = "Sharedresources.findByProjectid", query = "SELECT s FROM Sharedresources s WHERE s.sharedresourcesPK.projectid = :projectid"),
    @NamedQuery(name = "Sharedresources.findByEmailalias", query = "SELECT s FROM Sharedresources s WHERE s.emailalias = :emailalias"),
    @NamedQuery(name = "Sharedresources.findByAccessresources", query = "SELECT s FROM Sharedresources s WHERE s.accessresources = :accessresources"),
    @NamedQuery(name = "Sharedresources.findByExternalemail", query = "SELECT s FROM Sharedresources s WHERE s.externalemail = :externalemail")})
public class Sharedresources implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SharedresourcesPK sharedresourcesPK;
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

    public Sharedresources() {
    }

    public Sharedresources(SharedresourcesPK sharedresourcesPK) {
        this.sharedresourcesPK = sharedresourcesPK;
    }

    public Sharedresources(SharedresourcesPK sharedresourcesPK, String emailalias, String accessresources, String externalemail) {
        this.sharedresourcesPK = sharedresourcesPK;
        this.emailalias = emailalias;
        this.accessresources = accessresources;
        this.externalemail = externalemail;
    }

    public Sharedresources(String displaynameshared, String projectid) {
        this.sharedresourcesPK = new SharedresourcesPK(displaynameshared, projectid);
    }

    public SharedresourcesPK getSharedresourcesPK() {
        return sharedresourcesPK;
    }

    public void setSharedresourcesPK(SharedresourcesPK sharedresourcesPK) {
        this.sharedresourcesPK = sharedresourcesPK;
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
        hash += (sharedresourcesPK != null ? sharedresourcesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sharedresources)) {
            return false;
        }
        Sharedresources other = (Sharedresources) object;
        if ((this.sharedresourcesPK == null && other.sharedresourcesPK != null) || (this.sharedresourcesPK != null && !this.sharedresourcesPK.equals(other.sharedresourcesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Sharedresources[ sharedresourcesPK=" + sharedresourcesPK + " ]";
    }
    
}
