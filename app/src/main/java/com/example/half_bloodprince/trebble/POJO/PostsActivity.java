package com.example.half_bloodprince.trebble.POJO;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.half_bloodprince.trebble.R;

import java.util.ArrayList;

public class PostsActivity extends AppCompatActivity {
    Toolbar mActionBarToolbar;
    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        getSupportActionBar().setTitle("Post");
        ArrayList<String> input=new ArrayList<String>();
        input.add("AlienWare");
        input.add("heating");
        input.add("Check");
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontalAdapter=new HorizontalAdapter(input,getApplication());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(PostsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);


    }
}
