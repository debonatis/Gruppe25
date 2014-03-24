/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Groups;
import entityModels.Groupusers;
import entityModels.Logging;
import entityModels.Users;
import java.awt.Event;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.TransferEvent;

import org.primefaces.model.DualListModel;
import persistClasses.GroupsFacade;
import persistClasses.GroupusersFacade;
import persistClasses.LoggingFacade;
import persistClasses.UsersFacade;

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
    private String username;

    public DualListModel<Groups> getGroups() {
        return groups;
    }

    public void setGroups(DualListModel<Groups> groups) {
        this.groups = groups;
    }

    @PostConstruct
    public void preCompute() {
        List<Users> sourceU = new ArrayList<Users>();
        List<Users> targetU = new ArrayList<Users>();

        sourceU = usersEJB.findAll();

        users = new DualListModel<Users>(sourceU, targetU);

        List<Groups> sourceG = new ArrayList<Groups>();
        List<Groups> targetG = new ArrayList<Groups>();
        username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        sourceG = groupsEJB.findAll();

        groups = new DualListModel<Groups>(sourceG, targetG);
    }

    public UsersToGroups() {

    }

    public DualListModel<Users> getUsers() {
        return users;
    }

    public void setUsers(DualListModel<Users> users) {
        this.users = users;
    }

    public void saveU() {

        List<Groups> gr = groups.getTarget();
        List<Users> ur = users.getTarget();

        for (Groups g : gr) {
            for (Users u : ur) {
                groupsusersEJB.create(new Groupusers(u.getUsername(), g.getGroupname()));
            }
        }

        LoggingEJB.create(new Logging(new Date(System.currentTimeMillis()), username, "test", "INFO", "test"));

    }

    public void onTransferU(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            Users bruker = (Users)item;
            builder.append(bruker.getUsername()).append("<br />");

        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onTransferG(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        if (event.isAdd()) {

        
        for (Object item : event.getItems()) {
            Groups gruppe = (Groups) item;
            builder.append(gruppe.getGroupname()).append("<br />");

        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);

    }
    else if(!event.isAdd()) 
        
          for (Object item : event.getItems()) {
            Groups gruppe = (Groups) item;
            builder.append(gruppe.getGroupname()).append("<br />");

        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Removed");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    {

    
    }
    }

    private Groups getasGroupObject(Object item) {

        String value = (String) item;
        if (value == null || value.length() == 0) {
            return null;
        }

        return groupsEJB.find(getKey(value));
    }

    private java.lang.String getKey(String value) {
        java.lang.String key;
        key = value;
        return key;
    }

    private Users getasUserObject(Object item) {

        String value = (String) item;
        if (value == null || value.length() == 0) {
            return null;
        }

        return usersEJB.find(getKey(value));
    }

}
