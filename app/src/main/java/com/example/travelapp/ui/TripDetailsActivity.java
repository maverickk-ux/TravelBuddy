package com.example.travelapp.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.R;

import java.util.Calendar;

public class TripDetailsActivity extends AppCompatActivity {

    EditText editTextStartDate, editTextEndDate, editTextBudget, editTextCity;
    Button buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        editTextStartDate = findViewById(R.id.editTextStartDate);
        editTextEndDate = findViewById(R.id.editTextEndDate);
        editTextBudget = findViewById(R.id.editTextBudget);
        editTextCity = findViewById(R.id.editTextCity);
        buttonNext = findViewById(R.id.buttonNext);

        editTextStartDate.setOnClickListener(v -> showDatePicker(editTextStartDate));
        editTextEndDate.setOnClickListener(v -> showDatePicker(editTextEndDate));

        buttonNext.setOnClickListener(v -> {
            // Optional: validate inputs

            String city = editTextCity.getText().toString();
            String budget = editTextBudget.getText().toString();
            String startDate = editTextStartDate.getText().toString();
            String endDate = editTextEndDate.getText().toString();

            Intent intent = new Intent(TripDetailsActivity.this, PlaceCategoryActivity.class); // you'll create this next
            intent.putExtra("city", city);
            intent.putExtra("budget", budget);
            intent.putExtra("startDate", startDate);
            intent.putExtra("endDate", endDate);
            startActivity(intent);
        });
    }

    private void showDatePicker(final EditText target) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) ->
                        target.setText(String.format("%02d/%02d/%04d", dayOfMonth, month1 + 1, year1)),
                year, month, day);
        dialog.show();
    }
}
