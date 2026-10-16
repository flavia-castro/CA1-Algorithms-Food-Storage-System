/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorageapp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author sissy
 */
public class FoodItem {

    // The 5 food types sold by the restaurant
    public static final String[] FOOD_TYPES = {"Burger", "Pizza", "Fries", "Sandwich", "Hotdog"};

    // Validation limits
    public static final int MIN_WEIGHT = 50;      // grams
    public static final int MAX_WEIGHT = 2000;    // grams
    public static final int MAX_DAYS = 14;        // best-before: maximum of two weeks

    private String name;
    private int weight;               // in grams
    private LocalDate bestBefore;     // expiry date
    private LocalDateTime timeAdded;  // when it was placed in the storage

    public FoodItem(String name, int weight, LocalDate bestBefore) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Invalid food. Choose: Burger, Pizza, Fries, Sandwich or Hotdog.");
        }
        if (weight < MIN_WEIGHT || weight > MAX_WEIGHT) {
            throw new IllegalArgumentException("Invalid weight. It must be between "
                    + MIN_WEIGHT + "g and " + MAX_WEIGHT + "g.");
        }
        if (bestBefore == null || bestBefore.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid best-before date. It cannot be in the past.");
        }
        if (bestBefore.isAfter(LocalDate.now().plusDays(MAX_DAYS))) {
            throw new IllegalArgumentException("Invalid best-before date. Maximum is "
                    + MAX_DAYS + " days from today.");
        }

        this.name = findFoodType(name);
        this.weight = weight;
        this.bestBefore = bestBefore;
        this.timeAdded = LocalDateTime.now();
    }

    // Returns the food type written correctly (e.g. "burger" -> "Burger"),
    // or null if the name is not one of the 5 food types
    public static String findFoodType(String name) {
        if (name == null) {
            return null;
        }
        for (String type : FOOD_TYPES) {
            if (type.equalsIgnoreCase(name.trim())) {
                return type;
            }
        }
        return null;
    }

    // Checks if the name is one of the 5 food types (ignoring upper/lower case)
    public static boolean isValidName(String name) {
        return findFoodType(name) != null;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public LocalDateTime getTimeAdded() {
        return timeAdded;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return "Name: " + name + "\n"
                + "Weight: " + weight + "g\n"
                + "Best Before: " + bestBefore + "\n"
                + "Stored At: " + timeAdded.format(formatter);
    }
}