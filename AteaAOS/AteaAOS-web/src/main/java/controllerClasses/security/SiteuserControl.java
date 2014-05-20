/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.security;

import entityModels.City;
import entityModels.Roles;
import entityModels.Siteuser;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;
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
    
    private Siteuser user = new Siteuser();
    private Roles roleObject = new Roles();
    private String role = "";
    private City city = new City();
    
    @EJB
    private SiteuserFacade suF;
    @EJB
    private RolesFacade rF;
    @EJB
    private CityFacade cF;
    private String[] userRoles = {"admin", "superuser"};
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public Roles getRoleObject() {
        return roleObject;
    }
    
    public void setRoleObject(Roles roleObject) {
        this.roleObject = roleObject;
    }
    
    public City getCity() {
        return city;
    }
    
    public void setCity(City city) {
        this.city = city;
    }
    
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
            char firstInitial = user.getFirstname().charAt(0);            
            String shortened2 = user.getLastname().length() > 7 ? user.getLastname().substring(0, 7) : user.getLastname();            
            String userName = (firstInitial + shortened2);    
            user.setUsername(userName.toLowerCase());
            user.setPostalcode(city.getPostalcode());
            cF.create(city);
            user.setPassword(passGenerator());
            user.setPassword(encryptPassword(user.getPassword()));
            suF.create(user);
            roleObject = new Roles(user.getUsername(), role);
            rF.create(roleObject);
            user = new Siteuser();
            roleObject = new Roles();
            city = new City();
        } catch (Exception e) {
            
        }
    }
    
    private String encryptPassword(String planepassword) {
        try {
            MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            md.update(planepassword.getBytes("UTF-8"));
            byte[] passwordDigest = md.digest();
            String encodedPasswordHash = new sun.misc.BASE64Encoder().encode(passwordDigest);
            
            return encodedPasswordHash;
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            
        }
        return "";
    }
    
    public String onFlowProcess(FlowEvent event) {
        
        return event.getNewStep();
    }
    
    private String passGenerator() {
        String dCase = "abcdefghijklmnopqrstuvwxyz";
        String uCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String sChar = "!@#$%^&*";
        String intChar = "0123456789";
        Random r = new Random();
        String pass = "";
        while (pass.length() != 16) {
            int rPick = r.nextInt(4);
            if (rPick == 0) {
                int spot = r.nextInt(25);
                pass += dCase.charAt(spot);
            } else if (rPick == 1) {
                int spot = r.nextInt(25);
                pass += uCase.charAt(spot);
            } else if (rPick == 2) {
                int spot = r.nextInt(7);
                pass += sChar.charAt(spot);
            } else if (rPick == 3) {
                int spot = r.nextInt(9);
                pass += intChar.charAt(spot);
            }
        }
        return pass;
    }
}
