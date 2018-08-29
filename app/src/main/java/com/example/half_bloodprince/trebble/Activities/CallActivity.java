package com.example.half_bloodprince.trebble.Activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.half_bloodprince.trebble.POJO.Request;
import com.example.half_bloodprince.trebble.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CallActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextInputEditText oneline,contact;
        String[] country = { "India", "USA", "China", "Japan", "Other"};
        FirebaseDatabase firebaseDatabase;
        DatabaseReference myRef;
        String Uid;
        String Uname;
        SharedPreferences sharedPreferences;
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
            firebaseDatabase=FirebaseDatabase.getInstance();
            sharedPreferences=getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            Uid=sharedPreferences.getString("id","121234");
            Uname=sharedPreferences.getString("name","121234");
            oneline=(TextInputEditText)findViewById(R.id.oneliner);
            contact=(TextInputEditText)findViewById(R.id.contact);
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
            final Spinner spinner = (Spinner) findViewById(R.id.spinner);

            // Spinner click listener
            spinner.setOnItemSelectedListener(this);


            Button button=(Button)findViewById(R.id.requestCall);
            button.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String str=spinner.getSelectedItem().toString();
                    if(oneline.getText().length()>0 && contact.getText().length()==10)
                    {
                        myRef = firebaseDatabase.getReference("requests").child(Uid);
                        Calendar c=Calendar.getInstance();
                        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
                        SimpleDateFormat sdf1=new SimpleDateFormat("HH:mm");
                        Request request=new Request(str,sdf.format(c.getTime()),Integer.parseInt(Uid),Uname,Long.parseLong(contact.getText().toString()),oneline.getText().toString(),sdf1.format(c.getTime()));
                        myRef.setValue(request);
                        Toast.makeText(CallActivity.this,"Request Successfully sent",Toast.LENGTH_SHORT).show();
                        finish();

                    }
                    else
                    {
                        Toast.makeText(CallActivity.this,"Enter Valid Inputs",Toast.LENGTH_SHORT).show();
                    }
                }
            });


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