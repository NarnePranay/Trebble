package com.example.half_bloodprince.trebble.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.half_bloodprince.trebble.R;

import java.util.ArrayList;

/**
 * Created by j.girish on 26-08-2018.
 */

public class Comments_Adapter extends BaseAdapter {
    Context mContext;
    public Comments_Adapter(Context mContext)
    {
        this.mContext=mContext;
    }
    //ArrayList<>
    @Override
    public int getCount() {
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
        View view= LayoutInflater.from(mContext).inflate(R.layout.comment_layout, null);

        return view;
    }
}
