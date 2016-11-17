package com.example.pam.expendablelistview;

/**
 * Created by Ardy on 11/12/2016.
 */
public class Person {
    private int id;
    private String name;
    private String address;
    private String hobby;

    public Person(){

    }

    public Person(String name, String address, String hobby){
        //this.id = id;
        this.name = name;
        this.address = address;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
