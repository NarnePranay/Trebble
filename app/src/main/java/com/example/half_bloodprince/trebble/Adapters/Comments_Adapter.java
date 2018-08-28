package com.example.half_bloodprince.trebble.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.half_bloodprince.trebble.POJO.Comment;
import com.example.half_bloodprince.trebble.R;

import java.util.ArrayList;

/**
 * Created by j.girish on 26-08-2018.
 */

public class Comments_Adapter extends BaseAdapter {
    Context mContext;
    Comment mComments [];

    public Comments_Adapter(Context mContext,Comment mComments[]) {
        this.mContext = mContext;
        this.mComments = mComments;
    }

    public Comments_Adapter(Context mContext)
    {
        this.mContext=mContext;
    }
    //ArrayList<>
    @Override
    public int getCount() {
        return mComments.length-1;
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
        View view= LayoutInflater.from(mContext).inflate(R.layout.comment_layout, null);
        Log.d("position",position+"");
        TextView comment=(TextView)view.findViewById(R.id.Comment);
        comment.setText(mComments[position+1].getComment()+"");
        TextView name=(TextView)view.findViewById(R.id.UserName_Cmnt);
        name.setText(mComments[position+1].getName());
        TextView date=(TextView)view.findViewById(R.id.date_Cmnt);
        date.setText(mComments[position+1].getDate());
        return view;
    }
}
