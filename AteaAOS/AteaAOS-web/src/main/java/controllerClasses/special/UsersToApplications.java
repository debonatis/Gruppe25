<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Applicationaccess;
import entityModels.Applications;
import entityModels.Users;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import persistClasses.ApplicationaccessFacade;
import persistClasses.ApplicationsFacade;
import persistClasses.LoggingFacade;
import persistClasses.UsersFacade;

/**
 *
 * @author Martin
 */
@ManagedBean(name = "usersToApps")
@ViewScoped
public class UsersToApplications {

    @EJB
    private UsersFacade usersEJB;
    @EJB
    private ApplicationsFacade applicationsEJB;
    @EJB
    private ApplicationaccessFacade appAccessEJB;
    @EJB
    private LoggingFacade LoggingEJB;
    private DualListModel<Users> users;
    private DualListModel<Applications> applications;
    private String username;
    private String usernameProp;
    private boolean skip;
    private Users bruker = new Users();

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

    public DualListModel<Applications> getApplications() {
        return applications;
    }

    public void setApplications(DualListModel<Applications> applications) {
        this.applications = applications;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    @PostConstruct
    private void init() {

        users = new DualListModel<>(usersEJB.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")), new ArrayList<Users>());

        applications = new DualListModel<>(applicationsEJB.findAll(), new ArrayList<Applications>());
        username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }

    public UsersToApplications() {

    }

    public DualListModel<Users> getUsers() {
        return users;
    }

    public void setUsers(DualListModel<Users> users) {
        this.users = users;
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

    public void onTransferA(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        if (event.isAdd()) {

            for (Object item : event.getItems()) {
                Applications app = (Applications) item;
                builder.append(app.getApplicationname()).append("<br />");

            }

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else if (!event.isAdd()) {
            for (Object item : event.getItems()) {
                Applications apps = (Applications) item;
                builder.append(apps.getApplicationname()).append("<br />");

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

    public void savePick() {

        for (Applications a : getApplications().getTarget()) {
            for (Users u : getUsers().getTarget()) {
                appAccessEJB.create(new Applicationaccess(u.getUsername(), a.getApplicationid()));
            }
        }

        FacesMessage msg = new FacesMessage("Successful", "The pick is saved");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public String onFlowProcessPick(FlowEvent event) {

        return event.getNewStep();
    }
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special;

import entityModels.Applicationaccess;
import entityModels.Applications;
import entityModels.Logging;
import entityModels.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import persistClasses.ApplicationaccessFacade;
import persistClasses.ApplicationsFacade;
import persistClasses.LoggingFacade;
import persistClasses.UsersFacade;

/**
 *
 * @author Martin
 */

@ManagedBean(name = "usersToApps")
@ViewScoped
public class UsersToApplications {
    
    @EJB
    private UsersFacade usersEJB;
    @EJB
    private ApplicationsFacade applicationsEJB;
    @EJB
    private ApplicationaccessFacade appAccessEJB;
    @EJB
    private LoggingFacade LoggingEJB;
    private DualListModel<Users> users;
    private DualListModel<Applications> applications;
    private String username;
    private String usernameProp;
    private boolean skip;
    private Users bruker = new Users();
    private Applications apps = new Applications();
    
    
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

    public Applications getApps() {
        return apps;
    }

    public void setApps(Applications apps) {
        this.apps = apps;
    }
    
    

    public DualListModel<Applications> getApplications() {
        return applications;
    }

    public void setApplications(DualListModel<Applications> applications) {
        this.applications = applications;
    }
    
    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
    @PostConstruct
    private void init(){
    
        users = new DualListModel<>(usersEJB.findAllPro((String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")), new ArrayList<Users>());

        applications = new DualListModel<>(applicationsEJB.findAll(), new ArrayList<Applications>());
        username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
}

    public UsersToApplications() {

    }
    
    public DualListModel<Users> getUsers() {
        return users;
    }

    public void setUsers(DualListModel<Users> users) {
        this.users = users;
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
    
    public void onTransferA(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        if (event.isAdd()) {

            for (Object item : event.getItems()) {
                Applications app = (Applications) item;
                builder.append(app.getApplicationname()).append("<br />");

            }

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else if (!event.isAdd()) {
            for (Object item : event.getItems()) {
                Applications apps = (Applications) item;
                builder.append(apps.getApplicationname()).append("<br />");

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
    
    public void savePick() {
       
        for(Applications a:getApplications().getTarget()){
            for(Users u:getUsers().getTarget()){
                appAccessEJB.create(new Applicationaccess(u.getUsername(), a.getApplicationid()));
            }
        }
    
        
        FacesMessage msg = new FacesMessage("Successful", "The pick is saved");
        FacesContext.getCurrentInstance().addMessage(null, msg);
       

    }
    
    public String onFlowProcessPick(FlowEvent event) {

        return event.getNewStep();
    }
}
>>>>>>> origin/master
