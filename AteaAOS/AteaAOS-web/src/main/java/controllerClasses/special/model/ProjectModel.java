/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllerClasses.special.model;

import entityModels.Projects;

/**
 *
 * @author simond
 */
public class ProjectModel {
    private Projects pro;
    private boolean sel;

    public ProjectModel(Projects pro, boolean sel) {
        this.pro = pro;
        this.sel = sel;
    }

    public ProjectModel() {
    }
    

    public Projects getPro() {
        return pro;
    }

    public void setPro(Projects pro) {
        this.pro = pro;
    }

    public boolean isSel() {
        return sel;
    }

    public void setSel(boolean sel) {
        this.sel = sel;
    }
    
    
}
