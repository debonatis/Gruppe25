/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entityModels;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author simond
 */
@Entity
@Table(name = "Folders", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Folders.findAll", query = "SELECT f FROM Folders f"),
    @NamedQuery(name = "Folders.findByFoldername", query = "SELECT f FROM Folders f WHERE f.foldersPK.foldername = :foldername"),
    @NamedQuery(name = "Folders.findByProjectid", query = "SELECT f FROM Folders f WHERE f.foldersPK.projectid = :projectid"),
    @NamedQuery(name = "Folders.findByParentfolder", query = "SELECT f FROM Folders f WHERE f.parentfolder = :parentfolder")})
public class Folders implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FoldersPK foldersPK;
    @Size(max = 100)
    @Column(name = "PARENTFOLDER")
    private String parentfolder;

    public Folders() {
    }

    public Folders(FoldersPK foldersPK) {
        this.foldersPK = foldersPK;
    }

    public Folders(String foldername, String projectid) {
        this.foldersPK = new FoldersPK(foldername, projectid);
    }

    public FoldersPK getFoldersPK() {
        return foldersPK;
    }

    public void setFoldersPK(FoldersPK foldersPK) {
        this.foldersPK = foldersPK;
    }

    public String getParentfolder() {
        return parentfolder;
    }

    public void setParentfolder(String parentfolder) {
        this.parentfolder = parentfolder;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (foldersPK != null ? foldersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Folders)) {
            return false;
        }
        Folders other = (Folders) object;
        if ((this.foldersPK == null && other.foldersPK != null) || (this.foldersPK != null && !this.foldersPK.equals(other.foldersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Folders[ foldersPK=" + foldersPK + " ]";
    }
    
}
