/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.model;

import entityModels.Projects;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author simond
 */
public class ProjectsListModel extends ListDataModel<Projects> implements SelectableDataModel<Projects> {    
  
    public ProjectsListModel() {  
    }  
  
    public ProjectsListModel(List<Projects> data) {  
        super(data);  
    }  
      
    @Override  
    public Projects getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Projects> projects = (List<Projects>) getWrappedData();  
          
        for(Projects pr : projects) {  
            if(pr.getName().equals(rowKey))  
                return pr;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Projects pr) {  
        return pr.getName();
    }  
}  