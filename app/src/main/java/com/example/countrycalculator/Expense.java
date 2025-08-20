package com.example.countrycalculator;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(
        entity = Person.class,
        parentColumns = "id",
        childColumns = "payerId",
        onDelete = ForeignKey.CASCADE
))
public class Expense {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long payerId;
    public double amount;
    public String note;
    public long dateMillis;
}
