/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special;

import entityModels.Groups;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author simond
 */
@ManagedBean
@ViewScoped
public class FoldersEdit {
     private TreeNode root;  
  
    private TreeNode selectedNode;  
    
    private Groups gru = new Groups();
  
    public FoldersEdit() {  
        root = new DefaultTreeNode("Root", null); 
        TreeNode node0 = new DefaultTreeNode("root", root);  
         
        
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
        if(selectedNode != null) {  
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
    public void addNode(){
        TreeNode a = new DefaultTreeNode(gru, selectedNode);
        selectedNode = null;
    }
}
