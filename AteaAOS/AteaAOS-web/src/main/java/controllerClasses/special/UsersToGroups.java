/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Groups;
import entityModels.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.TransferEvent;

import org.primefaces.model.DualListModel;
import persistClasses.GroupsFacade;
import persistClasses.GroupusersFacade;
import persistClasses.LoggingFacade;
import persistClasses.UsersFacade;

/**
 *
 * @author simond
 *
 */
@ManagedBean(name = "usersToGroups")
@SessionScoped
public class UsersToGroups implements Serializable {

    @EJB
    private UsersFacade usersEJB;
    @EJB
    private GroupsFacade groupsEJB;
    @EJB
    private GroupusersFacade groupsusersEJB;
    @EJB
    private LoggingFacade LoggingEJB;
    private DualListModel<Users> users;
    private DualListModel<Groups> groups;

    public DualListModel<Groups> getGroups() {
        return groups;
    }

    public void setGroups(DualListModel<Groups> groups) {
        this.groups = groups;
    }

    public UsersToGroups() {
        //Players  
        List<Users> sourceU = new ArrayList<Users>();
        List<Users> targetU = new ArrayList<Users>();

        sourceU = usersEJB.findAll();

        users = new DualListModel<Users>(sourceU, targetU);

        List<Groups> sourceG = new ArrayList<Groups>();
        List<Groups> targetG = new ArrayList<Groups>();

        sourceG = groupsEJB.findAll();

        groups = new DualListModel<Groups>(sourceG, targetG);

    }

    public DualListModel<Users> getUsers() {
        return users;
    }

    public void setUsers(DualListModel<Users> users) {
        this.users = users;
    }

    public void saveU() {

    }

    public void saveG() {

    }

    public void onTransferU(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((Users) item).getUsername()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     public void onTransferG(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            builder.append(((Groups) item).getGroupname()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
