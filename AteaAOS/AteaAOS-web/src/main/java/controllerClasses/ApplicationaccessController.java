package controllerClasses;

import entityModels.Applicationaccess;
import controllerClasses.util.JsfUtil;
import controllerClasses.util.PaginationHelper;
import persistClasses.ApplicationaccessFacade;

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

@Named("applicationaccessController")
@SessionScoped
public class ApplicationaccessController implements Serializable {

    private Applicationaccess current;
    private DataModel items = null;
    @EJB
    private persistClasses.ApplicationaccessFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ApplicationaccessController() {
    }

    public Applicationaccess getSelected() {
        if (current == null) {
            current = new Applicationaccess();
            current.setApplicationaccessPK(new entityModels.ApplicationaccessPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ApplicationaccessFacade getFacade() {
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
        current = (Applicationaccess) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Applicationaccess();
        current.setApplicationaccessPK(new entityModels.ApplicationaccessPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ApplicationaccessCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Applicationaccess) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ApplicationaccessUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Applicationaccess) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ApplicationaccessDeleted"));
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

    public Applicationaccess getApplicationaccess(entityModels.ApplicationaccessPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Applicationaccess.class)
    public static class ApplicationaccessControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ApplicationaccessController controller = (ApplicationaccessController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "applicationaccessController");
            return controller.getApplicationaccess(getKey(value));
        }

        entityModels.ApplicationaccessPK getKey(String value) {
            entityModels.ApplicationaccessPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entityModels.ApplicationaccessPK();
            key.setUsername(values[0]);
            key.setApplicationname(values[1]);
            return key;
        }

        String getStringKey(entityModels.ApplicationaccessPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getUsername());
            sb.append(SEPARATOR);
            sb.append(value.getApplicationname());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Applicationaccess) {
                Applicationaccess o = (Applicationaccess) object;
                return getStringKey(o.getApplicationaccessPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Applicationaccess.class.getName());
            }
        }

    }

}
