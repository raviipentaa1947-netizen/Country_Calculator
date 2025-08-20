package com.example.countrycalculator;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ExpenseDao {
    @Insert long insert(Expense e);

    @Query("SELECT * FROM Expense ORDER BY dateMillis DESC, id DESC")
    List<Expense> all();

    @Query("SELECT SUM(amount) FROM Expense")
    Double totalPaid();

    @Query("SELECT SUM(amount) FROM Expense WHERE payerId = :personId")
    Double totalPaidBy(long personId);
}
