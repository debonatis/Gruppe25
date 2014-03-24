/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.cSVparser;

import org.jsefa.csv.annotation.CsvDataType;
import org.jsefa.csv.annotation.CsvField;

/**
 *
 * @author simond
 */
@CsvDataType
public class CSVRow {

    @CsvField(pos = 1)
    String objectclass;

    @CsvField(pos = 2)
    String dn;

    @CsvField(pos = 3)
    String sAMAccountName;

    @CsvField(pos = 4)
    String userPrincipalName;
    
    @CsvField(pos = 5)
    String userAccountControl;

    public String getObjectclass() {
        return objectclass;
    }

    public void setObjectclass(String objectclass) {
        this.objectclass = objectclass;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public String getsAMAccountName() {
        return sAMAccountName;
    }

    public void setsAMAccountName(String sAMAccountName) {
        this.sAMAccountName = sAMAccountName;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public String getUserAccountControl() {
        return userAccountControl;
    }

    public void setUserAccountControl(String userAccountControl) {
        this.userAccountControl = userAccountControl;
    }

}
