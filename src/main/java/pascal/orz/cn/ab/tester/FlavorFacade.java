/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pascal.orz.cn.ab.tester;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hnakada
 */
@Stateless
public class FlavorFacade extends AbstractFacade<Flavor> {
    @PersistenceContext(unitName = "pascal.orz.cn_AB-Tester_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FlavorFacade() {
        super(Flavor.class);
    }
    
}
