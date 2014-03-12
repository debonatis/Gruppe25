package controllerClasses;

import Entitys.Grouplevel1;
import controllerClasses.util.JsfUtil;
import controllerClasses.util.PaginationHelper;
import persistClasses.Grouplevel1Facade;

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

@Named("grouplevel1Controller")
@SessionScoped
public class Grouplevel1Controller implements Serializable {

    private Grouplevel1 current;
    private DataModel items = null;
    @EJB
    private persistClasses.Grouplevel1Facade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public Grouplevel1Controller() {
    }

    public Grouplevel1 getSelected() {
        if (current == null) {
            current = new Grouplevel1();
            current.setGrouplevel1PK(new Entitys.Grouplevel1PK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private Grouplevel1Facade getFacade() {
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
        current = (Grouplevel1) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Grouplevel1();
        current.setGrouplevel1PK(new Entitys.Grouplevel1PK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Grouplevel1Created"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Grouplevel1) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Grouplevel1Updated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Grouplevel1) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("Grouplevel1Deleted"));
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

    public Grouplevel1 getGrouplevel1(Entitys.Grouplevel1PK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Grouplevel1.class)
    public static class Grouplevel1ControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            Grouplevel1Controller controller = (Grouplevel1Controller) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "grouplevel1Controller");
            return controller.getGrouplevel1(getKey(value));
        }

        Entitys.Grouplevel1PK getKey(String value) {
            Entitys.Grouplevel1PK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Entitys.Grouplevel1PK();
            key.setGroupname(values[0]);
            key.setGuid(values[1]);
            return key;
        }

        String getStringKey(Entitys.Grouplevel1PK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getGroupname());
            sb.append(SEPARATOR);
            sb.append(value.getGuid());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Grouplevel1) {
                Grouplevel1 o = (Grouplevel1) object;
                return getStringKey(o.getGrouplevel1PK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Grouplevel1.class.getName());
            }
        }

    }

}
