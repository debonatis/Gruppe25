/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Users;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import persistClasses.ProjectsFacade;

import persistClasses.UsersFacade;

/**
 *
 * @author Martin
 */
@ManagedBean(name = "editUsers")
@ViewScoped
public class EditUsers {

    @EJB
    private UsersFacade uFac;
    @EJB
    private ProjectsFacade pFac;

    private List<Users> liste;

    @PostConstruct
    public void init() {
        liste = uFac.findAll();
    }

    public List<Users> getUsersList() {
        return liste;
    }

    public void setUsersList(List<Users> usersList) {
        this.liste = usersList;
    }

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }
    
    public void onEdit(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Car Edited", ((Users) event.getObject()).getUsername());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Car Cancelled", ((Users) event.getObject()).getUsername());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
}
