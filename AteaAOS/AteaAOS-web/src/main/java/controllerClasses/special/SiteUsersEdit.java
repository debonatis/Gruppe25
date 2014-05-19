/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import controllerClasses.special.model.ProjectUsersModel;
import controllerClasses.special.model.SiteuserListModel;
import entityModels.Logging;
import entityModels.Projects;
import entityModels.Prositeusers;
import entityModels.Siteuser;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import persistClasses.DistributiongroupsFacade;
import persistClasses.GroupsFacade;
import persistClasses.GroupusersFacade;
import persistClasses.LoggingFacade;
import persistClasses.ProjectsFacade;
import persistClasses.PrositeusersFacade;
import persistClasses.SiteuserFacade;
import persistClasses.UserdistributionFacade;
import persistClasses.UsersFacade;

/**
 *
 * @author Martin
 */
@ManagedBean(name = "superUsers")
@ViewScoped

public class SiteUsersEdit implements Serializable {

    @EJB
    private SiteuserFacade siteuserEJB;
    @EJB
    private ProjectsFacade projectsFacade;
    @EJB
    private PrositeusersFacade prositeusersFacade;
    @EJB
    private LoggingFacade lF;
    @EJB
    private DistributiongroupsFacade dgF;
    @EJB
    private GroupsFacade gF;
    @EJB
    private UserdistributionFacade dgMMF;
    @EJB
    private GroupusersFacade sgMMF;
    @EJB
    private UsersFacade uF;
    private SiteuserListModel selectList = new SiteuserListModel();
    private Siteuser selected = new Siteuser();
    private List<Siteuser> projectListT = new ArrayList<>();
    private DualListModel<Projects> listPro;
    private DualListModel<Siteuser> listSU;
    private List<ProjectUsersModel> proUsers = new ArrayList<>();
    private String[] projectTypes = {"V4", "Single Forrest, Single Domain", "Single Forrest, Multiple Domain"};

    public Siteuser getSelected() {
        return selected;
    }

    public void setSelected(Siteuser selected) {

        this.selected = selected;

    }

    public List<ProjectUsersModel> getProUsers() {
        return proUsers;
    }

    public void setProUsers(List<ProjectUsersModel> proUsers) {
        this.proUsers = proUsers;
    }

    public String[] getProjectTypes() {
        return projectTypes;
    }

    public void setProjectTypes(String[] projectTypes) {
        this.projectTypes = projectTypes;
    }

    @PostConstruct
    public void init() {

        projectListT = siteuserEJB.findAll();

        selectList = new SiteuserListModel(projectListT);
        listPro = new DualListModel<>(projectsFacade.findAll(), new ArrayList<Projects>());
        listSU = new DualListModel<>(siteuserEJB.findAll(), new ArrayList<Siteuser>());
        proUsers.clear();
        List<Siteuser> roger;
        for (Projects g : projectsFacade.findAll()) {
            roger = new ArrayList<>();
            for (Prositeusers gu : prositeusersFacade.findAll()) {
                if (gu.getPrositeusersPK().getProjectid().equalsIgnoreCase(g.getProjectid())) {
                    roger.add(siteuserEJB.find(gu.getPrositeusersPK().getUsername()));

                }
            }
            proUsers.add(new ProjectUsersModel(g, roger));
        }
    }

    public DualListModel<Projects> getListPro() {
        return listPro;
    }

    public void setListPro(DualListModel<Projects> listPro) {
        this.listPro = listPro;
    }

    public DualListModel<Siteuser> getListSU() {
        return listSU;
    }

    public void setListSU(DualListModel<Siteuser> listSU) {
        this.listSU = listSU;
    }

    public SiteuserListModel getSelectList() {
        return selectList;
    }

    public void setSelectList(SiteuserListModel selectList) {
        this.selectList = selectList;
    }

    public void deleteItemUsr(Siteuser e) {

        siteuserEJB.remove(e);
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", e.getUsername() + " has been deleted."));
        projectListT.remove(e);

    }

    public void onEdit(RowEditEvent event) {

        try {

            Siteuser test = getFacade().find(((Siteuser) event.getObject()).getUsername());

            test.setUsername(((Siteuser) event.getObject()).getUsername());
            test.setPassword(((Siteuser) event.getObject()).getPassword());
            test.setFirstname(((Siteuser) event.getObject()).getFirstname());
            siteuserEJB.edit(test);
            lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", test.getUsername() + " has been edited."));

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Siteuser edited sucsessfully!");
            msg.setDetail(" ");

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Siteuser not edited!");
            msg.setDetail("Maybe faulty inputs?");

            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Editing Cancelled", ((Siteuser) event.getObject()).getUsername());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private SiteuserFacade getFacade() {
        return siteuserEJB;
    }

    public void onTransferSU(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        if (event.isAdd()) {

            for (Object item : event.getItems()) {
                Siteuser u = (Siteuser) item;
                builder.append(u.getUsername()).append("<br />");

            }

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else if (!event.isAdd()) {
            for (Object item : event.getItems()) {
                Siteuser u = (Siteuser) item;
                builder.append(u.getUsername()).append("<br />");

            }
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Removed");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onTransferP(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        if (event.isAdd()) {

            for (Object item : event.getItems()) {
                Projects pro = (Projects) item;
                builder.append(pro.getName()).append("<br />");

            }

            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Transferred");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else if (!event.isAdd()) {
            for (Object item : event.getItems()) {
                Projects pro = (Projects) item;
                builder.append(pro.getName()).append("<br />");

            }
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Items Removed");
            msg.setDetail(builder.toString());

            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void saveSUP() {

        List<Siteuser> su = listSU.getTarget();
        List<Projects> pro = listPro.getTarget();

        for (Projects p : pro) {
            for (Siteuser s : su) {
                prositeusersFacade.create(new Prositeusers(p.getProjectid(), s.getUsername()));
                lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", s.getUsername() + " has been added to: " + p.getName() + ""));
            }
        }

    }

    public String onFlowProcess(FlowEvent event) {

        return event.getNewStep();

    }

    public void onEditPro(RowEditEvent event) {

        ProjectUsersModel e = (ProjectUsersModel) event.getObject();

        Projects pro = projectsFacade.find(e.getPro().getProjectid());
        pro.setDescription(e.getPro().getDescription());
        pro.setName(e.getPro().getName());
        pro.setProjecttype(e.getPro().getProjecttype());
        projectsFacade.edit(pro);
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", pro.getName() + " has been edited."));

        init();
        FacesMessage msg = new FacesMessage("Project Edited", ((ProjectUsersModel) event.getObject()).getPro().getName());

        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onCancelPro(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Project Edit Cancelled", ((ProjectUsersModel) event.getObject()).getPro().getName());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteItemPro(ProjectUsersModel e) {

        for (Siteuser u : e.getUserList()) {
            prositeusersFacade.remove(new Prositeusers(e.getPro().getProjectid(), u.getUsername()));
            lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", u.getUsername() + " has been removed: " + e.getPro().getName() + ""));
        }
        Projects pro = projectsFacade.find(e.getPro().getProjectid());
        projectsFacade.remove(pro);
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", e.getPro().getName() + " has been deleted."));
        proUsers.remove(e);

    }

    public void deleteItemProUser(ProjectUsersModel g, Siteuser e) {

        prositeusersFacade.remove(new Prositeusers(g.getPro().getProjectid(), e.getUsername()));
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", e.getUsername() + " has been removed: " + g.getPro().getName() + ""));
        int i = proUsers.indexOf(g);
        proUsers.get(i).getUserList().remove(e);

    }
}
