/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.foodstorageapp;

/**
 *
 * @author flaviacastro
 */
// used when storage has no items
public class StorageEmptyException extends RuntimeException {

    public StorageEmptyException(String message) {
        super(message);
    }
}
