package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.cardview.widget.CardView;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int ADD_NOTICE_ID = R.id.addNotice;
    private static final int ADD_GALLERY_IMAGE_ID = R.id.addGalleryImage;

    CardView uploadNotice, addGalleryImage, addEbook, faculty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uploadNotice = findViewById(R.id.addNotice);
        uploadNotice.setOnClickListener(this);

        addGalleryImage = findViewById(R.id.addGalleryImage);
        addGalleryImage.setOnClickListener(this);

        // Initialize other views and set onClick listeners if needed
        // addEbook = findViewById(R.id.addEbook);
        // addEbook.setOnClickListener(this);
        // faculty = findViewById(R.id.faculty);
        // faculty.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == ADD_NOTICE_ID) {
            // Handle click for addNotice
            Log.d("MainActivity", "UploadNotice card clicked");
            startActivity(new Intent(MainActivity.this, UploadNotice.class));
        } else if (view.getId() == ADD_GALLERY_IMAGE_ID) {
            // Handle click for addGalleryImage
            Log.d("MainActivity", "UploadImage card clicked");
            // startActivity(new Intent(MainActivity.this, UploadImage.class));
        }
        // Add more else-if conditions for other views if needed
    }
}
