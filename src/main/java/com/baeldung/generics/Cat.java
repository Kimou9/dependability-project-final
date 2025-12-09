package com.baeldung.generics;

import java.util.Objects;

/**
 * Cat class extending Animal and implementing Comparable.
 * 
 * @invariant name.length() >= 0
 */
class Cat extends Animal implements Comparable<Cat> {
    
    /**
     * @requires type != null && name != null
     * @ensures this.type == type && this.name == name
     */
    public Cat(String type, String name) {
        super(type, name);
    }

    /**
     * @ensures \result.equals("Meow")
     */
    @Override
    public String makeSound() {
        return "Meow";
    }

    /**
     * Warning: Inconsistent with the equals method.
     * 
     * @requires cat != null
     * @ensures \result == this.getName().length() - cat.getName().length()
     */
    @Override
    public int compareTo(Cat cat) {
        return this.getName().length() - cat.getName().length();
    }

    @Override
    public String toString() {
        return "Cat{" + "type='" + type + '\'' + ", name='" + name + '\'' + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }

    /**
     * @requires o != null
     * @ensures \result == (o == this || (o instanceof Cat && 
     *          type.equals(((Cat)o).type) && name.equals(((Cat)o).name)))
     */
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Cat)) return false;
        Cat cat = (Cat) o;
        return type.equals(cat.type) && name.equals(cat.name);
    }
}
