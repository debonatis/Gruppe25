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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
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

    @Resource(name = "mail/AOSMail")
    private javax.mail.Session mailAOSMail;

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

            user.setPostalcode(city.getPostalcode());
            cF.create(city);
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
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            md.update(planepassword.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);

            return output;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {

        }
        return "";
    }

    @RolesAllowed("admin")
    public void sendInvite() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURL().toString();
        url = url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/";
        try {
            sendMail(user.getEmail(), "AOS user generation (Do not repley)", url);
        } catch (NamingException | MessagingException ex) {
            Logger.getLogger(SiteuserControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendMail(String email, String subject, String body) throws NamingException, javax.mail.MessagingException {
        javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(mailAOSMail);
        message.setSubject(subject);
        message.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(email, false));
        message.setText(body);
        javax.mail.Transport.send(message);
    }
    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();
    }

}
