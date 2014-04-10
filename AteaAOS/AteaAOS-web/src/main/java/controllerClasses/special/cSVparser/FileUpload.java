/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.cSVparser;

import com.google.common.hash.Hashing;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import controllerClasses.special.newProject;
import entityModels.Distributiongroups;
import entityModels.Groups;
import entityModels.Users;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.apache.commons.collections.IteratorUtils;
import org.apache.directory.shared.ldap.exception.LdapException;
import org.apache.directory.shared.ldap.exception.LdapInvalidAttributeValueException;
import org.apache.directory.shared.ldap.ldif.LdapLdifException;
import org.apache.directory.shared.ldap.ldif.LdifEntry;
import org.apache.directory.shared.ldap.ldif.LdifReader;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import persistClasses.DistributiongroupsFacade;
import persistClasses.GroupsFacade;
import persistClasses.UsersFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@SessionScoped
public class FileUpload implements Serializable {

    private static ADSIentity list = new ADSIentity();
    @EJB
    private UsersFacade brukerEJB;
    @EJB
    private GroupsFacade grF;
    @EJB
    private DistributiongroupsFacade dgrF;
    private UploadedFile file;

    private File fil;

    public FileUpload() {

    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if (file != null) {
            FacesMessage msg = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            copyFile(event.getFile().getInputstream(), event.getFile().getFileName());
        } catch (IOException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private synchronized void copyFile(InputStream inputStream, String filename) {
        fil = new File(filename);
        try {
            OutputStream out;

            out = new FileOutputStream(fil);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {

                out.write(bytes, 0, read);

            }

            out.flush();

            out.close();

            System.out.println("New file created!");
            System.out.println(fil.getAbsolutePath());

        } catch (IOException e) {

            System.out.println(e.getMessage());

        } finally {

            try {
                readAndPopulateList();
            } catch (FileNotFoundException | LdapLdifException ex) {
                Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private synchronized void readAndPopulateList() throws FileNotFoundException, LdapLdifException {
        LdifReader reader = new LdifReader();
        List<LdifEntry> entries = reader.parseLdifFile(fil.getAbsolutePath());
        int ep = 0;
        for (LdifEntry entry : entries) {
            ep++;
            if (null != entry.get("cn")) {
                List<String> oc = IteratorUtils.toList(entry.get("objectClass").getAll());

                if (oc.contains("person") && oc.contains("user")) {

                    Users entity = new Users();
                    try {
                        entity.setUsername((entry.get("sAMAccountName").getString() == null) ? "" : entry.get("sAMAccountName").getString());
                    } catch (LdapInvalidAttributeValueException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        entity.setFirstname((entry.get("givenName").getString() == null) ? "" : entry.get("givenName").getString());
                    } catch (LdapInvalidAttributeValueException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        entity.setLastname((entry.get("sn").getString() == null) ? "" : entry.get("sn").getString());
                    } catch (LdapInvalidAttributeValueException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        entity.setTitle((entry.get("title").getString() == null) ? "" : entry.get("title").getString());
                    } catch (LdapInvalidAttributeValueException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    entity.setItcontact("NO");

                    try {
                        entity.setDepartment((entry.get("department").getString() == null) ? "" : entry.get("department").getString());
                    } catch (LdapInvalidAttributeValueException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    entity.setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
                    try {
                        entity.setMobile(Integer.parseInt(entry.get("moblie").getString()));
                    } catch (LdapInvalidAttributeValueException | NumberFormatException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.setMobile(12345);
                    }
                    try {
                        entity.setEmploymentnr(entry.get("employmentNr").getString());
                    } catch (LdapInvalidAttributeValueException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.setEmploymentnr(Integer.toString(ep));
                    }
                    entity.setDn(entry.getDn().getNormName());
                    List<String> emailalias = IteratorUtils.toList(entry.get("proxyAddresses").getAll());
                    String res = "";
                    for (String e : emailalias) {

                        res = (res + ", " + e + " ");
                    }
                    entity.setEmailalias(res);
                    try {
                        entity.setEmail(entry.get("mail").getString());
                    } catch (LdapInvalidAttributeValueException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.setEmploymentnr(Integer.toString(ep));
                    }
                    list.getUsr().add(entity);
                } else if (oc.contains("group")) {
                    try {
                        if (entry.get("groupType").isValid()) {
                            int gr = Integer.parseInt(entry.get("groupType").toString());
                            if (gr > 0) {
                                Distributiongroups dgro = new Distributiongroups();
                                dgro.setDisplayname(entry.get("cn").getString());
                                dgro.setDn(entry.getDn().getNormName());
                                dgro.setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
                                dgro.setEmailalias(entry.get("mail").getString());
                                list.getDgr().add(dgro);
                            } else if (gr < 0) {
                                Groups gro = new Groups();
                                gro.setDescription(entry.get("info").getString());
                                gro.setGroupowner(entry.get("managedBy").getString());
                                gro.setGroupname(entry.get("cn").getString());
                                gro.setFunctions("");
                                gro.setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
                                list.getGr().add(gro);
                            }
                        }
                    } catch (LdapException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

        }
    }

    public synchronized void readAndPersist() {
        for(Groups gr:list.getGr()){
            grF.create(gr);
        }
        for(Users usr:list.getUsr()){
            brukerEJB.create(usr);
        }
        for(Distributiongroups dgr:list.getDgr()){
            dgrF.create(dgr);
        }

    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void deleteItem(Object e) {
        if (e instanceof Users) {
            Users usr = (Users) e;
            list.getUsr().remove(usr);
        } else if (e instanceof Distributiongroups) {
            Distributiongroups dgr = (Distributiongroups) e;
            list.getDgr().remove(dgr);
        } else {
            Groups gr = (Groups) e;
            list.getGr().remove(gr);
        }

    }

    public void emptyList() {
        list.getDgr().clear();
        list.getGr().clear();
        list.getUsr().clear();
        fil.delete();
    }

}
