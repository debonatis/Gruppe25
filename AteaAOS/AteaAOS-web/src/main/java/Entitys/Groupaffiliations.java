/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entitys;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author simond
 */
@Entity
@Table(name = "GROUPAFFILIATIONS", catalog = "AteaAOS", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupaffiliations.findAll", query = "SELECT g FROM Groupaffiliations g"),
    @NamedQuery(name = "Groupaffiliations.findByGroupnamemany", query = "SELECT g FROM Groupaffiliations g WHERE g.groupaffiliationsPK.groupnamemany = :groupnamemany"),
    @NamedQuery(name = "Groupaffiliations.findByGroupmambership", query = "SELECT g FROM Groupaffiliations g WHERE g.groupaffiliationsPK.groupmambership = :groupmambership")})
public class Groupaffiliations implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GroupaffiliationsPK groupaffiliationsPK;

    public Groupaffiliations() {
    }

    public Groupaffiliations(GroupaffiliationsPK groupaffiliationsPK) {
        this.groupaffiliationsPK = groupaffiliationsPK;
    }

    public Groupaffiliations(String groupnamemany, String groupmambership) {
        this.groupaffiliationsPK = new GroupaffiliationsPK(groupnamemany, groupmambership);
    }

    public GroupaffiliationsPK getGroupaffiliationsPK() {
        return groupaffiliationsPK;
    }

    public void setGroupaffiliationsPK(GroupaffiliationsPK groupaffiliationsPK) {
        this.groupaffiliationsPK = groupaffiliationsPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupaffiliationsPK != null ? groupaffiliationsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupaffiliations)) {
            return false;
        }
        Groupaffiliations other = (Groupaffiliations) object;
        if ((this.groupaffiliationsPK == null && other.groupaffiliationsPK != null) || (this.groupaffiliationsPK != null && !this.groupaffiliationsPK.equals(other.groupaffiliationsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Groupaffiliations[ groupaffiliationsPK=" + groupaffiliationsPK + " ]";
    }
    
}
