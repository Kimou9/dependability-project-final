package com.baeldung.inttoenum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import java.util.HashSet;

/**
 * Integration tests for Enum conversion with edge cases.
 */
@DisplayName("Enum Conversion Integration Tests")
class IntToEnumIT {

    @Test
    @DisplayName("Test enum conversion round-trip consistency")
    void testEnumRoundTripConversion() {
        for (PizzaStatus status : PizzaStatus.values()) {
            int timeToDelivery = status.getTimeToDelivery();
            PizzaStatus converted = PizzaStatus.castIntToEnum(timeToDelivery);
            
            assertNotNull(converted);
            assertEquals(status, converted);
            assertEquals(timeToDelivery, converted.getTimeToDelivery());
        }
    }

    @Test
    @DisplayName("Test enum ordering by delivery time")
    void testEnumOrdering() {
        assertTrue(PizzaStatus.DELIVERED.getTimeToDelivery() < 
                  PizzaStatus.READY.getTimeToDelivery());
        assertTrue(PizzaStatus.READY.getTimeToDelivery() < 
                  PizzaStatus.ORDERED.getTimeToDelivery());
    }

    @Test
    @DisplayName("Test boundary values for conversion")
    void testBoundaryValues() {
        // Valid boundaries
        assertNotNull(PizzaStatus.castIntToEnum(0));
        assertNotNull(PizzaStatus.castIntToEnum(2));
        assertNotNull(PizzaStatus.castIntToEnum(5));
        
        // Invalid boundaries
        assertNull(PizzaStatus.castIntToEnum(-1));
        assertNull(PizzaStatus.castIntToEnum(1));
        assertNull(PizzaStatus.castIntToEnum(3));
        assertNull(PizzaStatus.castIntToEnum(100));
    }

    @Test
    @DisplayName("Test all enum values have unique times")
    void testUniqueDeliveryTimes() {
        Set<Integer> times = new HashSet<>();
        for (PizzaStatus status : PizzaStatus.values()) {
            assertTrue(times.add(status.getTimeToDelivery()));
        }
        assertEquals(PizzaStatus.values().length, times.size());
    }
}
