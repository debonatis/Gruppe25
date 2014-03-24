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
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
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
    FileOutputStream csvOutputStream;

    public readerCSV(UploadedFile file) throws IOException {
        CSVfile = file.getInputstream();
        filename = file.getFileName();
    }

    private void persistFile() throws FileNotFoundException {

       csvOutputStream = new FileOutputStream(System.getProperty("controllerClasses.files") + File.separator + filename);
        try {
            IOUtils.copy(CSVfile, csvOutputStream);
        } catch (IOException ex) {
            Logger.getLogger(readerCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void readData(){
        
        try {
 
		br = new BufferedReader(new InputStreamReader(CSVfile));
		while ((line = br.readLine()) != null) {
 
		        // use comma as separator
			String[] country = line.split(cvsSplitBy);
 
			System.out.println("Country [code= " + country[4] 
                                 + " , name=" + country[5] + "]");
 
		}
 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	System.out.println("Done");
  }
    
}

}
