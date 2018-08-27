package com.example.half_bloodprince.trebble;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.half_bloodprince.trebble.Adapters.FAQAdapter;

public class FAQActivity extends AppCompatActivity {
    String[] Questions={"How do I request service?","How do I verify if a Dell computer is in warranty?","What repairs not covered by the Dell warranty?","Where will the repairs be completed, and is off-campus service available?","How long will it take to complete repairs?","What if the repair is not covered under warranty?","What if the computer is covered, but the technician can't fix it?"};
    String[] Answer={"See the Request Warranty Service page for more information.","See the Request Warranty Service page for more information.","You can find more information about ProSupport and Basic Hardware Service warranty coverage on the Dell Support website","See where repairs are completed in Terms and Conditions. ","The Dell hardware technician will provide you with an estimate after the problem is diagnosed. ","If the technician determines that the repair is not covered under warranty, the computer will be returned to you.","If the computer can't be repaired, the technician will advise you of the process to request a replacement computer from your Dell sales representative"};
    int[] images={R.drawable.service,R.drawable.warranty,R.drawable.repair,R.drawable.offcampus,R.drawable.time,R.drawable.laptop,R.drawable.replace};
    ListView lv1;
    FAQAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        lv1=(ListView)findViewById(R.id.myList);
        adapter=new FAQAdapter(getApplicationContext(),Questions,Answer,images);
        lv1.setAdapter(adapter);
        getSupportActionBar().setTitle("FAQ");
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(), FAQAnsActivity.class);
                intent.putExtra("Ans",Answer[position]);
                intent.putExtra("Qn",Questions[position]);
                startActivity(intent);
            }
        });

    }
}
