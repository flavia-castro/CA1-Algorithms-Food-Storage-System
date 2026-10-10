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

    @Override
    public void display() {
    }

    @Override
    public boolean search(String name) {
        return false;
    }
}