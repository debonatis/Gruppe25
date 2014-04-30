/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.cSVparser;

import controllerClasses.special.UsersToGroups;
import entityModels.Distributiongroups;
import entityModels.Groups;
import entityModels.Userdistribution;
import entityModels.Users;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author simond
 */
public class ADSIentity {

    private List<Distributiongroups> dgr = new ArrayList<>();
    private List<Groups> gr = new ArrayList<>();
    private List<Users> usr = new ArrayList<>();
    private List<UsersToGroups> usrgr = new ArrayList<>();
    private List<Userdistribution> usrdgr = new ArrayList<>();

    public ADSIentity() {
    }

    public List<Distributiongroups> getDgr() {
        return dgr;
    }

    public void setDgr(List<Distributiongroups> dgr) {
        this.dgr = dgr;
    }

    public List<Groups> getGr() {
        return gr;
    }

    public void setGr(List<Groups> gr) {
        this.gr = gr;
    }

    public List<Users> getUsr() {
        return usr;
    }

    public void setUsr(List<Users> usr) {
        this.usr = usr;
    }

    public List<UsersToGroups> getUsrgr() {
        return usrgr;
    }

    public void setUsrgr(List<UsersToGroups> usrgr) {
        this.usrgr = usrgr;
    }

    public List<Userdistribution> getUsrdgr() {
        return usrdgr;
    }

    public void setUsrdgr(List<Userdistribution> usrdgr) {
        this.usrdgr = usrdgr;
    }

}
