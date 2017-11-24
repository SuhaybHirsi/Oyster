package com.tfl.tests;

import com.tfl.billing.Journey;
import com.tfl.billing.JourneyEnd;
import com.tfl.billing.JourneyEvent;
import com.tfl.billing.JourneyStart;
import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class JourneyTest {


    final UUID CARD_ID = UUID.randomUUID();
    final UUID START_READER_ID = UUID.randomUUID();
    final UUID END_READER_ID = UUID.randomUUID();

    final JourneyEvent JOURNEY_START = new JourneyStart(CARD_ID, START_READER_ID);

    final JourneyEvent JOURNEY_END = new JourneyEnd(CARD_ID, END_READER_ID);

    Journey testJourney = new Journey(JOURNEY_START,JOURNEY_END);


    @Test
    public void originId() {
        assertEquals(testJourney.originId(),(JOURNEY_START.readerId()));

    }

    @Test
    public void destinationId() {
        assertEquals(testJourney.destinationId(),(JOURNEY_END.readerId()));

    }

    @Test
    public void formattedStartTime() {

       assertEquals(testJourney.formattedStartTime(),(SimpleDateFormat.getInstance().format(new Date(JOURNEY_START.time()))));




//        System.out.println(JOURNEY_START.time());
//        System.out.println(JOURNEY_END.time());
//
//        System.out.println(testJourney.formattedStartTime());
//        System.out.println(testJourney.formattedEndTime());
//
//        System.out.println(testJourney.startTime());
//        System.out.println(testJourney.endTime());
//
//
//        System.out.println(testJourney.durationSeconds());
//        System.out.println(testJourney.durationMinutes());

    }

    @Test
    public void formattedEndTime() {
        assertEquals(testJourney.formattedEndTime(),(SimpleDateFormat.getInstance().format(new Date(JOURNEY_END.time()))));
    }

    @Test
    public void startTime() {
        assertTrue(testJourney.startTime().equals(new Date(JOURNEY_START.time())));
    }

    @Test
    public void endTime() {
        assertTrue(testJourney.endTime().equals(new Date(JOURNEY_END.time())));
    }

    @Test
    public void durationSeconds()  {
        assertEquals(testJourney.durationSeconds(), (int) ((JOURNEY_END.time() - JOURNEY_START.time()) / 1000));
    }

    @Test
    public void durationMinutes()  {
        assertEquals(testJourney.durationMinutes(), "" + testJourney.durationSeconds() / 60 + ":" + testJourney.durationSeconds() % 60);
    }

}