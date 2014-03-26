/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.cSVparser;

import entityModels.Users;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import org.jsefa.Deserializer;
import org.jsefa.common.lowlevel.filter.HeaderAndFooterFilter;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.config.CsvConfiguration;
import persistClasses.UsersFacade;

/**
 *
 * @author simond
 */
public class readerCSV {

    private File fil;

    @EJB
    private UsersFacade brukerEJB;
    private CSVRow row = new CSVRow();
    private ArrayList<CSVRow> CsvList = new ArrayList<>();

    public readerCSV() {
    }

    public File getFil() {
        return fil;
    }

    public void setFil(File fil) {
        this.fil = fil;
    }

    public ArrayList<CSVRow> getCsvList() {

        return CsvList;
    }

    public void setCsvList(ArrayList<CSVRow> CsvList) {
        this.CsvList = CsvList;
    }

    public synchronized void readAndPopulateList() throws FileNotFoundException {

        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setFieldDelimiter(',');
        csvConfiguration.setLineFilter(new HeaderAndFooterFilter(1, false, false));

        Deserializer deserializer = CsvIOFactory.createFactory(csvConfiguration, CSVRow.class).createDeserializer();

        deserializer.open(new FileReader(fil));
        while (deserializer.hasNext()) {
            row = deserializer.next();
            CsvList.add(row);
        }
        deserializer.close(true);

    }

    public synchronized void readAndPersist(List<CSVRow> list) {
        for (CSVRow e : list) {
            brukerEJB.create(new Users(e.samAccountName, e.sn, e.getGivenName(), e.dn, "no", "no", "no", "no", "no", 1337, "no"));
        }
    }

}
