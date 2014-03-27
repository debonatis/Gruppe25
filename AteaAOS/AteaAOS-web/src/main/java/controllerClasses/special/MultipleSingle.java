/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Projects;
import java.util.List;  
import javax.faces.model.ListDataModel; 
import org.primefaces.model.SelectableDataModel;  
  
public class MultipleSingle extends ListDataModel<Projects> implements SelectableDataModel<Projects> {    
  
    public MultipleSingle() {  
    }  
  
    public MultipleSingle(List<Projects> data) {  
        super(data);  
    }  
      
    @Override  
    public Projects getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Projects> projects = (List<Projects>) getWrappedData();  
          
        for(Projects project : projects) {  
            if(project.getProjecttype().equals(rowKey))  
                return project;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Projects project) {  
        return project.getProjecttype();  
    }  
}  
