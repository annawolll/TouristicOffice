/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.touristicoffice;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Person implements Serializable{
    private int ID;
    private String name, surname, password;

    public Person(int ID, String name, String surname, String password) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    
}
