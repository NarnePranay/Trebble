package com.example.half_bloodprince.trebble.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.half_bloodprince.trebble.Activities.FeedbackListActivity;
import com.example.half_bloodprince.trebble.POJO.Post;
import com.example.half_bloodprince.trebble.PostsActivity;
import com.example.half_bloodprince.trebble.R;

import java.util.ArrayList;

/**
 * Created by j.girish on 30-08-2018.
 */

public class FeedBackList_Adapter extends BaseAdapter {
    ArrayList<Post> mArrayList;
     ArrayList<String> mstr;
     Context context;

    public FeedBackList_Adapter(ArrayList<Post> mArrayList, ArrayList<String> mstr, Context context) {
        this.mArrayList = mArrayList;
        this.mstr = mstr;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mArrayList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.post_list_view, null);
        TextView quest=(TextView)view.findViewById(R.id.question);
        quest.setText(mArrayList.get(position).getHeading());
        TextView date=(TextView)view.findViewById(R.id.date);
        date.setText(mArrayList.get(position).getDate());
        TextView name=(TextView)view.findViewById(R.id.UserName);
        name.setText(mArrayList.get(position).getName());
        TextView views=(TextView)view.findViewById(R.id.views);
        views.setText(mArrayList.get(position).getViews()+" Views");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context, PostsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("post", mArrayList.get(position));
                bundle.putString("postName",mstr.get(position));
                bundle.putInt("position", position);
                i.putExtras(bundle);
                context.startActivity(i);
                ((Activity)context).finish();
            }
        });
        return view;
    }
}
