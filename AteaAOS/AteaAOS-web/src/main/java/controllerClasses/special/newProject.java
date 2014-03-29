/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Projects;
import entityModels.Projecttypes;
import java.io.Serializable;
import java.util.UUID;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

import java.util.logging.Logger;
import persistClasses.ProjectsFacade;

/**
 *
 * @author Martin
 */
@ManagedBean(name = "newProject")
@SessionScoped

public class newProject implements Serializable {

    @EJB
    private ProjectsFacade projectsEJB;

    private boolean skip;
    private Projects projects = new Projects();
    private Projecttypes projecttypes;
    private static final Logger logger = Logger.getLogger(Projects.class.getName());
    private String[] projectTypes = {"V4", "Single Forrest, Single Domain", "Single Forrest, Multiple Domain"};

    private void prepareCreate() {
        projects = new Projects();
    }

    public String[] getProjectTypes() {
        return projectTypes;
    }

    public void setProjectTypes(String[] projectTypes) {
        this.projectTypes = projectTypes;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Object projects) {
        this.projects = (Projects) projects;
    }

    public Projecttypes getProjecttypes() {
        return projecttypes;
    }

    public void setProjecttypes(Object projecttypes) {
        this.projecttypes = (Projecttypes) projecttypes;
    }


    private UUID getUUID() {
        UUID idOne = UUID.randomUUID();
        return idOne;
    }

    public void save() {
        try {
            projects.setProjectid(getUUID().toString());
            projectsEJB.create(projects);
            prepareCreate();

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Project is created");

            FacesContext.getCurrentInstance().addMessage(null, msg);
            prepareCreate();

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Project not created!");
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
