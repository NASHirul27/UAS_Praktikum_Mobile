package com.nashirul.uts_praktikum_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        int photoResource = getIntent().getIntExtra("item_photo", 0);
        String name = getIntent().getStringExtra("item_name");
        String description = getIntent().getStringExtra("item_description");

        ImageView imageView = findViewById(R.id.img_item_photo);
        TextView nameTextView = findViewById(R.id.tv_item_name);
        TextView descTextView = findViewById(R.id.tv_item_description);

        imageView.setImageResource(photoResource);
        nameTextView.setText(name);
        descTextView.setText(description);
    }
}