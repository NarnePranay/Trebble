package com.example.half_bloodprince.trebble.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.half_bloodprince.trebble.Activities.SplashScreenActivity;
import com.example.half_bloodprince.trebble.Activities.postGetActivity;
import com.example.half_bloodprince.trebble.Adapters.Community_Adapter;
import com.example.half_bloodprince.trebble.Homeactivity;
import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.example.half_bloodprince.trebble.R;
import com.example.half_bloodprince.trebble.SearchActivity;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class communityFragment extends Fragment {

    public static ArrayList<PostBasic> mList=new ArrayList<>();
    public  static  ArrayList<String> mList1=new ArrayList<>();
    ProgressDialog dialog;
    public communityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_community, container, false);
        ListView lv=(ListView)view.findViewById(R.id.community_List);
        mList.clear();
        mList1.clear();
       // ArrayList<PostBasic> fl=new ArrayList<>();
       Button button1=(Button)view.findViewById(R.id.but1);
       final String but1=button1.getText().toString();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                mList1.clear();
                dialog = ProgressDialog.show(getContext(), "", "Loading. Please wait...", true);
                getPostIds(but1);
            }
        });
        Button button2=(Button)view.findViewById(R.id.but2);
        final String but2=button2.getText().toString();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                mList1.clear();
                dialog = ProgressDialog.show(getContext(), "", "Loading. Please wait...", true);
                getPostIds(but2);
            }
        });
        Button button3=(Button)view.findViewById(R.id.but3);
        final String but3=button3.getText().toString();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                mList1.clear();
                dialog = ProgressDialog.show(getContext(), "", "Loading. Please wait...", true);
                getPostIds(but3);
            }
        });
        Button button4=(Button)view.findViewById(R.id.but11);
        final String but4=button4.getText().toString();
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                mList1.clear();
                dialog = ProgressDialog.show(getContext(), "", "Loading. Please wait...", true);
                getPostIds(but4);
            }
        });
        Button button5=(Button)view.findViewById(R.id.but12);
        final String but5=button5.getText().toString();
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                mList1.clear();
                dialog = ProgressDialog.show(getContext(), "", "Loading. Please wait...", true);
                getPostIds(but5);
            }
        });
        Button button6=(Button)view.findViewById(R.id.but13);
        final String but6=button6.getText().toString();
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                mList1.clear();
                dialog = ProgressDialog.show(getContext(), "", "Loading. Please wait...", true);
                getPostIds(but6);
            }
        });
        Button button7=(Button)view.findViewById(R.id.but14);
        final String but7=button7.getText().toString();
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                mList1.clear();
                dialog = ProgressDialog.show(getContext(), "", "Loading. Please wait...", true);
                getPostIds(but7);
            }
        });
        Button button8=(Button)view.findViewById(R.id.but15);
        final String but8=button8.getText().toString();
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                mList1.clear();
                dialog = ProgressDialog.show(getContext(), "", "Loading. Please wait...", true);
                getPostIds(but8);
            }
        });
      //  dialog = ProgressDialog.show(getContext(), "", "Loading. Please wait...", true);
        Community_Adapter community_adapter=new Community_Adapter(getContext(), SplashScreenActivity.postsArr,SplashScreenActivity.postsArr1);
        lv.setAdapter(community_adapter);
         return view;
    }

    public  void getPostIds(String str)
    {
        Log.d("Value",str);
        str=str.replace(" ","%20");
//        final int p=pos;
//        final int s=size;
//        Log.d("posid",str.get(pos));
        String url = "https://trebble-b578d.firebaseio.com/tags/"+str+"/posts.json";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response ids",response);

                        //  JSONObject jsonObject=new JSONObject(response);
                        //     String str=jsonObject.getString("");
                        char [] q=response.toCharArray();
                        String respo="";
                        for(int i=1;i<q.length-1;i++)
                        {
                            respo+=q[i];
                        }
                        String[]tokens=respo.split(",");
                        Log.d("response ids",tokens[0]);
                        for(int i=0;i<tokens.length-1;i++)
                        {
                            Log.d("str",tokens[i]);
                           // if(!tokens[i].contentEquals("  "));
                           getPostbasic(tokens[i].replace(" ",""),tokens.length,i);

                        }



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work! heeeee");
                Toast.makeText(getContext(),"Oops Something went wrong",Toast.LENGTH_SHORT).show();
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
        Log.d("vales",str);
       // str=str.replace(" ","%20");
        String url = "https://trebble-b578d.firebaseio.com/shortPosts/"+str+".json";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Gson gson = new Gson();
                                PostBasic post = gson.fromJson(response, PostBasic.class);
                                mList.add(post);
                                mList1.add(check);



                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        if(s-1==cs+1)
                        {
                            Intent intent=new Intent(getContext(), postGetActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("mList",(Serializable)mList);
                            bundle.putStringArrayList("mList1",mList1);
                            dialog.dismiss();
                            getContext().startActivity(intent);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work! heu");
                Toast.makeText(getContext(),"Oops Something went wrong",Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });

        queue.add(stringRequest);
    }

}
