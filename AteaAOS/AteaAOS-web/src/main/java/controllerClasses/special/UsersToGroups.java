/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Distributiongroups;
import entityModels.Groups;
import entityModels.Groupusers;
import entityModels.Logging;
import entityModels.Userdistribution;
import entityModels.Users;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TransferEvent;

import org.primefaces.model.DualListModel;
import persistClasses.DistributiongroupsFacade;
import persistClasses.GroupsFacade;
import persistClasses.GroupusersFacade;
import persistClasses.LoggingFacade;
import persistClasses.UserdistributionFacade;
import persistClasses.UsersFacade;

@ManagedBean(name = "usersToGroups")
@ViewScoped
public class UsersToGroups implements Serializable {

    @EJB
    private UsersFacade usersEJB;
    @EJB
    private GroupsFacade groupsEJB;
    @EJB
    private GroupusersFacade groupsusersEJB;
    @EJB
    private LoggingFacade LoggingEJB;
    @EJB
    private DistributiongroupsFacade dgF;
    @EJB
    private UserdistributionFacade dMMF;
    private DualListModel<Users> users;
    private DualListModel<Users> dusers;
    private DualListModel<Groups> groups;
    private DualListModel<Distributiongroups> dGroups;
    private String username;
    private String usernameProp;
    private boolean skip;
    private Users bruker = new Users();

    public DualListModel<Users> getDusers() {
        return dusers;
    }

    public void setDusers(DualListModel<Users> dusers) {
        this.dusers = dusers;
    }

    public DualListModel<Distributiongroups> getdGroups() {
        return dGroups;
    }

    public void setdGroups(DualListModel<Distributiongroups> dGroups) {
        this.dGroups = dGroups;
    }

    public String getUsernameProp() {
        usernameProp = (bruker.getFirstname().substring(0, 8) + bruker.getLastname().substring(3, 5));
        return usernameProp;
    }

    public void setUsernameProp(String usernameProp) {
        this.usernameProp = usernameProp;
    }

    public Users getBruker() {
        return bruker;
    }

    public void setBruker(Users bruker) {
        this.bruker = bruker;
    }

    public DualListModel<Groups> getGroups() {
        return groups;
    }

    public void setGroups(DualListModel<Groups> groups) {
        this.groups = groups;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    @PostConstruct
    private void init() {

        users = new DualListModel<>(usersEJB.findAll(), new ArrayList<Users>());
        dusers = new DualListModel<>(usersEJB.findAll(), new ArrayList<Users>());
        dGroups = new DualListModel<>(dgF.findAllPro(((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))), new ArrayList<Distributiongroups>());
        groups = new DualListModel<>(groupsEJB.findAllPro(((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))), new ArrayList<Groups>());
        username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
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

        LoggingEJB.create(new Logging(new Date(System.currentTimeMillis()), "simond", "test", "INFO", "test"));

    }

    public void saveDU() {
        List<Distributiongroups> gr = dGroups.getTarget();
        List<Users> ur = dusers.getTarget();
        for (Distributiongroups dg : gr) {
            for (Users u : ur) {
                dMMF.create(new Userdistribution(u.getUsername(), dg.getDisplayname()));
            }
        }

        LoggingEJB.create(new Logging(new Date(System.currentTimeMillis()), "simond", "test", "INFO", "test"));
    }

    public void onTransferU(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            Users bruker3 = (Users) item;
            builder.append(bruker3.getUsername()).append("<br />");

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

        } else if (!event.isAdd()) {
            for (Object item : event.getItems()) {
                Groups gruppe = (Groups) item;
                builder.append(gruppe.getGroupname()).append("<br />");

            }
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Removed");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onTransferDU(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        if (event.isAdd()) {
            for (Object item : event.getItems()) {
                Users bruker3 = (Users) item;
                builder.append(bruker3.getUsername()).append("<br />");

            }

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (!event.isAdd()) {
            for (Object item : event.getItems()) {
                Users bruker3 = (Users) item;
                builder.append(bruker3.getUsername()).append("<br />");

            }

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Removed");
            msg.setDetail(builder.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onTransferDG(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        if (event.isAdd()) {

            for (Object item : event.getItems()) {
                Distributiongroups gruppe = (Distributiongroups) item;
                builder.append(gruppe.getDisplayname()).append("<br />");

            }

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else if (!event.isAdd()) {
            for (Object item : event.getItems()) {
                Distributiongroups gruppe = (Distributiongroups) item;
                builder.append(gruppe.getDisplayname()).append("<br />");

            }
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Removed");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

    public void saveW(ActionEvent actionEvent) {
        bruker.setProjectid(((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")));
        usersEJB.create(bruker);
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + bruker.getFirstname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        bruker = new Users();

    }

    public String onFlowProcessPick(FlowEvent event) {

        return event.getNewStep();
    }

}
