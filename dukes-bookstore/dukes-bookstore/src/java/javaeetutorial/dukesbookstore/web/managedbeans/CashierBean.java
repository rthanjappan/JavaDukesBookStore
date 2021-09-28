/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://java.net/projects/javaeetutorial/pages/BerkeleyLicense
 */
package javaeetutorial.dukesbookstore.web.managedbeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javaeetutorial.dukesbookstore.ejb.BookRequestBean;
import javaeetutorial.dukesbookstore.ejb.TaxRequestBean;
import javaeetutorial.dukesbookstore.entity.StateTax;
import javaeetutorial.dukesbookstore.exception.OrderException;
import javaeetutorial.dukesbookstore.exception.TaxesNotFoundException;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIOutput;
import javax.faces.component.UISelectBoolean;
import javax.faces.model.SelectItem;
import javax.inject.Named;

/**
 * <p>Backing bean for the <code>/bookcashier.xhtml</code> and
 * <code>bookreceipt.xhtml</code> pages.</p>
 */
@Named
@RequestScoped
public class CashierBean extends AbstractBean {

    
    
    private static final long serialVersionUID = -9221440716172304017L;
    @EJB
    BookRequestBean bookRequestBean;
    
    
    
    //variables for name and credit card number
    private String name = null;
    private String creditCardNumber = null;
    
    //implementing tax function
    @EJB
    TaxRequestBean taxRequestBean;
    
    private Double tax=0.0;
    private String taxOption = "1";//the taxState the user selected
    
    List<StateTax> taxItems=null;//to save the StateTax records returned from  DB.
    List<SelectItem> taxOptions;
    
    
    //implementing extra checkout page
    private Double subTotal=0.0;
    //calculating total
    private Double total=0.0;
    
    //implementing shipping option
    private String shippingOption = "2";
    private double shippingCharge=0.0;
    private Date shipDate;
    
    
    
    
    //special offers
    UIOutput specialOfferText = null;
    UISelectBoolean specialOffer = null;
    UIOutput thankYou = null;
    private String stringProperty = "This is a String property";
    
    //newsletters
    private String[] newsletters;
    private static final SelectItem[] newsletterItems = {
        new SelectItem("Duke's Quarterly"),
        new SelectItem("Innovator's Almanac"),
        new SelectItem("Duke's Diet and Exercise Journal"),
        new SelectItem("Random Ramblings")
    };

    /**
     * no-arg constructor
     */
    public CashierBean() {
        this.newsletters = new String[0];
        
    }
    
    /**
     * populates the taxOptions list
     * 
     */
    public void populateTaxOptions()  {
        
        taxOptions = new ArrayList<>();
        
        try{
            //getting the statetax values from the db
            taxItems=taxRequestBean.getTaxes();

            //populating the list of items with taxItems
            for(StateTax taxRecord : taxItems){
                taxOptions.add(new SelectItem(taxRecord.getTaxState(),taxRecord.getTaxState()));
            }
            
        }catch(TaxesNotFoundException ex){
            // then the taxOptions list will be an empty list.
        }
        
    }
    
    /**
     * handling "submit" button click from bookcashier page.
     * @return the "bookcheckout" page
     */
     public String submit() {
         
         
        // Calculate and save the ship date
        int days = Integer.valueOf(shippingOption).intValue();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        setShipDate(cal.getTime());
        
        //setting the subTotal
        setSubTotal(cart.getTotal());
        
        
        //calculating the tax
        String stateNameSelected= getTaxOption();
        double taxSelected=0;
        for(StateTax  taxRecord : taxItems){
            if(taxRecord.getTaxState().equals(stateNameSelected)){
                taxSelected=taxRecord.getTax();
            }
        }
        setTax(taxSelected*cart.getTotal());
        
        //calculating the shipping charges
        switch(days){
            
            case 2:
                setShippingCharge(5.80);
                break;
            case 5:
                setShippingCharge(3.80);
                break;
            case 7:    
                setShippingCharge(1.80);
                break;
        }
        //calculating the total
        setTotal(getSubTotal()+getTax()+getShippingCharge());
        
        
        //special offers
        if ((cart.getTotal() > 100.00) && !specialOffer.isRendered()) {
            specialOfferText.setRendered(true);
            specialOffer.setRendered(true);

            return null;
        } else if (specialOffer.isRendered() && !thankYou.isRendered()) {
            thankYou.setRendered(true);

            return null;
        } else {
            try {
                bookRequestBean.updateInventory(cart);
            } catch (OrderException ex) {
                return "bookordererror";
            }

            
            return ("bookcheckout");
        }
    }
     
     /**
     * handling "submit" button click from "bookcheckout" page.
     * @return the "bookreceipt" page
     */
    public String submitCheckout() {
        // Calculate and save the ship date
        int days = Integer.valueOf(shippingOption).intValue();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, days);
        setShipDate(cal.getTime());

        cart.clear();
        
        return ("bookreceipt");
         
    }
    
    
    //accessors and mutators
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
    /**
     * @return the List of Tax options
     */
    public List<SelectItem> getTaxOptions() {
        populateTaxOptions();
        return this.taxOptions;
    }
    
    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

    /**
     * @return the shippingCharge
     */
    public double getShippingCharge() {
        return shippingCharge;
    }

    /**
     * @param shippingCharge the shippingCharge to set
     */
    public void setShippingCharge(double shippingCharge) {
        this.shippingCharge = shippingCharge;
    }

    /**
     * @return the subTotal
     */
    public Double getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal the subTotal to set
     */
    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    

    
    
    
    public void setNewsletters(String[] newsletters) {
        this.newsletters = newsletters;
    }

    public String[] getNewsletters() {
        return this.newsletters;
    }

    public SelectItem[] getNewsletterItems() {
        return newsletterItems;
    }

    public Date getShipDate() {
        return this.shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public void setShippingOption(String shippingOption) {
        this.shippingOption = shippingOption;
    }

    public String getShippingOption() {
        return this.shippingOption;
    }

    public UIOutput getSpecialOfferText() {
        return this.specialOfferText;
    }

    public void setSpecialOfferText(UIOutput specialOfferText) {
        this.specialOfferText = specialOfferText;
    }

    public UISelectBoolean getSpecialOffer() {
        return this.specialOffer;
    }

    public void setSpecialOffer(UISelectBoolean specialOffer) {
        this.specialOffer = specialOffer;
    }

    public UIOutput getThankYou() {
        return this.thankYou;
    }

    public void setThankYou(UIOutput thankYou) {
        this.thankYou = thankYou;
    }

    public String getStringProperty() {
        return (this.stringProperty);
    }

    public void setStringProperty(String stringProperty) {
        this.stringProperty = stringProperty;
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
    /**
     * @return the taxOption
     */
    public String getTaxOption() {
        return taxOption;
    }

    /**
     * @param taxOption the taxOption to set
     */
    public void setTaxOption(String taxOption) {
        this.taxOption = taxOption;
        
    }


    
}
