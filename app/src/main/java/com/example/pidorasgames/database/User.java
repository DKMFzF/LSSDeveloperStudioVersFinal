package com.example.pidorasgames.database;

// Пользовательский класс

public class User {
    public String id, name, password, email;
    public int reting;

    public User(String id, String name, String password, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.reting = 0;
    }

    public User() {}
    public int getReting(){
        return reting;
    }
}
