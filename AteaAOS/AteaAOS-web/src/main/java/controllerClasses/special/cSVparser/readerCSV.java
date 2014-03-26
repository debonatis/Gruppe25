/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.cSVparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import org.jsefa.Deserializer;
import org.jsefa.common.lowlevel.filter.HeaderAndFooterFilter;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.config.CsvConfiguration;

/**
 *
 * @author simond
 */
public class readerCSV {

    private File fil;

  
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

    

}
