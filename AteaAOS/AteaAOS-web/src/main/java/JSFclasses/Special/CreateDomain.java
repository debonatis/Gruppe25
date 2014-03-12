/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JSFclasses.Special;

import Entitys.Domains;
import JSFclasses.DomainsController;
import JSFclasses.util.JsfUtil;
import JSFclasses.util.PaginationHelper;
import SessionBeans.DomainsFacade;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;


@ManagedBean(name = "createDomain")
@SessionScoped
/**
 *
 * @author Martin
 */
public class CreateDomain {


    private Domains current;
    private DataModel items = null;
    @EJB
    private DomainsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;   
    private static final Logger logger = Logger.getLogger(DomainsController.class.getName());
    private boolean skip;

    /**
     *
     */
    public CreateDomain() {
    }

    /**
     *
     * @return
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     *
     * @param skip
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     *
     * @return
     */
    public Domains getSelected() {
        if (current == null) {
            current = new Domains();
            selectedItemIndex = -1;
        }
        return current;
    }

    private DomainsFacade getFacade() {
        return ejbFacade;
    }

    /**
     *
     * @return
     */
//    public boolean isBeskrivelse() {
//        if (getSelected().getBeskrivelse().isEmpty()) {
//            return false;
//        }
//        return true;
//    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public String prepareList() {
        recreateModel();
        return "List";
    }

    /**
     *
     * @return
     */
    public String prepareView() {
        current = (Domains) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    /**
     *
     * @return
     */
    public String prepareCreate() {
        current = new Domains();
        selectedItemIndex = -1;
        return "Create";
    }

    /**
     *
     * @return
     */
    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DomainsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     *
     * @return
     */
    public String prepareEdit() {
        current = (Domains) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    /**
     *
     * @return
     */
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DomainsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    /**
     *
     * @return
     */
    public String destroy() {
        current = (Domains) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    /**
     *
     * @return
     */
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DomainsDeleted"));
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

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    /**
     *
     * @return
     */
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    /**
     *
     * @return
     */
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    /**
     *
     * @param event
     * @return
     */
    public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());
        logger.info("Next step:" + event.getNewStep());

        if (skip) {
            skip = false;
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    /**
     *
     * @param event
     */
    public void handleFileUpload(FileUploadEvent event) {
        
       


        try {
            
           String mick = FacesContext.getCurrentInstance().getExternalContext().getRealPath("//resources//CSV");
             File file = new File(mick, event.getFile().getFileName());
              

            InputStream inputStream = event.getFile().getInputstream();
            current.setSetFIL(event.getFile().getFileName());
            OutputStream out = new FileOutputStream(file);


            int read = 0;

            byte[] bytes = new byte[1024];



            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);

            }

            inputStream.close();

            out.flush();

            out.close();

        } catch (IOException e) {
        }
    }

    /**
     *
     */
//    @FacesConverter(forClass = Domains.class, value="retterControllerConverter")
//    public static class DomainsControllerConverter implements Converter {
//
//        /**
//         *
//         * @param facesContext
//         * @param component
//         * @param value
//         * @return
//         */
//        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
//            if (value == null || value.length() == 0) {
//                return null;
//            }
//            DomainsController controller = (DomainsController) facesContext.getApplication().getELResolver().
//                    getValue(facesContext.getELContext(), null, "retterController");
//            return controller.ejbFacade.find(getKey(value));
//        }
//
//        /**
//         *
//         * @param value
//         * @return
//         */
//        public  java.lang.String getKey(String value) {
//            java.lang.String key;
//            key = value;
//            return key;
//        }
//
//        String getStringKey(java.lang.String value) {
//            StringBuffer sb = new StringBuffer();
//            sb.append(value);
//            return sb.toString();
//        }
//
//        /**
//         *
//         * @param facesContext
//         * @param component
//         * @param object
//         * @return
//         */
//        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
//            if (object == null) {
//                return null;
//            }
//            if (object instanceof Domains) {
//                Domains o = (Domains) object;
//                return getStringKey(o.getRettnummer());
//            } else {
//                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Domains.class.getName());
//            }
//        }
//    }
//}  
}
