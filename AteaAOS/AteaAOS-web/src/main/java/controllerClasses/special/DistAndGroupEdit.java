/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special;

import controllerClasses.special.model.DistSecGroupModel;
import entityModels.Distributiongroups;
import entityModels.Groups;
import entityModels.Groupusers;
import entityModels.Userdistribution;
import entityModels.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import persistClasses.DistributiongroupsFacade;
import persistClasses.GroupsFacade;
import persistClasses.GroupusersFacade;
import persistClasses.UserdistributionFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
public class DistAndGroupEdit {
    @EJB
    private DistributiongroupsFacade dgF;
    @EJB
    private GroupsFacade gF;
    @EJB
    private UserdistributionFacade dgMMF;
    @EJB
    private GroupusersFacade sgMMF;
    private List<DistSecGroupModel> liste;
    
    
    
    @PostConstruct
    private void init(){
      List<Users> roger;
        for(Groups g :gF.findAll()){
            roger= new ArrayList<>();
            for(Groupusers gu : sgMMF.findAll()){
                if(gu.getGroupusersPK().getUsergroupname().equalsIgnoreCase(g.getGroupname())){
                    roger.add(new Users(gu.getGroupusersPK().getUsername()));
                    
                }
            }
            liste.add(new DistSecGroupModel(g.getGroupname(), true, false, "-", roger));
        }
        for(Distributiongroups d :dgF.findAll()){
            roger= new ArrayList<>();
            for(Userdistribution du : dgMMF.findAll()){
                if(du.getUserdistributionPK().getDisplayname().equalsIgnoreCase(d.getDisplayname())){
                    roger.add(new Users(du.getUserdistributionPK().getUsername()));
                    
                }
            }
            liste.add(new DistSecGroupModel(d.getDisplayname(), false, true, "-", roger));
        }
    }

    public List<DistSecGroupModel> getListe() {
        return liste;
    }

    public void setListe(List<DistSecGroupModel> liste) {
        this.liste = liste;
    }
     public void onEditDIST(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Car Edited", ((DistSecGroupModel) event.getObject()).getGrname());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancelDIST(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Car Cancelled", ((DistSecGroupModel) event.getObject()).getGrname());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
     public void onEditList(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Car Edited", ((Users) event.getObject()).getUsername());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public void onCancelList(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Car Cancelled", ((Users) event.getObject()).getUsername());  
  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    } 
     public void deleteItemDIST(DistSecGroupModel e) {
        liste.remove(e);

    }
     public void deleteItemDISTUser(DistSecGroupModel g,Users e) {
        int i =liste.indexOf(g);
        liste.get(i).getUsers().remove(e);

    }
    
    
}
