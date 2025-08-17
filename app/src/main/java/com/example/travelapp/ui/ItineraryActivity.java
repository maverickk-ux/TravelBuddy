package com.example.travelapp.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.travelapp.R;
import java.io.File;
import java.io.FileOutputStream;

public class ItineraryActivity extends AppCompatActivity {

    TextView summaryText;
    ImageView mapImage;
    Button btnGeneratePdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        summaryText = findViewById(R.id.summaryText);
        mapImage = findViewById(R.id.mapImage);
        btnGeneratePdf = findViewById(R.id.btnGeneratePdf);

        // Placeholder summary
        summaryText.setText("Day 1: Visit Lalbagh\nDay 2: Bangalore Palace\nCuisine: Indian, Street Food");

        // Set a static map image for now (can use Google Static Maps API later)
        mapImage.setImageResource(R.drawable.route_sample); // Add route_sample.png to res/drawable

        btnGeneratePdf.setOnClickListener(v -> generatePdf());
    }

    private void generatePdf() {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();

        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        // Draw summary text
        summaryText.draw(canvas);

        document.finishPage(page);

        try {
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "itinerary.pdf");
            FileOutputStream fos = new FileOutputStream(file);
            document.writeTo(fos);
            document.close();
            Toast.makeText(this, "PDF saved to Downloads", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "PDF creation failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
