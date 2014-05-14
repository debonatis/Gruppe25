/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.fileParser;

import entityModels.Groups;
import entityModels.Users;
import java.util.List;

/**
 *
 * @author simond
 */
public class GroupUsersModel {

    private Groups group;
    private List<Users> userList;

    public GroupUsersModel() {
    }

    public GroupUsersModel(Groups group, List<Users> userList) {
        this.group = group;
        this.userList = userList;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public List<Users> getUserList() {
        return userList;
    }

    public void setUserList(List<Users> userList) {
        this.userList = userList;
    }

}
