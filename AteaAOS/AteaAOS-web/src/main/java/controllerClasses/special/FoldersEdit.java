/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Foldergroups;
import entityModels.Folders;
import entityModels.Groups;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import persistClasses.FolderGroupsFacade;
import persistClasses.FoldersFacade;
import persistClasses.GroupsFacade;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
public class FoldersEdit {

    private TreeNode root;
    @EJB
    private FoldersFacade fF;
    @EJB
    private FolderGroupsFacade fgF;
    @EJB
    private GroupsFacade gF;
    private TreeNode selectedNode;
    private Folders folder = new Folders();
    private List<Groups> gruSel = new ArrayList<>();
    private List<Groups> gru = new ArrayList<>();
    private boolean rw = false;
    private boolean r = false;
    
    

    public FoldersEdit() {
        root = new DefaultTreeNode("Root", null);
        TreeNode node0 = new DefaultTreeNode(new Folders("root", (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID")), root);
        try {
            for (Folders f : fF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))) {
              if(f.getParentfolder().isEmpty()){
                  TreeNode node = new DefaultTreeNode(f, root);
              } else if (!f.getParentfolder().isEmpty()){
                  for(TreeNode node : root.getChildren()){
                     if(f.getParentfolder().equalsIgnoreCase(((Folders) node.getData()).getParentfolder())){
                         TreeNode node2 = new DefaultTreeNode(f, node);
                     }
                  }
                 
                  
              }
            }
        } catch (NullPointerException e) {

        }
        try{
            
        
        gru = gF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
        } catch(Exception e){
            
        }
    }

    public boolean isRw() {
        return rw;
    }

    public void setRw(boolean rw) {
        this.rw = rw;
    }

    public boolean isR() {
        return r;
    }

    public void setR(boolean r) {
        this.r = r;
    }

    public List<Groups> getGruSel() {
        return gruSel;
    }

    public void setGruSel(List<Groups> gruSel) {
        this.gruSel = gruSel;
    }

    public List<Groups> getGru() {
        return gru;
    }

    public void setGru(List<Groups> gru) {
        this.gru = gru;
    }

    public Folders getFolder() {
        return folder;
    }

    public void setFolder(Folders folder) {
        this.folder = folder;
    }
    

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void displaySelectedSingle() {
        if (selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());

            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void deleteNode() {
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);

        selectedNode = null;
    }

    public void addNode() {
        folder.getFoldersPK().setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
        folder.setParentfolder(((Folders)selectedNode.getParent().getData()).getFoldersPK().getFoldername());
        TreeNode a = new DefaultTreeNode(folder, selectedNode);
        selectedNode = null;
        folder = new Folders();
    }
    
    public void saveF(){
        for(TreeNode e : root.getChildren()){
            
           Folders f = (Folders)e.getData();
           try{
            fF.create(f);
           } catch(Exception k){
               
           }
        }
    }
    public void addGroups(){
        Folders f = (Folders)selectedNode.getData();
        for(Groups gr:gruSel){
            try{
            fgF.create(new Foldergroups(f.getFoldersPK().getFoldername(), f.getFoldersPK().getProjectid(), gr.getGroupname()));
            }catch(Exception k){
                
            }
        }
        gruSel.clear();
        
        
    }
}
