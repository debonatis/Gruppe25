/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.special.model.DistSecGroupModel;
import entityModels.Distributiongroups;
import entityModels.Groups;
import entityModels.Groupusers;
import entityModels.Userdistribution;
import entityModels.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import persistClasses.DistributiongroupsFacade;
import persistClasses.GroupsFacade;
import persistClasses.GroupusersFacade;
import persistClasses.UserdistributionFacade;
import persistClasses.UsersFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
public class DistAndGroupEdit {

    @EJB
    private DistributiongroupsFacade dgF;
    @EJB
    private GroupsFacade gF;
    @EJB
    private UserdistributionFacade dgMMF;
    @EJB
    private GroupusersFacade sgMMF;
    @EJB
    private UsersFacade uF;
    private List<DistSecGroupModel> liste = new ArrayList<>();
    private List<Groups> listsg = new ArrayList<>();
    private List<Distributiongroups> listdg = new ArrayList<>();
    private List<Users> users = new ArrayList<>();
    private Groups selectsg;
    private Distributiongroups selectdg;

    public List<Users> getUsers() {
        String projectID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID");
        users = uF.findAllPro(projectID);
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public void init() {
        liste.clear();
        List<Users> roger;
        for (Groups g : gF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))) {
            roger = new ArrayList<>();
            for (Groupusers gu : sgMMF.findAll()) {
                if (gu.getGroupusersPK().getUsergroupname().equalsIgnoreCase(g.getGroupname())) {
                    roger.add(new Users(gu.getGroupusersPK().getUsername()));

                }
            }
            liste.add(new DistSecGroupModel(g.getGroupname(), true, false, g.getGroupowner(), roger));
        }
        for (Distributiongroups d : dgF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))) {
            roger = new ArrayList<>();
            for (Userdistribution du : dgMMF.findAll()) {
                if (du.getUserdistributionPK().getDisplayname().equalsIgnoreCase(d.getDisplayname())) {
                    roger.add(new Users(du.getUserdistributionPK().getUsername()));

                }
            }
            liste.add(new DistSecGroupModel(d.getDisplayname(), false, true, "-", roger));
        }

    }

    public void initdg() {
        listdg = dgF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));

    }

    public void initsg() {
        listsg = gF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));

    }

    public List<Groups> getListsg() {
        return listsg;
    }

    public void setListsg(List<Groups> listsg) {
        this.listsg = listsg;
    }

    public List<Distributiongroups> getListdg() {
        return listdg;
    }

    public void setListdg(List<Distributiongroups> listdg) {
        this.listdg = listdg;
    }

    public Distributiongroups getSelectdg() {
        if (selectdg == null) {
            selectdg = new Distributiongroups();

        }
        return selectdg;
    }

    public void setSelectdg(Distributiongroups selectdg) {
        this.selectdg = selectdg;
    }

    public void saveSelectdg() {

        selectdg.setProjectid(((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")));
        dgF.create(selectdg);
        selectdg = new Distributiongroups();
    }

    public Groups getSelectsg() {
        if (selectsg == null) {
            selectsg = new Groups();

        }
        return selectsg;
    }

    public void setSelectsg(Groups select) {
        this.selectsg = select;
    }

    public void saveSelectsg() {

        selectsg.setProjectid(((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")));
        gF.create(selectsg);
        selectsg = new Groups();
    }

    public List<DistSecGroupModel> getListe() {
        return liste;
    }

    public void setListe(List<DistSecGroupModel> liste) {
        this.liste = liste;
    }

    public void onEditDIST(RowEditEvent event) {

        DistSecGroupModel e = (DistSecGroupModel) event.getObject();
        if (e.isSg()) {

            Groups gru = gF.find(e.getGrname());
            gru.setGroupowner(e.getGowner());
            gF.edit(gru);

            init();
            FacesMessage msg = new FacesMessage("Group Edited", ((DistSecGroupModel) event.getObject()).getGrname());

            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onCancelDIST(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Group Edit Cancelled", ((DistSecGroupModel) event.getObject()).getGrname());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteItemDIST(DistSecGroupModel e) {
        if (e.isSg()) {
            for (Users u : e.getUsers()) {
                sgMMF.remove(new Groupusers(u.getUsername(), e.getGrname()));

            }
            Groups gru = gF.find(e.getGrname());
            gF.remove(gru);

            liste.remove(e);
        } else if (e.isDg()) {
            for (Users u : e.getUsers()) {
                dgMMF.remove(new Userdistribution(u.getUsername(), e.getGrname()));

            }
            Distributiongroups roger = dgF.find(e.getGrname());
            dgF.remove(roger);
            liste.remove(e);
        }

    }

    public void deleteItemDISTUser(DistSecGroupModel g, Users e) {

        if (g.isSg()) {
            sgMMF.remove(new Groupusers(e.getUsername(), g.getGrname()));
        } else if (g.isDg()) {
            dgMMF.remove(new Userdistribution(e.getUsername(), g.getGrname()));
        }

        int i = liste.indexOf(g);
        liste.get(i).getUsers().remove(e);

    }

}
