package com.baeldung.finallykeyword;

/**
 * Demonstrates finally keyword execution patterns.
 */
public class FinallyExample {

    /**
     * Prints count or "No count" with finally block.
     * 
     * @requires count != null
     * @ensures finally block always executes
     */
    public static void printCount(String count) {
        try {
            System.out.println("The count is " + Integer.parseInt(count));
        } catch (NumberFormatException e) {
            System.out.println("No count");
        } finally {
            System.out.println("In finally");
        }
    }
}
