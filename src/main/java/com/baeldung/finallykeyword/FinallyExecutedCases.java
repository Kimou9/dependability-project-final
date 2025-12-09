package com.baeldung.finallykeyword;

/**
 * Demonstrates when finally block IS executed.
 */
public class FinallyExecutedCases {

    /**
     * Finally executes with no exception.
     * 
     * @ensures finally block executes
     */
    public static String noExceptionFinally() {
        try {
            System.out.println("Inside try");
            return "executed";
        } finally {
            System.out.println("Inside finally");
        }
    }

    /**
     * Finally executes with unhandled exception.
     * 
     * @ensures finally block executes before exception is thrown
     */
    public static void unhandledException() throws Exception {
        try {
            System.out.println("Inside try");
            throw new Exception();
        } finally {
            System.out.println("Inside finally");
        }
    }

    /**
     * Finally executes with handled exception.
     * 
     * @ensures finally block executes
     */
    public static String handledException() {
        try {
            System.out.println("Inside try");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Inside catch");
            return "handled";
        } finally {
            System.out.println("Inside finally");
        }
    }

    /**
     * Finally executes even with return from try.
     * 
     * @ensures \result == "from try" && finally block executes first
     */
    public static String returnFromTry() {
        try {
            System.out.println("Inside try");
            return "from try";
        } finally {
            System.out.println("Inside finally");
        }
    }

    /**
     * Finally executes even with return from catch.
     * 
     * @ensures \result == "from catch" && finally block executes first
     */
    public static String returnFromCatch() {
        try {
            System.out.println("Inside try");
            throw new Exception();
        } catch (Exception e) {
            System.out.println("Inside catch");
            return "from catch";
        } finally {
            System.out.println("Inside finally");
        }
    }
}
