/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.converters;

import entityModels.Projects;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import persistClasses.ProjectsFacade;

/**
 *
 * @author simond
 */
@Named
@ApplicationScoped
public class indexConverterP implements Converter{
    
    private Projects pro;
    @EJB
    private ProjectsFacade pF;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        return pF.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        pro = (Projects) value;

        return pro.getProjectid();

    }
    
}
