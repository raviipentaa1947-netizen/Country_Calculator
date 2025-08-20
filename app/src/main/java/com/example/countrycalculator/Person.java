package com.example.countrycalculator;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;

    public Person(String name) { this.name = name; }

    @Override public String toString() { return name; }
}
