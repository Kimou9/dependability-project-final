package com.baeldung.generics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

/**
 * Integration tests for Generics with complex scenarios.
 */
@DisplayName("Generics Integration Tests")
class GenericsIT {

    @Test
    @DisplayName("Test complex generic operations with multiple types")
    void testComplexGenericOperations() {
        List<Integer> intList1 = Arrays.asList(1, 2, 3);
        List<Integer> intList2 = Arrays.asList(4, 5, 6);
        List<Integer> merged = CollectionUtils.mergeTypeParameter(intList1, intList2);

        assertEquals(6, merged.size());
        assertTrue(merged.stream().anyMatch(x -> x == 1));
        assertTrue(merged.stream().anyMatch(x -> x == 6));
    }

    @Test
    @DisplayName("Test animal polymorphism with multiple subclasses")
    void testAnimalPolymorphism() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog("Canine", "Rex"));
        animals.add(new Cat("Feline", "Whiskers"));

        assertEquals(2, animals.size());
        assertEquals("Wuf", animals.get(0).makeSound());
        assertEquals("Meow", animals.get(1).makeSound());
    }

    @Test
    @DisplayName("Test cat sorting with multiple instances")
    void testCatSorting() {
        List<Cat> cats = new ArrayList<>(Arrays.asList(
            new Cat("Feline", "Alexander"),
            new Cat("Feline", "Bo"),
            new Cat("Feline", "Cat")
        ));
        Collections.sort(cats);

        assertEquals("Bo", cats.get(0).getName());
        assertEquals("Cat", cats.get(1).getName());
        assertEquals("Alexander", cats.get(2).getName());
    }

    @Test
    @DisplayName("Test multiple swaps maintain list consistency")
    void testMultipleSwaps() {
        List<String> list = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5"));
        CollectionUtils.swap(list, 0, 4);
        CollectionUtils.swap(list, 1, 3);

        assertEquals("5", list.get(0));
        assertEquals("4", list.get(1));
        assertEquals("3", list.get(2));
        assertEquals("2", list.get(3));
        assertEquals("1", list.get(4));
    }
}
