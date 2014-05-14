/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Emailcontacts;
import entityModels.EmailcontactsPK;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import persistClasses.EmailcontactsFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
public class EmailContactsEdit {

    @EJB
    private EmailcontactsFacade cF;
    private Emailcontacts contact = new Emailcontacts(new EmailcontactsPK());
    private List<Emailcontacts> clist = new ArrayList<>();

    @PostConstruct
    public void init() {
        try {
            clist = cF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
        } catch (NullPointerException e) {
            clist = new ArrayList<>();
        }
    }

    public Emailcontacts getContact() {
        return contact;
    }

    public void setContact(Emailcontacts contact) {
        this.contact = contact;
    }

    public List<Emailcontacts> getClist() {
        return clist;
    }

    public void setClist(List<Emailcontacts> clist) {
        this.clist = clist;
    }

    public void save(ActionEvent actionEvent) {
        contact.getEmailcontactsPK().setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
        cF.create(contact);
        FacesMessage msg = new FacesMessage("Successful", "You saved :" + contact.getEmailcontactsPK().getContactname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        contact = new Emailcontacts(new EmailcontactsPK());
    }

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

    public void onEdit(RowEditEvent event) {

        try {

            Emailcontacts test = cF.find(((Emailcontacts) event.getObject()).getEmailcontactsPK());

            test.setEmailaddress(((Emailcontacts) event.getObject()).getEmailaddress());

            cF.edit(test);

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
        FacesMessage msg = new FacesMessage("Editing Cancelled", ((Emailcontacts) event.getObject()).getEmailcontactsPK().getContactname());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteItemDom(Emailcontacts e) {

        cF.remove(e);

        clist.remove(e);

    }
}
