package com.example.countrycalculator;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Person.class, parentColumns = "id", childColumns = "giverId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Person.class, parentColumns = "id", childColumns = "receiverId", onDelete = ForeignKey.CASCADE)
})
public class Transfer {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long giverId;
    public long receiverId;
    public double amount;
    public String paymentMode;
    public long dateMillis;
}
