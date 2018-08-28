package com.example.half_bloodprince.trebble.Activities;

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
import com.example.half_bloodprince.trebble.POJO.Comment;
import com.example.half_bloodprince.trebble.POJO.Post;
import com.example.half_bloodprince.trebble.POJO.TagsUser;
import com.example.half_bloodprince.trebble.R;
import com.example.half_bloodprince.trebble.SearchActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class FeedBackActivity extends AppCompatActivity {
    TextInputEditText que,title;
    SharedPreferences sharedPreferences;
    Post post;
    ArrayList<TagsUser>tag=new ArrayList<>();
    ArrayList<String> str=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        sharedPreferences= getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        android.support.v7.widget.Toolbar myToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
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
                    String str=title.getText().toString()+"."+que.getText().toString();
                   // str=str.toLowerCase();
                    str="Laptop Inspiron 15 7000 Series Model 7579 wont start or boot";
                    getGoogleEntitySenti(str);




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

    public synchronized void getGoogleEntitySenti(final String Str)  {
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
        String url = "https://language.googleapis.com/v1/documents:analyzeEntitySentiment?key=AIzaSyAruffiuZO9dfi3w_7HvmCe5E_cnj5HL3A";
        StringRequest postRequest = new  StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        String strq="";
                        Log.d("Response", response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonBody.optJSONArray("entities");

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                String name=jsonArray.getJSONObject(i).get("name").toString();
                                Double senti=Double.parseDouble(jsonArray.getJSONObject(i).getJSONArray("mentions").getJSONObject(2).get("score").toString());
                                Calendar c=Calendar.getInstance();
                                SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
                                String post_date=sdf1.format(c.getTime());
                                post.setDate(post_date);
                                post.setTime(sdf2.format(c.getTime()));
                                String search_date="";
                                TagsUser tg=new TagsUser(0,post_date,search_date,senti);
                                tag.add(tg);
                                str.add(name);
                                strq+=name+",";
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        post.setName("mom2dylkaychase");
                        post.setName(sharedPreferences.getString("name","Akhil"));
                        post.setRank("Copper");
                        post.setBody(que.getText().toString());
                        post.setHeading(title.getText().toString());
                        Comment cmt[]={new Comment("","","","","")};
                        post.setReplies(cmt);
                        post.setReply_count(0);
                        post.setTags(strq);
                        Random rand=new Random();
                        post.setViews(0);


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

                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        queue.add(postRequest);
        // return str;
    }

}
