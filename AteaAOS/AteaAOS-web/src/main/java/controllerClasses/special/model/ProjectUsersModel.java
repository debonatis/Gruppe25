/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.model;

import entityModels.Projects;
import entityModels.Siteuser;
import java.util.List;

/**
 *
 * @author simond
 */
public class ProjectUsersModel {
    
    private Projects pro;
    private List<Siteuser> userList;

    public ProjectUsersModel(Projects pro, List<Siteuser> userList) {
        this.pro = pro;
        this.userList = userList;
    }

    public Projects getPro() {
        return pro;
    }

    public void setPro(Projects pro) {
        this.pro = pro;
    }

    public List<Siteuser> getUserList() {
        return userList;
    }

    public void setUserList(List<Siteuser> userList) {
        this.userList = userList;
    }
    
    
}
