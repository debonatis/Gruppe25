/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.util.PaginationHelper;
import entityModels.Groupusers;
import entityModels.Logging;
import entityModels.Userdistribution;
import entityModels.Users;
import entityModels.UsersPK;
import java.util.Date;
import java.util.List;
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
import persistClasses.GroupusersFacade;
import persistClasses.LoggingFacade;
import persistClasses.UserdistributionFacade;

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
    private LoggingFacade lF;
    @EJB
    private UserdistributionFacade dgMMF;
    @EJB
    private GroupusersFacade sgMMF;
    private List<Users> liste;
    private Users users;
    private DataModel items = null;
    private PaginationHelper pagination;

    @PostConstruct
    public void init() {
        liste = uFac.findAllPro(((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")));
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

    public void prepareEdit() {
        users = (Users) getItems().getRowData();
    }

    public void onEdit(RowEditEvent event) {

        try {

            Users test = getFacade().find(((UsersPK) ((Users) event.getObject()).getUsersPK()));

            test.setFirstname(((Users) event.getObject()).getFirstname());
            test.setLastname(((Users) event.getObject()).getLastname());
            test.setMobile(((Users) event.getObject()).getMobile());
            test.setEmploymentnr(((Users) event.getObject()).getEmploymentnr());
            uFac.edit(test);
            lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", test.getUsersPK().getUsername() + " has been edited."));

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("User edited sucsessfully!");
            msg.setDetail(" ");

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("User not edited!");
            msg.setDetail("Maybe faulty inputs?");

            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Editing Cancelled", ((Users) event.getObject()).getUsersPK().getUsername());

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

    public void deleteItemUsr(Users e) {

        for (Groupusers usr : sgMMF.findAllUsr(e.getUsersPK().getUsername())) {
            sgMMF.remove(usr);
        }

        for (Userdistribution dusr : dgMMF.findAllUsr(e.getUsersPK().getUsername())) {
            dgMMF.remove(dusr);
        }

        uFac.remove(e);
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", e.getUsersPK().getUsername() + " has been deleted."));

        liste.remove(e);

    }
}
