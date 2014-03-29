/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.model;

import entityModels.Users;
import java.util.List;

/**
 *
 * @author simond
 */
public class DistSecGroupModel {
    
    private String grname;
    private boolean sg;
    private boolean dg;
    private String gowner;
    private List<Users> users;

    public DistSecGroupModel(String grname, boolean sg, boolean dg, String gowner, List<Users> users) {
        this.grname = grname;
        this.sg = sg;
        this.dg = dg;
        this.gowner = gowner;
        this.users = users;
    }

    public DistSecGroupModel() {
    }
    

    public String getGrname() {
        return grname;
    }

    public void setGrname(String grname) {
        this.grname = grname;
    }

    public boolean isSg() {
        return sg;
    }

    public void setSg(boolean sg) {
        this.sg = sg;
    }

    public boolean isDg() {
        return dg;
    }

    public void setDg(boolean dg) {
        this.dg = dg;
    }

    public String getGowner() {
        return gowner;
    }

    public void setGowner(String gowner) {
        this.gowner = gowner;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "DistSecGroupModel{" + "grname=" + grname + ", sg=" + sg + ", dg=" + dg + ", gowner=" + gowner + ", users=" + users + '}';
    }

   
   
    
}
