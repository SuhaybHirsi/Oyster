package com.tfl.tests;

import com.tfl.billing.*;
import com.tfl.underground.OysterReaderLocator;
import com.tfl.underground.Station;
import org.junit.Test;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;


import java.util.UUID;

import com.oyster.*;
import com.tfl.external.Customer;

import java.math.BigDecimal;
import java.util.*;

import com.oyster.OysterCard;

public class TravelTrackerTest {

    private class ClockTestDouble implements ClockInterface
    {
        ArrayList<Long> myTimes = new ArrayList<>();
        @Override
        public long getTime()
        {
            long temp = myTimes.get(0);
            myTimes.remove(0);

            return  temp;
        }
        public void addTime(long time)
        {
            myTimes.add(time);
        }

    }

    private class CustomerDatabaseTestDouble implements Database
    {
        private List<Customer> customers = new ArrayList<Customer>() {
            {
                this.add(new Customer("Zlatan Ibrahimovic", new OysterCard("38400000-8cf0-11bd-b23e-10b96e4ef00d")));
//                this.add(new Customer("Shelly Cooper", new OysterCard("3f1b3b55-f266-4426-ba1b-bcc506541866")));
//                this.add(new Customer("Oliver Morrell", new OysterCard("07b0bcb1-87df-447f-bf5c-d9961ab9d01e")));
//                this.add(new Customer("Jesse Schmitz", new OysterCard("3b5a03cb-2be6-4ed3-b83e-94858b43e407")));
            }

            public boolean add(Customer customer) {
                return super.add(customer);
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

    private class PaymentSystemTestDouble implements GeneralPaymentsSystem
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

    ClockTestDouble myClock= new ClockTestDouble();
    final Database myCustomerDB = new CustomerDatabaseTestDouble();
    final GeneralPaymentsSystem myPS= new PaymentSystemTestDouble();

    final OysterCard myCard = new OysterCard("38400000-8cf0-11bd-b23e-10b96e4ef00d");

    OysterCardReader paddingtonReader = OysterReaderLocator.atStation(Station.PADDINGTON);
    OysterCardReader bakerStreetReader = OysterReaderLocator.atStation(Station.BAKER_STREET);
    OysterCardReader kingsCrossReader = OysterReaderLocator.atStation(Station.KINGS_CROSS);


    @Test
    public void chargeAccountsForOneTripStartingAtPeakAndEndingAtOffPeak() {


        myClock.addTime(25200000l); //peak
        myClock.addTime(75200000l); //off peak

        TravelTracker travelTracker = new TravelTracker(myCustomerDB, myPS, myClock);

        travelTracker.connect(paddingtonReader, bakerStreetReader, kingsCrossReader);

        paddingtonReader.touch(myCard);

        bakerStreetReader.touch(myCard);


        travelTracker.chargeAccounts();

//        UUID CARD_ID = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
//        UUID START_READER_ID = UUID.randomUUID();
//        UUID END_READER_ID = UUID.randomUUID();

    }

    @Test
    public void connect() {
    }

    @Test
    public void cardScanned() {


    }

}


