/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25.service;

import com.Gruppe25.GruppeLevel1;
import com.Gruppe25.GruppeLevel1PK;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.PathSegment;

/**
 *
 * @author simond
 */
@Stateless
@Path("com.gruppe25.gruppelevel1")
public class GruppeLevel1FacadeREST extends AbstractFacade<GruppeLevel1> {
    @PersistenceContext(unitName = "com.Gruppe25_AteaAOS-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private GruppeLevel1PK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;guID=guIDValue;groupName=groupNameValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.Gruppe25.GruppeLevel1PK key = new com.Gruppe25.GruppeLevel1PK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> guID = map.get("guID");
        if (guID != null && !guID.isEmpty()) {
            key.setGuID(guID.get(0));
        }
        java.util.List<String> groupName = map.get("groupName");
        if (groupName != null && !groupName.isEmpty()) {
            key.setGroupName(groupName.get(0));
        }
        return key;
    }

    public GruppeLevel1FacadeREST() {
        super(GruppeLevel1.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(GruppeLevel1 entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, GruppeLevel1 entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.Gruppe25.GruppeLevel1PK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public GruppeLevel1 find(@PathParam("id") PathSegment id) {
        com.Gruppe25.GruppeLevel1PK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<GruppeLevel1> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<GruppeLevel1> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
