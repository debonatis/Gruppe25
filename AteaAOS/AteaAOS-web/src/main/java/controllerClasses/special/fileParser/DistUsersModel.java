/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.fileParser;

import entityModels.Distributiongroups;
import entityModels.Users;
import java.util.List;

/**
 *
 * @author simond
 */
public class DistUsersModel {
    
    private Distributiongroups dist;
    private List<Users> userList;

    public DistUsersModel(Distributiongroups dist, List<Users> userList) {
        this.dist = dist;
        this.userList = userList;
    }

    public DistUsersModel() {
    }

    public Distributiongroups getDist() {
        return dist;
    }

    public void setDist(Distributiongroups dist) {
        this.dist = dist;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }
           
    
    
}
