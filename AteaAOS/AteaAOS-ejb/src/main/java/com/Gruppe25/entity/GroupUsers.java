/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25.entity;

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
@Table(name = "groupUsers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GroupUsers.findAll", query = "SELECT g FROM GroupUsers g"),
    @NamedQuery(name = "GroupUsers.findByUsername", query = "SELECT g FROM GroupUsers g WHERE g.groupUsersPK.username = :username"),
    @NamedQuery(name = "GroupUsers.findByUserGroupName", query = "SELECT g FROM GroupUsers g WHERE g.groupUsersPK.userGroupName = :userGroupName")})
public class GroupUsers implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GroupUsersPK groupUsersPK;

    public GroupUsers() {
    }

    public GroupUsers(GroupUsersPK groupUsersPK) {
        this.groupUsersPK = groupUsersPK;
    }

    public GroupUsers(String username, String userGroupName) {
        this.groupUsersPK = new GroupUsersPK(username, userGroupName);
    }

    public GroupUsersPK getGroupUsersPK() {
        return groupUsersPK;
    }

    public void setGroupUsersPK(GroupUsersPK groupUsersPK) {
        this.groupUsersPK = groupUsersPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupUsersPK != null ? groupUsersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupUsers)) {
            return false;
        }
        GroupUsers other = (GroupUsers) object;
        if ((this.groupUsersPK == null && other.groupUsersPK != null) || (this.groupUsersPK != null && !this.groupUsersPK.equals(other.groupUsersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Gruppe25.entity.GroupUsers[ groupUsersPK=" + groupUsersPK + " ]";
    }
    
}
