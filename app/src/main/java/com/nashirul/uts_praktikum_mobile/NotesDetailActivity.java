package com.nashirul.uts_praktikum_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NotesDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_detail);

        int photoResource = getIntent().getIntExtra("notes_photo", 0);
        String name = getIntent().getStringExtra("notes_name");
        String description = getIntent().getStringExtra("notes_description");

        ImageView imageView = findViewById(R.id.img_notes_photo);
        TextView nameTextView = findViewById(R.id.tv_notes_name);
        TextView descTextView = findViewById(R.id.tv_notes_description);

        imageView.setImageResource(photoResource);
        nameTextView.setText(name);
        descTextView.setText(description);
    }
}