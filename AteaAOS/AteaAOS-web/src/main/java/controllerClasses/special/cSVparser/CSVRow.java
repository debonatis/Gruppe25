/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.cSVparser;

import org.jsefa.csv.annotation.CsvDataType;

/**
 *
 * @author simond
 */
@CsvDataType
public class CSVRow {
    
    @CsvField(pos = 1)
    String   objectclass;

    @CsvField(pos = 2)
    String  dn;

    @CsvField(pos = 3)
    int     weeks;

    @CsvField(pos = 4)
    long    cost;
    
}
