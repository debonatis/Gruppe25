/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.converters;

import entityModels.Distributiongroups;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import persistClasses.DistributiongroupsFacade;

/**
 *
 * @author simond
 */
@Named
@ApplicationScoped
public class IndexConverterD implements Converter {

    private Distributiongroups dGruppe;
    @EJB
    private DistributiongroupsFacade dGroupsEJB;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        return dGroupsEJB.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        dGruppe = (Distributiongroups) value;

        return dGruppe.getDistributiongroupsPK().getDisplayname();

    }

}
