/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.special.model.ProjectsListModel;
import entityModels.Logging;
import entityModels.Projects;
import entityModels.Prositeusers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import persistClasses.LoggingFacade;
import persistClasses.ProjectsFacade;
import persistClasses.PrositeusersFacade;

/**
 *
 * @author Martin
 */
@ManagedBean(name = "newProject")
@SessionScoped

public class newProject implements Serializable {

    @EJB
    private ProjectsFacade projectsEJB;
    @EJB
    private LoggingFacade lF;
    @EJB
    private PrositeusersFacade psuF;
    private boolean skip;
    private Projects projects = new Projects();
    private static final Logger logger = Logger.getLogger(Projects.class.getName());
    private String[] projectTypes = {"V4", "Single Forrest, Single Domain", "Single Forrest, Multiple Domain"};
    private ProjectsListModel selectList = new ProjectsListModel();

    private Projects selected = new Projects();
    private List<Projects> projectListT = new ArrayList<>();

    public Projects getSelected() {
        return selected;
    }

    public void setSelected(Projects selected) {

        this.selected = selected;

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("projectID", selected.getProjectid());
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("projectName", selected.getName());
    }

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

    @PostConstruct
    public void init() {

        if (FacesContext.getCurrentInstance().getExternalContext().getRemoteUser() == null) {
            projectListT = new ArrayList<>();
            selectList = new ProjectsListModel(projectListT);

        } else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin")) {
            projectListT = projectsEJB.findAll();
            selectList = new ProjectsListModel(projectListT);

        } else if(!FacesContext.getCurrentInstance().getExternalContext().isUserInRole("admin")){

            for (Prositeusers psu : psuF.findAllUsr(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser())) {
                projectListT.add(projectsEJB.find(psu.getPrositeusersPK().getProjectid()));
            }
            selectList = new ProjectsListModel(projectListT);
        }

    }

    private UUID getUUID() {
        UUID idOne = UUID.randomUUID();
        return idOne;
    }

    public ProjectsListModel getSelectList() {
        return selectList;
    }

    public void setSelectList(ProjectsListModel selectList) {
        this.selectList = selectList;
    }

    public void save() {
        try {
            projects.setProjectid(getUUID().toString());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("projectID", projects.getProjectid());
            projectsEJB.create(projects);
            psuF.create(new Prositeusers(projects.getProjectid(), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()));
            lF.create(new Logging(new java.util.Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", projects.getName() + " has been created."));
            
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
