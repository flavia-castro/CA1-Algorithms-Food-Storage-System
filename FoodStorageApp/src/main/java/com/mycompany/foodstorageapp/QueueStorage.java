/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorageapp;

/**
 *
 * @author sissy
 */
// circular queue using an array (FIFO)
public class QueueStorage implements StorageInterface {

    private FoodItem[] items;
    private int front; // index of first item
    private int count; // number of items

    public QueueStorage() {
        items = new FoodItem[MAX_CAPACITY];
        front = 0;
        count = 0;
    }

    // adds to the back
    @Override
    public void add(FoodItem item) {
        if (isFull()) {
            throw new StorageFullException("The storage is full. Maximum is " + MAX_CAPACITY + " items.");
        }
        int rear = (front + count) % MAX_CAPACITY; // goes back to 0 at the end
        items[rear] = item;
        count++;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public boolean isFull() {
        return count == MAX_CAPACITY;
    }

    @Override
    public int size() {
        return count;
    }

    // removes from the front
    @Override
    public FoodItem remove() {
        if (isEmpty()) {
            throw new StorageEmptyException("The storage is empty. There is nothing to remove.");
        }
        FoodItem removed = items[front];
        items[front] = null;
        front = (front + 1) % MAX_CAPACITY;
        count--;
        return removed;
    }

    // shows first item but doesn't remove it
    @Override
    public FoodItem peek() {
        if (isEmpty()) {
            throw new StorageEmptyException("The storage is empty. There is no item at the front.");
        }
        return items[front];
    }

    // prints all items from front to back
    @Override
    public void display() {
        if (isEmpty()) {
            System.out.println("The storage is empty.");
            return;
        }
        System.out.println("\nStored Food Items (" + count + "/" + MAX_CAPACITY + "):");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX_CAPACITY;
            System.out.println("----------------------");
            System.out.println(items[index]);
        }
        System.out.println("----------------------");
    }

    // linear search by name
    @Override
    public boolean search(String name) {
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX_CAPACITY;
            if (items[index].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
