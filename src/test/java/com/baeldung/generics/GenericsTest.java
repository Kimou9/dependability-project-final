package com.baeldung.generics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Test suite for Generics module.
 * Tests bounded type parameters, wildcards, and generic utility methods.
 */
@DisplayName("Generics Tests")
class GenericsTest {

    @Test
    @DisplayName("Test animal creation and methods")
    void testAnimalCreation() {
        Dog dog = new Dog("Canine", "Rex");
        Cat cat = new Cat("Feline", "Whiskers");

        assertEquals("Canine", dog.getType());
        assertEquals("Rex", dog.getName());
        assertEquals("Wuf", dog.makeSound());

        assertEquals("Feline", cat.getType());
        assertEquals("Whiskers", cat.getName());
        assertEquals("Meow", cat.makeSound());
    }

    @Test
    @DisplayName("Test cat compareTo based on name length")
    void testCatCompareTo() {
        Cat cat1 = new Cat("Feline", "Tom");
        Cat cat2 = new Cat("Feline", "Garfield");

        assertTrue(cat1.compareTo(cat2) < 0); // "Tom" length 3 < "Garfield" length 8
        assertEquals(0, cat1.compareTo(new Cat("Feline", "Tom")));
    }

    @Test
    @DisplayName("Test cat equality")
    void testCatEquality() {
        Cat cat1 = new Cat("Feline", "Whiskers");
        Cat cat2 = new Cat("Feline", "Whiskers");
        Cat cat3 = new Cat("Feline", "Tom");

        assertEquals(cat1, cat2);
        assertNotEquals(cat1, cat3);
    }

    @Test
    @DisplayName("Test collection swap method")
    void testCollectionSwap() {
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        CollectionUtils.swap(list, 0, 2);

        assertEquals("C", list.get(0));
        assertEquals("A", list.get(2));
    }

    @Test
    @DisplayName("Test merge with type parameters")
    void testMergeTypeParameter() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);

        List<Integer> merged = CollectionUtils.mergeTypeParameter(list1, list2);

        assertEquals(6, merged.size());
        assertTrue(merged.contains(1));
        assertTrue(merged.contains(6));
    }

    @Test
    @DisplayName("Test sum with numbers")
    void testSum() {
        List<Number> numbers = Arrays.asList(1L, 2, 3.0, 4);
        long sum = CollectionUtils.sum(numbers);

        assertEquals(10L, sum);
    }

    @Test
    @DisplayName("Test addNumber to super list")
    void testAddNumber() {
        List<Number> list = new ArrayList<>();
        CollectionUtils.addNumber(list, 42);

        assertEquals(1, list.size());
        assertTrue(list.contains(42));
    }

    @Test
    @DisplayName("Test generic print does not throw exception")
    void testGenericPrint() {
        assertDoesNotThrow(() -> {
            CollectionUtils.print("Test");
            CollectionUtils.print(123);
            CollectionUtils.print(new Cat("Feline", "Mittens"));
        });
    }
}
