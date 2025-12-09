package com.baeldung.generics;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Generic utility methods for collections.
 * Demonstrates bounded type parameters and wildcards.
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * Prints a single item of any type.
     * 
     * @requires item != null
     * @ensures System output contains item.toString()
     */
    public static <T> void print(T item) {
        System.out.println(item);
    }

    /**
     * Swaps two elements in a list.
     * 
     * @requires list != null && 0 <= src < list.size() && 0 <= des < list.size()
     * @ensures list.get(src) == old list.get(des) && list.get(des) == old list.get(src)
     */
    public static void swap(List<?> list, int src, int des) {
        swapHelper(list, src, des);
    }

    /**
     * Helper method for swap using generics.
     */
    private static <E> void swapHelper(List<E> list, int src, int des) {
        list.set(src, list.set(des, list.get(src)));
    }

    /**
     * Merges two lists using type parameter.
     * 
     * @requires listOne != null && listTwo != null
     * @ensures \result.size() == listOne.size() + listTwo.size()
     */
    public static <E> List<E> mergeTypeParameter(List<? extends E> listOne, List<? extends E> listTwo) {
        return Stream.concat(listOne.stream(), listTwo.stream())
            .collect(Collectors.toList());
    }

    /**
     * Merges two lists using wildcard.
     */
    public static <E> List<? extends E> mergeWildcard(List<? extends E> listOne, List<? extends E> listTwo) {
        return Stream.concat(listOne.stream(), listTwo.stream())
            .collect(Collectors.toList());
    }

    /**
     * Sums a list of numbers.
     * 
     * @requires numbers != null
     * @ensures \result >= 0
     */
    public static long sum(List<Number> numbers) {
        return numbers.stream()
            .mapToLong(Number::longValue)
            .sum();
    }

    /**
     * Sums a list of generic numbers using type parameter.
     */
    public static <T extends Number> long sumTypeParameter(List<T> numbers) {
        return numbers.stream()
            .mapToLong(Number::longValue)
            .sum();
    }

    /**
     * Sums a list of generic numbers using wildcard.
     */
    public static long sumWildcard(List<? extends Number> numbers) {
        return numbers.stream()
            .mapToLong(Number::longValue)
            .sum();
    }

    /**
     * Adds a number to a list using super wildcard.
     * 
     * @requires list != null && number != null
     * @ensures list.contains(number)
     */
    public static void addNumber(List<? super Integer> list, Integer number) {
        list.add(number);
    }
}
