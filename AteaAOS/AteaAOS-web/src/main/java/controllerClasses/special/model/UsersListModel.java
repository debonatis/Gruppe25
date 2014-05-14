/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.model;

import entityModels.Users;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Martin
 */
public class UsersListModel extends ListDataModel<Users> implements SelectableDataModel<Users> {    
  
    public UsersListModel() {  
    }  
  
    public UsersListModel(List<Users> data) {  
        super(data);  
    }  
      
    @Override  
    public Users getRowData(String rowKey) {  
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data  
          
        List<Users> user = (List<Users>) getWrappedData();  
          
        for(Users pr : user) {  
            if(pr.getUsersPK().getUsername().equals(rowKey))  
                return pr;  
        }  
          
        return null;  
    }  
  
    @Override  
    public Object getRowKey(Users pr) {  
        return pr.getUsersPK().getUsername();
    }  
}  
