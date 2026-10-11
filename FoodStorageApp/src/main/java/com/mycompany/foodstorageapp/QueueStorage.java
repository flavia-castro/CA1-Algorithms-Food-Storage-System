/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorageapp;

/**
 *
 * @author sissy and flavia
 */
public class QueueStorage implements StorageInterface {

    private FoodItem[] items;
    private int front;
    private int count;

    public QueueStorage() {
        items = new FoodItem[MAX_CAPACITY];
        front = 0;
        count = 0;
    }

    @Override
    public void add(FoodItem item) {
        if (isFull()) {
            System.out.println("The storage is full. Maximum is " + MAX_CAPACITY + " items.");
            return;
        }
        int rear = (front + count) % MAX_CAPACITY;
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

    // Dequeue: removes and returns the item at the front of the queue
    @Override
    public FoodItem remove() {
        if (isEmpty()) {
            System.out.println("The storage is empty. There is nothing to remove.");
            return null;
        }
        FoodItem removed = items[front];
        items[front] = null;
        front = (front + 1) % MAX_CAPACITY;
        count--;
        return removed;
    }

    // Peek: returns the item at the front without removing it
    @Override
    public FoodItem peek() {
        if (isEmpty()) {
            System.out.println("The storage is empty.");
            return null;
        }
        return items[front];
    }

    // Display: shows all items, from the front to the rear of the queue
    @Override
    public void display() {
        if (isEmpty()) {
            System.out.println("The storage is empty.");
            return;
        }
        System.out.println("Items in the storage (" + count + "/" + MAX_CAPACITY + "):");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX_CAPACITY;
            System.out.println((i + 1) + ". " + items[index]);
        }
    }

    // Search: checks if a food with this name is in the storage
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