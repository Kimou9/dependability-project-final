package com.baeldung.generics;

/**
 * Abstract base class for animals.
 * Specification:
 * @invariant type != null && name != null
 */
public abstract class Animal {

    protected final String type;
    protected final String name;

    /**
     * @requires type != null && name != null
     * @ensures this.type == type && this.name == name
     */
    protected Animal(String type, String name) {
        this.type = type;
        this.name = name;
    }

    /**
     * @ensures \result != null
     */
    abstract String makeSound();

    /**
     * @ensures \result != null
     */
    public String getType() {
        return type;
    }

    /**
     * @ensures \result != null
     */
    public String getName() {
        return name;
    }
}
