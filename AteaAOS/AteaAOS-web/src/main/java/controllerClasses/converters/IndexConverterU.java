/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.converters;

import controllerClasses.util.JsfUtil;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author simond
 */
@FacesConverter("indexConverterU")
public class IndexConverterU implements Converter {

    private List<?> list;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        int index = Integer.valueOf(value);
        if (index < 0 || index >= list.size()) {

            JsfUtil.addErrorMessage("The List do not match");

        }

        return list.get(index);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(value)) {
                return i + "";
            }
            JsfUtil.addErrorMessage("The List do not contain value");
        }

        return null;

    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
