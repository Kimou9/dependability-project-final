package com.baeldung.generics;

/**
 * Dog class extending Animal.
 */
public class Dog extends Animal {

    /**
     * @requires type != null && name != null
     * @ensures this.type == type && this.name == name
     */
    public Dog(String type, String name) {
        super(type, name);
    }

    /**
     * @ensures \result.equals("Wuf")
     */
    @Override
    public String makeSound() {
        return "Wuf";
    }
}
