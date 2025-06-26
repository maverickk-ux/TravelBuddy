package com.example.travelapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.R;

import java.util.ArrayList;

public class PlaceCategoryActivity extends AppCompatActivity {

    CheckBox cbAdventure, cbNature, cbHistorical, cbShopping, cbReligious, cbMuseum, cbAmusement;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_category);

        cbAdventure = findViewById(R.id.cbAdventure);
        cbNature = findViewById(R.id.cbNature);
        cbHistorical = findViewById(R.id.cbHistorical);
        cbShopping = findViewById(R.id.cbShopping);
        cbReligious = findViewById(R.id.cbReligious);
        cbMuseum = findViewById(R.id.cbMuseum);
        cbAmusement = findViewById(R.id.cbAmusement);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            ArrayList<String> selectedCategories = new ArrayList<>();

            if (cbAdventure.isChecked()) selectedCategories.add("adventure");
            if (cbNature.isChecked()) selectedCategories.add("nature");
            if (cbHistorical.isChecked()) selectedCategories.add("historical");
            if (cbShopping.isChecked()) selectedCategories.add("shopping");
            if (cbReligious.isChecked()) selectedCategories.add("religious");
            if (cbMuseum.isChecked()) selectedCategories.add("museum");
            if (cbAmusement.isChecked()) selectedCategories.add("amusement");

            Intent intent = new Intent(PlaceCategoryActivity.this, FoodPreferenceActivity.class); // next step
            intent.putStringArrayListExtra("categories", selectedCategories);

            // Also pass trip info from previous screen
            intent.putExtras(getIntent()); // Pass budget, city, date, etc.
            startActivity(intent);
        });
    }
}
