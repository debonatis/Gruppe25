/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import java.util.List;
import javax.faces.model.ListDataModel;
import entityModels.Projecttypes;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Martin
 */
public class MultipleSingle extends ListDataModel<Projecttypes> implements SelectableDataModel<Projecttypes> {

    public MultipleSingle() {
    }

    public MultipleSingle(List<Projecttypes> data) {
        super(data);
    }

    @Override
    public Projecttypes getRowData(String rowKey) {
        
        List<Projecttypes> projects = (List<Projecttypes>) getWrappedData();

        for (Projecttypes project : projects) {
            if (project.getProjecttype().equals(rowKey)) {
                return project;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Projecttypes t) {
        return t.getProjecttype();
    }

}
