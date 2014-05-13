/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.model;

import entityModels.Foldergroups;
import java.util.List;

/**
 *
 * @author simond
 */
public class FolderGroupsModel {
    
    private String foldername;
    private List<Foldergroups> grList;

    public FolderGroupsModel(String foldername, List<Foldergroups> grList) {
        this.foldername = foldername;
        this.grList = grList;
    }

    public FolderGroupsModel() {
    }

    public String getFoldername() {
        return foldername;
    }

    public void setFoldername(String foldername) {
        this.foldername = foldername;
    }

    public List<Foldergroups> getGrList() {
        return grList;
    }

    public void setGrList(List<Foldergroups> grList) {
        this.grList = grList;
    }
    
    
    
}
