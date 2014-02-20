/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.Gruppe25.session;

import com.Gruppe25.entity.Printers;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author simond
 */
@Stateless
public class PrintersFacade extends AbstractFacade<Printers> {
    @PersistenceContext(unitName = "com.Gruppe25_AteaAOS-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrintersFacade() {
        super(Printers.class);
    }
    
}
