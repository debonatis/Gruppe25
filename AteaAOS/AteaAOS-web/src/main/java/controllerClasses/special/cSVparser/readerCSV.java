/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.cSVparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
    File fil;

    private String filename = "";
    FileOutputStream csvOutputStream;
    private CSVRow row;
    private ArrayList<CSVRow> CsvList = new ArrayList<>();

    public readerCSV() {
    }

    public ArrayList<CSVRow> getCsvList() {

        return CsvList;
    }

    public void setCsvList(ArrayList<CSVRow> CsvList) {
        this.CsvList = CsvList;
    }

    public void setFile(UploadedFile file) throws IOException {

        copyFile(file);
    }

    public synchronized void persistFile() throws FileNotFoundException {

    }

    private synchronized void copyFile(UploadedFile input) {

        try {
            InputStream inputStream = input.getInputstream();
            fil = new File(input.getFileName());

            OutputStream out = new FileOutputStream(fil);

            int read = 0;

            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {

                out.write(bytes, 0, read);

            }

            inputStream.close();

            out.flush();

            out.close();

            System.out.println("New file created!");

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }

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
