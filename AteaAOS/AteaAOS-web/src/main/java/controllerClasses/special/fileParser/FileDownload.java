/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.fileParser;

import com.unboundid.ldap.sdk.Attribute;
import com.unboundid.ldap.sdk.DN;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldif.LDIFWriter;
import entityModels.Distributiongroups;
import entityModels.Groups;
import entityModels.Groupusers;
import entityModels.Userdistribution;
import entityModels.Users;
import entityModels.UsersPK;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import persistClasses.DistributiongroupsFacade;
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
public class FileDownload {

    private StreamedContent file;
    private boolean onlyUsers = false;
    private boolean onlyGroups = false;
    private boolean csv = false;
    private boolean okDownload = false;
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
    private String Projectid = "";
    private List<DistUsersModel> duList = new ArrayList<>();
    private List<GroupUsersModel> guList = new ArrayList<>();
    private String dc0;
    private String dc1;
    private String dc2;

    public FileDownload() {
        Projectid = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID");
    }

    public String getDc0() {
        return dc0;
    }

    public void setDc0(String dc0) {
        this.dc0 = dc0;
    }

    public String getDc1() {
        return dc1;
    }

    public void setDc1(String dc1) {
        this.dc1 = dc1;
    }

    public String getDc2() {
        return dc2;
    }

    public void setDc2(String dc2) {
        this.dc2 = dc2;
    }

    private String getProjectid() {
        return Projectid;
    }

    public void setDown(boolean e) {
        this.okDownload = e;
    }

    public StreamedContent getFile() {
        return file;
    }

    public boolean isOkDownload() {
        return okDownload;
    }

    public void setOkDownload(boolean okDownload) {
        this.okDownload = okDownload;
    }

    public boolean isOnlyUsers() {
        return onlyUsers;
    }

    public void setOnlyUsers(boolean onlyUsers) {
        this.onlyUsers = onlyUsers;
    }

    public boolean isOnlyGroups() {
        return onlyGroups;
    }

    public void setOnlyGroups(boolean onlyGroups) {
        this.onlyGroups = onlyGroups;
    }

    public boolean isCsv() {
        return csv;
    }

    public void setCsv(boolean csv) {
        this.csv = csv;
    }

    public void addMessage() {
        String summary = onlyUsers ? "Users Checked" : "Users Unchecked";
        String summary1 = onlyGroups ? "Groups Checked" : "Groups Unchecked";
        String summary2 = csv ? "CSV Checked" : "CSV Unchecked";

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("" + summary + "<br />" + summary1 + "<br />" + summary2 + ""));
    }

    public synchronized void generateFile() {

        if (csv) {
            try {
                File k = new File("/secure/FILES/CSVExport.ldf");
                k.delete();
                k.createNewFile();
                InputStream stream = new InputStream() {
                    
                    @Override
                    public int read() throws IOException {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                };
                try {
                    stream = new FileInputStream(k);
                    file = new DefaultStreamedContent(stream, "system/ldf", k.getName());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if(!csv) {
            try {
                File k = new File("/secure/FILES/LDFExport.ldf");
                k.delete();
                k.createNewFile();
                InputStream stream = new InputStream() {
                    
                    @Override
                    public int read() throws IOException {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                };
                try {
                    stream = new FileInputStream(k);
                    file = new DefaultStreamedContent(stream, "system/ldf", k.getName());
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (onlyUsers) {
                    try {
                        populateLists();
                        List<Users> usrL = uF.findAllPro(Projectid);
                        List<Entry> entryList = new ArrayList<>();
                        List<String> members = new ArrayList<>();
                        
                        LDIFWriter wr = getLDFWriter(k);
                        Entry entry;
                        DN dn;
                        try {
                            dn = new DN("ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                            entry = new Entry(dn);
                            entry.setAttribute(new Attribute("changeType:", "add"));
                            entry.addAttribute(new Attribute("objectClass:", "top", "organizationalUnit"));
                            
                            entry.setAttribute(new Attribute("description:", "Here is your imported resources!"));
                            try {
                                wr.writeEntry(entry);
                            } catch (IOException ex) {
                                Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (LDAPException ex) {
                            Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        for (Users usr : usrL) {
                            members.clear();
                            try {
                                dn = new DN("cn=" + usr.getFirstname() + " " + usr.getLastname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                entry = new Entry(dn);
                                entry.setAttribute(new Attribute("changeType:", "add"));
                                entry.addAttribute(new Attribute("objectClass:", "top", "organizationalPerson", "person", "user"));
                                entry.addAttribute(new Attribute("cn:", "" + usr.getFirstname() + " " + usr.getLastname() + ""));
                                entry.addAttribute(new Attribute("sn:", usr.getLastname()));
                                entry.addAttribute(new Attribute("givenName:", usr.getFirstname()));
                                entry.addAttribute(new Attribute("distinguishedName:", "cn=" + usr.getFirstname() + " " + usr.getLastname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + ""));
                                for (Groupusers gu : sgMMF.findAllPro(Projectid)) {
                                    if (gu.getGroupusersPK().getUsername().equalsIgnoreCase(usr.getUsersPK().getUsername())) {
                                        members.add("cn=" + gu.getGroupusersPK().getUsergroupname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                    }
                                }
                                entry.addAttribute(new Attribute("memberOf:", members));
                                entry.addAttribute(new Attribute("sAMAccountName:", usr.getUsersPK().getUsername()));
                                
                                entry.addAttribute(new Attribute("mail:", usr.getEmail()));
                                entry.addAttribute(new Attribute("mobile:", Integer.toString(usr.getMobile())));
                                wr.writeEntry(entry);
                            } catch (LDAPException ex) {
                                Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        }
                        wr.flush();
                        wr.close();
                        members.clear();
                    } catch (IOException ex) {
                        Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                if (onlyGroups) {
                    try {
                        populateLists();
                        List<Users> usrL = uF.findAllPro(Projectid);
                        List<Entry> entryList = new ArrayList<>();
                        List<String> members = new ArrayList<>();
                        LDIFWriter wr = getLDFWriter(k);
                        Entry entry;
                        DN dn;
                        try {
                            dn = new DN("ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                            entry = new Entry(dn);
                            entry.setAttribute(new Attribute("changeType:", "add"));
                            entry.addAttribute(new Attribute("objectClass:", "top", "organizationalUnit"));
                            
                            entry.setAttribute(new Attribute("description:", "Here is your imported resources!"));
                            try {
                                wr.writeEntry(entry);
                            } catch (IOException ex) {
                                Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } catch (LDAPException ex) {
                            Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        for (DistUsersModel du : duList) {
                            members.clear();
                            try {
                                dn = new DN("cn=" + du.getDist().getDistributiongroupsPK().getDisplayname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                entry = new Entry(dn);
                                entry.setAttribute(new Attribute("changeType:", "add"));
                                entry.addAttribute(new Attribute("objectClass:", "top", "group"));
                                entry.addAttribute(new Attribute("cn:", du.getDist().getDistributiongroupsPK().getDisplayname()));
                                entry.addAttribute(new Attribute("name:", du.getDist().getDistributiongroupsPK().getDisplayname()));
                                entry.addAttribute(new Attribute("groupType:", "2"));
                                entry.addAttribute(new Attribute("distinguishedName:", "cn=" + du.getDist().getDistributiongroupsPK().getDisplayname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + ""));
                                for (Users usr : du.getUserList()) {
                                    
                                    members.add("cn=" + usr.getFirstname() + " " + usr.getLastname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                }
                                
                                entry.addAttribute(new Attribute("member:", members));
                                entry.addAttribute(new Attribute("sAMAccountName:", du.getDist().getDistributiongroupsPK().getDisplayname()));
                                
                                entry.addAttribute(new Attribute("mail:", du.getDist().getEmailalias()));
                                if (du.getDist().getExternalemail().equalsIgnoreCase("YES")) {
                                    entry.addAttribute(new Attribute("msExchRequireAuthToSentTo:", "true"));
                                    
                                }
                                wr.writeEntry(entry);
                            } catch (LDAPException ex) {
                                Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        for (GroupUsersModel gu : guList) {
                            members.clear();
                            try {
                                dn = new DN("cn=" + gu.getGroup().getGroupsPK().getGroupname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                entry = new Entry(dn);
                                entry.setAttribute(new Attribute("changeType:", "add"));
                                entry.addAttribute(new Attribute("objectClass:", "top", "group"));
                                entry.addAttribute(new Attribute("cn:", gu.getGroup().getGroupsPK().getGroupname()));
                                entry.addAttribute(new Attribute("name:", gu.getGroup().getGroupsPK().getGroupname()));
                                entry.addAttribute(new Attribute("groupType:", "-2147483646"));
                                entry.addAttribute(new Attribute("distinguishedName:", "cn=" + gu.getGroup().getGroupsPK().getGroupname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + ""));
                                for (Users usr : gu.getUserList()) {
                                    
                                    members.add("cn=" + usr.getFirstname() + " " + usr.getLastname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                }
                                
                                entry.addAttribute(new Attribute("member:", members));
                                entry.addAttribute(new Attribute("sAMAccountName:", gu.getGroup().getGroupsPK().getGroupname()));
                                
                                entry.addAttribute(new Attribute("description:", gu.getGroup().getFunctions()));
                                entry.addAttribute(new Attribute("managedBy:", gu.getGroup().getGroupowner()));
                                wr.writeEntry(entry);
                            } catch (LDAPException ex) {
                                Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        wr.flush();
                        wr.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                if (!onlyGroups && !onlyUsers) {
                    populateLists();
                    List<Users> usrL = uF.findAllPro(Projectid);
                    List<Entry> entryList = new ArrayList<>();
                    List<String> members = new ArrayList<>();
                    try {
                        LDIFWriter wr = getLDFWriter(k);
                        Entry entry;
                        DN dn;
                        try {
                            dn = new DN("ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                            entry = new Entry(dn);
                            entry.setAttribute(new Attribute("changeType:", "add"));
                            entry.addAttribute(new Attribute("objectClass:", "top", "organizationalUnit"));
                            
                            entry.setAttribute(new Attribute("description:", "Here is your imported resources!"));
                            wr.writeEntry(entry);
                        } catch (LDAPException ex) {
                            Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        for (Users usr : usrL) {
                            members.clear();
                            try {
                                dn = new DN("cn=" + usr.getFirstname() + " " + usr.getLastname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                entry = new Entry(dn);
                                entry.setAttribute(new Attribute("changeType:", "add"));
                                entry.addAttribute(new Attribute("objectClass:", "top", "organizationalPerson", "person", "user"));
                                entry.addAttribute(new Attribute("cn:", "" + usr.getFirstname() + " " + usr.getLastname() + ""));
                                entry.addAttribute(new Attribute("sn:", usr.getLastname()));
                                entry.addAttribute(new Attribute("givenName:", usr.getFirstname()));
                                entry.addAttribute(new Attribute("distinguishedName:", "cn=" + usr.getFirstname() + " " + usr.getLastname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + ""));
                                for (Groupusers gu : sgMMF.findAllPro(Projectid)) {
                                    if (gu.getGroupusersPK().getUsername().equalsIgnoreCase(usr.getUsersPK().getUsername())) {
                                        members.add("cn=" + gu.getGroupusersPK().getUsergroupname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                    }
                                }
                                entry.addAttribute(new Attribute("memberOf:", members));
                                entry.addAttribute(new Attribute("sAMAccountName:", usr.getUsersPK().getUsername()));
                                
                                entry.addAttribute(new Attribute("mail:", usr.getEmail()));
                                entry.addAttribute(new Attribute("mobile:", Integer.toString(usr.getMobile())));
                                wr.writeEntry(entry);
                            } catch (LDAPException ex) {
                                Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        members.clear();
                        
                        for (DistUsersModel du : duList) {
                            members.clear();
                            try {
                                dn = new DN("cn=" + du.getDist().getDistributiongroupsPK().getDisplayname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                entry = new Entry(dn);
                                entry.setAttribute(new Attribute("changeType:", "add"));
                                entry.addAttribute(new Attribute("objectClass:", "top", "group"));
                                entry.addAttribute(new Attribute("cn:", du.getDist().getDistributiongroupsPK().getDisplayname()));
                                entry.addAttribute(new Attribute("name:", du.getDist().getDistributiongroupsPK().getDisplayname()));
                                entry.addAttribute(new Attribute("groupType:", "2"));
                                entry.addAttribute(new Attribute("distinguishedName:", "cn=" + du.getDist().getDistributiongroupsPK().getDisplayname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + ""));
                                for (Users usr : du.getUserList()) {
                                    
                                    members.add("cn=" + usr.getFirstname() + " " + usr.getLastname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                }
                                
                                entry.addAttribute(new Attribute("member:", members));
                                entry.addAttribute(new Attribute("sAMAccountName:", du.getDist().getDistributiongroupsPK().getDisplayname()));
                                
                                entry.addAttribute(new Attribute("mail:", du.getDist().getEmailalias()));
                                if (du.getDist().getExternalemail().equalsIgnoreCase("YES")) {
                                    entry.addAttribute(new Attribute("msExchRequireAuthToSentTo:", "true"));
                                    
                                }
                                wr.writeEntry(entry);
                            } catch (LDAPException ex) {
                                Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        for (GroupUsersModel gu : guList) {
                            members.clear();
                            try {
                                dn = new DN("cn=" + gu.getGroup().getGroupsPK().getGroupname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                entry = new Entry(dn);
                                entry.setAttribute(new Attribute("changeType:", "add"));
                                entry.addAttribute(new Attribute("objectClass:", "top", "group"));
                                entry.addAttribute(new Attribute("cn:", gu.getGroup().getGroupsPK().getGroupname()));
                                entry.addAttribute(new Attribute("name:", gu.getGroup().getGroupsPK().getGroupname()));
                                entry.addAttribute(new Attribute("groupType:", "-2147483646"));
                                entry.addAttribute(new Attribute("distinguishedName:", "cn=" + gu.getGroup().getGroupsPK().getGroupname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + ""));
                                for (Users usr : gu.getUserList()) {
                                    
                                    members.add("cn=" + usr.getFirstname() + " " + usr.getLastname() + ",ou=import,dc=" + dc2 + ",dc=" + dc1 + ",dc=" + dc0 + "");
                                }
                                
                                entry.addAttribute(new Attribute("member:", members));
                                entry.addAttribute(new Attribute("sAMAccountName:", gu.getGroup().getGroupsPK().getGroupname()));
                                
                                entry.addAttribute(new Attribute("description:", gu.getGroup().getFunctions()));
                                entry.addAttribute(new Attribute("managedBy:", gu.getGroup().getGroupowner()));
                                wr.writeEntry(entry);
                            } catch (LDAPException ex) {
                                Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        wr.flush();
                        wr.close();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(FileDownload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        okDownload=true;

    }

    private LDIFWriter getLDFWriter(File k) throws IOException {

        LDIFWriter wr = new LDIFWriter(k.getAbsoluteFile());
        return wr;

    }

    private void populateLists() {
        duList.clear();
        guList.clear();
        List<Users> toadd = new ArrayList<>();
        for (Distributiongroups d : dgF.findAllPro(getProjectid())) {
            toadd.clear();
            for (Userdistribution ud : dgMMF.findAllPro(Projectid)) {
                if (ud.getUserdistributionPK().getDisplayname().equalsIgnoreCase(d.getDistributiongroupsPK().getDisplayname())) {
                    toadd.add(uF.find(new UsersPK(ud.getUserdistributionPK().getUsername(), Projectid)));
                }
            }
            duList.add(new DistUsersModel(d, toadd));
        }
        toadd.clear();
        for (Groups g : gF.findAllPro(Projectid)) {
            toadd.clear();
            for (Groupusers gu : sgMMF.findAllPro(Projectid)) {
                if (gu.getGroupusersPK().getUsergroupname().equalsIgnoreCase(g.getGroupsPK().getGroupname())) {
                    toadd.add(uF.find(new UsersPK(gu.getGroupusersPK().getUsername(), Projectid)));
                }
            }
            guList.add(new GroupUsersModel(g, toadd));
        }
    }

}
