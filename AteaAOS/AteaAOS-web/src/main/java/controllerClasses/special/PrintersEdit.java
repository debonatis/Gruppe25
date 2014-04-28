/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Printers;
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
import persistClasses.PrintersFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
public class PrintersEdit {

    @EJB
    private PrintersFacade pF;
    private Printers printer = new Printers();
    private List<Printers> plist = new ArrayList<>();

    @PostConstruct
    public void init() {
        plist = pF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
    }

    public Printers getPrinter() {
        return printer;
    }

    public void setPrinter(Printers printer) {
        this.printer = printer;
    }

    public List<Printers> getPlist() {
        return plist;
    }

    public void setPlist(List<Printers> pList) {
        this.plist = pList;
    }

    public void save(ActionEvent actionEvent) {
        printer.setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
        pF.create(printer);
        FacesMessage msg = new FacesMessage("Successful", "You saved :" + printer.getPrintername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        printer = new Printers();
    }

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

    public void onEdit(RowEditEvent event) {

        try {

            Printers test = pF.find(((Printers) event.getObject()).getPrintername());

            test.setAccessories(((Printers) event.getObject()).getAccessories());
            test.setDriver(((Printers) event.getObject()).getDriver());
            test.setIpaddr(((Printers) event.getObject()).getIpaddr());
            test.setLocation(((Printers) event.getObject()).getLocation());
            test.setModel(((Printers) event.getObject()).getModel());
            test.setScantomail(((Printers) event.getObject()).getScantomail());
            pF.edit(test);

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
        FacesMessage msg = new FacesMessage("Editing Cancelled", ((Printers) event.getObject()).getPrintername());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteItemPri(Printers e) {

        pF.remove(e);

        plist.remove(e);

    }
}
