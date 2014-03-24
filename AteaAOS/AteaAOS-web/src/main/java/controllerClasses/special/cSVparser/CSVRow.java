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

    @CsvField(pos = 2)
    String objectclass;

    @CsvField(pos = 1)
    String dn;
    
    @CsvField(pos = 3)
    String DisplayName;

    @CsvField(pos=4)
    String samAccountName;

    @CsvField(pos = 5)
    String sn;
    
    @CsvField(pos = 6)
    String givenName;

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

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
    }

    public String getSamAccountName() {
        return samAccountName;
    }

    public void setSamAccountName(String samAccountName) {
        this.samAccountName = samAccountName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    
}
