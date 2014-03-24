/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.converters;

import entityModels.Groups;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import persistClasses.GroupsFacade;

/**
 *
 * @author simond
 */
@Named
@ApplicationScoped
public class IndexConverterG implements Converter {

    private Groups gruppe;
    @EJB
    private GroupsFacade groupsEJB;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        gruppe = new Groups(value);
        return groupsEJB.find(gruppe);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        gruppe = (Groups) value;

        return gruppe.getGroupname();

    }

}
