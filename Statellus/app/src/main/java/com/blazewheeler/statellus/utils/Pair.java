package com.blazewheeler.statellus.utils;

import java.util.Objects;

public class Pair {
    private final Float first;   // The first element of the pair
    private final Float second;  // The second element of the pair

    // Constructor to initialize the Pair with given values for first and second elements
    public Pair(Float first, Float second) {
        this.first = first;
        this.second = second;
    }

    // Getter method for retrieving the first element of the pair
    public Float getFirst() {
        return first;
    }

    // Getter method for retrieving the second element of the pair
    public Float getSecond() {
        return second;
    }

    // Override the equals() method to compare two Pair objects for equality
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // If both objects refer to the same memory location, they are equal
        if (o == null || getClass() != o.getClass()) return false; // If the objects are not of the same class, they are not equal
        Pair pair = (Pair) o;  // Cast the Object to Pair type
        // Check if the first and second elements of both Pair objects are equal using Objects.equals method
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    // Override the hashCode() method to generate a hash code for the Pair object
    @Override
    public int hashCode() {
        return Objects.hash(first, second); // Generate hash code based on the first and second elements
    }

    // Override the toString() method to return a String representation of the Pair object
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")"; // Return a String representation of the Pair as (first, second)
    }
}