/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import java.util.List;
import javax.faces.model.ListDataModel;
import entityModels.Projects;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Martin
 */
public class MultipleSingle extends ListDataModel<Projects> implements SelectableDataModel<Projects> {

    public MultipleSingle() {
    }

    public MultipleSingle(List<Projects> data) {
        super(data);
    }

    @Override
    public Projects getRowData(String rowKey) {

        List<Projects> projects = (List<Projects>) getWrappedData();
        for (Projects project : projects) {
            if (project.getProjecttype().equals(rowKey)) 
                return project;
            
        }
        return null;
    }

    @Override
    public Object getRowKey(Projects t) {
        return t.getProjecttype();
    }

}
