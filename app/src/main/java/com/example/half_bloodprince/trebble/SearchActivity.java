package com.example.half_bloodprince.trebble;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.half_bloodprince.trebble.Activities.FeedBackActivity;
import com.example.half_bloodprince.trebble.Adapters.Community_Adapter;
import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchActivity extends AppCompatActivity {
    Community_Adapter community_adapter;
    ListView lv;
    ArrayList<String>str1=new ArrayList<>();
    HashMap<String ,Integer> hm=new HashMap<>();
    ArrayList <PostBasic> posts=new ArrayList<>();
    ArrayList <String> postIds=new ArrayList<>();
    ArrayList<String> str=new ArrayList<>();
    ProgressDialog dialog;
    TextView oops;
    int check=0,check1=0;
    RelativeLayout rela;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);  //toolbars
        setSupportActionBar(myToolbar);
        //myToolbar.setTitle("COMMUNITY");
        myToolbar.setTitleTextColor(Color.GRAY);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        lv=(ListView)findViewById(R.id.lv);

        oops=(TextView)findViewById(R.id.oops);

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        SearchView searchView=(SearchView)findViewById(R.id.searchView);
        rela=(RelativeLayout)findViewById(R.id.rela);
        Button button=(Button)findViewById(R.id.issue);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this, FeedBackActivity.class);
                startActivity(intent);
                finish();

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
              dialog = ProgressDialog.show(SearchActivity.this, "", "Loading. Please wait...", true);
              posts.clear();
              str.clear();
              postIds.clear();
              str1.clear();
              hm.clear();;
              str1.clear();
              rela.setVisibility(View.GONE);
              check=0;
              check1=0;
              oops.setVisibility(View.GONE);
               // Toast.makeText(SearchActivity.this,query,Toast.LENGTH_SHORT).show();
              getGoogleEntity(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }




    public synchronized void getGoogleEntity(final String Str)  {
         final
        JSONObject jsonBody = new JSONObject();
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

        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        String url = "https://language.googleapis.com/v1/documents:analyzeEntities?key=AIzaSyAruffiuZO9dfi3w_7HvmCe5E_cnj5HL3A";
        StringRequest postRequest = new  StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response

                        Log.d("Response", response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray=jsonObject.getJSONArray("entities");

                            for(int i=0;i<jsonArray.length();i++)
                            {
                                str.add(jsonArray.getJSONObject(i).get("name").toString());
                                Log.d("tags",str.get(i));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        if(str.size()>0) {

                            for (int i = 0; i < str.size(); i++) // getting postsIds
                            {
                                getPostIds(i,str.size());
                                Log.d("hi", "hi");
                            }

                        }
                        else
                        {
                            rela.setVisibility(View.VISIBLE);
                            dialog.dismiss();
                        }
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
    public  void getPostIds(int pos,int size)
    {
        final int p=pos;
        final int s=size;
        Log.d("posid",str.get(pos));
        String url = "https://trebble-b578d.firebaseio.com/tags/"+ str.get(pos)+"/posts.json";
        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response ids",response);

                          //  JSONObject jsonObject=new JSONObject(response);
                       //     String str=jsonObject.getString("");
                        char [] q=response.toCharArray();
                        String respo="";
                        check++;
                        for(int i=1;i<q.length-1;i++)
                        {
                            respo+=q[i];
                        }
                            String[]tokens=respo.split(", ");
                        Log.d("response ids",tokens[0]);
                            for(int i=0;i<tokens.length;i++)
                            {
                                Log.d("response ids",tokens[i]);
                                if(!tokens[i].contentEquals("")) {
                                    if (hm.get(tokens[i]) != null) {
                                        hm.put(tokens[i], hm.get(tokens[i]) + 1);
                                    } else {
                                        str1.add(tokens[i]);
                                        hm.put(tokens[i], 1);
                                        Log.d("size",str1.size()+"");
                                    }
                                }

                            }
                    if(s==check) {
                                Log.d("pstids",p+"");
                        if (str1.size() == 0) {
                            rela.setVisibility(View.VISIBLE);
                            Log.d("size", str1.size() + "");
                            dialog.dismiss();
                        } else {
                            List<Map.Entry<String, Integer>> list =
                                    new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());
                            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                                    return (o2.getValue()).compareTo(o1.getValue());
                                }
                            });
                            //  HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
                            int count=0;
                            for (Map.Entry<String, Integer> aa : list) {
                                //  temp.put(aa.getKey(), aa.getValue());
                                Log.d("key",aa.getKey());
                                getPostbasic(aa.getKey(),str1.size(),count);
                                count++;
                            }


                        }

                    }
                    else
                    {
                        Log.d("pstids",p+" "+s);
                     //   dialog.dismiss();
                    }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work! heeeee");
                oops.setVisibility(View.VISIBLE);
                dialog.dismiss();

            }
        });

        queue.add(stringRequest);

    }

    public void  getPostbasic(String str,int size,int csize)
    {
        final int s=size;
        final int cs=csize;
        final String check=str;
        String url = "https://trebble-b578d.firebaseio.com/shortPosts/"+str+".json";
        RequestQueue queue = Volley.newRequestQueue(SearchActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                        check1++;
                        if(!response.contentEquals("null")) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Gson gson = new Gson();
                                PostBasic post = gson.fromJson(response, PostBasic.class);
                                posts.add(post);
                                postIds.add(check);
                                Log.d("gpb",posts.size()+"");

                                if (s == check1 ) {
                                    community_adapter = new Community_Adapter(SearchActivity.this, posts, postIds);
                                    lv.setAdapter(community_adapter);
                                    dialog.dismiss();
                                } else {
                                    Log.d("pbsic", s + " " + cs);
                                    Log.d("gpb",posts.size()+"");
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            dialog.dismiss();
                            rela.setVisibility(View.VISIBLE);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work! heu");
                oops.setVisibility(View.VISIBLE);
                dialog.dismiss();

            }
        });

        queue.add(stringRequest);
    }
}
