/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author simond
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.Gruppe25.service.ApplicationAccessFacadeREST.class);
        resources.add(com.Gruppe25.service.ApplicationsFacadeREST.class);
        resources.add(com.Gruppe25.service.DistributionGroupsFacadeREST.class);
        resources.add(com.Gruppe25.service.DomainsFacadeREST.class);
        resources.add(com.Gruppe25.service.EmailContactsFacadeREST.class);
        resources.add(com.Gruppe25.service.GroupUsersFacadeREST.class);
        resources.add(com.Gruppe25.service.GroupsFacadeREST.class);
        resources.add(com.Gruppe25.service.GruppeLevel1FacadeREST.class);
        resources.add(com.Gruppe25.service.GruppeLevel2FacadeREST.class);
        resources.add(com.Gruppe25.service.GruppeLevel3FacadeREST.class);
        resources.add(com.Gruppe25.service.GruppeLevel4FacadeREST.class);
        resources.add(com.Gruppe25.service.GruppeLevel5FacadeREST.class);
        resources.add(com.Gruppe25.service.GruppeLevel6FacadeREST.class);
        resources.add(com.Gruppe25.service.GruppeLevel7FacadeREST.class);
        resources.add(com.Gruppe25.service.GruppeLevel8FacadeREST.class);
        resources.add(com.Gruppe25.service.GruppeTilhorighetFacadeREST.class);
        resources.add(com.Gruppe25.service.Level1FacadeREST.class);
        resources.add(com.Gruppe25.service.Level2FacadeREST.class);
        resources.add(com.Gruppe25.service.Level3FacadeREST.class);
        resources.add(com.Gruppe25.service.Level4FacadeREST.class);
        resources.add(com.Gruppe25.service.Level5FacadeREST.class);
        resources.add(com.Gruppe25.service.Level6FacadeREST.class);
        resources.add(com.Gruppe25.service.Level7FacadeREST.class);
        resources.add(com.Gruppe25.service.Level8FacadeREST.class);
        resources.add(com.Gruppe25.service.PrintersFacadeREST.class);
        resources.add(com.Gruppe25.service.SharedResourcesFacadeREST.class);
        resources.add(com.Gruppe25.service.UserDistributionFacadeREST.class);
        resources.add(com.Gruppe25.service.UsersFacadeREST.class);
    }
    
}
