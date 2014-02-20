/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25.service;

import com.Gruppe25.GroupUsers;
import com.Gruppe25.GroupUsersPK;
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
@Path("com.gruppe25.groupusers")
public class GroupUsersFacadeREST extends AbstractFacade<GroupUsers> {
    @PersistenceContext(unitName = "com.Gruppe25_AteaAOS-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private GroupUsersPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;username=usernameValue;userGroupName=userGroupNameValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        com.Gruppe25.GroupUsersPK key = new com.Gruppe25.GroupUsersPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> username = map.get("username");
        if (username != null && !username.isEmpty()) {
            key.setUsername(username.get(0));
        }
        java.util.List<String> userGroupName = map.get("userGroupName");
        if (userGroupName != null && !userGroupName.isEmpty()) {
            key.setUserGroupName(userGroupName.get(0));
        }
        return key;
    }

    public GroupUsersFacadeREST() {
        super(GroupUsers.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(GroupUsers entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, GroupUsers entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        com.Gruppe25.GroupUsersPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public GroupUsers find(@PathParam("id") PathSegment id) {
        com.Gruppe25.GroupUsersPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<GroupUsers> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<GroupUsers> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
