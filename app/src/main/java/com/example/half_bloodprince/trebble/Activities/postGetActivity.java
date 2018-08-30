package com.example.half_bloodprince.trebble.Activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.half_bloodprince.trebble.Adapters.Community_Adapter;
import com.example.half_bloodprince.trebble.Adapters.FeedBackList_Adapter;
import com.example.half_bloodprince.trebble.Fragments.communityFragment;
import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.example.half_bloodprince.trebble.R;

import java.util.ArrayList;

public class postGetActivity extends AppCompatActivity {
   // ArrayList<PostBasic> postBasics;
    ArrayList<String> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=this.getIntent().getExtras();
        // postBasics=(ArrayList<PostBasic>)bundle.getSerializable("mList");
//        posts=bundle.getStringArrayList("mList1");
        Log.d("postGetActivity","true");
        setContentView(R.layout.activity_post_get);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_postGet);  //toolbars
        setSupportActionBar(myToolbar);
        myToolbar.setTitle("helo");
        myToolbar.setTitleTextColor(Color.GRAY);
        ListView listView=(ListView)findViewById(R.id.feedback_postGet);
       //
        // FeedBackList_Adapter feedBackList_adapter=new FeedBackList_Adapter(SplashScreenActivity.UserPost,SplashScreenActivity.UserPost_name,postGetActivity.this);
         Community_Adapter community_adapter=new Community_Adapter(postGetActivity.this, communityFragment.mList,communityFragment.mList1);
         listView.setAdapter(community_adapter);
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
