package com.example.helloworldjava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        int paddingInDp = 48;  // (density-independent pixels)
        final float scale = getResources().getDisplayMetrics().density;
        int paddingInPx = (int) (paddingInDp * scale + 0.5f);
        layout.setPadding(paddingInPx, paddingInPx, paddingInPx, paddingInPx);

        EditText editTextName = new EditText(this);
        editTextName.setHint("Nom");

        EditText editTextFirstName = new EditText(this);
        editTextFirstName.setHint("Prénom");

        EditText editTextAge = new EditText(this);
        editTextAge.setHint("Âge");
        editTextAge.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);

        EditText editTextSkill = new EditText(this);
        editTextSkill.setHint("Domaine de compétences");

        EditText editTextPhone = new EditText(this);
        editTextPhone.setHint("Numéro de téléphone");
        editTextPhone.setInputType(android.text.InputType.TYPE_CLASS_PHONE);

        Button submitButton = new Button(this);
        submitButton.setText("Valider");
        submitButton.setOnClickListener(view -> {
            String name = editTextName.getText().toString();
            String firstName = editTextFirstName.getText().toString();
            String age = editTextAge.getText().toString();
            String skill = editTextSkill.getText().toString();
            String phone = editTextPhone.getText().toString();

            Toast.makeText(MainActivity.this, "Informations validées", Toast.LENGTH_LONG).show();
        });

        layout.addView(editTextName);
        layout.addView(editTextFirstName);
        layout.addView(editTextAge);
        layout.addView(editTextSkill);
        layout.addView(editTextPhone);
        layout.addView(submitButton);

        FrameLayout container = findViewById(R.id.container);
        container.addView(layout);
    }
}
