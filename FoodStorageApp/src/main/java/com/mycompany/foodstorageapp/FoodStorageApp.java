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

        try {
            storage.remove();
        } catch (StorageEmptyException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            storage.peek();
        } catch (StorageEmptyException e) {
            System.out.println("Error: " + e.getMessage());
        }

        for (int i = 1; i <= 9; i++) {
            try {
                storage.add(new FoodItem("Pizza", 800, LocalDate.now().plusDays(3)));
                System.out.println("Item " + i + " added. Total: " + storage.size());
            } catch (StorageFullException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
