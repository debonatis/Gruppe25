/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.util.JsfUtil;
import controllerClasses.util.PaginationHelper;
import entityModels.Users;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
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
    private Users users;
    private DataModel items = null;
    private PaginationHelper pagination;

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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

    public void prepareEdit(){
        users = (Users) getItems().getRowData();
    }
    
    public void onEdit(RowEditEvent event) {
        
        try {
            uFac.edit(users);
            prepareEdit();

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Project is created");

            FacesContext.getCurrentInstance().addMessage(null, msg);
            prepareEdit();

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Project not created!");
            msg.setDetail("Maybe faulty inputs?");
            prepareEdit();

            FacesContext.getCurrentInstance().addMessage(null, msg);

        }       
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Car Cancelled", ((Users) event.getObject()).getUsername());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private UsersFacade getFacade() {
        return uFac;
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
}
