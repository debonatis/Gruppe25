/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.special.model.ApplicationsModel;
import entityModels.Applications;
import persistClasses.ApplicationsFacade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Martin
 */
@ManagedBean(name = "newApplication")
@SessionScoped
public class NewApplication implements Serializable {

    @EJB
    private ApplicationsFacade applicationsEJB;
    private boolean skip;
    private Applications applications = new Applications();
    private static final Logger logger = Logger.getLogger(Applications.class.getName());
    private ApplicationsModel selectList = new ApplicationsModel();
    private Applications selected = new Applications();
    private List<Applications> applicationListT = new ArrayList<>();

    public Applications getSelected() {
        return selected;
    }

    public void setSelected(Applications selected) {

        this.selected = selected;

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("projectID", selected.getApplicationid());
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

    @PostConstruct
    public void init() {

        applicationListT = applicationsEJB.findAll();

        selectList = new ApplicationsModel(applicationListT);
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

    /**
     *
     * @return
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     *
     * @param skip
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     *
     * @param event
     * @return returns given wisard step
     */
    public String onFlowProcess(FlowEvent event) {
        logger.log(Level.INFO, "Current wizard step:{0}", event.getOldStep());
        logger.log(Level.INFO, "Next step:{0}", event.getNewStep());

        if (skip) {
            skip = false;
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

}
