/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.model;

import entityModels.Applications;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Martin
 */
public class ApplicationsModel extends ListDataModel<Applications> implements SelectableDataModel<Applications> {    
  
    public ApplicationsModel() {  
    }  
  
    public ApplicationsModel(List<Applications> data) {  
        super(data);  
    }  
      
    @Override  
    public Applications getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Applications> applications = (List<Applications>) getWrappedData();  
          
        for(Applications pr : applications) {  
            if(pr.getApplicationname().equals(rowKey))  
                return pr;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Applications pr) {  
        return pr.getApplicationname();
    }  
}  