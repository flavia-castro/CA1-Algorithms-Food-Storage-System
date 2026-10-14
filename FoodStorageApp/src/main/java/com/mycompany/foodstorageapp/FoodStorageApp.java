/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.foodstorageapp;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author flavia and sissy
 */
public class FoodStorageApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        QueueStorage storage = new QueueStorage();
        int option = 0;

        do {
            System.out.println("\n===== FOOD STORAGE MENU =====");
            System.out.println("1. Add food item");
            System.out.println("2. Display all items");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid option! Please enter a number.");
                continue;
            }

            switch (option) {
                case 1:
                    addItem(sc, storage);
                    break;
                case 2:
                    storage.display();
                    break;
                case 3:
                    System.out.println("Closing the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option! Choose a number from the menu.");
            }

        } while (option != 3);

        sc.close();
    }

    // Asks the user for the food details and adds it to the storage
    private static void addItem(Scanner sc, QueueStorage storage) {

        if (storage.isFull()) {
            System.out.println("The storage is full. Remove an item first.");
            return;
        }

        System.out.println("\nEnter details for the food item");

        System.out.print("Name (Burger, Pizza, Fries, Sandwich, Hotdog): ");
        String name = sc.nextLine();

        if (!FoodItem.isValidName(name)) {
            System.out.println("Invalid food! Choose: Burger, Pizza, Fries, Sandwich or Hotdog.");
            return;
        }

        System.out.print("Weight (grams): ");
        int weight;
        try {
            weight = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid weight! Please enter a whole number.");
            return;
        }

        System.out.print("Best-before (YYYY-MM-DD, max 2 weeks): ");
        LocalDate date;
        try {
            date = LocalDate.parse(sc.nextLine());
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date! Use the format YYYY-MM-DD.");
            return;
        }

        try {
            FoodItem item = new FoodItem(name, weight, date);
            storage.add(item);
            System.out.println("Item added! Total in storage: " + storage.size());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (StorageFullException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}