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

    private CheckBox cbIndian, cbChinese, cbItalian, cbStreetFood;
    private EditText editFoodBudget;
    private Button btnNextFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_preference); // ✅ must match file name

        // Bind UI
        cbIndian = findViewById(R.id.cbIndian);
        cbChinese = findViewById(R.id.cbChinese);
        cbItalian = findViewById(R.id.cbItalian);
        cbStreetFood = findViewById(R.id.cbStreetFood);
        editFoodBudget = findViewById(R.id.editFoodBudget);
        btnNextFood = findViewById(R.id.btnNextFood);

        RadioButton radioVeg = findViewById(R.id.radioVeg);
        RadioButton radioNonVeg = findViewById(R.id.radioNonVeg);

        btnNextFood.setOnClickListener(v -> {
            ArrayList<String> cuisines = new ArrayList<>();
            if (cbIndian.isChecked()) cuisines.add("Indian");
            if (cbChinese.isChecked()) cuisines.add("Chinese");
            if (cbItalian.isChecked()) cuisines.add("Italian");
            if (cbStreetFood.isChecked()) cuisines.add("Street Food");

            // Food type
            String foodType = radioVeg.isChecked() ? "Veg" :
                    (radioNonVeg.isChecked() ? "Non-Veg" : "Not Selected");

            String foodBudget = editFoodBudget.getText().toString().trim();

            Intent intent = new Intent(FoodPreferenceActivity.this, ItineraryActivity.class);

            // Forward all previous extras
            intent.putExtras(getIntent());

            // Add food preferences
            intent.putStringArrayListExtra("cuisines", cuisines);
            intent.putExtra("foodType", foodType);
            intent.putExtra("foodBudget", foodBudget);

            startActivity(intent);
        });
    }
}
