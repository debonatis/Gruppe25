/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistClasses;

import entityModels.Foldergroups;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author simond
 */
@Stateless
public class FolderGroupsFacade extends AbstractFacade<Foldergroups> {

    @PersistenceContext(unitName = "com.Gruppe25_AteaAOS-web_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FolderGroupsFacade() {
        super(Foldergroups.class);
    }

    public List<Foldergroups> FindAllfolder(String foldername) {
        javax.persistence.Query q = getEntityManager().createNamedQuery("Foldergroups.findByFoldername", Foldergroups.class);
        q.setParameter("foldername", foldername);
        return q.getResultList();
    }
}
