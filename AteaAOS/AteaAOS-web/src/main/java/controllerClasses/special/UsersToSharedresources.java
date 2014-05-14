/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.special.model.ProjectUsersModel;
import controllerClasses.special.model.SharedresourcesUsersModel;
import controllerClasses.special.model.SiteuserListModel;
import controllerClasses.special.model.UsersListModel;
import entityModels.Logging;
import entityModels.Projects;
import entityModels.Prositeusers;
import entityModels.Sharedresources;
import entityModels.Sharedresourcesusers;
import entityModels.SharedresourcesusersPK;
import entityModels.Siteuser;
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
import persistClasses.SharedresourcesFacade;
import persistClasses.LoggingFacade;
import persistClasses.SharedresourcesusersFacade;
import persistClasses.UsersFacade;

@ManagedBean(name = "usersToSharedresources")
@ViewScoped
public class UsersToSharedresources implements Serializable {

    @EJB
    private UsersFacade usersEJB;
    @EJB
    private SharedresourcesFacade sharedresourcesEJB;
    @EJB
    private SharedresourcesusersFacade sharedresourcesusersEJB;
    @EJB
    private LoggingFacade LoggingEJB;

    private DualListModel<Users> users;
    private DualListModel<Users> dusers;
    private List<SharedresourcesUsersModel> srUsers = new ArrayList<>();
    private DualListModel<Sharedresources> sharedresources;
    private String username;
    private String usernameProp;
    private boolean skip;
    private Users bruker = new Users(new UsersPK());
    
    private List<Users> projectListT = new ArrayList<>();
    private UsersListModel selectList = new UsersListModel();
    private DualListModel<Sharedresources> listPro;
    private DualListModel<Users> listSU;

    public DualListModel<Users> getDusers() {
        return dusers;
    }

    public void setDusers(DualListModel<Users> dusers) {
        this.dusers = dusers;
    }

    public String getUsernameProp() {
        usernameProp = (bruker.getFirstname().substring(0, 8) + bruker.getLastname().substring(3, 5));
        return usernameProp;
    }

    public void setUsernameProp(String usernameProp) {
        this.usernameProp = usernameProp;
    }

    public List<SharedresourcesUsersModel> getSrUsers() {
        return srUsers;
    }

    public void setSrUsers(List<SharedresourcesUsersModel> srUsers) {
        this.srUsers = srUsers;
    }

    public Users getBruker() {
        return bruker;
    }

    public void setBruker(Users bruker) {
        this.bruker = bruker;
    }

    public DualListModel<Sharedresources> getSharedresources() {
        return sharedresources;
    }

    public void setGroups(DualListModel<Sharedresources> sharedresources) {
        this.sharedresources = sharedresources;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

//    @PostConstruct
//    private void init() {
//        String projectID = ((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
//        users = new DualListModel<>(usersEJB.findAllPro(projectID), new ArrayList<Users>());
//        dusers = new DualListModel<>(usersEJB.findAllPro(projectID), new ArrayList<Users>());
//        sharedresources = new DualListModel<>(sharedresourcesEJB.findAllPro(projectID), new ArrayList<Sharedresources>());
//        username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
//    }

    public UsersToSharedresources() {

    }

    public DualListModel<Users> getUsers() {
        return users;
    }

    public void setUsers(DualListModel<Users> users) {
        this.users = users;
    }

    public void saveU() {

        List<Sharedresources> gr = sharedresources.getTarget();
        List<Users> ur = users.getTarget();

        for (Sharedresources g : gr) {
            for (Users u : ur) {
                sharedresourcesusersEJB.create(new Sharedresourcesusers(new SharedresourcesusersPK(g.getSharedresourcesPK().getDisplaynameshared(), u.getUsersPK().getUsername(), (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))));
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
                Sharedresources shared = (Sharedresources) item;
                builder.append(shared.getSharedresourcesPK().getDisplaynameshared()).append("<br />");
            }

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else if (!event.isAdd()) {
            for (Object item : event.getItems()) {
                Sharedresources shared = (Sharedresources) item;
                builder.append(shared.getSharedresourcesPK().getDisplaynameshared()).append("<br />");

            }
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Removed");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }
    
    @PostConstruct
    public void init() {

        projectListT = usersEJB.findAll();

        selectList = new UsersListModel(projectListT);
        listPro = new DualListModel<>(sharedresourcesEJB.findAll(), new ArrayList<Sharedresources>());
        listSU = new DualListModel<>(usersEJB.findAll(), new ArrayList<Users>());
        srUsers.clear();
        List<Users> roger;
        for (Sharedresources g : sharedresourcesEJB.findAll()) {
            roger = new ArrayList<>();
            for (Sharedresourcesusers gu : sharedresourcesusersEJB.findAll()) {
                if (gu.getSharedresourcesusersPK().getProjectiddisp().equalsIgnoreCase(g.getSharedresourcesPK().getProjectid())) {
                    roger.add(usersEJB.find(gu.getSharedresourcesusersPK().getUsername()));

                }
            }
            srUsers.add(new SharedresourcesUsersModel(g, roger));
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
    
    public void deleteItemPro(SharedresourcesUsersModel e) {

        for (Users u : e.getUserList()) {
            sharedresourcesusersEJB.remove(new Sharedresourcesusers(e.getSr().getSharedresourcesPK().getDisplaynameshared(), u.getUsersPK().getUsername(), e.getSr().getSharedresourcesPK().getProjectid()));

        }
        Sharedresources pro = sharedresourcesEJB.find(e.getSr().getSharedresourcesPK().getProjectid());
        sharedresourcesEJB.remove(pro);

        srUsers.remove(e);

    }
    
    
    public void deleteItemProUser(SharedresourcesUsersModel g, Users e) {

        sharedresourcesusersEJB.remove(new Sharedresourcesusers(g.getSr().getSharedresourcesPK().getDisplaynameshared(),e.getUsersPK().getUsername(),g.getSr().getSharedresourcesPK().getProjectid()));

        int i = srUsers.indexOf(g);
        srUsers.get(i).getUserList().remove(e);

    }

    public String onFlowProcessPick(FlowEvent event) {

        return event.getNewStep();
    }

    public String onFlowProcessPick2(FlowEvent event) {

        return event.getNewStep();
    }

}
