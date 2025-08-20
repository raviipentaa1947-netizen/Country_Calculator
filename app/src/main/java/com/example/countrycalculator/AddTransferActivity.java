package com.example.countrycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class AddTransferActivity extends AppCompatActivity {
    private AppDatabase db;
    private Spinner giverSp, receiverSp;
    private EditText amountEt, modeEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transfer);
        db = AppDatabase.get(this);

        giverSp = findViewById(R.id.spGiver);
        receiverSp = findViewById(R.id.spReceiver);
        amountEt = findViewById(R.id.etAmountTransfer);
        modeEt = findViewById(R.id.etMode);
        Button save = findViewById(R.id.btnSaveTransfer);

        setupPeopleSpinners();

        save.setOnClickListener(v -> {
            String amtStr = amountEt.getText().toString().trim();
            if (amtStr.isEmpty()) {
                Toast.makeText(this, "Enter amount", Toast.LENGTH_SHORT).show();
                return;
            }
            double amount = Double.parseDouble(amtStr);
            Person giver = (Person) giverSp.getSelectedItem();
            Person receiver = (Person) receiverSp.getSelectedItem();
            if (giver.id == receiver.id) {
                Toast.makeText(this, "Choose different people", Toast.LENGTH_SHORT).show();
                return;
            }
            Transfer t = new Transfer();
            t.giverId = giver.id;
            t.receiverId = receiver.id;
            t.amount = amount;
            t.paymentMode = modeEt.getText().toString().trim();
            t.dateMillis = System.currentTimeMillis();
            db.transferDao().insert(t);

            Toast.makeText(this, "Transfer added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void setupPeopleSpinners() {
        List<Person> people = db.personDao().all();
        ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, android.R.layout.simple_spinner_item, people) {
            @Override public long getItemId(int position) { return getItem(position).id; }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        giverSp.setAdapter(adapter);
        receiverSp.setAdapter(adapter);
    }
}
