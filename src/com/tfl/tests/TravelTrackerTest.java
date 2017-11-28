package com.tfl.tests;

import com.tfl.billing.*;
import com.tfl.external.CustomerDatabase;
import org.junit.Test;

import static org.junit.Assert.*;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;


import java.util.UUID;

import com.oyster.*;
import com.tfl.external.Customer;
import com.tfl.external.CustomerDatabase;
import com.tfl.external.PaymentsSystem;

import java.math.BigDecimal;
import java.util.*;

import com.oyster.OysterCard;

public class TravelTrackerTest {

    private class clockTestDouble implements ClockInterface
    {
        ArrayList<Integer> myTimes = new ArrayList<Integer>() {{
            add(25200000);
            add(75200000);
        }};
        @Override
        public long getTime()
        {
            long temp = (long) myTimes.get(0);
            myTimes.remove(0);

            return  temp;
        }

    }

    private class customerDatabaseTestDouble implements Database
    {
        private List<Customer> customers = new ArrayList<Customer>() {
            {
                this.add(new Customer("Zlatan Ibrahimovic", new OysterCard("38400000-8cf0-11bd-b23e-10b96e4ef00d")));
//                this.add(new Customer("Shelly Cooper", new OysterCard("3f1b3b55-f266-4426-ba1b-bcc506541866")));
//                this.add(new Customer("Oliver Morrell", new OysterCard("07b0bcb1-87df-447f-bf5c-d9961ab9d01e")));
//                this.add(new Customer("Jesse Schmitz", new OysterCard("3b5a03cb-2be6-4ed3-b83e-94858b43e407")));
//                this.add(new Customer("Jenny King", new OysterCard("89adbd1c-4de6-40e5-98bc-b3315c6873f2")));
//                this.add(new Customer("Danny Conner", new OysterCard("609e72ac-8be3-4476-8b45-01db8c7f122b")));
//                this.add(new Customer("Fern Taylor", new OysterCard("ffa3f53a-e225-43ea-b854-5130a1fa016d")));
//                this.add(new Customer("Logan Wakefield", new OysterCard("cd5e097d-e2f1-4bc4-bf7b-d47903d89441")));
//                this.add(new Customer("Angela Harris", new OysterCard("365d7f7d-0ff5-4f87-a51d-f6443a36d035")));
//                this.add(new Customer("John Smith", new OysterCard("0c79d9e0-3874-4b02-9492-80c232a07640")));
//                this.add(new Customer("Mary Clarke", new OysterCard("34bbfc54-916b-42b2-a6d8-5ec2eaf7dd7a")));
//                this.add(new Customer("Emily Scott", new OysterCard("267b3378-592d-4da7-825d-3252982d49ba")));
            }

            public boolean add(Customer customer) {
                return Math.random() > 0.2D ? super.add(customer) : false;
            }
        };


        @Override
        public List<Customer> getCustomers() {
            return customers;
        }

        @Override
        public boolean isRegisteredId(UUID cardId) {
            return true;
        }
    }

    private class paymentSystemTestDouble implements GeneralPaymentsSystem
    {

        @Override
        public void charge(Customer customer, List<Journey> journeys, BigDecimal totalBill) {
            System.out.println("\n\n*****************\n\n");
            System.out.println("Customer: " + customer.fullName() + " - " + customer.cardId());
            System.out.println("Journey Summary:");
            Iterator i$ = journeys.iterator();


            System.out.println("Total charge £: " + totalBill);
        }
    }

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();



    @Test
    public void chargeAccounts() {
        ClockInterface myClock= new clockTestDouble();
        Database myCustomerDB = new customerDatabaseTestDouble();
        GeneralPaymentsSystem myPS= new paymentSystemTestDouble();
//
//        new TravelTracker(myCustomerDB, myPS, myClock).chargeAccounts();
        TravelTracker myTravelTracker = new TravelTracker(myCustomerDB, myPS, myClock);
        UUID CARD_ID = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
        UUID START_READER_ID = UUID.randomUUID();
        UUID END_READER_ID = UUID.randomUUID();
        myTravelTracker.cardScanned(CARD_ID, START_READER_ID);
        myTravelTracker.cardScanned(CARD_ID,END_READER_ID);
        myTravelTracker.chargeAccounts();
    }

    @Test
    public void connect() {
    }

    @Test
    public void cardScanned() {
        ClockInterface myClock= new clockTestDouble();
        Database myCustomerDB = new customerDatabaseTestDouble();
        GeneralPaymentsSystem myPS= new paymentSystemTestDouble();

    }

}


