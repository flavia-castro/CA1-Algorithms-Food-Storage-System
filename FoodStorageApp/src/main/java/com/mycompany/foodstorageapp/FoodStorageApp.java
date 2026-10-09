/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.foodstorageapp;

import java.time.LocalDate;

/**
 *
 * @author flaviacastro
 */
public class FoodStorageApp {

    public static void main(String[] args) {
        QueueStorage storage = new QueueStorage();

        System.out.println("Empty? " + storage.isEmpty());

        for (int i = 1; i <= 9; i++) {
            storage.add(new FoodItem("Burger", 250, LocalDate.now().plusDays(7)));
            System.out.println("Items: " + storage.size());
        }

        System.out.println("Full? " + storage.isFull());
    }
}
