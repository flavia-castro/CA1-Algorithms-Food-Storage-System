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

        storage.add(new FoodItem("Burger", 250, LocalDate.now().plusDays(7)));
        storage.add(new FoodItem("Pizza", 800, LocalDate.now().plusDays(3)));
        storage.add(new FoodItem("Fries", 150, LocalDate.now().plusDays(2)));

        System.out.println("Front: " + storage.peek());
        System.out.println("Removed: " + storage.remove());
        System.out.println("Removed: " + storage.remove());
        System.out.println("Front now: " + storage.peek());
        System.out.println("Removed: " + storage.remove());

        // The storage is empty now
        System.out.println("Removed: " + storage.remove());
        System.out.println("Front: " + storage.peek());
    }
}