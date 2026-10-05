/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.foodstorageapp;
import java.time.LocalDate;

/**
 *
 * @author flaviacastro
 */
public class FoodStorageApp {

    public static void main(String[] args) {
        FoodItem item1 = new FoodItem("Burger", 250, LocalDate.now().plusDays(7));
        FoodItem item2 = new FoodItem("Pizza", 800, LocalDate.now().plusDays(3));

        System.out.println(item1);
        System.out.println(item2);
    }
}
