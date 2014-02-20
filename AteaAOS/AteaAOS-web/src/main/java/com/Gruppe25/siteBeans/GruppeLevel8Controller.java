package com.Gruppe25.siteBeans;

import com.Gruppe25.GruppeLevel8;
import com.Gruppe25.siteBeans.util.JsfUtil;
import com.Gruppe25.siteBeans.util.PaginationHelper;
import com.Gruppe25.session.GruppeLevel8Facade;

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

@Named("gruppeLevel8Controller")
@SessionScoped
public class GruppeLevel8Controller implements Serializable {

    private GruppeLevel8 current;
    private DataModel items = null;
    @EJB
    private com.Gruppe25.session.GruppeLevel8Facade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public GruppeLevel8Controller() {
    }

    public GruppeLevel8 getSelected() {
        if (current == null) {
            current = new GruppeLevel8();
            current.setGruppeLevel8PK(new com.Gruppe25.GruppeLevel8PK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private GruppeLevel8Facade getFacade() {
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
        current = (GruppeLevel8) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new GruppeLevel8();
        current.setGruppeLevel8PK(new com.Gruppe25.GruppeLevel8PK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruppeLevel8Created"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (GruppeLevel8) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruppeLevel8Updated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (GruppeLevel8) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruppeLevel8Deleted"));
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

    public GruppeLevel8 getGruppeLevel8(com.Gruppe25.GruppeLevel8PK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = GruppeLevel8.class)
    public static class GruppeLevel8ControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GruppeLevel8Controller controller = (GruppeLevel8Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "gruppeLevel8Controller");
            return controller.getGruppeLevel8(getKey(value));
        }

        com.Gruppe25.GruppeLevel8PK getKey(String value) {
            com.Gruppe25.GruppeLevel8PK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.Gruppe25.GruppeLevel8PK();
            key.setGuID(values[0]);
            key.setGroupName(values[1]);
            return key;
        }

        String getStringKey(com.Gruppe25.GruppeLevel8PK value) {
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
            if (object instanceof GruppeLevel8) {
                GruppeLevel8 o = (GruppeLevel8) object;
                return getStringKey(o.getGruppeLevel8PK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + GruppeLevel8.class.getName());
            }
        }

    }

}
