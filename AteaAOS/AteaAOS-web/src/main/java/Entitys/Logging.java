/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    @NamedQuery(name = "Logging.findAll", query = "SELECT l FROM Logging l"),
    @NamedQuery(name = "Logging.findByUserId", query = "SELECT l FROM Logging l WHERE l.userId = :userId"),
    @NamedQuery(name = "Logging.findByDated", query = "SELECT l FROM Logging l WHERE l.dated = :dated"),
    @NamedQuery(name = "Logging.findByLogger", query = "SELECT l FROM Logging l WHERE l.logger = :logger"),
    @NamedQuery(name = "Logging.findBySeverity", query = "SELECT l FROM Logging l WHERE l.severity = :severity"),
    @NamedQuery(name = "Logging.findByMessage", query = "SELECT l FROM Logging l WHERE l.message = :message")})
public class Logging implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "USER_ID", nullable = false, length = 100)
    private String userId;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dated;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String logger;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String severity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String message;

    public Logging() {
    }

    public Logging(Date dated) {
        this.dated = dated;
    }

    public Logging(Date dated, String userId, String logger, String severity, String message) {
        this.dated = dated;
        this.userId = userId;
        this.logger = logger;
        this.severity = severity;
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDated() {
        return dated;
    }

    public void setDated(Date dated) {
        this.dated = dated;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dated != null ? dated.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logging)) {
            return false;
        }
        Logging other = (Logging) object;
        if ((this.dated == null && other.dated != null) || (this.dated != null && !this.dated.equals(other.dated))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entitys.Logging[ dated=" + dated + " ]";
    }
    
}
