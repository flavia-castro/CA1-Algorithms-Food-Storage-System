/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.foodstorageapp;

import java.time.LocalDate;

/**
 *
 * @author flaviacastro and sissy
 */
public class FoodStorageApp {

    public static void main(String[] args) {
        QueueStorage storage = new QueueStorage();

        //valid item
        tryToAdd(storage, "Burger", 250, LocalDate.now().plusDays(7));

        //invalid items
        tryToAdd(storage, "Taco", 250, LocalDate.now().plusDays(7));        
        tryToAdd(storage, "Pizza", 10, LocalDate.now().plusDays(7));        
        tryToAdd(storage, "Pizza", 5000, LocalDate.now().plusDays(7));      
        tryToAdd(storage, "Fries", 150, LocalDate.now().minusDays(1));     
        tryToAdd(storage, "Fries", 150, LocalDate.now().plusDays(20));

        storage.display();
    }

    //error message if the data is invalid
    private static void tryToAdd(QueueStorage storage, String name, int weight, LocalDate bestBefore) {
        try {
            FoodItem item = new FoodItem(name, weight, bestBefore);
            storage.add(item);
            System.out.println("Added: " + item);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
