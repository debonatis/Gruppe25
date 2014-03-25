/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.converters;

import entityModels.Users;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import persistClasses.UsersFacade;

/**
 *
 * @author simond
 */
@Named
@ApplicationScoped
public class IndexConverterU implements Converter {

    private Users bruker;
    @EJB
    private UsersFacade usersEJB;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       

        return usersEJB.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        bruker = (Users) value;
        
            
        

        return bruker.getUsername();

    }

   
}