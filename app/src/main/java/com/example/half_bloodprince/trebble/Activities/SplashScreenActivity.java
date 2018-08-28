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
import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.example.half_bloodprince.trebble.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;


public class SplashScreenActivity extends AppCompatActivity implements Animation.AnimationListener {

    private static final long SPLASH_SCREEN_TIMEOUT = 800;
    private boolean can_be_finished=false;

    public static ArrayList<PostBasic > postsArr=new ArrayList<>();
    public static ArrayList <String >postsArr1=new ArrayList<>();

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

                            Intent homeint = new Intent(getApplicationContext(), Homeactivity.class);
                            startActivity(homeint);
                            finish();

                            Toast.makeText(SplashScreenActivity.this,"Done with text",Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work!");
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
}
