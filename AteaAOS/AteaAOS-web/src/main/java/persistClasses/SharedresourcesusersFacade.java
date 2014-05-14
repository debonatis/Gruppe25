/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistClasses;

import entityModels.Sharedresourcesusers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Martin
 */
@Stateless
public class SharedresourcesusersFacade extends AbstractFacade<Sharedresourcesusers> {
    @PersistenceContext(unitName = "com.Gruppe25_AteaAOS-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SharedresourcesusersFacade() {
        super(Sharedresourcesusers.class);
    }
    
    @Override
    public List<Sharedresourcesusers> findAllPro(String projectID) {
        
        javax.persistence.Query q = getEntityManager().createNamedQuery(""+Sharedresourcesusers.class.getName().substring(13)+".findByProjectiddisp", Sharedresourcesusers.class);
        q.setParameter("projectiddisp", projectID);
       List<Sharedresourcesusers> res = q.getResultList();
        return res;
    }
    
}


