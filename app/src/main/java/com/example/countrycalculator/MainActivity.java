package com.example.countrycalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private BalanceAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.get(this);

        seedPeopleIfEmpty();

        RecyclerView rv = findViewById(R.id.balanceRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BalanceAdapter();
        rv.setAdapter(adapter);

        Button addExpense = findViewById(R.id.btnAddExpense);
        Button addTransfer = findViewById(R.id.btnAddTransfer);
        Button refresh = findViewById(R.id.btnRefresh);

        addExpense.setOnClickListener(v ->
                startActivity(new Intent(this, AddExpenseActivity.class)));
        addTransfer.setOnClickListener(v ->
                startActivity(new Intent(this, AddTransferActivity.class)));
        refresh.setOnClickListener(v -> refreshBalances());
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshBalances();
    }

    private void refreshBalances() {
        List<BalanceCalculator.PersonBalance> balances = BalanceCalculator.compute(db);
        adapter.submit(balances);
    }

    private void seedPeopleIfEmpty() {
        if (db.personDao().count() == 0) {
            List<String> names = Arrays.asList("Ravi", "Ganesh", "Hari", "Lakhan");
            for (String n : names) db.personDao().insert(new Person(n));
        }
    }
}
