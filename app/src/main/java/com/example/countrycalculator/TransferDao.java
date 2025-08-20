package com.example.countrycalculator;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransferDao {
    @Insert long insert(Transfer t);

    @Query("SELECT * FROM Transfer ORDER BY dateMillis DESC, id DESC")
    List<Transfer> all();

    @Query("SELECT COALESCE(SUM(amount),0) FROM Transfer WHERE receiverId = :personId")
    double totalIncoming(long personId);

    @Query("SELECT COALESCE(SUM(amount),0) FROM Transfer WHERE giverId = :personId")
    double totalOutgoing(long personId);
}
