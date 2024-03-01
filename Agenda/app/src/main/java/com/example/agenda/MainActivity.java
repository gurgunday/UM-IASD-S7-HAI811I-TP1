package com.example.agenda;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private List<Object> items = new ArrayList<>();
    private EventsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.eventsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventsAdapter(items);
        recyclerView.setAdapter(adapter);

        FloatingActionButton addEventButton = findViewById(R.id.addEventButton);
        addEventButton.setOnClickListener(view -> {
            View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_event, null);
            final Button buttonSelectDate = dialogView.findViewById(R.id.buttonSelectDate);
            final EditText editTextDescription = dialogView.findViewById(R.id.editTextDescription);
            final Calendar selectedDate = Calendar.getInstance();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            buttonSelectDate.setText(sdf.format(selectedDate.getTime()));

            DatePickerDialog.OnDateSetListener dateSetListener = (view1, year, month, dayOfMonth) -> {
                selectedDate.set(Calendar.YEAR, year);
                selectedDate.set(Calendar.MONTH, month);
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                buttonSelectDate.setText(sdf.format(selectedDate.getTime()));
            };

            buttonSelectDate.setOnClickListener(v -> new DatePickerDialog(MainActivity.this, dateSetListener, selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH)).show());

            new AlertDialog.Builder(this)
                    .setTitle("Add Event")
                    .setView(dialogView)
                    .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                        String date = buttonSelectDate.getText().toString();
                        String description = editTextDescription.getText().toString().trim();
                        if (!description.isEmpty()) {
                            // Ajoute l'événement et potentiellement une nouvelle date à la liste
                            if (!items.contains(date)) {
                                items.add(date); // Ajoute la date si elle n'est pas déjà présente
                            }
                            items.add(new Event(date, description)); // Ajoute l'événement
                            sortAndRebuildItemsList(); // Trie et reconstruit la liste d'items
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .show();
        });
    }

    private void sortAndRebuildItemsList() {
        TreeMap<String, List<Event>> eventsByDate = new TreeMap<>();
        for (Object item : items) {
            if (item instanceof String) {
                eventsByDate.putIfAbsent((String) item, new ArrayList<>());
            } else if (item instanceof Event) {
                Event event = (Event) item;
                eventsByDate.get(event.getDate()).add(event);
            }
        }

        items.clear();
        for (Map.Entry<String, List<Event>> entry : eventsByDate.entrySet()) {
            items.add(entry.getKey()); // Ajoute la date
            items.addAll(entry.getValue()); // Ajoute les événements pour cette date
        }
        adapter.notifyDataSetChanged();
    }
}

