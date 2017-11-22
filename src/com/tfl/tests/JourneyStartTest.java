package com.tfl.tests;

import com.tfl.billing.JourneyEvent;
import com.tfl.billing.JourneyStart;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

public class JourneyStartTest {
    UUID CARD_ID = UUID.randomUUID();
    UUID READER_ID = UUID.randomUUID();

    
    JourneyEvent start_journey = new JourneyStart(CARD_ID, READER_ID);
    
    @Test
    public void checksReturnOfCardID()
    {
        assertThat(start_journey.cardId(), is(CARD_ID));

    }

}