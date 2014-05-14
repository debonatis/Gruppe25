/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.special.model.SharedresourcesUsersModel;
import entityModels.Logging;
import entityModels.Sharedresources;
import entityModels.SharedresourcesPK;
import entityModels.Sharedresourcesusers;
import entityModels.SharedresourcesusersPK;
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
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
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

    private List<SharedresourcesUsersModel> srUsers = new ArrayList<>();
    private DualListModel<Sharedresources> sharedresources;

    private boolean skip;

    private Sharedresources shared = new Sharedresources(new SharedresourcesPK());

    public Sharedresources getShared() {
        return shared;
    }

    public void setShared(Sharedresources shared) {
        this.shared = shared;
    }

    public List<SharedresourcesUsersModel> getSrUsers() {
        return srUsers;
    }

    public void setSrUsers(List<SharedresourcesUsersModel> srUsers) {
        this.srUsers = srUsers;
    }

    public DualListModel<Sharedresources> getSharedresources() {
        return sharedresources;
    }

    public void setSharedresources(DualListModel<Sharedresources> sharedresources) {
        this.sharedresources = sharedresources;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public UsersToSharedresources() {

    }

    public DualListModel<Users> getUsers() {
        return users;
    }

    public void setUsers(DualListModel<Users> users) {
        this.users = users;
    }

    public void savePick() {

        List<Sharedresources> gr = sharedresources.getTarget();
        List<Users> ur = users.getTarget();

        for (Sharedresources g : gr) {
            for (Users u : ur) {
                sharedresourcesusersEJB.create(new Sharedresourcesusers(new SharedresourcesusersPK(g.getSharedresourcesPK().getDisplaynameshared(), u.getUsersPK().getUsername(), (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))));
            }
        }

        LoggingEJB.create(new Logging(new Date(System.currentTimeMillis()), "simond", "test", "INFO", "test"));

    }

    public void save() {
        shared.setAccessresources("NOT IN USE");
        shared.getSharedresourcesPK().setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
        sharedresourcesEJB.create(shared);
        FacesMessage msg = new FacesMessage("Successful", "You saved :" + shared.getSharedresourcesPK().getDisplaynameshared());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        shared = new Sharedresources(new SharedresourcesPK());
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

    public void onTransferSR(TransferEvent event) {
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

        users = new DualListModel<>(usersEJB.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")), new ArrayList<Users>());

        sharedresources = new DualListModel<>(sharedresourcesEJB.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")), new ArrayList<Sharedresources>());
        srUsers = new ArrayList<>();
        List<Users> roger = new ArrayList<>();
        for (Sharedresources sr : sharedresourcesEJB.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))) {
            roger.clear();
            for (Sharedresourcesusers sru : sharedresourcesusersEJB.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))) {
                if (sr.getSharedresourcesPK().getDisplaynameshared().equalsIgnoreCase(sru.getSharedresourcesusersPK().getDisplaynameshared())) {
                    roger.add(usersEJB.find(new UsersPK(sru.getSharedresourcesusersPK().getUsername(), (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))));
                }
            }
            srUsers.add(new SharedresourcesUsersModel(sr, roger));
        }
    }

    public void deleteItemSR(SharedresourcesUsersModel e) {

        for (Users u : e.getUserList()) {
            sharedresourcesusersEJB.remove(new Sharedresourcesusers(e.getSr().getSharedresourcesPK().getDisplaynameshared(), u.getUsersPK().getUsername(), e.getSr().getSharedresourcesPK().getProjectid()));

        }
        Sharedresources pro = sharedresourcesEJB.find(e.getSr().getSharedresourcesPK());
        sharedresourcesEJB.remove(pro);

        srUsers.remove(e);

    }

    public void deleteItemSRUser(SharedresourcesUsersModel g, Users e) {

        sharedresourcesusersEJB.remove(new Sharedresourcesusers(g.getSr().getSharedresourcesPK().getDisplaynameshared(), e.getUsersPK().getUsername(), g.getSr().getSharedresourcesPK().getProjectid()));

        int i = srUsers.indexOf(g);
        srUsers.get(i).getUserList().remove(e);

    }

    public String onFlowProcessPick(FlowEvent event) {

        return event.getNewStep();
    }

    public void onEdit(RowEditEvent event) {

        try {

            Sharedresources test = sharedresourcesEJB.find(((SharedresourcesUsersModel) event.getObject()).getSr().getSharedresourcesPK());

            test.setEmailalias(((Sharedresources) event.getObject()).getEmailalias());
            test.setExternalemail(((Sharedresources) event.getObject()).getExternalemail());

            sharedresourcesEJB.edit(test);

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Sharedresource edited sucsessfully!");
            msg.setDetail(" ");

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Shared Resource not edited!");
            msg.setDetail("Maybe faulty inputs?");

            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Editing Cancelled", ((Sharedresources) event.getObject()).getSharedresourcesPK().getDisplaynameshared());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
