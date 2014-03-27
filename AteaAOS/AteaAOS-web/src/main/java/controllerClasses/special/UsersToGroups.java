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
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FlowEvent;
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
        }
        
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Removed");
        msg.setDetail(builder.toString());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
        {
            
        }
    }
    
    public String onFlowProcess(FlowEvent event) {
        
        return event.getNewStep();
        
    }
    
    public void saveW(ActionEvent actionEvent) {
        bruker.setProjectid(String.valueOf(56));
        usersEJB.create(bruker);
        bruker = new Users();
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + bruker.getFirstname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        
    }

    public String onFlowProcessPick(FlowEvent event) {
        
        return event.getNewStep();
    }
    
}
