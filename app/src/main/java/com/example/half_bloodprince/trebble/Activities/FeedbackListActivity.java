package com.example.half_bloodprince.trebble.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.half_bloodprince.trebble.Adapters.Community_Adapter;
import com.example.half_bloodprince.trebble.Adapters.FeedBackList_Adapter;
import com.example.half_bloodprince.trebble.R;

public class FeedbackListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_list);  //toolbars
        setSupportActionBar(myToolbar);
        //myToolbar.setTitle("COMMUNITY");
        myToolbar.setTitleTextColor(Color.GRAY);
        ListView listView=(ListView)findViewById(R.id.feedback_list);
        FeedBackList_Adapter feedBackList_adapter=new FeedBackList_Adapter(SplashScreenActivity.UserPost,SplashScreenActivity.UserPost_name,FeedbackListActivity.this);
       // Community_Adapter community_adapter=new Community_Adapter(FeedbackListActivity.this,,SplashScreenActivity.UserPost_name);
        getSupportActionBar().setTitle("FeedBack List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
