package com.example.horairestrains;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText departureCity = findViewById(R.id.departureCity);
        EditText arrivalCity = findViewById(R.id.arrivalCity);
        Button searchButton = findViewById(R.id.searchButton);
        ListView trainScheduleList = findViewById(R.id.trainScheduleList);

        searchButton.setOnClickListener(view -> {
            String departure = departureCity.getText().toString().trim();
            String arrival = arrivalCity.getText().toString().trim();

            if ("Paris".equalsIgnoreCase(departure) && "Madrid".equalsIgnoreCase(arrival)) {
                String[] trainTimes = {"08:00 - Train rapide", "10:30 - Train express", "13:00 - Train régional"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, trainTimes);
                trainScheduleList.setAdapter(adapter);
            } else {
                String[] noResults = {"Pas de résultats"};
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, noResults);
                trainScheduleList.setAdapter(adapter);
            }
        });
    }
}

