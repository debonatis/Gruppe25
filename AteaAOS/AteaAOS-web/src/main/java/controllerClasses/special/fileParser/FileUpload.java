/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.fileParser;

import entityModels.Distributiongroups;
import entityModels.DistributiongroupsPK;
import entityModels.Emailcontacts;
import entityModels.EmailcontactsPK;
import entityModels.Groups;
import entityModels.GroupsPK;
import entityModels.Groupusers;
import entityModels.GroupusersPK;
import entityModels.Userdistribution;
import entityModels.UserdistributionPK;
import entityModels.Users;
import entityModels.UsersPK;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.collections.IteratorUtils;
import org.apache.directory.shared.ldap.entry.Value;
import org.apache.directory.shared.ldap.exception.LdapInvalidAttributeValueException;
import org.apache.directory.shared.ldap.exception.LdapInvalidDnException;
import org.apache.directory.shared.ldap.ldif.LdapLdifException;
import org.apache.directory.shared.ldap.ldif.LdifEntry;
import org.apache.directory.shared.ldap.ldif.LdifReader;
import org.apache.directory.shared.ldap.name.DN;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import persistClasses.DistributiongroupsFacade;
import persistClasses.EmailcontactsFacade;
import persistClasses.GroupsFacade;
import persistClasses.GroupusersFacade;
import persistClasses.UserdistributionFacade;
import persistClasses.UsersFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
public class FileUpload implements Serializable {
    
    private ADSIentity list = new ADSIentity();
    @EJB
    private UsersFacade brukerEJB;
    @EJB
    private GroupsFacade grF;
    @EJB
    private DistributiongroupsFacade dgrF;
    @EJB
    private UserdistributionFacade udF;
    @EJB
    private GroupusersFacade guF;
    @EJB
    private EmailcontactsFacade ecF;
    private UploadedFile file;
    private boolean checkSave = false;
    private File fil;
    private List<Members> members = new ArrayList<>();
    
    public FileUpload() {
        
    }
    
    public List<Members> getMembers() {
        return members;
    }
    
    public void setMembers(List<Members> members) {
        this.members = members;
    }
    
    public ADSIentity getList() {
        return list;
    }
    
    public void setList(ADSIentity list) {
        this.list = list;
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
    
    private <T> List<T> fraIteratorTilListe(Iterator<T> iter) {
        List<T> liste = new ArrayList<T>();
        while (iter.hasNext()) {
            liste.add(iter.next());
        }
        return liste;
    }
    
    private synchronized void readAndPopulateList() throws FileNotFoundException, LdapLdifException {
        LdifReader reader = new LdifReader();
        List<LdifEntry> entries = reader.parseLdifFile(fil.getAbsolutePath());
        List<String> ocStringList = new ArrayList<>();
        int ep = 0;
        for (LdifEntry entry : entries) {
            
            if (null != entry.get("cn")) {
                List<Value<?>> oc = fraIteratorTilListe(entry.get("objectClass").getAll());
                for (Value<?> s : oc) {
                    ocStringList.add(s.getString());
                }
                if(ocStringList.contains("person") && !ocStringList.contains("user") && !ocStringList.contains("computer") &&ocStringList.contains("contact")){
                    Emailcontacts entity = new Emailcontacts(new EmailcontactsPK());
                    try {
                        entity.getEmailcontactsPK().setContactname((entry.get("cn").getString() == null) ? "NOT SET" : entry.get("cn").getString());
                    } catch (LdapInvalidAttributeValueException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.getEmailcontactsPK().setContactname("NOT SET");
                    }
                    try {
                        entity.setEmailaddress((entry.get("mail").getString() == null) ? "NOT SET" : entry.get("mail").getString());
                    } catch (LdapInvalidAttributeValueException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.setEmailaddress("NOT SET");
                    }
                    entity.getEmailcontactsPK().setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
                    list.getEc().add(entity);
                    
                }else if (ocStringList.contains("person") && ocStringList.contains("user") && !ocStringList.contains("computer") &&!ocStringList.contains("contact")) {
                    ep++;
                    Users entity = new Users(new UsersPK());
                    try {
                        entity.getUsersPK().setUsername((entry.get("sAMAccountName").getString() == null) ? "NOT SET" : entry.get("sAMAccountName").getString());
                    } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        entity.setFirstname((entry.get("givenName").getString() == null) ? "NOT SET" : entry.get("givenName").getString());
                    } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.setFirstname("NOT SET");
                    }
                    try {
                        entity.setLastname((entry.get("sn").getString() == null) ? "NOT SET" : entry.get("sn").getString());
                    } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.setLastname("NOT SET");
                    }
                    try {
                        entity.setTitle((entry.get("title").getString() == null) ? "NOT SET" : entry.get("title").getString());
                    } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.setTitle("NOT SET");
                    }
                    entity.setItcontact("NO");
                    
                    try {
                        entity.setDepartment((entry.get("department").getString() == null) ? "NOT SET" : entry.get("department").getString());
                    } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.setDepartment("NOT SET");
                    }
                    entity.getUsersPK().setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
                    try {
                        entity.setMobile(Integer.parseInt(entry.get("mobile").getString()));
                    } catch (LdapInvalidAttributeValueException | NullPointerException | NumberFormatException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.setMobile(12345);
                    }
                    try {
                        entity.setEmploymentnr(entry.get("employmentNr").getString());
                    } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.setEmploymentnr(Integer.toString(ep));
                    }
                    try {
                        entity.setDn(entry.getDn().getNormName());
                        List<Value<?>> emailalias = IteratorUtils.toList(entry.get("proxyAddresses").getAll());
                        String res = "";
                        for (Value e : emailalias) {
                            
                            res = (res + ", " + e.getString() + " ");
                        }
                        entity.setEmailalias(res);
                    } catch (NullPointerException e) {
                        entity.setEmailalias("NOT SET");
                    }
                    
                    try {
                        entity.setEmail(entry.get("mail").getString());
                    } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                        Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        entity.setEmail("NOT SET");
                       
                    }
                    list.getUsr().add(entity);
                } else if (ocStringList.contains("group")) {
                    
                    int gr = Integer.parseInt(entry.get("grouptype").get().getString());
                    if (gr > 0) {
                        Distributiongroups dgro = new Distributiongroups(new DistributiongroupsPK());
                        try {
                            dgro.getDistributiongroupsPK().setDisplayname(entry.get("cn").getString());
                        } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        dgro.setDn(entry.getDn().getNormName());
                        dgro.getDistributiongroupsPK().setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
                        try {
                            dgro.setEmailalias(entry.get("mail").getString());
                        } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                            dgro.setEmailalias("NOT SET");
                        }
                        try {
                            dgro.setExternalemail(entry.get("msExchRequireAuthToSentTo").getString());
                        } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                            
                            dgro.setExternalemail("NO");
                            
                        }
                        if ((!dgro.getExternalemail().isEmpty()) && dgro.getExternalemail().equalsIgnoreCase("true")) {
                            dgro.setExternalemail("YES");
                        }
                        
                        list.getDgr().add(dgro);
                        try {
                            
                            List<Value<?>> memberss = fraIteratorTilListe(entry.get("member").getAll());
                            List<DN> mem = new ArrayList<>();
                            
                            for (Value<?> s : memberss) {
                                try {
                                    mem.add(new DN(s.getString()));
                                } catch (LdapInvalidDnException ex) {
                                    Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                            getMembers().add(new Members(mem, dgro.getDistributiongroupsPK().getDisplayname(), false));
                        } catch (NullPointerException ex) {
                            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (gr < 0) {
                        Groups gro = new Groups(new GroupsPK());
                        try {
                            gro.setDescription(entry.get("description").getString());
                        } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                            gro.setDescription("NOT SET");
                        }
                        try {
                            gro.setGroupowner(entry.get("managedBy").getString());
                        } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                            gro.setGroupowner("NOT SET");
                        }
                        try {
                            gro.getGroupsPK().setGroupname(entry.get("cn").getString());
                        } catch (LdapInvalidAttributeValueException | NullPointerException ex) {
                            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        gro.setDn(entry.getDn().toString());
                        gro.setFunctions("NOT SET");
                        gro.getGroupsPK().setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
                        list.getGr().add(gro);
                        try {
                            List<Value<?>> members = fraIteratorTilListe(entry.get("member").getAll());
                            List<DN> mem = new ArrayList<>();
                            
                            for (Value<?> s : members) {
                                try {
                                    mem.add(new DN(s.getString()));
                                } catch (LdapInvalidDnException ex) {
                                    Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                            }
                            getMembers().add(new Members(mem, gro.getGroupsPK().getGroupname(), true));
                        } catch (NullPointerException ex) {
                            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    
                }
                
            }
            
        }
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void readAndPersist() {
        for (Groups gr : list.getGr()) {
            try {
                grF.create(gr);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage("Unsuccesful", (gr.getGroupsPK().getGroupname()) + " is probably already made.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            
        }
        
        for (Distributiongroups dgr : list.getDgr()) {
            try {
                dgrF.create(dgr);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage("Unsuccesful", (dgr.getDistributiongroupsPK().getDisplayname()) + " is probably already made.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        for (Users usr : list.getUsr()) {
            try {
                brukerEJB.create(usr);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage("Unsuccesful", (usr.getUsersPK().getUsername()) + " is probably already made.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            
        }
        for(Emailcontacts ec :list.getEc()){
            try {
                ecF.create(ec);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage("Unsuccesful", ec.getEmailcontactsPK().getContactname() + " is probably already made.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
        checkSave = true;
        
    }
    
    public void saveGroupMemberships() {
        if (checkSave) {
            for (Members mem : members) {
                if (mem.isSecgr()) {
                    for (DN s : mem.getMembers()) {
                        List<Users> ur = brukerEJB.findDN(s.getNormName());
                        
                        try {
                            guF.create(new Groupusers( new GroupusersPK(ur.iterator().next().getUsersPK().getUsername(), mem.getName(),(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))));
                        } catch (Exception e) {
                            FacesMessage msg = new FacesMessage("Unsuccesful", "Binding is probably already made.");
                            FacesContext.getCurrentInstance().addMessage(null, msg);
                        }
                    }
                } else if (!mem.isSecgr()) {
                    for (DN s : mem.getMembers()) {
                        List<Users> ur = brukerEJB.findDN(s.getNormName());
                        try {
                            udF.create(new Userdistribution(new UserdistributionPK(ur.iterator().next().getUsersPK().getUsername(), mem.getName(),(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))));
                        } catch (Exception e) {
                            FacesMessage msg = new FacesMessage("Unsuccesful", "Binding is probably already made.");
                            FacesContext.getCurrentInstance().addMessage(null, msg);
                        }
                    }
                }
            }
            members.clear();
            checkSave = false;
            emptyList();
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
    
    public void deleteItemM2(Object e, Object f) {
        if (e instanceof Members) {
            Members mem = (Members) e;
            mem = members.get(members.indexOf(mem));
            mem.getMembers().remove((DN) f);
        }
        
    }
    
    public void deleteItemM(Object e) {
        if (e instanceof Members) {
            Members mem = (Members) e;
            members.remove(mem);
        }
        
    }
    
    public void emptyList() {
        list.getDgr().clear();
        list.getGr().clear();
        list.getUsr().clear();
        list.getEc().clear();
        fil.delete();
    }
    
}
