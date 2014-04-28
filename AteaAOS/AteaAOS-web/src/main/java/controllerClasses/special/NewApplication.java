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
import entityModels.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import persistClasses.ApplicationaccessFacade;
import persistClasses.ApplicationsFacade;

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

        applicationListT = applicationsEJB.findAll();

        selectList = new ApplicationsModel(applicationListT);
        appUsers.clear();
        List<Users> roger;
        for (Applications g : applicationsEJB.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))) {
            roger = new ArrayList<>();
            for (Applicationaccess gu : aaF.findAll()) {
                if (gu.getApplicationaccessPK().getApplicationid().equalsIgnoreCase(g.getApplicationid())) {
                    roger.add(new Users(gu.getApplicationaccessPK().getUsername()));

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
            aaF.remove(new Applicationaccess(u.getUsername(), e.getApp().getApplicationid()));

        }
        Applications app = applicationsEJB.find(e.getApp().getApplicationid());
        applicationsEJB.remove(app);

        appUsers.remove(e);

    }

    public void deleteItemAppUser(ApplicationUsers g, Users e) {

        aaF.remove(new Applicationaccess(e.getUsername(), g.getApp().getApplicationid()));

        int i = appUsers.indexOf(g);
        appUsers.get(i).getUsers().remove(e);

    }

}
