/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.special.model.LoggingModel;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import persistClasses.LoggingFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
public class LogEdit {

    private LoggingModel loggList;

    private Object selectedLog;

    
    @EJB
    private LoggingFacade lF;

    @PostConstruct
    public void init() {
        loggList = new LoggingModel(lF.findAll());
       
    }

    public LoggingModel getLoggList() {
        return loggList;
    }

    public Object getSelectedLog() {
        return selectedLog;
    }

    public void setSelectedLog(Object selectedLog) {
        this.selectedLog = selectedLog;
    }

    

    public void setlF(LoggingFacade lF) {
        this.lF = lF;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Log Selected", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
