/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.special.model.ApplicationUsers;
import controllerClasses.special.model.ApplicationsModel;
import entityModels.Applicationaccess;
import entityModels.Applications;
import entityModels.Logging;
import entityModels.Users;
import entityModels.UsersPK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import persistClasses.ApplicationaccessFacade;
import persistClasses.ApplicationsFacade;
import persistClasses.LoggingFacade;

/**
 *
 * @author Martin
 */
@ManagedBean(name = "newApplication")
@ViewScoped
public class NewApplication implements Serializable {

    @EJB
    private ApplicationsFacade applicationsEJB;
    @EJB
    private ApplicationaccessFacade aaF;
    @EJB
    private LoggingFacade lF;
    private boolean skip;
    private Applications applications = new Applications();
    private static final Logger logger = Logger.getLogger(Applications.class.getName());
    private ApplicationsModel selectList = new ApplicationsModel();

    private Applications selected = new Applications();
    private List<Applications> applicationListT = new ArrayList<>();
    private List<ApplicationUsers> appUsers = new ArrayList<>();

    public List<ApplicationUsers> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(List<ApplicationUsers> appUsers) {
        this.appUsers = appUsers;
    }

    public Applications getSelected() {
        return selected;
    }

    public void setSelected(Applications selected) {

        this.selected = selected;

    }

    private void prepareCreate() {
        applications = new Applications();
    }

    public Applications getApplications() {
        return applications;
    }

    public void setApplications(Object applications) {
        this.applications = (Applications) applications;
    }

    public List<Applications> getApplicationListT() {
        return applicationListT;
    }

    public void setApplicationListT(List<Applications> applicationListT) {
        this.applicationListT = applicationListT;
    }

    @PostConstruct
    public void init() {

        applicationListT = applicationsEJB.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));

        selectList = new ApplicationsModel(applicationListT);
        appUsers.clear();
        List<Users> roger;
        for (Applications g : applicationsEJB.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))) {
            roger = new ArrayList<>();
            for (Applicationaccess gu : aaF.findAll()) {
                if (gu.getApplicationaccessPK().getApplicationid().equalsIgnoreCase(g.getApplicationid())) {
                    roger.add(new Users(new UsersPK(gu.getApplicationaccessPK().getUsername(), gu.getApplicationaccessPK().getProjectidu())));

                }
            }
            appUsers.add(new ApplicationUsers(g, roger));
        }
    }

    private UUID getUUID() {
        UUID idOne = UUID.randomUUID();
        return idOne;
    }

    public ApplicationsModel getSelectList() {
        return selectList;
    }

    public void setSelectList(ApplicationsModel selectList) {
        this.selectList = selectList;
    }

    public void save() {
        try {
            applications.setApplicationid(getUUID().toString());
            applications.setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
            applicationsEJB.create(applications);
            lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", applications.getApplicationname() + " has been created."));
            prepareCreate();

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Application is created");

            FacesContext.getCurrentInstance().addMessage(null, msg);
            prepareCreate();

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Application not created!");
            msg.setDetail("Maybe faulty inputs?");
            prepareCreate();

            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void onEdidApp(RowEditEvent event) {

        ApplicationUsers e = (ApplicationUsers) event.getObject();

        Applications app = applicationsEJB.find(e.getApp().getApplicationid());
        app.setVersion(e.getApp().getVersion());
        app.setApplanguage(e.getApp().getApplanguage());
        app.setApplicationname(e.getApp().getApplicationname());
        app.setApplicationownercustomer(e.getApp().getApplicationownercustomer());
        app.setContractinformation(e.getApp().getContractinformation());
        app.setContractinformation(e.getApp().getContractinformation());
        app.setLicense(e.getApp().getLicense());
        app.setSizedatabase(e.getApp().getSizedatabase());
        app.setSizefile(e.getApp().getSizefile());
        app.setSubcontractor(e.getApp().getSubcontractor());
        applicationsEJB.edit(app);
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", app.getApplicationname() + " has been edited."));

        init();
        FacesMessage msg = new FacesMessage("Group Edited", ((ApplicationUsers) event.getObject()).getApp().getApplicationname());

        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onCancelApp(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Group Edit Cancelled", ((ApplicationUsers) event.getObject()).getApp().getApplicationname());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteItemApp(ApplicationUsers e) {

        for (Users u : e.getUsers()) {
            aaF.remove(new Applicationaccess(u.getUsersPK().getUsername(), e.getApp().getApplicationid(), (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")));
            lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", u.getUsersPK().getUsername() + " has been removed from: " + e.getApp().getApplicationname() + ""));
        }
        Applications app = applicationsEJB.find(e.getApp().getApplicationid());
        applicationsEJB.remove(app);
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", app.getApplicationname() + " has been deleted"));

        appUsers.remove(e);

    }

    public void deleteItemAppUser(ApplicationUsers g, Users e) {

        aaF.remove(new Applicationaccess(e.getUsersPK().getUsername(), g.getApp().getApplicationid(), (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")));
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", e.getUsersPK().getUsername() + " has been removed from: " + g.getApp().getApplicationname() + ""));
        int i = appUsers.indexOf(g);
        appUsers.get(i).getUsers().remove(e);

    }

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

}
