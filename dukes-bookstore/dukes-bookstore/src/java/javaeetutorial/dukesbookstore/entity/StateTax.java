/*
 * The entity class for representing StateTax object.  
 */
package javaeetutorial.dukesbookstore.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Rose_NetBeansFish
 */
@Entity
@Table(name = "WEB_BOOKSTORE_TAXES")
@NamedQuery(
        name = "findTaxes",
        query = "SELECT t FROM StateTax t ORDER BY t.taxState")
public class StateTax implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String taxState;// the id to access the StateTax record from database.
    private Double tax;//the tax value in different states
    
    public StateTax() {
    }

    public StateTax(String taxState,Double tax) {
        this.taxState = taxState;
        this.tax = tax;
    }
    public String getTaxState() {
        return taxState;
    }

    public void setTaxState(String taxState) {
        this.taxState = taxState;
    }
/**
     * @return the tax
     */
    public Double getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(Double tax) {
        this.tax = tax;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxState != null ? taxState.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the taxState fields are not set
        if (!(object instanceof StateTax)) {
            return false;
        }
        StateTax other = (StateTax) object;
        if ((this.taxState == null && other.taxState != null) || (this.taxState != null && !this.taxState.equals(other.taxState))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaeetutorial.dukesbookstore.entity.StateTax[ id=" + taxState + " ]";
    }
    
}
