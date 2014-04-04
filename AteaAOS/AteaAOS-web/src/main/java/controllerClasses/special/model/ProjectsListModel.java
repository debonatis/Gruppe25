/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.model;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author simond
 */
public class ProjectsListModel extends ListDataModel<ProjectModel> implements SelectableDataModel<ProjectModel> {    
  
    public ProjectsListModel() {  
    }  
  
    public ProjectsListModel(List<ProjectModel> data) {  
        super(data);  
    }  
      
    @Override  
    public ProjectModel getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<ProjectModel> projects = (List<ProjectModel>) getWrappedData();  
          
        for(ProjectModel pr : projects) {  
            if(pr.getPro().getProjectid().equals(rowKey))  
                return pr;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(ProjectModel pr) {  
        return pr.getPro().getProjectid();  
    }  
}  