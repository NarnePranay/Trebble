package com.example.half_bloodprince.trebble;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.half_bloodprince.trebble.Adapters.Comments_Adapter;
import com.example.half_bloodprince.trebble.Adapters.Community_Adapter;
import com.example.half_bloodprince.trebble.Adapters.HorizontalAdapter;

import java.util.ArrayList;

public class PostsActivity extends AppCompatActivity {
    Toolbar mActionBarToolbar;
    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //myToolbar.setTitle("COMMUNITY");
        myToolbar.setTitleTextColor(Color.GRAY);
        getSupportActionBar().setTitle("Community");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
       // getSupportActionBar().setTitle("Post");
        ArrayList<String> input=new ArrayList<String>();
        input.add("AlienWare");
        input.add("heating");
        input.add("Check");
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontalAdapter=new HorizontalAdapter(input,getApplication());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(PostsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
        NonScrollListView listView=(NonScrollListView)findViewById(R.id.myList);
        Comments_Adapter comments_adapter=new Comments_Adapter(PostsActivity.this);
        listView.setAdapter(comments_adapter);


    }
}
