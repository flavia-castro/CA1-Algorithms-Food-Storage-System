# CA1-Algorithms-Food-Storage-System CA1 Group Project - Algorithms and Constructs - Java Food Storage System 
## Team 
- Flavia Castro 2024587
– Sissyane Rodrigues 2024410

# About the project 
This is a Java console application designed to imitate the food storage system of a fast-food restaurant. 
The storage has the capacity of up to 8 trays, which can be filled with any of the 5 food items (Burger, Pizza, Fries, Sandwich, Hotdog). 
The storage will keep data about each item such as its name, weight(in grams), expiry date (max 2 weeks) and arriving time into the storage. 

# Data Structure 
This is an implementation of queue (FIFO – First In First Out). 
Food is introduced into one side of the storage and removed from the opposite side, thus the food item that was introduced first will be used first. 
This should prevent food from staying in storage until being expired. 
The implementation of a queue is a circular array of size 8. After reaching the end of the array, it returns back to 0th position, so the items do not need to be moved.

## Classes – 
**FoodItem** - stores the food details and checks if the values are valid 
- **StorageInterface** - lists the operations of the storage 
- **QueueStorage** - the circular queue, implements StorageInterface 
- **StorageFullException / StorageEmptyException** - errors for full and empty storage 
- **FoodStorageApp** - the menu (main) 
- **StorageTest** - simple tests 

## Time Complexity | Operation | Method | Big-O | 
|---|---|---| |
Add (enqueue) | add() | O(1) |
| Remove (dequeue) | remove() | O(1) |
| Peek | peek() | O(1) | 
| Display | display() | O(n) |
| Search | search() | O(n) | 
| isEmpty / isFull / size | | O(1) |

During Add, Remove and Peek operations of the circular queue, one needs to move the front/rear index where the time complexity is O(1). On the contrary, methods like Display or Search have time complexity O(n) as it involves all items inside the queue.

## Validation 
- Food name has to include 5 specified foods (either in uppercase/lowercase) 
- Weight has to be represented as a number in range between 50 and 2000 grams 
- Data of best-before shall have the format of "YYYY-MM-DD" and does not lie in the past while at the most can be in the future by 14 days.
- The error messages in case of storage getting either 8 full items or being empty.

## How to run 
1. Open the project in NetBeans 
2. Run **FoodStorageApp.java** to use the menu 
3. Right-click **StorageTest.java** > Run File to see the tests
<img width="468" height="647" alt="image" src="https://github.com/user-attachments/assets/d53936fd-c37f-4261-8eac-09a36c722a1b" />
