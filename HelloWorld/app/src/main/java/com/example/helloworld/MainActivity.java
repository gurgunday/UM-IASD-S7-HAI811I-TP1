package com.example.helloworld;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextName = findViewById(R.id.editTextName);
        final EditText editTextAge = findViewById(R.id.editTextAge);
        final EditText editTextFirstName = findViewById(R.id.editTextFirstName);
        final EditText editTextSkill = findViewById(R.id.editTextSkill);
        final EditText editTextPhone = findViewById(R.id.editTextPhone);
        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.confirmation));
                builder.setMessage(getString(R.string.confirm_info));

                builder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editTextName.getText().toString().trim();
                        String firstName = editTextFirstName.getText().toString().trim();
                        String age = editTextAge.getText().toString().trim();
                        String skill = editTextSkill.getText().toString().trim();
                        String phone = editTextPhone.getText().toString().trim();

                        // Vérification que aucun champ n'est vide
                        if (name.isEmpty() || firstName.isEmpty() || age.isEmpty() || skill.isEmpty() || phone.isEmpty()) {
                            Toast.makeText(MainActivity.this, getString(R.string.error_message_empty_fields), Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("firstName", firstName);
                            intent.putExtra("age", age);
                            intent.putExtra("skill", skill);
                            intent.putExtra("phone", phone);
                            startActivity(intent);
                        }
                    }
                });

                // Bouton d'annulation
                builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Action à effectuer pour l'annulation
                        dialog.dismiss(); // Fermer la fenêtre de dialogue
                    }
                });

                // Affichage de l'AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}