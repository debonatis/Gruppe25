/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.model;

import entityModels.Siteuser;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Martin
 */
public class SiteuserListModel extends ListDataModel<Siteuser> implements SelectableDataModel<Siteuser> {    
  
    public SiteuserListModel() {  
    }  
  
    public SiteuserListModel(List<Siteuser> data) {  
        super(data);  
    }  
      
    @Override  
    public Siteuser getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Siteuser> siteuser = (List<Siteuser>) getWrappedData();  
          
        for(Siteuser pr : siteuser) {  
            if(pr.getUsername().equals(rowKey))  
                return pr;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Siteuser pr) {  
        return pr.getUsername();
    }  
}  
