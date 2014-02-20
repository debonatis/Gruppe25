/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25;

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
@Table(name = "userDistribution")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserDistribution.findAll", query = "SELECT u FROM UserDistribution u"),
    @NamedQuery(name = "UserDistribution.findByDistUsername", query = "SELECT u FROM UserDistribution u WHERE u.userDistributionPK.distUsername = :distUsername"),
    @NamedQuery(name = "UserDistribution.findByDistDisplayName", query = "SELECT u FROM UserDistribution u WHERE u.userDistributionPK.distDisplayName = :distDisplayName")})
public class UserDistribution implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserDistributionPK userDistributionPK;

    public UserDistribution() {
    }

    public UserDistribution(UserDistributionPK userDistributionPK) {
        this.userDistributionPK = userDistributionPK;
    }

    public UserDistribution(String distUsername, String distDisplayName) {
        this.userDistributionPK = new UserDistributionPK(distUsername, distDisplayName);
    }

    public UserDistributionPK getUserDistributionPK() {
        return userDistributionPK;
    }

    public void setUserDistributionPK(UserDistributionPK userDistributionPK) {
        this.userDistributionPK = userDistributionPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userDistributionPK != null ? userDistributionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserDistribution)) {
            return false;
        }
        UserDistribution other = (UserDistribution) object;
        if ((this.userDistributionPK == null && other.userDistributionPK != null) || (this.userDistributionPK != null && !this.userDistributionPK.equals(other.userDistributionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.UserDistribution[ userDistributionPK=" + userDistributionPK + " ]";
    }
    
}
