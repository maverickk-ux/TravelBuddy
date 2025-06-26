package com.example.travelapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelapp.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ItineraryActivity extends AppCompatActivity {

    TextView tvSummary;
    ImageView ivMap;
    Button btnGeneratePdf;
    LinearLayout timelineContainer;
    Bitmap mapBitmap; // for PDF

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        tvSummary = findViewById(R.id.tvSummary);
        ivMap = findViewById(R.id.ivMap);
        btnGeneratePdf = findViewById(R.id.btnGeneratePdf);
        timelineContainer = findViewById(R.id.timelineContainer);

        ArrayList<String> dailyPlan = getIntent().getStringArrayListExtra("dailyPlan");
        ArrayList<String> cuisines = getIntent().getStringArrayListExtra("cuisines");

        StringBuilder summaryBuilder = new StringBuilder();
        if (dailyPlan != null) {
            for (String line : dailyPlan) {
                summaryBuilder.append(line).append("\n");
                TextView tv = new TextView(this);
                tv.setText(line);
                tv.setTextSize(16);
                tv.setPadding(8, 8, 8, 8);
                timelineContainer.addView(tv);
            }
        } else {
            TextView tv = new TextView(this);
            tv.setText("No trip plan available.");
            timelineContainer.addView(tv);
        }

        if (cuisines != null && !cuisines.isEmpty()) {
            summaryBuilder.append("Cuisine: ").append(String.join(", ", cuisines));
        }
        tvSummary.setText(summaryBuilder.toString());

        // Load dynamic map
        if (dailyPlan != null && !dailyPlan.isEmpty()) {
            ArrayList<String> places = new ArrayList<>();
            for (String line : dailyPlan) {
                String[] parts = line.split(":");
                if (parts.length > 1) {
                    String place = parts[1].split(" at")[0].trim();
                    places.add(place);
                }
            }
            loadStaticMap(places);
        }

        btnGeneratePdf.setOnClickListener(v -> generatePdf());
    }

    private void loadStaticMap(ArrayList<String> places) {
        try {
            StringBuilder urlBuilder = new StringBuilder("https://maps.googleapis.com/maps/api/staticmap?size=600x400");
            for (String place : places) {
                urlBuilder.append("&markers=").append(place.replace(" ", "+")).append(",Bangalore");
            }
            urlBuilder.append("&key=AIzaSyDv0NIE4f0Lb_9H-D0vp5sHOp9E5SQ6lsA"); // Replace with your actual key

            String mapUrl = urlBuilder.toString();

            new Thread(() -> {
                try {
                    URL url = new URL(mapUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    mapBitmap = BitmapFactory.decodeStream(input);
                    runOnUiThread(() -> ivMap.setImageBitmap(mapBitmap));
                } catch (Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(this, "Map load failed", Toast.LENGTH_SHORT).show());
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generatePdf() {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(595, 842, 1).create();

        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        int y = 40;

        Paint paint = new Paint();
        for (String line : tvSummary.getText().toString().split("\n")) {
            canvas.drawText(line, 40, y, paint);
            y += 25;
        }
        y += 20;

        if (mapBitmap != null) {
            canvas.drawBitmap(Bitmap.createScaledBitmap(mapBitmap, 500, 300, false), 40, y, null);
        }

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