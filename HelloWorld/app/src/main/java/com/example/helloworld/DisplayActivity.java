package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String firstName = intent.getStringExtra("firstName");
        String age = intent.getStringExtra("age");
        String skill = intent.getStringExtra("skill");
        String phone = intent.getStringExtra("phone");

        TextView nameTextView = findViewById(R.id.nameTextView);
        TextView firstNameTextView = findViewById(R.id.firstNameTextView);
        TextView ageTextView = findViewById(R.id.ageTextView);
        TextView skillTextView = findViewById(R.id.skillTextView);
        TextView phoneTextView = findViewById(R.id.phoneTextView);

        nameTextView.setText(getString(R.string.label_name) + ": " + name);
        firstNameTextView.setText(getString(R.string.label_first_name) + ": " + firstName);
        ageTextView.setText(getString(R.string.label_age) + ": " + age);
        skillTextView.setText(getString(R.string.label_skill) + ": " + skill);
        phoneTextView.setText(getString(R.string.label_phone) + ": " + phone);


        // Gestion du bouton "OK"
        findViewById(R.id.okButton).setOnClickListener(v -> {
            Intent okIntent = new Intent(DisplayActivity.this, ThirdActivity.class);
            okIntent.putExtra("phone", phone);
            startActivity(okIntent);
        });

        // Gestion du bouton "Retour"
        findViewById(R.id.backButton).setOnClickListener(v -> finish()); // Termine cette activité
    }
}
