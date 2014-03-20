/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.converters;

import controllerClasses.util.JsfUtil;
import entityModels.Users;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import persistClasses.UsersFacade;

/**
 *
 * @author simond
 */
@FacesConverter("indexConverterU")
public class IndexConverterU implements Converter {

    private Users bruker;
    @EJB
    private UsersFacade usersEJB;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        bruker = new Users(value);

        return usersEJB.find(bruker);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        bruker = (Users) value;
        
            
        

        return bruker.getUsername();

    }

   
}
