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
import entityModels.Logging;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import persistClasses.FolderGroupsFacade;
import persistClasses.FoldersFacade;
import persistClasses.GroupsFacade;
import persistClasses.LoggingFacade;

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
    @EJB
    private LoggingFacade lF;
    private Folders folder = new Folders(new FoldersPK("Empty", "Empty"));
    private TreeNode selectedNode = new DefaultTreeNode(folder, root);

    private List<Groups> gruSel = new ArrayList<>();
    private List<Groups> gru = new ArrayList<>();

    private boolean rw = false;
    private boolean r = false;
    private HashMap<String, String> nodes = new HashMap<>();
    private String nameText = "";

    private TreeMap<String, TreeNode> treeMap = new TreeMap<>();
    private Foldergroups selectedFoldergroups;
    private String[] list = {"true", "false"};

    public FoldersEdit() {

    }

    @PostConstruct
    public void lagTre() {
        String projectID = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID");
        if (projectID == null) {
            root = new DefaultTreeNode(new Folders(new FoldersPK("Root", "null")), null);
            root.setExpanded(true);
        } else {
            root = new DefaultTreeNode(new Folders(new FoldersPK("Root", projectID)), null);
            root.setExpanded(true);
            if (fF.find(new FoldersPK("Root", projectID)) == null) {
                fF.create(new Folders("Root", projectID));

            }
            List<Foldergroups> k = fgF.findAllPro(projectID);
            List<Folders> l = fF.findAllPro(projectID);

            getFoldersFromDB();
            try {

                gru = gF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
            } catch (Exception e) {

            }

            treeMap = new TreeMap<>();
            if (!nodes.isEmpty()) {
                for (String subordinateNodeName : nodes.keySet()) {
                    if (subordinateNodeName != null) {
                        TreeNode treeNode = new DefaultTreeNode(new Folders(new FoldersPK(subordinateNodeName, projectID)), root);
                        treeNode.setExpanded(true);
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
    }

    public void refresh() {
        String proString = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID");
        root = new DefaultTreeNode(new Folders(new FoldersPK("Root", proString)), null);
        root.setExpanded(true);
        treeMap = new TreeMap<>();
        nodes = new HashMap<>();
        getFoldersFromDB();
        if (!nodes.isEmpty()) {
            for (String subordinateNodeName : nodes.keySet()) {
                if (subordinateNodeName != null) {
                    TreeNode treeNode = new DefaultTreeNode(new Folders(new FoldersPK(subordinateNodeName, proString)), root);
                    treeNode.setExpanded(true);
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

    public String[] getList() {
        return list;
    }

    public void setList(String[] list) {
        this.list = list;
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

    public Foldergroups getSelectedFoldergroups() {
        return selectedFoldergroups;
    }

    public void setSelectedFoldergroups(Foldergroups selectedFoldergroups) {
        this.selectedFoldergroups = selectedFoldergroups;
    }
    public void setFg(Foldergroups sel){
        selectedFoldergroups=sel;
    }

    public void delSecGroup(Foldergroups g) {
        fgF.remove(g);
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", g.getFoldergroupsPK().getGroupname() + " has been removed from: " + g.getFoldergroupsPK().getFoldername() + ""));
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
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", ((Folders) selectedNode.getData()).getFoldersPK().getFoldername() + " and its subfolders is removed."));
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);
        treeMap.remove(getName(selectedNode.getData()));
        nodes.remove(getName(selectedNode.getData()));
        selectedNode = null;
        refresh();

    }

    public void addNode() {
        folder.getFoldersPK().setProjectid((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"));
        Folders k = (Folders) selectedNode.getData();

        folder.setParentfolder(k.getFoldersPK().getFoldername());

        TreeNode a = new DefaultTreeNode(folder, selectedNode);

        fF.create(folder);
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", folder.getFoldersPK().getFoldername() + " has been created."));
        folder = new Folders(new FoldersPK());
        refresh();

    }

    public List<Foldergroups> getGrList(Object s) {
        List<Foldergroups> gro = new ArrayList<>();
        if (s == null) {
            return gro;
        }
        Folders k = (Folders) s;
        for (Foldergroups go : fgF.findAllPro((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"))) {
            if (k.getFoldersPK().getFoldername().equalsIgnoreCase(go.getFoldergroupsPK().getFoldername())) {
                gro.add(go);
            }
        }
        return gro;
    }

    public void deleteItemFoGR(Object g, Foldergroups e) {

        if (g == null) {
            return;
        }
        Folders k = (Folders) g;
        Foldergroups fos = new Foldergroups(k.getFoldersPK().getFoldername(), (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("projectID"), e.getFoldergroupsPK().getGroupname());
        fos.setR(e.getR());
        fos.setRw(e.getRw());
        fgF.remove(fos);
        lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", fos.getFoldergroupsPK().getGroupname() + " has been removed from: " + fos.getFoldergroupsPK().getFoldername() + ""));
    }

    public String getName(Object s) {
        if (s == null) {
            try {
                return ((Folders) selectedNode.getData()).getFoldersPK().getFoldername();
            } catch (NullPointerException e) {
                return "Root";
            }
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
                lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", sett.getFoldergroupsPK().getGroupname() + " has been added to: " + sett.getFoldergroupsPK().getFoldername() + ""));
            } catch (Exception k) {

            }
        }
        setR(false);
        setRw(false);
        gruSel.clear();
        refresh();

    }

    public void onEdit() {

        try {

            fgF.edit(getSelectedFoldergroups());
            lF.create(new Logging(new Date(System.currentTimeMillis()), FacesContext.getCurrentInstance().getExternalContext().getRemoteUser(), getClass().getName(), "INFO", selectedFoldergroups.getFoldergroupsPK().getGroupname() + " has been edited in: " + selectedFoldergroups.getFoldergroupsPK().getFoldername() + ""));
            selectedFoldergroups = null;
            refresh();
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Group edited sucsessfully!");
            msg.setDetail(" ");

            FacesContext.getCurrentInstance().addMessage(null, msg);

        } catch (Exception e) {
            FacesMessage msg = new FacesMessage();
            msg.setSeverity(FacesMessage.SEVERITY_INFO);
            msg.setSummary("Group not edited!");
            msg.setDetail("Maybe faulty inputs?");

            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

}
