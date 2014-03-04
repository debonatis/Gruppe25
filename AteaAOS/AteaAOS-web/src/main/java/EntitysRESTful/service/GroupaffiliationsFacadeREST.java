/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EntitysRESTful.service;

import Entitys.Groupaffiliations;
import Entitys.GroupaffiliationsPK;
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
@Path("entitys.groupaffiliations")
public class GroupaffiliationsFacadeREST extends AbstractFacade<Groupaffiliations> {
    @PersistenceContext(unitName = "com.Gruppe25_AteaAOS-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private GroupaffiliationsPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;groupnamemany=groupnamemanyValue;groupmambership=groupmambershipValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        Entitys.GroupaffiliationsPK key = new Entitys.GroupaffiliationsPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> groupnamemany = map.get("groupnamemany");
        if (groupnamemany != null && !groupnamemany.isEmpty()) {
            key.setGroupnamemany(groupnamemany.get(0));
        }
        java.util.List<String> groupmambership = map.get("groupmambership");
        if (groupmambership != null && !groupmambership.isEmpty()) {
            key.setGroupmambership(groupmambership.get(0));
        }
        return key;
    }

    public GroupaffiliationsFacadeREST() {
        super(Groupaffiliations.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Groupaffiliations entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") PathSegment id, Groupaffiliations entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        Entitys.GroupaffiliationsPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Groupaffiliations find(@PathParam("id") PathSegment id) {
        Entitys.GroupaffiliationsPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Groupaffiliations> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Groupaffiliations> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
