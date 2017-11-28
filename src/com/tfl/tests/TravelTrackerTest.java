package com.tfl.tests;

import com.tfl.billing.TravelTracker;
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


public class TravelTrackerTest {


    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();



    @Test
    public void chargeAccounts() {


        new TravelTracker().chargeAccounts();
    }

    @Test
    public void connect() {
    }

    @Test
    public void cardScanned() {
    }

}


