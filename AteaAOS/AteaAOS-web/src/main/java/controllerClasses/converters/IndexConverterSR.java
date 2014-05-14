/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.converters;

import entityModels.Sharedresources;
import entityModels.SharedresourcesPK;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import persistClasses.SharedresourcesFacade;

/**
 *
 * @author simond
 */
@Named
@ApplicationScoped
public class IndexConverterSR implements Converter {

    private Sharedresources gruppe;
    @EJB
    private SharedresourcesFacade sharedresourceEJB;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Sharedresources g = new Sharedresources(new SharedresourcesPK(value, (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")));
        return sharedresourceEJB.find(g.getSharedresourcesPK());
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        gruppe = (Sharedresources) value;

        return gruppe.getSharedresourcesPK().getDisplaynameshared();

    }

}
