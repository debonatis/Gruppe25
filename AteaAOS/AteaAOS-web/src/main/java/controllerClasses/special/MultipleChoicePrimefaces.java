/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special;

import java.util.List;  
import javax.faces.model.ListDataModel;   
import entityModels.Domains;
import org.primefaces.model.SelectableDataModel; 

/**
 *
 * @author Martin
 */
public class MultipleChoicePrimefaces extends ListDataModel<Domains> implements SelectableDataModel<Domains>{

    public MultipleChoicePrimefaces() {  
    }  
  
    public MultipleChoicePrimefaces(List<Domains> data) {  
        super(data);  
    }  
    
    @Override
    public Object getRowKey(Domains t) {
    return t.getDomainname();
    }

    @Override
    public Domains getRowData(String rowKey) {
    List<Domains> domains = (List<Domains>) getWrappedData();  
          
        for(Domains domain : domains) {  
            if(domain.getDomainname().equals(rowKey))  
                return domain;  
        }  
          
        return null; 
    }
    
}
