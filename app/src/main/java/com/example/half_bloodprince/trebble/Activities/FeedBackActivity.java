package com.example.half_bloodprince.trebble.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.half_bloodprince.trebble.Adapters.Community_Adapter;
import com.example.half_bloodprince.trebble.POJO.Comment;
import com.example.half_bloodprince.trebble.POJO.Post;
import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.example.half_bloodprince.trebble.POJO.SendPost;
import com.example.half_bloodprince.trebble.POJO.TagsUser;
import com.example.half_bloodprince.trebble.POJO.sentiment;
import com.example.half_bloodprince.trebble.R;
import com.example.half_bloodprince.trebble.SearchActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import static com.example.half_bloodprince.trebble.Activities.SplashScreenActivity.user;

public class FeedBackActivity extends AppCompatActivity {
    TextInputEditText que,title;
    SharedPreferences sharedPreferences;
    SendPost post;
    Post post1;
    String Uname="",Uid="";
    int check=0;
    String p_date;
    int post_number;
    PostBasic pbasic;
    ProgressDialog dialog;
    //ArrayList<TagsUser>tag=new ArrayList<>();
    ArrayList<String> str=new ArrayList<>();
    ArrayList<Double> senq=new ArrayList<>();
    ArrayList<Double> sent=new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference myRef,myRef1,myRef2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        database = FirebaseDatabase.getInstance();
        sharedPreferences= getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        android.support.v7.widget.Toolbar myToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        pbasic=new PostBasic();
        post1=new Post();
        post=new SendPost();
        Uid=sharedPreferences.getString("id","lol");
        que=(TextInputEditText)findViewById(R.id.question1);

        title=(TextInputEditText)findViewById(R.id.title);

        //myToolbar.setTitle("COMMUNITY");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Button button=(Button)findViewById(R.id.send_Post);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(que.getText().length()>0 && title.getText().length()>0)
                {
                    String str=title.getText().toString()+" "+que.getText().toString();
                   str=str.toLowerCase();
                    //str="Laptop Inspiron 15 7000 Series Model 7579 wont start or boot";
                    dialog= ProgressDialog.show(FeedBackActivity.this, "", "Posting...Please Wait", true);
                   getGoogleEntitySenti(str);
                   // getSentiGoogle(str);


                }
                else
                    {
                        Toast.makeText(FeedBackActivity.this,"Input Not Valid",Toast.LENGTH_SHORT).show();
                    }
            }
        });



        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public synchronized void getGoogleEntitySenti(String string)  {
        final String Str=string;

        final JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("type", "PLAIN_TEXT");
            jsonBody.put("content", Str.toLowerCase());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jbody=new JSONObject();
        try {
            jbody.put("document",jsonBody);
            jbody.put("encodingType","UTF8");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody=jbody.toString();

        RequestQueue queue = Volley.newRequestQueue(FeedBackActivity.this);
        final String url = "https://language.googleapis.com/v1/documents:analyzeEntitySentiment?key=AIzaSyAruffiuZO9dfi3w_7HvmCe5E_cnj5HL3A";
        StringRequest postRequest = new  StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        String strq="";
                        Calendar c=Calendar.getInstance();
                        SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd-yyyy");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                        String post_date=sdf1.format(c.getTime());
                        p_date=post_date;

                        Log.d("Response", response+" "+p_date);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.optJSONArray("entities");
                            Log.d("Lent/Google",jsonArray.length()+"");
                            for(int i=0;i<jsonArray.length();i++)
                            {
                                String name=jsonArray.getJSONObject(i).getString("name");
                                name=name.replace(".","*");
                                Double senti=jsonArray.getJSONObject(i).getJSONObject("sentiment").getDouble("score");
                                Log.d("Tagname",name);
                                Log.d("sentiment",senti+"");
                                String search_date="";
                                str.add(name);
                                sent.add(senti);
                                if(user.getHm().get(name)!=null)
                                {
                                    myRef1=database.getReference("users").child(Uid).child("tags").child(name).child("sentiment");
                                    Double sentimentx=calculateSentiment(user.getHm().get(name).getPost_date(), user.getHm().get(name).getSentiment(),senti);
                                    myRef1.setValue(sentimentx);
                                    senq.add(sentimentx);
                                    myRef1=database.getReference("users").child(Uid).child("tags").child(name).child("post_date");
                                    myRef1.setValue(post_date);



                                }
                                else
                                {
                                    TagsUser tg=new TagsUser(0,post_date,search_date,senti);
                                    //user.getHm().put(name,tg);
                                    myRef=database.getReference("users").child(Uid).child("tags").child(name);
                                    myRef.setValue(tg);
                                    senq.add(senti);
                                    HashMap<String,TagsUser> hm=user.getHm();
                                    hm.put(name,tg);
                                    user.setHm(hm);
                                    strq+=name+",";
                                }


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        post.setDate(post_date);
                        post1.setDate(post_date);
                        pbasic.setDate(post_date);
                        pbasic.setTime(sdf2.format(c.getTime()));
                        post.setTime(sdf2.format(c.getTime()));
                        post1.setTime(sdf2.format(c.getTime()));
                        post.setName(sharedPreferences.getString("name","Akhil"));
                        post1.setName(sharedPreferences.getString("name","Akhil"));
                        Uname=sharedPreferences.getString("name","Akhil");
                        post.setRank("Copper");
                        post1.setRank("Copper");
                        post.setBody(que.getText().toString());
                        post1.setBody(que.getText().toString());
                        post.setHeading(title.getText().toString());
                        post1.setHeading(title.getText().toString());
                        ArrayList<Comment> cmt=new ArrayList<>();
                        Comment cmt1[]={new Comment("","","","","")};
                        cmt.add(new Comment("","","","",""));
                        post.setReplies(cmt);
                        post1.setReplies(cmt1);
                        post.setReply_count(0);
                        post1.setReply_count(0);
                        post.setTags(strq);
                        post1.setTags(strq);
                        post.setViews(0);
                        post1.setViews(0);
                        pbasic.setHeading(title.getText().toString());
                        pbasic.setName(sharedPreferences.getString("name","Akhil"));
                        pbasic.setRank("Copper");
                        pbasic.setReply_count(0);
                        getPostNo();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }

        )
        {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody== null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    Charset UTF8_CHARSET = Charset.forName("UTF-8");
                    responseString = new String(response.data, UTF8_CHARSET);

                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        queue.add(postRequest);
        // return str;
    }

   public double calculateSentiment(String prev_date, Double sentiment,Double pres_sentiment)
    {
        Date date1=null,date2=null;
        prev_date=prev_date.substring(1);
        String presdate=p_date.substring(1);
        Log.d("prev_date",prev_date.charAt(0)+"-"+prev_date.charAt(1)+"-"+prev_date.charAt(2)+prev_date.charAt(3)+"-"+prev_date.charAt(4));

        prev_date=prev_date.replace("\200e","");
        presdate=p_date.replace("\200e","");
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
        Log.d("prev_date",prev_date);
        Log.d("p_date",presdate);
        try {
           // sdf.setLenient(false);
            date1=sdf.parse(prev_date);
            date2=sdf.parse(presdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        float diff= (date1.getTime()-date2.getTime())/(1000*60*60*24);
        diff/=100;
        Double alpha=1/(Math.pow(2.71828182846,diff));
        Double present_senti=alpha*sentiment+(1-alpha)*pres_sentiment;
//        myRef1=database.getReference("users").child(Uname).child("tags").child(str).child("sentiment");
//        myRef1.setValue(present_senti);
//        myRef1=database.getReference("users").child(Uname).child("tags").child(str).child("post_date");
//        myRef1.setValue(p_date);

return present_senti;
    }

    void getPostNo()
    {
        String url="https://trebble-b578d.firebaseio.com/posts/post_count.json";
        RequestQueue queue = Volley.newRequestQueue(FeedBackActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    Log.d("response",response);
                    post_number=Integer.parseInt(response);
                    int x=1000+post_number;
                    int y=post_number+1;
                    String check=user.getPosts()+x+",";
                    int z=1+user.getPost_count();
                        myRef2=database.getReference("shortPosts").child(x+"");
                        myRef2.setValue(pbasic);
                        myRef2=database.getReference("posts").child(x+"");
                        myRef2.setValue(post);
                        myRef2=database.getReference("posts").child("post_count");
                        myRef2.setValue(y);
                        myRef2=database.getReference("users").child(Uid).child("post_count");
                        myRef2.setValue(z);
                        myRef2=database.getReference("users").child(Uid).child("post_count");
                        myRef2.setValue(z);
                        myRef2=database.getReference("users").child(Uid).child("posts");
                        myRef2.setValue(check);
                       // post_number=post_number+1+1000;
                        user.setPost_count(z);
                        for(int i=0;i<str.size();i++) //userTags
                        {
                            Log.d("string/feed",str.get(i));
                            getTag(str.size(),str.get(i),i);


                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work! heu");
                Toast.makeText(FeedBackActivity.this,"SomeThing Went Wrong...",Toast.LENGTH_SHORT).show();
                dialog.dismiss();


            }
        });

        queue.add(stringRequest);
    }

    void getTag(int pos, String string, int curre_position)
    {
       final  int po=pos;
        final String str=string;
        final int  curr=curre_position;
        string=string.replace(" ","%20");
        String url="https://trebble-b578d.firebaseio.com/tags/"+string+".json";
        Log.d("url",url);
        RequestQueue queue = Volley.newRequestQueue(FeedBackActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response/getTag",response);
                        check++;
                        if(!response.contentEquals("null"))
                        {
                            try {
                            JSONObject jsonObject=new JSONObject(response);
                            int post_c=jsonObject.getInt("post_count");
                            double overall_sentiment=jsonObject.getDouble("overall_sentiment");
                            overall_sentiment=(overall_sentiment*post_c+sent.get(curr))/(post_c+1);
                            String posts=jsonObject.getString("posts");
                            posts=posts+" "+(post_number+1000)+",";
                            myRef2=database.getReference("tags").child(str+"").child("overall_sentiment");
                            myRef2.setValue(overall_sentiment);
                            myRef2=database.getReference("tags").child(str+"").child("posts");
                            myRef2.setValue(posts);
                            myRef2=database.getReference("tags").child(str+"").child("post_count");
                            myRef2.setValue(post_c+1);

                            if(sent.get(curr)<0) {
                                myRef1=database.getReference("tags").child(str+"").child("negative_post_count");
                                myRef1.setValue(jsonObject.getInt("negative_post_count")+1);
                            }
                            else if(sent.get(curr)>0)
                            {
                                myRef1=database.getReference("tags").child(str+"").child("positive_post_count");
                                myRef1.setValue(jsonObject.getInt("positive_post_count")+1);
                            }
                            myRef=database.getReference("tags").child(str+"").child("sentiment_array").child((post_c+1)+"").child("0");
                            myRef.setValue(sent.get(curr));
                            myRef=database.getReference("tags").child(str+"").child("sentiment_array").child((post_c+1)+"").child("1");
                            myRef.setValue(p_date);


                            } catch (JSONException e) {
                            e.printStackTrace();
                            }


                        }
                        else
                        {

                            if(sent.get(curr)>0)
                            {
                                myRef=database.getReference("tags").child(str+"").child("positive_post_count");
                                myRef.setValue(1);
                                myRef=database.getReference("tags").child(str+"").child("negative_post_count");
                                myRef.setValue(0);
                            }
                            else if(sent.get((curr))<0)
                            {

                                    myRef=database.getReference("tags").child(str+"").child("positive_post_count");
                                    myRef.setValue(0);
                                    myRef=database.getReference("tags").child(str+"").child("negative_post_count");
                                    myRef.setValue(1);

                            }
                            myRef=database.getReference("tags").child(str+"").child("overall_sentiment");
                            myRef.setValue(sent.get(curr));
                            myRef=database.getReference("tags").child(str+"").child("post_count");
                            myRef.setValue(1);
                            myRef=database.getReference("tags").child(str+"").child("posts");
                            myRef.setValue((post_number+1000)+",");
                            myRef=database.getReference("tags").child(str+"").child("search_count");
                            myRef.setValue(0);
                            myRef=database.getReference("tags").child(str+"").child("sentiment_array").child("0").child("0");
                            myRef.setValue(sent.get(curr));
                            myRef=database.getReference("tags").child(str+"").child("sentiment_array").child("0").child("1");
                            myRef.setValue(p_date);

                        }
                        if(po==check)
                        getSentiGoogle(str);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work! heu");
                Toast.makeText(FeedBackActivity.this,"SomeThing Went Wrong...",Toast.LENGTH_SHORT).show();
                dialog.dismiss();


            }
        });
        queue.add(stringRequest);
    }
    public synchronized void getSentiGoogle(String string)  {
        final String Str=string;

        final JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("type", "PLAIN_TEXT");
            jsonBody.put("content", Str.toLowerCase());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final JSONObject jbody=new JSONObject();
        try {
            jbody.put("document",jsonBody);
            jbody.put("encodingType","UTF8");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody=jbody.toString();

        RequestQueue queue = Volley.newRequestQueue(FeedBackActivity.this);
        String url = "https://language.googleapis.com/v1/documents:analyzeSentiment?key=AIzaSyAruffiuZO9dfi3w_7HvmCe5E_cnj5HL3A";
        StringRequest postRequest = new  StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            Double senti=jsonObject.getJSONObject("documentSentiment").getDouble("score");
                            senti=calculateSentiment(SplashScreenActivity.user.getPost_date(),SplashScreenActivity.user.getSentiment(),senti);
                            myRef=database.getReference("users").child(Uid).child("sentiment");
                            myRef.setValue(senti);
                            user.setSentiment(senti);
                            ArrayList<sentiment>arrayList=user.getArrylist();
                            arrayList.add(new sentiment(senti,p_date));
                            user.setArrylist(arrayList);
                            myRef=database.getReference("users").child(Uid).child("sentiment_array").child((user.getPost_count()-1)+"").child("0");
                            myRef.setValue(senti);
                            myRef=database.getReference("users").child(Uid).child("sentiment_array").child((user.getPost_count()-1)+"").child("1");
                            myRef.setValue(p_date);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        SplashScreenActivity.UserPost.add(post1);
                        SplashScreenActivity.UserPost_name.add((post_number+1000)+"");
                        Toast.makeText(FeedBackActivity.this,"Successfully posted",Toast.LENGTH_SHORT).show();
                        finish();

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", error.toString());
                    }
                }

        )
        {

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody== null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    Charset UTF8_CHARSET = Charset.forName("UTF-8");
                    responseString = new String(response.data, UTF8_CHARSET);

                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        queue.add(postRequest);
        // return str;
    }

}
