/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.converters;

import entityModels.Users;
import entityModels.UsersPK;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
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
        Users u = new Users(new UsersPK(value, (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")));

        return usersEJB.find(u.getUsersPK());
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        } else if (!(value instanceof Users)) {
            throw new ConverterException("The value is not a valid User: " + value);
        } else {
            bruker = (Users) value;
        }

        return bruker.getUsersPK().getUsername();

    }

}
