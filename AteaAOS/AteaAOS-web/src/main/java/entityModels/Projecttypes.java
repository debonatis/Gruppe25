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
 * @author Martin
 */
@Entity
@Table(name = "PROJECTTYPES", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projecttypes.findAll", query = "SELECT p FROM Projecttypes p"),
    @NamedQuery(name = "Projecttypes.findByProjecttype", query = "SELECT p FROM Projecttypes p WHERE p.projecttype = :projecttype"),
    @NamedQuery(name = "Projecttypes.findByDescription", query = "SELECT p FROM Projecttypes p WHERE p.description = :description")})
public class Projecttypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "PROJECTTYPE")
    private String projecttype;
    @Size(max = 100)
    @Column(name = "DESCRIPTION")
    private String description;

    public Projecttypes() {
    }

    public Projecttypes(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getProjecttype() {
        return projecttype;
    }

    public void setProjecttype(String projecttype) {
        this.projecttype = projecttype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projecttype != null ? projecttype.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projecttypes)) {
            return false;
        }
        Projecttypes other = (Projecttypes) object;
        if ((this.projecttype == null && other.projecttype != null) || (this.projecttype != null && !this.projecttype.equals(other.projecttype))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityModels.Projecttypes[ projecttype=" + projecttype + " ]";
    }
    
}
