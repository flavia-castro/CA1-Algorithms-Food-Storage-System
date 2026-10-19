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
// simple tests for the queue and the validation
public class StorageTest {

    public static void main(String[] args) {

        LocalDate validDate = LocalDate.now().plusDays(5);

        System.out.println("===== TESTS =====\n");

        // test 1 - storage starts empty
        QueueStorage storage = new QueueStorage();
        if (storage.isEmpty()) {
            System.out.println("Test 1 PASS - storage starts empty");
        } else {
            System.out.println("Test 1 FAIL - storage starts empty");
        }

        // test 2 - first in, first out
        storage.add(new FoodItem("Burger", 200, validDate));
        storage.add(new FoodItem("Pizza", 300, validDate));
        FoodItem removed = storage.remove();
        if (removed.getName().equals("Burger")) {
            System.out.println("Test 2 PASS - first item in is the first out");
        } else {
            System.out.println("Test 2 FAIL - first item in is the first out");
        }

        // test 3 - peek shows the front but doesn't remove it
        FoodItem front = storage.peek();
        if (front.getName().equals("Pizza") && storage.size() == 1) {
            System.out.println("Test 3 PASS - peek doesn't remove the item");
        } else {
            System.out.println("Test 3 FAIL - peek doesn't remove the item");
        }

        // test 4 - search
        if (storage.search("pizza") && !storage.search("Hotdog")) {
            System.out.println("Test 4 PASS - search finds Pizza and not Hotdog");
        } else {
            System.out.println("Test 4 FAIL - search finds Pizza and not Hotdog");
        }

        // test 5 - can't add more than 8
        while (!storage.isFull()) {
            storage.add(new FoodItem("Fries", 150, validDate));
        }
        try {
            storage.add(new FoodItem("Fries", 150, validDate));
            System.out.println("Test 5 FAIL - 9th item was added");
        } catch (StorageFullException e) {
            System.out.println("Test 5 PASS - 9th item rejected: " + e.getMessage());
        }

        // test 6 - remove from empty storage
        QueueStorage emptyStorage = new QueueStorage();
        try {
            emptyStorage.remove();
            System.out.println("Test 6 FAIL - removed from empty storage");
        } catch (StorageEmptyException e) {
            System.out.println("Test 6 PASS - empty storage: " + e.getMessage());
        }

        // test 7 - invalid food name
        try {
            new FoodItem("Taco", 200, validDate);
            System.out.println("Test 7 FAIL - Taco was accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 7 PASS - " + e.getMessage());
        }

        // test 8 - invalid weight
        try {
            new FoodItem("Pizza", 5000, validDate);
            System.out.println("Test 8 FAIL - 5000g was accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 8 PASS - " + e.getMessage());
        }

        // test 9 - date more than 2 weeks
        try {
            new FoodItem("Hotdog", 150, LocalDate.now().plusDays(20));
            System.out.println("Test 9 FAIL - date after 2 weeks was accepted");
        } catch (IllegalArgumentException e) {
            System.out.println("Test 9 PASS - " + e.getMessage());
        }
    }
}
