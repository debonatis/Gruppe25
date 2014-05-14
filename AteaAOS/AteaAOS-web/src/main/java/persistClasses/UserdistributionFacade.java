/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistClasses;

import entityModels.Userdistribution;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author simond
 */
@Stateless
public class UserdistributionFacade extends AbstractFacade<Userdistribution> {
    @PersistenceContext(unitName = "com.Gruppe25_AteaAOS-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserdistributionFacade() {
        super(Userdistribution.class);
    }
    @Override
     public List<Userdistribution> findAllPro(String projectID) {
        
        javax.persistence.Query q = getEntityManager().createNamedQuery(""+Userdistribution.class.getName().substring(13)+".findByProjectidd", Userdistribution.class);
        q.setParameter("projectidd", projectID);
       List<Userdistribution> res = q.getResultList();
        return res;
    }
    
}
