/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorageapp;

import java.time.LocalDate;

/**
 *
 * @author flaviacastro and sissy
 */
// Runs automatic tests on QueueStorage and FoodItem and shows PASS or FAIL for each one
public class StorageTest {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        System.out.println("===== RUNNING TESTS =====\n");

        // Each test is inside try/catch, so an unexpected error shows FAIL
        // instead of stopping all the other tests
        try { testNewStorageIsEmpty(); } catch (Exception e) { crashed("New storage is empty", e); }
        try { testAddIncreasesSize(); } catch (Exception e) { crashed("Adding 2 items makes size 2", e); }
        try { testFifoOrder(); } catch (Exception e) { crashed("Items are removed in FIFO order", e); }
        try { testPeekDoesNotRemove(); } catch (Exception e) { crashed("Peek does not remove", e); }
        try { testSearch(); } catch (Exception e) { crashed("Search", e); }
        try { testStorageFull(); } catch (Exception e) { crashed("Storage full", e); }
        try { testRemoveFromEmptyStorage(); } catch (Exception e) { crashed("Remove from empty storage", e); }
        try { testPeekOnEmptyStorage(); } catch (Exception e) { crashed("Peek on empty storage", e); }
        try { testCircularQueue(); } catch (Exception e) { crashed("Circular queue", e); }
        try { testInvalidName(); } catch (Exception e) { crashed("Invalid name", e); }
        try { testInvalidWeight(); } catch (Exception e) { crashed("Invalid weight", e); }
        try { testInvalidDates(); } catch (Exception e) { crashed("Invalid dates", e); }
        try { testNameIsNormalized(); } catch (Exception e) { crashed("Name is normalized", e); }

        System.out.println("\n===== RESULT =====");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
    }

    // Prints PASS or FAIL and counts the result
    private static void check(String testName, boolean condition) {
        if (condition) {
            System.out.println("PASS - " + testName);
            passed++;
        } else {
            System.out.println("FAIL - " + testName);
            failed++;
        }
    }

    // Used when a test stops because of an unexpected error
    private static void crashed(String testName, Exception e) {
        System.out.println("FAIL - " + testName + " (unexpected error: " + e.getMessage() + ")");
        failed++;
    }

    // Creates a valid food item to use in the tests
    private static FoodItem validItem(String name) {
        return new FoodItem(name, 200, LocalDate.now().plusDays(5));
    }

    // ---------- Normal cases ----------

    private static void testNewStorageIsEmpty() {
        QueueStorage storage = new QueueStorage();
        check("New storage is empty", storage.isEmpty() && storage.size() == 0);
    }

    private static void testAddIncreasesSize() {
        QueueStorage storage = new QueueStorage();
        storage.add(validItem("Burger"));
        storage.add(validItem("Pizza"));
        check("Adding 2 items makes size 2", storage.size() == 2);
    }

    private static void testFifoOrder() {
        QueueStorage storage = new QueueStorage();
        storage.add(validItem("Burger"));
        storage.add(validItem("Pizza"));
        storage.add(validItem("Fries"));
        boolean firstOk = storage.remove().getName().equals("Burger");
        boolean secondOk = storage.remove().getName().equals("Pizza");
        check("Items are removed in FIFO order", firstOk && secondOk);
    }

    private static void testPeekDoesNotRemove() {
        QueueStorage storage = new QueueStorage();
        storage.add(validItem("Hotdog"));
        FoodItem front = storage.peek();
        check("Peek shows the front item without removing it",
                front.getName().equals("Hotdog") && storage.size() == 1);
    }

    private static void testSearch() {
        QueueStorage storage = new QueueStorage();
        storage.add(validItem("Sandwich"));
        check("Search finds an item that is in the storage", storage.search("Sandwich"));
        check("Search ignores upper/lower case", storage.search("sandwich"));
        check("Search does not find an item that is not in the storage", !storage.search("Pizza"));
    }

    // ---------- Edge cases ----------

    private static void testStorageFull() {
        QueueStorage storage = new QueueStorage();
        for (int i = 0; i < StorageInterface.MAX_CAPACITY; i++) {
            storage.add(validItem("Pizza"));
        }
        boolean exceptionThrown = false;
        try {
            storage.add(validItem("Pizza"));
        } catch (StorageFullException e) {
            exceptionThrown = true;
        }
        check("Storage is full after 8 items", storage.isFull());
        check("Adding a 9th item throws StorageFullException", exceptionThrown);
    }

    private static void testRemoveFromEmptyStorage() {
        QueueStorage storage = new QueueStorage();
        boolean exceptionThrown = false;
        try {
            storage.remove();
        } catch (StorageEmptyException e) {
            exceptionThrown = true;
        }
        check("Removing from empty storage throws StorageEmptyException", exceptionThrown);
    }

    private static void testPeekOnEmptyStorage() {
        QueueStorage storage = new QueueStorage();
        boolean exceptionThrown = false;
        try {
            storage.peek();
        } catch (StorageEmptyException e) {
            exceptionThrown = true;
        }
        check("Peek on empty storage throws StorageEmptyException", exceptionThrown);
    }

    // Fills the storage, removes 2 items and adds 2 more,
    // so the rear goes back to the start of the array
    private static void testCircularQueue() {
        QueueStorage storage = new QueueStorage();
        for (int i = 0; i < StorageInterface.MAX_CAPACITY; i++) {
            storage.add(validItem("Pizza"));
        }
        storage.remove();
        storage.remove();
        storage.add(validItem("Burger"));
        storage.add(validItem("Fries"));
        check("Circular queue reuses free trays", storage.isFull() && storage.search("Fries"));
    }

    // ---------- Input validation ----------

    private static void testInvalidName() {
        boolean exceptionThrown = false;
        try {
            new FoodItem("Taco", 200, LocalDate.now().plusDays(5));
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        check("Invalid food name is rejected", exceptionThrown);
    }

    private static void testInvalidWeight() {
        boolean tooLight = false;
        boolean tooHeavy = false;
        try {
            new FoodItem("Pizza", 10, LocalDate.now().plusDays(5));
        } catch (IllegalArgumentException e) {
            tooLight = true;
        }
        try {
            new FoodItem("Pizza", 5000, LocalDate.now().plusDays(5));
        } catch (IllegalArgumentException e) {
            tooHeavy = true;
        }
        check("Weight below the minimum is rejected", tooLight);
        check("Weight above the maximum is rejected", tooHeavy);
    }

    private static void testInvalidDates() {
        boolean pastDate = false;
        boolean tooFar = false;
        try {
            new FoodItem("Fries", 150, LocalDate.now().minusDays(1));
        } catch (IllegalArgumentException e) {
            pastDate = true;
        }
        try {
            new FoodItem("Fries", 150, LocalDate.now().plusDays(15));
        } catch (IllegalArgumentException e) {
            tooFar = true;
        }
        check("Best-before date in the past is rejected", pastDate);
        check("Best-before date more than 14 days ahead is rejected", tooFar);
    }

    private static void testNameIsNormalized() {
        FoodItem item = new FoodItem("  bURGER ", 200, LocalDate.now().plusDays(5));
        check("Food name is saved as 'Burger'", item.getName().equals("Burger"));
    }
}