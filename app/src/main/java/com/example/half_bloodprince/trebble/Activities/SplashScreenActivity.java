package com.example.half_bloodprince.trebble.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.half_bloodprince.trebble.Homeactivity;
import com.example.half_bloodprince.trebble.POJO.Post;
import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.example.half_bloodprince.trebble.POJO.TagsUser;
import com.example.half_bloodprince.trebble.POJO.User;
import com.example.half_bloodprince.trebble.POJO.sentiment;
import com.example.half_bloodprince.trebble.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;


public class SplashScreenActivity extends AppCompatActivity implements Animation.AnimationListener {

    private static final long SPLASH_SCREEN_TIMEOUT = 800;
    private boolean can_be_finished=false;
    public static User user;
    int check=0;
    public static ArrayList<PostBasic > postsArr=new ArrayList<>();
    public static ArrayList <String >postsArr1=new ArrayList<>();
    public ArrayList <sentiment> arrylist=new ArrayList<>();
    HashMap <String,TagsUser> hm=new HashMap<>();
    public static ArrayList<Post> UserPost=new ArrayList<>();

    Animation animation;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        sharedPreferences= getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name", "mom2dylkaychase");
        editor.putString("id", "3506189");

        //editor.putInt("post_count", 1);
        user=new User();
        editor.commit();

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               ImageView imageView=findViewById(R.id.loading);
               imageView.setVisibility(View.VISIBLE);
            }
        },SPLASH_SCREEN_TIMEOUT);

/*
        ImageView imageView=findViewById(R.id.img_dell);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoomin);
        imageView.startAnimation(animation);
*/
        getPosts();




        /*

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getPosts();
                Intent homeint = new Intent(getApplicationContext(), Homeactivity.class);
                startActivity(homeint);
                finish();
            }
        },SPLASH_SCREEN_TIMEOUT);

    */
    }

    public void getPosts()
    {
        RequestQueue queue = Volley.newRequestQueue(SplashScreenActivity.this);
        final String url = "https://trebble-b578d.firebaseio.com/shortPosts.json?orderBy=%22$key%22&limitToFirst=10";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                        try {
                            JSONObject object = new JSONObject(response);
                            Iterator<String> iterator = object.keys();
                            int count=0;
                            while (iterator.hasNext()) {
                                JSONObject obj = object.getJSONObject(iterator.next());
                                Gson gson=new Gson();
                                Log.d("TAG", obj.toString());
                                postsArr.add(gson.fromJson(obj.toString(),PostBasic.class));
                                Log.d("lol",postsArr.get(count).getName());
                                count++;
                            }
                            iterator=object.keys();
                            for(int i=0;i<count;i++)
                            {
                                postsArr1.add(iterator.next());
                                Log.d("hey",postsArr1.get(i));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        getUser();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work!");
                Toast.makeText(SplashScreenActivity.this,"Sorry Something went wrong",Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(stringRequest);
        Log.d("size",postsArr.size()+"");
// Access the RequestQueue through your singleton class.


    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
    public void getUser()
    {
        RequestQueue queue = Volley.newRequestQueue(SplashScreenActivity.this);
        Log.d("Name/Splash",sharedPreferences.getString("id","Akhil"));
        final String url = "https://trebble-b578d.firebaseio.com/users/"+sharedPreferences.getString("id","Akhil")+".json";
        Log.d("url",url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                        try {
                            JSONObject object = new JSONObject(response);
                            Log.d("object",object.getString("name"));
                            user.setName(object.getString("name"));
                            user.setPost_count(object.getInt("post_count"));
                            user.setPost_date(object.getString("post_date"));
                            user.setPosts(object.getString("posts"));
                            user.setRank(object.getString("rank"));
                            user.setSentiment(object.getDouble("sentiment"));
                            JSONArray jsonArray=object.getJSONArray("sentiment_array");
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                JSONArray jsonArray1=jsonArray.getJSONArray(i);
                                sentiment senti=new sentiment(jsonArray1.getInt(0),jsonArray1.getString(1));
                                arrylist.add(senti);

                            }
                            user.setArrylist(arrylist);
                            JSONObject object1=object.getJSONObject("tags");
                            Iterator<String> iterator =object1.keys();
                            Iterator<String> iterator1 =object1.keys();
                            int count=0;
                            while (iterator.hasNext()) {
                                JSONObject obj = object1.getJSONObject(iterator.next());
                                Gson gson=new Gson();
                                Log.d("TAG", obj.toString());
                                TagsUser tg=gson.fromJson(obj.toString(),TagsUser.class);
                                hm.put(iterator1.next(),tg);
                                count++;
                            }
                            user.setHm(hm);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("user posts",user.getPosts());

                        String [] str=user.getPosts().split(",");
                        for(int i=0;i<str.length;i++)
                        getUserPost(str.length,str[i]);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work!");
                Toast.makeText(SplashScreenActivity.this,"Sorry Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
        Log.d("size",postsArr.size()+"");
    }
    void getUserPost(int pos,String str)
    {
        final int p=pos;
        final String st=str;
        RequestQueue queue = Volley.newRequestQueue(SplashScreenActivity.this);
        Log.d("Name/Splash",sharedPreferences.getString("id","Akhil"));
        final String url = "https://trebble-b578d.firebaseio.com/posts/"+st+".json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                        check++;
                        try {
                            JSONObject object = new JSONObject(response);
                            Gson gson=new Gson();
                            Post post=gson.fromJson(object.toString(),Post.class);
                            UserPost.add(post);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(check==p) {
                            Intent homeint = new Intent(getApplicationContext(), Homeactivity.class);
                            startActivity(homeint);
                            finish();
                            Toast.makeText(SplashScreenActivity.this, "Done with text", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work!");
                Toast.makeText(SplashScreenActivity.this,"Sorry Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

    }
}
