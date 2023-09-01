package com.example.ecom;

public class customer {
    private int id;
    private String Email,Name;

    public customer(int id, String Name, String Email) {
        this.id = id;
        this.Email = Email;
        this.Name = Name;

    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return Email;
    }

    public String getName() {
        return Name;
    }



}
