/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaeetutorial.dukesbookstore.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Rose_NetBeansFish
 */
@Singleton
@Startup
public class TaxConfigBean {

    @EJB
    private TaxRequestBean request;
    
    @PostConstruct
    public void createData() {

        request.createTax("Alabama",0.04 );

        request.createTax("Alaska",0.0 );

        request.createTax("Arizona",0.06 );

        request.createTax("Arkansas",0.07 );

        request.createTax("California",0.08 );

        request.createTax("Colorado",0.03 );

        request.createTax("Connecticut",0.06 );

        request.createTax("Delaware",0.0 );

        request.createTax("District of Columbia",0.06 );

        request.createTax("Florida",0.06 );

        request.createTax("Georgia",0.04 );

        request.createTax("Hawaii",0.04 );

        request.createTax("Idaho",0.06 );

        request.createTax("Illinois",0.06 );

        request.createTax("Indiana",0.07 );

        request.createTax("Iowa",0.06 );

        request.createTax("Kansas",0.07 );

        request.createTax("Kentucky",0.06 );

        request.createTax("Louisiana",0.04 );

        request.createTax("Maine",0.06 );

        request.createTax("Maryland",0.06 );

        request.createTax("Massachusetts",0.06 );

        request.createTax("Michigan",0.06 );

        request.createTax("Minnesota",0.07 );

        request.createTax("Mississippi",0.07 );

        request.createTax("Missouri",0.04 );

        request.createTax("Montana",0.0 );

        request.createTax("Nebraska",0.06 );

        request.createTax("Nevada",0.07 );

        request.createTax("New Hampshire",0.0 );

        request.createTax("New Jersey",0.07 );

        request.createTax("New Mexico",0.05 );

        request.createTax("New York",0.04 );

        request.createTax("North Carolina",0.05 );

        request.createTax("North Dakota",0.05 );

        request.createTax("Ohio",0.06 );

        request.createTax("Oklahoma",0.05 );

        request.createTax("Oregon",0.0 );

        request.createTax("Pennsylvania",0.06 );

        request.createTax("Puerto Rico",0.06 );

        request.createTax("Rhode Island",0.07 );

        request.createTax("South Carolina",0.06 );

        request.createTax("South Dakota",0.04 );

        request.createTax("Tennessee",0.07 );

        request.createTax("Texas",0.06 );

        request.createTax("Utah",0.06 );

        request.createTax("Vermont",0.06 );

        request.createTax("Virginia",0.05 );

        request.createTax("Washington",0.07 );

        request.createTax("West Virginia",0.06 );

        request.createTax("Wisconsin",0.05 );


    }
}
