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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

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

    private List<Users> usersList;

    @PostConstruct
    public void init() {
        usersList = uFac.findAll();
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }
}
