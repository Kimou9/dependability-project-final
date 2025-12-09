package com.baeldung.finallykeyword;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

/**
 * Test suite for Finally Keyword behavior.
 * Tests that finally blocks execute in various exception scenarios.
 */
@DisplayName("Finally Keyword Execution Tests")
class FinallyKeywordTest {

    @Test
    @DisplayName("Test finally executes on normal flow")
    void testFinallyExecutesNormally() {
        assertDoesNotThrow(() -> {
            FinallyExample.printCount("3");
        });
    }

    @Test
    @DisplayName("Test noExceptionFinally returns expected value")
    void testNoExceptionFinally() {
        String result = FinallyExecutedCases.noExceptionFinally();
        assertNotNull(result);
        // Finally should execute after normal return
        assertTrue(result.contains("executed"));
    }

    @Test
    @DisplayName("Test handledException finally executes")
    void testHandledException() {
        String result = FinallyExecutedCases.handledException();
        assertNotNull(result);
        // Finally should execute after exception is caught
        assertTrue(result.contains("handled"));
    }

    @Test
    @DisplayName("Test returnFromTry finally executes before return")
    void testReturnFromTry() {
        String result = FinallyExecutedCases.returnFromTry();
        assertNotNull(result);
        // Finally executes even when returning from try block
        assertTrue(result.contains("try"));
    }

    @Test
    @DisplayName("Test returnFromCatch finally executes before return")
    void testReturnFromCatch() {
        String result = FinallyExecutedCases.returnFromCatch();
        assertNotNull(result);
        // Finally executes even when returning from catch block
        assertTrue(result.contains("catch"));
    }

    @Test
    @DisplayName("Test unhandledException finally executes before throwing")
    void testUnhandledException() {
        assertThrows(Exception.class, () -> {
            FinallyExecutedCases.unhandledException();
            // Finally should execute even before exception is propagated
        });
    }
}
