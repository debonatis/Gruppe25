/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistClasses;

import entityModels.Logging;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author simond
 */
@Stateless
public class LoggingFacade extends AbstractFacade<Logging> {

    @PersistenceContext(unitName = "com.Gruppe25_AteaAOS-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoggingFacade() {
        super(Logging.class);
    }

    public void removeAll() {

        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Logging.class));
        List<Logging> res = getEntityManager().createQuery(cq).getResultList();
        for (Logging entity : res) {
            remove(entity);
        }
    }

}
