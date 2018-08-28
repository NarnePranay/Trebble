package com.example.half_bloodprince.trebble.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.half_bloodprince.trebble.Homeactivity;
import com.example.half_bloodprince.trebble.POJO.Post;
import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.example.half_bloodprince.trebble.PostsActivity;
import com.example.half_bloodprince.trebble.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Iterator;

/*
 * Created by j.girish on 25-08-2018.
 */

public class Community_Adapter extends BaseAdapter {
    Context mcontext;
    ArrayList<PostBasic> mArrayList;
    Post post;
    ArrayList<String>mStrings;
    Intent i;
    ProgressDialog dialog;
    FirebaseDatabase database;
    DatabaseReference myRef;
    int flag=0;
    String postName;
    public Community_Adapter(Context mContext, ArrayList<PostBasic> mArrayList,ArrayList<String>mStrings)
    {
        this.mcontext=mContext;
        this.mArrayList=mArrayList;
        this.mStrings=mStrings;
       database = FirebaseDatabase.getInstance();

    }
    @Override
    public int getCount() {
      return mArrayList.size();
      //  return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.post_list_view, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog= ProgressDialog.show(mcontext, "", "Loading. Please wait...", true);

                 i = new Intent(mcontext,PostsActivity.class);
                RequestQueue queue = Volley.newRequestQueue(mcontext);
                postName=mStrings.get(position);
                final String url = "https://trebble-b578d.firebaseio.com/posts/"+mStrings.get(position)+".json";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("response",response);
                                try {
                                    JSONObject object = new JSONObject(response);
                                    Gson gson=new Gson();
                                    post=gson.fromJson(response,Post.class);
//                                    Iterator<String> iterator = object.keys();
//                                    int count=0;
//                                    while (iterator.hasNext()) {
//                                        postsArr1.add(iterator.next());
//                                        JSONObject obj = object.getJSONObject(iterator.next());
//                                        Gson gson=new Gson();
//                                        postsArr.add(gson.fromJson(obj.toString(),PostBasic.class));
//                                        Log.d("TAG", obj.toString());
//                                        Log.d("lol",postsArr.get(count).getName());
//                                        count++;
                                    //}
                                  //  Log.d("heyeye",post.getReplies()[1].getComment());
                                    String [] str=post.getTags().split(",");
                                    for(int i=0;i<str.length;i++)
                                    {
                                        getMyFrequency(str.length,i,str[i]);
                                    }
                                    for(int i=0;i<str.length;i++)
                                    {
                                        getFrequency(str.length,i,str[i]);
                                    }

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

            }
        });
        TextView quest=(TextView)view.findViewById(R.id.question);
        quest.setText(mArrayList.get(position).getHeading());
        TextView date=(TextView)view.findViewById(R.id.date);
        date.setText(mArrayList.get(position).getDate());
        TextView name=(TextView)view.findViewById(R.id.UserName);
        name.setText(mArrayList.get(position).getName());
        TextView views=(TextView)view.findViewById(R.id.views);
        views.setText(mArrayList.get(position).getViews()+" Views");

        return view;
    }

void getFrequency(int position, int cs,String str) {
        final String s=str;
        final int pos=position;
        final int css=cs;
    RequestQueue queue = Volley.newRequestQueue(mcontext);
    final String url = "https://trebble-b578d.firebaseio.com/tags/"+str+ "/search_count.json";
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    int x=Integer.parseInt(response);
                    Log.d("response", response);
                    myRef = database.getReference("tags").child(s).child("search_count");
                    myRef.setValue(x+1);
                    if(pos==css+1 && flag==1) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("post", post);
                        bundle.putString("postName",postName);
                        bundle.putInt("position", pos);
                        i.putExtras(bundle);
                        dialog.dismiss();
                        mcontext.startActivity(i);
                    }
                    else
                    {
                        Log.e("getFreq",pos+"  "+css+"    "+flag);
                    }


                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("place", "That didn't work!");
        }
    });

    queue.add(stringRequest);





}

    void getMyFrequency(int position, int cs,String str) {
        final String s=str;
        final int pos=position;
        final int css=cs;
        RequestQueue queue = Volley.newRequestQueue(mcontext);
        final String url = "https://trebble-b578d.firebaseio.com/users/3506189/tags/"+str+ "/frequency.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response", response+"jk;nkl");
                        if(response.contentEquals("null"))
                        {
                            myRef = database.getReference("users").child("3506189").child("tags").child(s);
                            JSONObject jsonObject=new JSONObject();
                            try {
                                jsonObject.put("frequency",1);
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                jsonObject.put("post_date",sdf.format(calendar.getTime()));
                                jsonObject.put("search_date","\"\"");
                                jsonObject.put("sentiment",-2);

                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                            myRef.setValue(jsonObject);
                        }
                        else {
                            char[] z = response.toCharArray();
                            int x = Integer.parseInt(response);
                            myRef = database.getReference("users").child("3506189").child("tags").child(s).child("frequency");
                            myRef.setValue(x + 1);
                            myRef = database.getReference("users").child("3506189").child("tags").child(s).child("search_date");
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                            // Log.d("time",sdf.format(calendar.getTime()));

                            myRef.setValue(sdf.format(calendar.getTime()));
                            if (pos == css + 1) {
                                flag = 1;
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work!");
            }
        });

        queue.add(stringRequest);





    }
}