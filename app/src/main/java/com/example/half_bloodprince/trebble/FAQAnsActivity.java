package com.example.half_bloodprince.trebble;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FAQAnsActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqans);
        getSupportActionBar().setTitle("Answer");
        Intent intent=getIntent();
        String out=intent.getStringExtra("Ans");
        tv=(TextView)findViewById(R.id.txt2);
        tv.setText(out);
        out=intent.getStringExtra("Qn");
        tv=(TextView)findViewById(R.id.txt);
        tv.setText(out);

    }
}
