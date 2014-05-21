/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.security;

import entityModels.City;
import entityModels.Siteuser;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import persistClasses.CityFacade;
import persistClasses.SiteuserFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
public class ProfileHandler {

    @EJB
    private SiteuserFacade suF;
    @EJB
    private CityFacade cF;

    private Siteuser user = new Siteuser();
    
    private String city = "";
    private String password ="";

    @PostConstruct
    public void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

        this.user = suF.find(username);
        for(City c:cF.findAll()){
            if(c.getPostalcode().equalsIgnoreCase(user.getPostalcode())){
                city = c.getCity();
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    

    public Siteuser getUser() {
        return user;
    }

    public void setUser(Siteuser user) {
        this.user = user;
    }
    

    public void saveSU() {
        if(!getPassword().equalsIgnoreCase("")){
            user.setPassword(encryptPassword(password));
        }
        cF.create(new City(user.getPostalcode(), city));
        suF.edit(user);
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("User edited sucsessfully!");
        msg.setDetail(" ");
    }
    
    public void delSU(){
        suF.edit(user);
        suF.remove(user);
        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("User edited sucsessfully!");
        msg.setDetail(" ");
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
}
