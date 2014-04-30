/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.model;

import entityModels.Applications;
import entityModels.Users;
import java.util.List;

/**
 *
 * @author simond
 */
public class ApplicationUsers {

    private Applications app;

    private List<Users> users;

    public ApplicationUsers(Applications app, List<Users> users) {
        this.app = app;
        this.users = users;
    }

    public Applications getApp() {
        return app;
    }

    public void setApp(Applications app) {
        this.app = app;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

}
