package com.example.helloworld;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        String phoneNumber = getIntent().getStringExtra("phone");
        TextView phoneNumberText = findViewById(R.id.phoneNumberText);
        phoneNumberText.setText(phoneNumber);

        findViewById(R.id.callButton).setOnClickListener(view -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL); // Utilisez ACTION_DIAL
            dialIntent.setData(Uri.parse("tel:" + phoneNumber));
            if (dialIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(dialIntent);
            } else {
                Toast.makeText(ThirdActivity.this, "Aucune application pour passer des appels n'est installée", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

