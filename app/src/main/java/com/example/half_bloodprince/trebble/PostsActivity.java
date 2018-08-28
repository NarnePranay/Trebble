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
import android.widget.TextView;

import com.example.half_bloodprince.trebble.Adapters.Comments_Adapter;
import com.example.half_bloodprince.trebble.Adapters.Community_Adapter;
import com.example.half_bloodprince.trebble.Adapters.HorizontalAdapter;
import com.example.half_bloodprince.trebble.POJO.Post;

import java.util.ArrayList;

public class PostsActivity extends AppCompatActivity {
    Toolbar mActionBarToolbar;
    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle bundle=this.getIntent().getExtras();
        Post post=(Post)bundle.getSerializable("post");
        String check=post.getTags();
        String [] str=check.split(",");
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
        TextView question=(TextView)findViewById(R.id.ques_post);
        question.setText(post.getHeading());
        TextView name=(TextView)findViewById(R.id.UserName_post);
        name.setText(post.getName());
        TextView date=(TextView)findViewById(R.id.date_post);
        date.setText(post.getDate());
        TextView views=(TextView)findViewById(R.id.views_post);
        views.setText(post.getViews()+" Views");
        TextView descrip=(TextView)findViewById(R.id.ques_post_des);
        descrip.setText(post.getBody());
        TextView com=(TextView)findViewById(R.id.titleComments);
        com.setText("Comments ("+post.getReply_count()+")");
        ArrayList<String> input=new ArrayList<String>();
        for(int i=0;i<str.length;i++)
        {
            input.add(str[i]);
        }
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontalAdapter=new HorizontalAdapter(input,getApplication());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(PostsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
        NonScrollListView listView=(NonScrollListView)findViewById(R.id.myList);
        Comments_Adapter comments_adapter=new Comments_Adapter(PostsActivity.this,post.getReplies());
        listView.setAdapter(comments_adapter);


    }
}
