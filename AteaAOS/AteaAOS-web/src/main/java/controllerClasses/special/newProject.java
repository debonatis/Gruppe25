/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.util.PaginationHelper;
import entityModels.Projects;
import entityModels.Projecttypes;
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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import persistClasses.ProjectsFacade;
import persistClasses.ProjecttypesFacade;

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
    private ProjecttypesFacade projecttypesEJB;
    
    private boolean skip;  
    private Projects projects = new Projects();
    private Projecttypes projecttypes = new Projecttypes();
    private static final Logger logger = Logger.getLogger(Projects.class.getName());
    
    private List<Projecttypes> projecttype;
    private Projecttypes selectedType;  
    private Projecttypes[] selectedTypes; 
    private MultipleSingle multipleSingle;
    
    private DataModel items = null;
    private int selectedItemIndex;
    private PaginationHelper pagination;
    
    public Projecttypes getSelected() {
        if (selectedType == null) {
            selectedType = new Projecttypes();
            selectedItemIndex = -1;
        }
        return selectedType;
    }
    
    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }
    
    private ProjecttypesFacade getFacade() {
        return projecttypesEJB;
    }

    private void recreateModel() {
        items = null;
    }
    
    
    private void prepareCreate() {
        projects = new Projects();
        projecttypes = new Projecttypes();
    }
    
    public newProject(){
        projecttype = new ArrayList<Projecttypes>();
        
        populateTypes(projecttype);
        
        multipleSingle = new MultipleSingle(projecttype);
    }
    
    public Projects getProjects() {
        return projects;
    }
   
    
    public void setProjects(Object projects) {
        this.projects = (Projects) projects;
    }
    
    private UUID getUUID() {
        UUID idOne = UUID.randomUUID();
        return idOne;
    }
    
    public void save() {
        try {
            projects.setProjectid(getUUID().toString());
            projectsEJB.create(projects);
            projecttypesEJB.create(projecttypes);
            
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
    
    public Projecttypes[] getSelectedTypes(){
        return selectedTypes;
    }
    public void setSelectedTypes(Projecttypes[] selectedTypes){
        this.selectedTypes = selectedTypes;
    }
    public Projecttypes getSelectedType(){
        return selectedType;
    }
    public void setSelectedType(Projecttypes selectedType){
        this.selectedType = selectedType;
    }
    
    public String getProjecttypeString(){
        return selectedType.getProjecttype();
    }
    
    public String getDescriptionString(){
        return selectedType.getDescription();
    }
    
    public MultipleSingle getMultipleSingle(){
        return multipleSingle;
    }
    
    private void populateTypes(List<Projecttypes> list){
        for(int i = 0; i < projecttype.size() ;i++){
            list.add(new Projecttypes(getProjecttypeString(), getDescriptionString()));
        }
    }
}
