/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.cSVparser;

import java.util.List;
import org.apache.directory.shared.ldap.name.DN;

/**
 *
 * @author simond
 */
public class Members {

   
    
    private List<DN> members;
    private String name;
    boolean secgr;

    public Members(List<DN> members, String name, boolean secgr) {
        this.members = members;
        this.name = name;
        this.secgr = secgr;
    }

    public List<DN> getMembers() {
        return members;
    }

    public void setMembers(List<DN> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSecgr() {
        return secgr;
    }

    public void setSecgr(boolean secgr) {
        this.secgr = secgr;
    }

   
    
    

    

  

   
    

    
}
