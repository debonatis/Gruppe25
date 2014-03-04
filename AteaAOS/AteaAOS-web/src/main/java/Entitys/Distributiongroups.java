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
    @NamedQuery(name = "Distributiongroups.findAll", query = "SELECT d FROM Distributiongroups d"),
    @NamedQuery(name = "Distributiongroups.findByDisplayname", query = "SELECT d FROM Distributiongroups d WHERE d.displayname = :displayname"),
    @NamedQuery(name = "Distributiongroups.findByEmailalias", query = "SELECT d FROM Distributiongroups d WHERE d.emailalias = :emailalias"),
    @NamedQuery(name = "Distributiongroups.findByExternalemail", query = "SELECT d FROM Distributiongroups d WHERE d.externalemail = :externalemail")})
public class Distributiongroups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String displayname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String emailalias;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String externalemail;

    public Distributiongroups() {
    }

    public Distributiongroups(String displayname) {
        this.displayname = displayname;
    }

    public Distributiongroups(String displayname, String emailalias, String externalemail) {
        this.displayname = displayname;
        this.emailalias = emailalias;
        this.externalemail = externalemail;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getEmailalias() {
        return emailalias;
    }

    public void setEmailalias(String emailalias) {
        this.emailalias = emailalias;
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
        hash += (displayname != null ? displayname.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributiongroups)) {
            return false;
        }
        Distributiongroups other = (Distributiongroups) object;
        if ((this.displayname == null && other.displayname != null) || (this.displayname != null && !this.displayname.equals(other.displayname))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Distributiongroups[ displayname=" + displayname + " ]";
    }
    
}
