package com.nashirul.uts_praktikum_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SpellDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_detail);

        TextView textViewName = findViewById(R.id.textViewNameDetail);
        TextView textViewDesc = findViewById(R.id.textViewDescDetail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String desc = intent.getStringExtra("desc");

        textViewName.setText(name);
        textViewDesc.setText(desc);
    }
}