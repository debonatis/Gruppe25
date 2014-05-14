/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.converters;

import entityModels.Siteuser;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import persistClasses.SiteuserFacade;

/**
 *
 * @author simond
 */
@Named
@ApplicationScoped
public class indexConverterSU implements Converter{
    private Siteuser su;
    @EJB
    private SiteuserFacade suF;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        return suF.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        su = (Siteuser) value;

        return su.getUsername();

    }
}
