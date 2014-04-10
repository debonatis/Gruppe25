/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.converters;

import entityModels.Applications;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import persistClasses.ApplicationsFacade;

/**
 *
 * @author Martin
 */
@Named
@ApplicationScoped
public class IndexConverterA implements Converter {

    private Applications app;
    @EJB
    private ApplicationsFacade applicationsEJB;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        return applicationsEJB.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
       try{
         app = (Applications) value;

        return app.getApplicationid();  
       }
       catch(Exception e){
           String lol = "This went wrong";
           return lol;
       }
        

    }

}
