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

        // Empty storage
        storage.display();

        storage.add(new FoodItem("Burger", 250, LocalDate.now().plusDays(7)));
        storage.add(new FoodItem("Pizza", 800, LocalDate.now().plusDays(3)));
        storage.add(new FoodItem("Fries", 150, LocalDate.now().plusDays(2)));
        storage.display();

        System.out.println("Is there Pizza? " + storage.search("Pizza"));
        System.out.println("Is there pizza? " + storage.search("pizza"));
        System.out.println("Is there Hotdog? " + storage.search("Hotdog"));

        // Remove the first one and display again
        System.out.println("Removed: " + storage.remove());
        storage.display();
    }
}