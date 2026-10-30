# CA1-Algorithms-Food-Storage-System - Algorithms and Constructs - Food Storage System 

## Team 
- Flavia Castro 2024587
– Sissyane Rodrigues 2024410

# Project Overview
Java console application that simulates a fast food storage system that holds a maximum of 8 trays.
Accepts 5 types of food: Burger, Pizza, Fries, Sandwich and Hotdog. Each one is stored with its name, weight, and the time and date it was added to the list with the best-before date. 

# Data Structure 
The system is based on a Circular Queue (FIFO – First In, First Out), meaning that the oldest food will be taken out first.
A fixed array of 8 positions stores the items using circular indexing such that empty slots are reused without moving items in the fixed array.

## Classes 
-FoodItem: Stores and validates food details
-StorageInterface: Defines storage operations
-QueueStorage: Implements the circular queue
-StorageFullException / StorageEmptyException: Handle storage errors
-FoodStorageApp: Main application and user menu
-StorageTest: Tests the storage operations

## Time Complexity | Big-O
The time complexity for moving the front/rear pointer in Add, Remove and Peek operations of the circular queue should be O(1). On the contrary, methods like Display or Search have time complexity O(n) as it involves all items inside the queue.

## Validation 
Only the five specified food types will be accepted.
The weight should be within 50-2000 grams.
Best-before date should be within the next 14 days (YYYY-MM-DD).
The full and empty storage are managed by custom exceptions.