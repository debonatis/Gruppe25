/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.special.model.SiteuserListModel;
import entityModels.Siteuser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import persistClasses.SiteuserFacade;

/**
 *
 * @author Martin
 */
@ManagedBean(name = "superUsers")
@SessionScoped

public class Superusers implements Serializable {

    @EJB
    private SiteuserFacade siteuserEJB;

    private boolean skip;
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
}
