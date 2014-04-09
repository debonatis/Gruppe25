/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.cSVparser;

import entityModels.Users;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.jsefa.Deserializer;
import org.jsefa.common.lowlevel.filter.HeaderAndFooterFilter;
import org.jsefa.csv.CsvIOFactory;
import org.jsefa.csv.config.CsvConfiguration;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import persistClasses.UsersFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@SessionScoped
public class FileUpload implements Serializable {

    private static List<CSVRow> listCSV = new ArrayList<>();
    @EJB
    private UsersFacade brukerEJB;
    private UploadedFile file;

    private CSVRow row;

    private File fil;

    public FileUpload() {

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
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private synchronized void readAndPopulateList() throws FileNotFoundException {

        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setFieldDelimiter(',');
        csvConfiguration.setLineFilter(new HeaderAndFooterFilter(1, false, false));

        Deserializer deserializer = CsvIOFactory.createFactory(csvConfiguration, CSVRow.class).createDeserializer();

        deserializer.open(new FileReader(fil));
        while (deserializer.hasNext()) {
            row = deserializer.next();
            listCSV.add(row);
        }
        deserializer.close(true);
    }

    public List<CSVRow> getListCSV() {

        return listCSV;
    }

    public void setListCSV(List<CSVRow> listCSV) {
        FileUpload.listCSV = listCSV;
    }

    public synchronized void readAndPersist() {
        for (CSVRow e : getListCSV()) {
            brukerEJB.create(new Users(e.getSamAccountName(), e.getSn(), e.getDisplayName(), e.getDn(), e.getGivenName(), "no", "no", "no", "no", 1337,(String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")));
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

    public void deleteItem(CSVRow e) {
        listCSV.remove(e);

    }
    public void emptyList(){
        listCSV.clear();
        fil.delete();
    }

}
