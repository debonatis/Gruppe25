package com.Gruppe25.siteBeans;

import com.Gruppe25.GruppeLevel6;
import com.Gruppe25.siteBeans.util.JsfUtil;
import com.Gruppe25.siteBeans.util.PaginationHelper;
import com.Gruppe25.session.GruppeLevel6Facade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("gruppeLevel6Controller")
@SessionScoped
public class GruppeLevel6Controller implements Serializable {

    private GruppeLevel6 current;
    private DataModel items = null;
    @EJB
    private com.Gruppe25.session.GruppeLevel6Facade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public GruppeLevel6Controller() {
    }

    public GruppeLevel6 getSelected() {
        if (current == null) {
            current = new GruppeLevel6();
            current.setGruppeLevel6PK(new com.Gruppe25.GruppeLevel6PK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private GruppeLevel6Facade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (GruppeLevel6) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new GruppeLevel6();
        current.setGruppeLevel6PK(new com.Gruppe25.GruppeLevel6PK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruppeLevel6Created"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (GruppeLevel6) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruppeLevel6Updated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (GruppeLevel6) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruppeLevel6Deleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public GruppeLevel6 getGruppeLevel6(com.Gruppe25.GruppeLevel6PK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = GruppeLevel6.class)
    public static class GruppeLevel6ControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GruppeLevel6Controller controller = (GruppeLevel6Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "gruppeLevel6Controller");
            return controller.getGruppeLevel6(getKey(value));
        }

        com.Gruppe25.GruppeLevel6PK getKey(String value) {
            com.Gruppe25.GruppeLevel6PK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.Gruppe25.GruppeLevel6PK();
            key.setGuID(values[0]);
            key.setGroupName(values[1]);
            return key;
        }

        String getStringKey(com.Gruppe25.GruppeLevel6PK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getGuID());
            sb.append(SEPARATOR);
            sb.append(value.getGroupName());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof GruppeLevel6) {
                GruppeLevel6 o = (GruppeLevel6) object;
                return getStringKey(o.getGruppeLevel6PK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + GruppeLevel6.class.getName());
            }
        }

    }

}
