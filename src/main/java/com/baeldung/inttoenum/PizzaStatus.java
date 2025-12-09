package com.baeldung.inttoenum;

import java.util.HashMap;
import java.util.Map;

/**
 * Pizza Status enum with integer mapping.
 * Demonstrates integer to enum conversion techniques.
 * 
 * @invariant timeToDelivery >= 0
 */
public enum PizzaStatus {
    ORDERED(5),
    READY(2),
    DELIVERED(0);

    private int timeToDelivery;

    /**
     * @requires timeToDelivery >= 0
     * @ensures this.timeToDelivery == timeToDelivery
     */
    PizzaStatus(int timeToDelivery) {
        this.timeToDelivery = timeToDelivery;
    }

    /**
     * @ensures \result >= 0
     */
    public int getTimeToDelivery() {
        return timeToDelivery;
    }

    private static Map<Integer, PizzaStatus> timeToDeliveryToEnumValuesMapping = new HashMap<>();

    static {
        for (PizzaStatus pizzaStatus : PizzaStatus.values()) {
            timeToDeliveryToEnumValuesMapping.put(
                pizzaStatus.getTimeToDelivery(),
                pizzaStatus
            );
        }
    }

    /**
     * Converts an integer to PizzaStatus enum.
     * 
     * @requires timeToDelivery >= 0
     * @ensures \result != null || \result == null
     */
    public static PizzaStatus castIntToEnum(int timeToDelivery) {
        return timeToDeliveryToEnumValuesMapping.get(timeToDelivery);
    }
}
