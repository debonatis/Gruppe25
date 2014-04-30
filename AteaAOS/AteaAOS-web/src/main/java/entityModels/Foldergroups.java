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
@Table(name = "FOLDERGROUPS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Foldergroups.findAll", query = "SELECT f FROM Foldergroups f"),
    @NamedQuery(name = "Foldergroups.findByFoldername", query = "SELECT f FROM Foldergroups f WHERE f.foldergroupsPK.foldername = :foldername"),
    @NamedQuery(name = "Foldergroups.findByProjectid", query = "SELECT f FROM Foldergroups f WHERE f.foldergroupsPK.projectid = :projectid"),
    @NamedQuery(name = "Foldergroups.findByGroupname", query = "SELECT f FROM Foldergroups f WHERE f.foldergroupsPK.groupname = :groupname"),
    @NamedQuery(name = "Foldergroups.findByRw", query = "SELECT f FROM Foldergroups f WHERE f.rw = :rw"),
    @NamedQuery(name = "Foldergroups.findByR", query = "SELECT f FROM Foldergroups f WHERE f.r = :r")})
public class Foldergroups implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FoldergroupsPK foldergroupsPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "RW")
    private String rw;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "R")
    private String r;

    public Foldergroups() {
    }

    public Foldergroups(FoldergroupsPK foldergroupsPK) {
        this.foldergroupsPK = foldergroupsPK;
    }

    public Foldergroups(FoldergroupsPK foldergroupsPK, String rw, String r) {
        this.foldergroupsPK = foldergroupsPK;
        this.rw = rw;
        this.r = r;
    }

    public Foldergroups(String foldername, String projectid, String groupname) {
        this.foldergroupsPK = new FoldergroupsPK(foldername, projectid, groupname);
    }

    public FoldergroupsPK getFoldergroupsPK() {
        return foldergroupsPK;
    }

    public void setFoldergroupsPK(FoldergroupsPK foldergroupsPK) {
        this.foldergroupsPK = foldergroupsPK;
    }

    public String getRw() {
        return rw;
    }

    public void setRw(String rw) {
        this.rw = rw;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foldergroupsPK != null ? foldergroupsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foldergroups)) {
            return false;
        }
        Foldergroups other = (Foldergroups) object;
        if ((this.foldergroupsPK == null && other.foldergroupsPK != null) || (this.foldergroupsPK != null && !this.foldergroupsPK.equals(other.foldergroupsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Foldergroups[ foldergroupsPK=" + foldergroupsPK + " ]";
    }
    
}
