/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.special.model.LoggingModel;
import entityModels.Logging;
import java.io.Serializable;
import java.util.Date;
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
public class LogEdit implements Serializable {

    private LoggingModel loggList;

   

    @EJB
    private LoggingFacade lF;

    @PostConstruct
    public void init() {
        loggList = new LoggingModel(lF.findAll());

    }

    public LoggingModel getLoggList() {
        return loggList;
    }

    

    public void setlF(LoggingFacade lF) {
        this.lF = lF;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Log Selected", "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void delLogList() {
        lF.removeAll();
        lF.edit(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "Warning", " The log has been erased."));
        init();
    }
}
