/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.security;

import entityModels.City;
import entityModels.Roles;
import entityModels.Siteuser;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import persistClasses.CityFacade;
import persistClasses.RolesFacade;
import persistClasses.SiteuserFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
@DeclareRoles({"admin"})
public class SiteuserControl {

    private Siteuser user;
    private Roles roleObject;
    private String role = "";
    private City city;

    @EJB
    private SiteuserFacade suF;
    @EJB
    private RolesFacade rF;
    @EJB
    private CityFacade cF;
    private String[] userRoles = {"Admin", "superuser"};

    public Siteuser getUser() {
        return user;
    }

    public void setUser(Siteuser user) {
        this.user = user;
    }

    public String[] getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String[] userRoles) {
        this.userRoles = userRoles;
    }

    @RolesAllowed("admin")
    public void saveSU() {
        try {

            user.setPostalcode(city.getPostalcode());
            cF.create(city);
            suF.create(user);
            roleObject = new Roles(user.getUsername(), role);
            rF.create(roleObject);
            user = new Siteuser();
            roleObject = new Roles();
            city = new City();
        } catch (Exception e) {

        }
    }
}
