/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.model;

import entityModels.Sharedresources;
import entityModels.Users;
import java.util.List;

/**
 *
 * @author Martin
 */
public class SharedresourcesUsersModel {
    
    private Sharedresources sr;
    private List<Users> userList;

    public SharedresourcesUsersModel(Sharedresources sr, List<Users> userList) {
        this.sr = sr;
        this.userList = userList;
    }

    public Sharedresources getSr() {
        return sr;
    }

    public void setSr(Sharedresources sr) {
        this.sr = sr;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }
}

