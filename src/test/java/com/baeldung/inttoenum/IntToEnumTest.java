package com.baeldung.inttoenum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for Integer to Enum conversion.
 * Tests mapping between integer values and enum constants.
 */
@DisplayName("Int to Enum Conversion Tests")
class IntToEnumTest {

    @Test
    @DisplayName("Test enum constants have correct delivery times")
    void testEnumDeliveryTimes() {
        assertEquals(5, PizzaStatus.ORDERED.getTimeToDelivery());
        assertEquals(2, PizzaStatus.READY.getTimeToDelivery());
        assertEquals(0, PizzaStatus.DELIVERED.getTimeToDelivery());
    }

    @Test
    @DisplayName("Test casting integer 5 to ORDERED")
    void testCastIntToOrdered() {
        PizzaStatus status = PizzaStatus.castIntToEnum(5);
        assertNotNull(status);
        assertEquals(PizzaStatus.ORDERED, status);
        assertEquals(5, status.getTimeToDelivery());
    }

    @Test
    @DisplayName("Test casting integer 2 to READY")
    void testCastIntToReady() {
        PizzaStatus status = PizzaStatus.castIntToEnum(2);
        assertNotNull(status);
        assertEquals(PizzaStatus.READY, status);
        assertEquals(2, status.getTimeToDelivery());
    }

    @Test
    @DisplayName("Test casting integer 0 to DELIVERED")
    void testCastIntToDelivered() {
        PizzaStatus status = PizzaStatus.castIntToEnum(0);
        assertNotNull(status);
        assertEquals(PizzaStatus.DELIVERED, status);
        assertEquals(0, status.getTimeToDelivery());
    }

    @Test
    @DisplayName("Test casting invalid integer returns null")
    void testCastIntToInvalidInt() {
        PizzaStatus status = PizzaStatus.castIntToEnum(999);
        assertNull(status);
    }

    @Test
    @DisplayName("Test casting negative integer returns null")
    void testCastNegativeInt() {
        PizzaStatus status = PizzaStatus.castIntToEnum(-1);
        assertNull(status);
    }

    @Test
    @DisplayName("Test all enum values are mappable")
    void testAllEnumValuesAreMappable() {
        for (PizzaStatus status : PizzaStatus.values()) {
            PizzaStatus converted = PizzaStatus.castIntToEnum(status.getTimeToDelivery());
            assertEquals(status, converted);
        }
    }
}
