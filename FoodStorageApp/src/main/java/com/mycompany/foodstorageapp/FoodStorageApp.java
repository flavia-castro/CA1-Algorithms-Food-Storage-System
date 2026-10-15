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
            System.out.println("2. Remove food item (first in)");
            System.out.println("3. Show next item to be removed");
            System.out.println("4. Display all items");
            System.out.println("5. Search for a food item");
            System.out.println("6. Exit");
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
                    removeItem(storage);
                    break;
                case 3:
                    peekItem(storage);
                    break;
                case 4:
                    storage.display();
                    break;
                case 5:
                    searchItem(sc, storage);
                    break;
                case 6:
                    System.out.println("Closing the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option! Choose a number from 1 to 6.");
            }

        } while (option != 6);

        sc.close();
    }

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

    // FIFO
    private static void removeItem(QueueStorage storage) {
        try {
            FoodItem removed = storage.remove();
            System.out.println("\nItem removed:");
            System.out.println("----------------------");
            System.out.println(removed);
            System.out.println("----------------------");
            System.out.println("Items left in storage: " + storage.size());
        } catch (StorageEmptyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void peekItem(QueueStorage storage) {
        try {
            FoodItem next = storage.peek();
            System.out.println("\nNext item to be removed:");
            System.out.println("----------------------");
            System.out.println(next);
            System.out.println("----------------------");
        } catch (StorageEmptyException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchItem(Scanner sc, QueueStorage storage) {

        if (storage.isEmpty()) {
            System.out.println("The storage is empty. There is nothing to search.");
            return;
        }

        System.out.print("Enter the food name to search: ");
        String name = sc.nextLine();

        if (!FoodItem.isValidName(name)) {
            System.out.println("Invalid food! Choose: Burger, Pizza, Fries, Sandwich or Hotdog.");
            return;
        }

        if (storage.search(name)) {
            System.out.println(FoodItem.findFoodType(name) + " is in the storage.");
        } else {
            System.out.println(FoodItem.findFoodType(name) + " is not in the storage.");
        }
    }
}
