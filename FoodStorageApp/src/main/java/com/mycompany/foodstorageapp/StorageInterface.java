/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.foodstorageapp;

/**
 *
 * @author flaviacastro
 */
public interface StorageInterface {

    int MAX_CAPACITY = 8;

    void add(FoodItem item);
    FoodItem remove();
    FoodItem peek();
    void display();
    boolean search(String name);
    boolean isEmpty();
    boolean isFull();
    int size();
}
