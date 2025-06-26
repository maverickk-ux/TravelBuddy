package com.example.travelapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import com.example.travelapp.R;

import java.util.ArrayList;

public class FoodPreferenceActivity extends AppCompatActivity {

    CheckBox cbIndian, cbChinese, cbItalian, cbStreetFood;
    EditText editFoodBudget;
    Button btnNextFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_preference);

        cbIndian = findViewById(R.id.cbIndian);
        cbChinese = findViewById(R.id.cbChinese);
        cbItalian = findViewById(R.id.cbItalian);
        cbStreetFood = findViewById(R.id.cbStreetFood);
        editFoodBudget = findViewById(R.id.editFoodBudget);
        btnNextFood = findViewById(R.id.btnNextFood);

        // 🆕 Bind radio buttons here
        RadioButton radioVeg = findViewById(R.id.radioVeg);
        RadioButton radioNonVeg = findViewById(R.id.radioNonVeg);

        btnNextFood.setOnClickListener(v -> {
            ArrayList<String> cuisines = new ArrayList<>();
            if (cbIndian.isChecked()) cuisines.add("Indian");
            if (cbChinese.isChecked()) cuisines.add("Chinese");
            if (cbItalian.isChecked()) cuisines.add("Italian");
            if (cbStreetFood.isChecked()) cuisines.add("Street Food");

            // Use selected food type
            String foodType = radioVeg.isChecked() ? "Veg" : "Non-Veg";
            String foodBudget = editFoodBudget.getText().toString();

            Intent intent = new Intent(FoodPreferenceActivity.this, ItineraryActivity.class);

            // Bring all previous data forward
            intent.putExtras(getIntent());

            // Pass food data
            intent.putStringArrayListExtra("cuisines", cuisines);
            intent.putExtra("foodType", foodType);
            intent.putExtra("foodBudget", foodBudget);

            // Mock data: Pass selected attractions for each day (replace with actual logic later)
            ArrayList<String> plan = new ArrayList<>();
            plan.add("Day 1: Lalbagh at 10:00 AM");
            plan.add("Day 2: Bangalore Palace at 11:00 AM");
            plan.add("Day 3: Cubbon Park at 4:00 PM");

            intent.putStringArrayListExtra("dailyPlan", plan);

            startActivity(intent);
        });
    }

}
