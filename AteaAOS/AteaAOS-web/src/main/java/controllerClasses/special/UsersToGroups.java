/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Distributiongroups;
import entityModels.Groups;
import entityModels.Groupusers;
import entityModels.GroupusersPK;
import entityModels.Logging;
import entityModels.Userdistribution;
import entityModels.UserdistributionPK;
import entityModels.Users;
import entityModels.UsersPK;
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
    private DualListModel<Distributiongroups> dgroups;
    private String username;
    private String usernameProp;
    private boolean skip;
    private Users bruker = new Users(new UsersPK());

    public DualListModel<Users> getDusers() {
        return dusers;
    }

    public void setDusers(DualListModel<Users> dusers) {
        this.dusers = dusers;
    }

    public DualListModel<Distributiongroups> getDgroups() {
        return dgroups;
    }

    public void setDgroups(DualListModel<Distributiongroups> dgroups) {
        this.dgroups = dgroups;
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
        String projectID = ((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
        users = new DualListModel<>(usersEJB.findAllPro(projectID), new ArrayList<Users>());
        dusers = new DualListModel<>(usersEJB.findAllPro(projectID), new ArrayList<Users>());
        dgroups = new DualListModel<>(dgF.findAllPro(projectID), new ArrayList<Distributiongroups>());
        groups = new DualListModel<>(groupsEJB.findAllPro(projectID), new ArrayList<Groups>());
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
                groupsusersEJB.create(new Groupusers(new GroupusersPK(u.getUsersPK().getUsername(), g.getGroupsPK().getGroupname(),(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))));
            }
        }

        LoggingEJB.create(new Logging(new Date(System.currentTimeMillis()), "simond", "test", "INFO", "test"));

    }

    public void saveDU() {
        List<Distributiongroups> gr = dgroups.getTarget();
        List<Users> ur = dusers.getTarget();
        for (Distributiongroups dg : gr) {
            for (Users u : ur) {
                dMMF.create(new Userdistribution(new UserdistributionPK(u.getUsersPK().getUsername(), dg.getDistributiongroupsPK().getDisplayname(),(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))));
            }
        }

        LoggingEJB.create(new Logging(new Date(System.currentTimeMillis()), "simond", "test", "INFO", "test"));
    }

    public void onTransferU(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for (Object item : event.getItems()) {
            Users bruker3 = (Users) item;
            builder.append(bruker3.getUsersPK().getUsername()).append("<br />");

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
                builder.append(gruppe.getGroupsPK().getGroupname()).append("<br />");

            }

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else if (!event.isAdd()) {
            for (Object item : event.getItems()) {
                Groups gruppe = (Groups) item;
                builder.append(gruppe.getGroupsPK().getGroupname()).append("<br />");

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
                builder.append(bruker3.getUsersPK().getUsername()).append("<br />");

            }

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (!event.isAdd()) {
            for (Object item : event.getItems()) {
                Users bruker3 = (Users) item;
                builder.append(bruker3.getUsersPK().getUsername()).append("<br />");

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
                builder.append(gruppe.getDistributiongroupsPK().getDisplayname()).append("<br />");

            }

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else if (!event.isAdd()) {
            for (Object item : event.getItems()) {
                Distributiongroups gruppe = (Distributiongroups) item;
                builder.append(gruppe.getDistributiongroupsPK().getDisplayname()).append("<br />");

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
        bruker.getUsersPK().setProjectid(((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")));
        usersEJB.create(bruker);
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + bruker.getFirstname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        bruker = new Users();

    }

    public String onFlowProcessPick(FlowEvent event) {

        return event.getNewStep();
    }

    public String onFlowProcessPick2(FlowEvent event) {

        return event.getNewStep();
    }

}
