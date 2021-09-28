/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaeetutorial.dukesbookstore.entity.StateTax;
import javaeetutorial.dukesbookstore.exception.BooksNotFoundException;
import javaeetutorial.dukesbookstore.exception.TaxesNotFoundException;
import javax.ejb.EJBException;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rose_NetBeansFish
 */
@Stateful
public class TaxRequestBean {

    @PersistenceContext
    private EntityManager em;
    private static final Logger logger =
            Logger.getLogger("dukesbookstore.ejb.TaxRequestBean");

    public TaxRequestBean() throws Exception {
    }

    public void createTax(String taxState, Double price) {
        try {
            StateTax tax = new StateTax(taxState, price);
            logger.log(Level.INFO, "Created book {0}", taxState);
            em.persist(tax);
            logger.log(Level.INFO, "Persisted book {0}", taxState);
        } catch (Exception ex) {
            throw new EJBException(ex.getMessage());
        }
    }
    public List<StateTax> getTaxes() throws TaxesNotFoundException{//BooksNotFoundException {
        try {
            return (List<StateTax>) em.createNamedQuery("findTaxes").getResultList();
        } catch (Exception ex) {
            //throw new BooksNotFoundException(
            throw new TaxesNotFoundException(
                    "Could not get state taxes: " + ex.getMessage());
        }
    }
}
