package com.example.countrycalculator;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDao {
    @Insert long insert(Person p);
    @Query("SELECT * FROM Person ORDER BY name ASC")
    List<Person> all();

    @Query("SELECT COUNT(*) FROM Person")
    int count();
}
