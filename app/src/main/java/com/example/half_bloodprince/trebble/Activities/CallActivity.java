package com.example.half_bloodprince.trebble.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.half_bloodprince.trebble.R;

import java.util.ArrayList;
import java.util.List;

public class CallActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


        String[] country = { "India", "USA", "China", "Japan", "Other"};

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);  //toolbars
        setSupportActionBar(myToolbar);
        //myToolbar.setTitle("COMMUNITY");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // getSupportActionBar().setTitle("Post");

//Getting the instance of Spinner and applying OnItemSelectedListener on it
            Spinner spinner = (Spinner) findViewById(R.id.spinner);

            // Spinner click listener
            spinner.setOnItemSelectedListener(this);

            // Spinner Drop down elements
            List<String> categories = new ArrayList<String>();
            categories.add("Support Service");
            categories.add("Warranty & Contracts");
            categories.add("Drivers & Downloads");
            categories.add("Others");

            // Creating adapter for spinner
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

            // Drop down layout style - list view with radio button
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinner.setAdapter(dataAdapter);
        }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {

        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}