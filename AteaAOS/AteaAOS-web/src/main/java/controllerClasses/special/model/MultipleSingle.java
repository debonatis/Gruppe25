/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special.model;

import entityModels.Projecttypes;
import java.util.List;  
import javax.faces.model.ListDataModel; 
import org.primefaces.model.SelectableDataModel;  
  
public class MultipleSingle extends ListDataModel<Projecttypes> implements SelectableDataModel<Projecttypes> {    
  
    public MultipleSingle() {  
    }  
  
    public MultipleSingle(List<Projecttypes> data) {  
        super(data);  
    }  
      
    @Override  
    public Projecttypes getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Projecttypes> projecttypes = (List<Projecttypes>) getWrappedData();  
          
        for(Projecttypes projecttype : projecttypes) {  
            if(projecttype.getProjecttype().equals(rowKey))  
                return projecttype;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Projecttypes projecttype) {  
        return projecttype.getProjecttype();  
    }  
}  
