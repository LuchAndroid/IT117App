package com.example.it117app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class MyPet extends AppCompatActivity {

    AutoCompleteTextView txtType, txtBreed;
    Button btnAdopt;
    String[] types, breeds;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pet);

        // Instantiate
        txtType = findViewById(R.id.txtType);
        txtBreed = findViewById(R.id.txtBreed);
        btnAdopt = findViewById(R.id.btnAdopt);

        // Add Autocomplete to txtType
        types = getResources().getStringArray(R.array.types);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, types);
        txtType.setAdapter(adapter);

        // Add Item Click Listener to txtType
        txtType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateBreedAutocomplete(parent.getItemAtPosition(position).toString());
            }
        });
        // Add Click Listener to txtBreed
        txtBreed.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                updateBreedAutocomplete(txtType.getText().toString());
            }
        });
        // Add Button Listener to btnAdopt
        btnAdopt.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Thank you for adopting this pet!", Toast.LENGTH_LONG).show();
        });
    }

    private void updateBreedAutocomplete(String type){
        breeds = new String[]{};
        switch (type){
            case "dog":
                breeds = getResources().getStringArray(R.array.dog);
                break;
            case "cat":
                breeds = getResources().getStringArray(R.array.cat);
                break;
            case "snake":
                breeds = getResources().getStringArray(R.array.snake);
                break;
            case "pokemon":
                breeds = getResources().getStringArray(R.array.pokemon);
                break;
            default:
                break;
        }
        adapter = new ArrayAdapter<>(txtBreed.getContext(), android.R.layout.simple_list_item_1, breeds);
        txtBreed.setAdapter(adapter);
    }
}