package com.example.half_bloodprince.trebble.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.half_bloodprince.trebble.R;

import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {
    List<String> horizontalList ;
    Context context;

    public HorizontalAdapter(List<String> horizontalList, Context context) {
        this.horizontalList = horizontalList;
        this.context = context;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtview;
        public MyViewHolder(View view) {
            super(view);
            txtview=(TextView) view.findViewById(R.id.rowItem);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.txtview.setText(horizontalList.get(position));

        holder.txtview.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String list = horizontalList.get(position);
                Toast.makeText(context, list, Toast.LENGTH_SHORT).show();
            }

        });

    }


    @Override
    public int getItemCount()
    {
        return horizontalList.size();
    }
}

