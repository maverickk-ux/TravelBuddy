package com.example.travelapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText budgetInput, startDateInput, endDateInput;
    CheckBox nature, historical, adventure, shopping;
    Button generatePlanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        budgetInput = findViewById(R.id.editTextBudget);
        startDateInput = findViewById(R.id.editTextStartDate);
        endDateInput = findViewById(R.id.editTextEndDate);

        nature = findViewById(R.id.checkboxNature);
        historical = findViewById(R.id.checkboxHistorical);
        adventure = findViewById(R.id.checkboxAdventure);
        shopping = findViewById(R.id.checkboxShopping);

        generatePlanButton = findViewById(R.id.buttonGeneratePlan);

        startDateInput.setOnClickListener(view -> showDatePicker(startDateInput));
        endDateInput.setOnClickListener(view -> showDatePicker(endDateInput));

        generatePlanButton.setOnClickListener(view -> collectInput());
    }

    private void showDatePicker(EditText dateField) {
        Calendar calendar = Calendar.getInstance();
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, (datePicker, year, month, day) -> {
            String date = String.format("%02d/%02d/%d", day, month + 1, year);
            dateField.setText(date);
        }, y, m, d);
        dialog.show();
    }

    private void collectInput() {
        String budgetStr = budgetInput.getText().toString();
        String startDate = startDateInput.getText().toString();
        String endDate = endDateInput.getText().toString();

        boolean likesNature = nature.isChecked();
        boolean likesHistory = historical.isChecked();
        boolean likesAdventure = adventure.isChecked();
        boolean likesShopping = shopping.isChecked();

        if (budgetStr.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int budget = Integer.parseInt(budgetStr);

        // Show collected data for now
        String summary = "Budget: ₹" + budget + "\nFrom: " + startDate + " to " + endDate +
                "\nLikes: " + (likesNature ? "Nature " : "") +
                (likesHistory ? "History " : "") +
                (likesAdventure ? "Adventure " : "") +
                (likesShopping ? "Shopping " : "");

        Toast.makeText(this, summary, Toast.LENGTH_LONG).show();

        // TODO: Pass this data to next activity for processing
    }
}
