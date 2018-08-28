package com.example.half_bloodprince.trebble;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.half_bloodprince.trebble.Adapters.Comments_Adapter;
import com.example.half_bloodprince.trebble.Adapters.Community_Adapter;
import com.example.half_bloodprince.trebble.Adapters.HorizontalAdapter;
import com.example.half_bloodprince.trebble.POJO.Comment;
import com.example.half_bloodprince.trebble.POJO.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PostsActivity extends AppCompatActivity {
    Toolbar mActionBarToolbar;
    int position;
    RecyclerView horizontal_recycler_view;
    HorizontalAdapter horizontalAdapter;
    FirebaseDatabase database;
    DatabaseReference myRef,myRef1;
    int replies;
    ArrayList<Comment> cmts=new ArrayList<>();
    Comment [] fcmt;
    Post post;
    String postname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);


        Bundle bundle=this.getIntent().getExtras();
        post=(Post)bundle.getSerializable("post");
         position=bundle.getInt("position");
        String check=post.getTags();
        replies=post.getReply_count();
        postname=bundle.getString("postName");
        String [] str=check.split(",");

        database = FirebaseDatabase.getInstance(); //firebase
        myRef1=database.getReference("posts").child(postname).child("reply_count");
        for(int i=0;i<post.getReplies().length;i++)
        {

            cmts.add(post.getReplies()[i]);
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);  //toolbars
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
        final EditText comm=(EditText)findViewById(R.id.Comment_edit) ;



        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        horizontalAdapter=new HorizontalAdapter(input,getApplication());

        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(PostsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManager);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
        final NonScrollListView listView=(NonScrollListView)findViewById(R.id.myList);
        Comments_Adapter comments_adapter=new Comments_Adapter(PostsActivity.this,post.getReplies());
        listView.setAdapter(comments_adapter);

        Button send=(Button)findViewById(R.id.send_cmmnt);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comm.getText().length()>0)
                {
                  //  Date currentTime = Calendar.getInstance().getTime();
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
                    Log.d("date",sdf.format(c.getTime()));
                    Log.d("time",sdf1.format(c.getTime()));
                    Comment cmt=new Comment(sdf.format(c.getTime()),comm.getText().toString(),"Gj","Dianmond",sdf1.format(c.getTime()));
                    cmts.add(cmt);
                    fcmt=new Comment[cmts.size()];
                    for(int i=0;i<cmts.size();i++)
                    {
                        fcmt[i]=cmts.get(i);
                    }

                    replies+=1;
                    Comments_Adapter comments_adapter=new Comments_Adapter(PostsActivity.this,fcmt);
                    listView.setAdapter(comments_adapter);
                    postComment(cmt);
                   // Comment cmt=new Comment();
                }
                else
                {
                    Toast.makeText(PostsActivity.this,"No text Entered",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void postComment(Comment cmt)
    {
        myRef = database.getReference("posts").child(postname).child("replies").child(replies+"");
        Log.d("heyeye",cmt.getComment());
        myRef.setValue(cmt);
        myRef1.setValue(replies);

    }
}
