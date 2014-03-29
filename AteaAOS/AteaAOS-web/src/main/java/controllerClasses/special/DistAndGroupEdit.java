/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special;

import controllerClasses.special.model.DistSecGroups;
import entityModels.Groups;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
    
    
    @PostConstruct
    private void init(){
        List<Groups> gr = gF.findAll();
        
    }
    
    
    
}
