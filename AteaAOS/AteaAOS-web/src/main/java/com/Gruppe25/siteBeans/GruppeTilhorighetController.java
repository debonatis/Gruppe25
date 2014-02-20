package com.Gruppe25.siteBeans;

import com.Gruppe25.GruppeTilhorighet;
import com.Gruppe25.siteBeans.util.JsfUtil;
import com.Gruppe25.siteBeans.util.PaginationHelper;
import com.Gruppe25.session.GruppeTilhorighetFacade;

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

@Named("gruppeTilhorighetController")
@SessionScoped
public class GruppeTilhorighetController implements Serializable {

    private GruppeTilhorighet current;
    private DataModel items = null;
    @EJB
    private com.Gruppe25.session.GruppeTilhorighetFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public GruppeTilhorighetController() {
    }

    public GruppeTilhorighet getSelected() {
        if (current == null) {
            current = new GruppeTilhorighet();
            current.setGruppeTilhorighetPK(new com.Gruppe25.GruppeTilhorighetPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private GruppeTilhorighetFacade getFacade() {
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
        current = (GruppeTilhorighet) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new GruppeTilhorighet();
        current.setGruppeTilhorighetPK(new com.Gruppe25.GruppeTilhorighetPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruppeTilhorighetCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (GruppeTilhorighet) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruppeTilhorighetUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (GruppeTilhorighet) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GruppeTilhorighetDeleted"));
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

    public GruppeTilhorighet getGruppeTilhorighet(com.Gruppe25.GruppeTilhorighetPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = GruppeTilhorighet.class)
    public static class GruppeTilhorighetControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GruppeTilhorighetController controller = (GruppeTilhorighetController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "gruppeTilhorighetController");
            return controller.getGruppeTilhorighet(getKey(value));
        }

        com.Gruppe25.GruppeTilhorighetPK getKey(String value) {
            com.Gruppe25.GruppeTilhorighetPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.Gruppe25.GruppeTilhorighetPK();
            key.setGroupName(values[0]);
            key.setGruppeMedlem(values[1]);
            return key;
        }

        String getStringKey(com.Gruppe25.GruppeTilhorighetPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getGroupName());
            sb.append(SEPARATOR);
            sb.append(value.getGruppeMedlem());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof GruppeTilhorighet) {
                GruppeTilhorighet o = (GruppeTilhorighet) object;
                return getStringKey(o.getGruppeTilhorighetPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + GruppeTilhorighet.class.getName());
            }
        }

    }

}
