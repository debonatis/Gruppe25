/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.special.model.SiteuserListModel;
import entityModels.Siteuser;
import entityModels.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import persistClasses.SiteuserFacade;
import persistClasses.UsersFacade;

/**
 *
 * @author Martin
 */
@ManagedBean(name = "superUsers")
@SessionScoped

public class Superusers implements Serializable {

    @EJB
    private SiteuserFacade siteuserEJB;

    private SiteuserListModel selectList = new SiteuserListModel();
    private Siteuser selected = new Siteuser();
    private List<Siteuser> projectListT = new ArrayList<>();

    public Siteuser getSelected() {
        return selected;
    }

    public void setSelected(Siteuser selected) {

        this.selected = selected;

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("SiteUsername", selected.getUsername());
    }

    @PostConstruct
    public void init() {

        projectListT = siteuserEJB.findAll();

        selectList = new SiteuserListModel(projectListT);
    }

    public SiteuserListModel getSelectList() {
        return selectList;
    }

    public void setSelectList(SiteuserListModel selectList) {
        this.selectList = selectList;
    }

    public void deleteItemUsr(Siteuser e) {

        siteuserEJB.remove(e);
        projectListT.remove(e);

    }

    public void onEdit(RowEditEvent event) {
        
        try {
            
            Siteuser test = getFacade().find(((Siteuser) event.getObject()).getUsername());
            
            test.setUsername(((Users) event.getObject()).getUsername());
            test.setPassword(((Siteuser) event.getObject()).getPassword());
            test.setFirstname(((Users) event.getObject()).getFirstname());
            siteuserEJB.edit(test);
            
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Siteuser edited sucsessfully!");
            msg.setDetail(" ");
            
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Siteuser not edited!");
            msg.setDetail("Maybe faulty inputs?");
            
            FacesContext.getCurrentInstance().addMessage(null, msg);
            
        }
    }
    
    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Editing Cancelled", ((Users) event.getObject()).getUsername());
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private SiteuserFacade getFacade() {
        return siteuserEJB;
    }
}
