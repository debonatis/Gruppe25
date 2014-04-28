/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Domains;
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
import persistClasses.DomainsFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
public class DomainEdit {

    @EJB
    private DomainsFacade dF;
    private Domains domain = new Domains();
    private List<Domains> dlist = new ArrayList<>();

    @PostConstruct
    public void init() {
        dlist = dF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
    }

    public Domains getDomain() {
        return domain;
    }

    public void setDomain(Domains domain) {
        this.domain = domain;
    }

    public List<Domains> getDlist() {
        return dlist;
    }

    public void setDlist(List<Domains> dlist) {
        this.dlist = dlist;
    }
    

    public void save(ActionEvent actionEvent) {
        domain.setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
        dF.create(domain);
        FacesMessage msg = new FacesMessage("Successful", "You saved :" + domain.getDomainname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        domain = new Domains();
    }

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

    public void onEdit(RowEditEvent event) {

        try {

            Domains test = dF.find(((Domains) event.getObject()).getDomainname());

            test.setDomainfunction(((Domains) event.getObject()).getDomainfunction());
            test.setIppointer(((Domains) event.getObject()).getIppointer());
            test.setRegistrar(((Domains) event.getObject()).getRegistrar());
            test.setRegistrarcontact(((Domains) event.getObject()).getRegistrarcontact());

            dF.edit(test);

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
        FacesMessage msg = new FacesMessage("Editing Cancelled", ((Domains) event.getObject()).getDomainname());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteItemDom(Domains e) {

        dF.remove(e);

        dlist.remove(e);

    }

}
