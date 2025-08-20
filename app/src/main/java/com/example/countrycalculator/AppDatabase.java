package com.example.countrycalculator;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Person.class, Expense.class, Transfer.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract PersonDao personDao();
    public abstract ExpenseDao expenseDao();
    public abstract TransferDao transferDao();

    public static AppDatabase get(Context ctx) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                                    AppDatabase.class, "country_calc.db")
                            .allowMainThreadQueries() // keep sample simple
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
