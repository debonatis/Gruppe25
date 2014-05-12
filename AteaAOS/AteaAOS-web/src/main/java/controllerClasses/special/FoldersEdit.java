/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllerClasses.special;

import entityModels.Foldergroups;
import entityModels.Folders;
import entityModels.FoldersPK;
import entityModels.Groups;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
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
    private TreeNode selectedNode = new DefaultTreeNode();
    private Folders folder = new Folders(new FoldersPK());
    private List<Groups> gruSel = new ArrayList<>();
    private List<Groups> gru = new ArrayList<>();
    private List<Foldergroups> viewList = new ArrayList<>();
    private boolean rw = false;
    private boolean r = false;
    private HashMap<String, String> nodes = new HashMap<>();
    private String nameText = "";
    private TreeMap<String, TreeNode> treeMap = new TreeMap<>();

    public FoldersEdit() {

    }

    @PostConstruct
    public void lagTre() {
        String projectID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID");

        root = new DefaultTreeNode(new Folders("Root", projectID), null);
        root.setExpanded(true);
        if (fF.find(new FoldersPK("Root", projectID)) == null) {
            fF.create(new Folders("Root", projectID));

        }
        this.viewList = fgF.findAllPro(projectID);
        getFoldersFromDB();
        try {

            gru = gF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
        } catch (Exception e) {

        }

        treeMap = new TreeMap<>();
        if (!nodes.isEmpty()) {
            for (String subordinateNodeName : nodes.keySet()) {
                if (subordinateNodeName != null) {
                    TreeNode treeNode = new DefaultTreeNode(new Folders(subordinateNodeName, projectID), root);
//                treeNode.setExpanded(nodeExpanded);
                    treeMap.put(subordinateNodeName, treeNode);
                }
            }

            for (Map.Entry<String, String> entry : nodes.entrySet()) {
                String subordinateNodeName = entry.getKey();
                String superiorNodeName = entry.getValue();
                if (superiorNodeName != null) {
                    setParentFolder(treeMap.get(subordinateNodeName), treeMap.get(superiorNodeName));
                }
            }
        }
    }

    public void getFoldersFromDB() {
        nodes.clear();
        for (Folders f : fF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))) {
            nodes.put(f.getFoldersPK().getFoldername(), f.getParentfolder());
        }
    }

    private void setParentFolder(TreeNode node, TreeNode parent) {
        if (parent != null) {
            node.getParent().getChildren().remove(node);
            parent.getChildren().add(node);
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

    public List<Foldergroups> getViewList() {
        if(selectedNode.getData() == null) return new ArrayList<>();
      if(!this.viewList.isEmpty()){
           for(Foldergroups f : this.viewList) {
               if(!f.getFoldergroupsPK().getFoldername().equalsIgnoreCase(((Folders)selectedNode.getData()).getFoldersPK().getFoldername()))
                   this.viewList.remove(f);
               
           }
      }
        
            if(this.viewList.isEmpty())this.viewList = new ArrayList<>();

        
        return this.viewList;
    }

    public void setViewList(List<Foldergroups> viewList) {
        this.viewList = viewList;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public Folders getFolder() {
        folder = new Folders(new FoldersPK());
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

    public Boolean value(String k) {
        return Boolean.valueOf(k);
    }

    public void delSecGroup(Foldergroups g) {
        fgF.remove(g);
        FacesMessage msg = new FacesMessage("Group Deleted", g.getFoldergroupsPK().getGroupname());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    private void ryddTreNode(TreeNode e) {
        List<TreeNode> slettet = new ArrayList<>();
        if (!e.isLeaf()) {
            for (TreeNode h : e.getChildren()) {
                ryddTreNode(h);
                slettet.add(h);
            }
            for (TreeNode s : slettet) {
                e.getChildren().remove(s);
            }

        }
        if (e.isLeaf()) {
            fF.remove((Folders) e.getData());
            nodes.remove(((Folders) e.getData()).getFoldersPK().getFoldername());
            Folders f = (Folders) e.getData();
            for (Foldergroups g : fgF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))) {
                if (f.getFoldersPK().getFoldername().equalsIgnoreCase(g.getFoldergroupsPK().getFoldername())) {
                    fgF.remove(g);
                }
            }
        }
    }

    public void deleteNode() {

        ryddTreNode(selectedNode);
        fF.remove((Folders) selectedNode.getData());
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);
        treeMap.remove(getName(selectedNode.getData()));
        nodes.remove(getName(selectedNode.getData()));
        selectedNode = null;

    }

    public void addNode() {
        folder.getFoldersPK().setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
        Folders k = (Folders) selectedNode.getData();

        folder.setParentfolder(k.getFoldersPK().getFoldername());

        TreeNode a = new DefaultTreeNode(folder, selectedNode);

        fF.create(folder);

        folder = new Folders(new FoldersPK());
        lagTre();
    }

    public String getName(Object s) {
        if (s == null) {
            return "Select a Node";
        }
        Folders k = (Folders) s;
        return k.getFoldersPK().getFoldername();
    }

    public void addGroups() {
        Folders f = (Folders) selectedNode.getData();
        for (Groups gr : gruSel) {
            try {
                Foldergroups sett = new Foldergroups(f.getFoldersPK().getFoldername(), f.getFoldersPK().getProjectid(), gr.getGroupsPK().getGroupname());
                sett.setRw(Boolean.toString(isRw()));
                sett.setR(Boolean.toString(isR()));
                fgF.create(sett);
            } catch (Exception k) {

            }
        }
        setR(false);
        setRw(false);
        gruSel.clear();

    }
}
