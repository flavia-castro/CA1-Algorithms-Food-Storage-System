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
            throw new StorageFullException("The storage is full. Maximum is " + MAX_CAPACITY + " items.");
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

    @Override
    public FoodItem peek() {
        if (isEmpty()) {
            throw new StorageEmptyException("The storage is empty. There is no item at the front.");
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
        System.out.println("\nStored Food Items (" + count + "/" + MAX_CAPACITY + "):");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % MAX_CAPACITY;
            System.out.println("----------------------");
            System.out.println(items[index]);
        }
        System.out.println("----------------------");
    }

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
