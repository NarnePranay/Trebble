package com.example.half_bloodprince.trebble.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.example.half_bloodprince.trebble.PostsActivity;
import com.example.half_bloodprince.trebble.R;

import java.util.ArrayList;

/*
 * Created by j.girish on 25-08-2018.
 */

public class Community_Adapter extends BaseAdapter {
    Context mcontext;
    ArrayList<PostBasic> mArrayList;
    public Community_Adapter(Context mContext, ArrayList<PostBasic> mArrayList)
    {
      this.mcontext=mContext;
      this.mArrayList=mArrayList;

    }
    @Override
    public int getCount() {
      // return mArrayList.size();
        return 10;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(mcontext).inflate(R.layout.post_list_view, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext,PostsActivity.class);
                mcontext.startActivity(i);
            }
        });
       /* TextView quest=(TextView)view.findViewById(R.id.question);
        quest.setText(mArrayList.get(position).getQuestion());
        TextView date=(TextView)view.findViewById(R.id.question);
        date.setText(mArrayList.get(position).getDate());
        TextView name=(TextView)view.findViewById(R.id.question);
        name.setText(mArrayList.get(position).getName());
        TextView views=(TextView)view.findViewById(R.id.question);
        views.setText(mArrayList.get(position).getViews());*/

        return view;
    }
}
