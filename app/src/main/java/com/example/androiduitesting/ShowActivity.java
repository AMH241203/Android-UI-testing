package com.example.androiduitesting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        TextView title = findViewById(R.id.content_view);
        Button back = findViewById(R.id.back_button);

        // Get the city name sent from MainActivity
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("city_name");
        if (cityName == null) cityName = "Unknown city";

        title.setText(cityName);

        // Explicit Back button behavior
        back.setOnClickListener(v -> finish());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

