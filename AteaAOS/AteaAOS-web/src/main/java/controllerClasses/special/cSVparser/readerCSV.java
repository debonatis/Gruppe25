/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.cSVparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author simond
 */
public class readerCSV {

    private InputStream CSVfile;
    private BufferedReader br = null;
    private String line = "";
    private String cvsSplitBy = ",";
    private String filename = "";

    public readerCSV(UploadedFile file) throws IOException {
        CSVfile = file.getInputstream();
        filename = file.getFileName();
    }

    private void persistFile() throws FileNotFoundException {

        FileOutputStream csvOutputStream = new FileOutputStream(System.getProperty("controllerClasses.files") + File.separator + filename);
        try {
            IOUtils.copy(CSVfile, csvOutputStream);
        } catch (IOException ex) {
            Logger.getLogger(readerCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void readData(){
    
}

}
