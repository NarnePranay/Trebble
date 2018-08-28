package com.example.half_bloodprince.trebble.Adapters;

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
import com.example.half_bloodprince.trebble.Activities.SplashScreenActivity;
import com.example.half_bloodprince.trebble.Homeactivity;
import com.example.half_bloodprince.trebble.POJO.Post;
import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.example.half_bloodprince.trebble.PostsActivity;
import com.example.half_bloodprince.trebble.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Created by j.girish on 25-08-2018.
 */

public class Community_Adapter extends BaseAdapter {
    Context mcontext;
    ArrayList<PostBasic> mArrayList;
    Post post;
    public Community_Adapter(Context mContext, ArrayList<PostBasic> mArrayList)
    {
      this.mcontext=mContext;
      this.mArrayList=mArrayList;

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
                final Intent i = new Intent(mcontext,PostsActivity.class);
                RequestQueue queue = Volley.newRequestQueue(mcontext);
                final String url = "https://trebble-b578d.firebaseio.com/posts/"+ SplashScreenActivity.postsArr1.get(position)+".json";
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
                                    Log.d("heyeye",post.getReplies()[1].getComment());
                                    Bundle bundle=new Bundle();
                                    bundle.putSerializable("post",post);
                                    i.putExtras(bundle);
                                    mcontext.startActivity(i);

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



}
