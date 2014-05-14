/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistClasses;

import entityModels.Groupusers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author simond
 */
@Stateless
public class GroupusersFacade extends AbstractFacade<Groupusers> {
    @PersistenceContext(unitName = "com.Gruppe25_AteaAOS-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupusersFacade() {
        super(Groupusers.class);
    }
    @Override
    public List<Groupusers> findAllPro(String projectID) {
        
        javax.persistence.Query q = getEntityManager().createNamedQuery(""+Groupusers.class.getName().substring(13)+".findByProjectidg", Groupusers.class);
        q.setParameter("projectidg", projectID);
       List<Groupusers> res = q.getResultList();
        return res;
    }
    
}
