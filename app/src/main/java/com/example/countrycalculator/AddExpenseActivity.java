package com.example.countrycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class AddExpenseActivity extends AppCompatActivity {
    private AppDatabase db;
    private Spinner payerSpinner;
    private EditText amountEt, noteEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        db = AppDatabase.get(this);

        payerSpinner = findViewById(R.id.spPayer);
        amountEt = findViewById(R.id.etAmount);
        noteEt = findViewById(R.id.etNote);
        Button save = findViewById(R.id.btnSaveExpense);

        setupPeopleSpinner();

        save.setOnClickListener(v -> {
            String amtStr = amountEt.getText().toString().trim();
            if (amtStr.isEmpty()) {
                Toast.makeText(this, "Enter amount", Toast.LENGTH_SHORT).show();
                return;
            }
            double amount = Double.parseDouble(amtStr);
            Person selected = (Person) payerSpinner.getSelectedItem();

            Expense e = new Expense();
            e.payerId = selected.id;
            e.amount = amount;
            e.note = noteEt.getText().toString().trim();
            e.dateMillis = System.currentTimeMillis();
            db.expenseDao().insert(e);

            Toast.makeText(this, "Expense added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void setupPeopleSpinner() {
        List<Person> people = db.personDao().all();
        ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, android.R.layout.simple_spinner_item, people) {
            @Override public long getItemId(int position) { return getItem(position).id; }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        payerSpinner.setAdapter(adapter);
    }
}
