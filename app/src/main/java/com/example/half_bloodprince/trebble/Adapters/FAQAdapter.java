package com.example.half_bloodprince.trebble.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.half_bloodprince.trebble.FAQAnsActivity;
import com.example.half_bloodprince.trebble.R;
import com.squareup.picasso.Picasso;

public class FAQAdapter extends BaseAdapter {
    Context ApplicationContext;
    String[] Question;
    String[] Answer;
    int[] images;
    LayoutInflater inflater;

    public FAQAdapter(Context ApplicationContext, String[] Question, String[] Answer, int[] images) {
        this.ApplicationContext = ApplicationContext;
        this.Question = Question;
        this.Answer=Answer;
        this.images = images;
        inflater = (LayoutInflater.from(ApplicationContext));
    }

    @Override
    public int getCount() {
        return Question.length;
    }

    @Override
    public Object getItem(int position) {
        return Question[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int i, View view, ViewGroup parent) {
        final int k=i;
        view = inflater.inflate(R.layout.faq_element, null);
        TextView tv1 = (TextView) view.findViewById(R.id.txt1);
        ImageView iv1 = (ImageView) view.findViewById(R.id.iv1);
        tv1.setText(Question[i]);

        Picasso.with(view.getContext()).load(images[i]).resize(300, 300).centerCrop().onlyScaleDown().into(iv1);
        return view;
    }

}
