/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.cSVparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.jsefa.Deserializer;
import org.jsefa.common.lowlevel.filter.HeaderAndFooterFilter;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.config.CsvConfiguration;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author simond
 */
public class readerCSV {

    private InputStream CSVfile;
  
    private String filename = "";
    FileOutputStream csvOutputStream;
    private CSVRow row;
    private ArrayList<CSVRow> CsvList = new ArrayList<>();

    public readerCSV() {
    }
    

    public ArrayList<CSVRow> getCsvList() {
        if(CsvList.isEmpty()){
            CsvList.add(new CSVRow());
        }
        return CsvList;
    }

    public void setCsvList(ArrayList<CSVRow> CsvList) {
        this.CsvList = CsvList;
    }
    

   
    public void setFile(UploadedFile file) throws IOException{
        CSVfile = file.getInputstream();
        filename = file.getFileName();
    }

    public synchronized void persistFile() throws FileNotFoundException {

       csvOutputStream = new FileOutputStream(System.getProperty("controllerClasses.files") + File.separator + filename);
        try {
            IOUtils.copy(CSVfile, csvOutputStream);
        } catch (IOException ex) {
            Logger.getLogger(readerCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public synchronized void readAndPopulateList(){
        
        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setFieldDelimiter(',');
        csvConfiguration.setLineFilter(new HeaderAndFooterFilter(1, false, false));

        Deserializer deserializer = CsvIOFactory.createFactory(csvConfiguration, CSVRow.class).createDeserializer();

        deserializer.open(new InputStreamReader(CSVfile));
        while (deserializer.hasNext()) {
            row = deserializer.next();
            CsvList.add(row);
        }
        deserializer.close(true);
        
  }
    
}

