package com.example.half_bloodprince.trebble.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.half_bloodprince.trebble.Adapters.Community_Adapter;
import com.example.half_bloodprince.trebble.Homeactivity;
import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.example.half_bloodprince.trebble.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class communityFragment extends Fragment {

 ArrayList<PostBasic> mList;
    public communityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View view=inflater.inflate(R.layout.fragment_community, container, false);
        ListView lv=(ListView)view.findViewById(R.id.community_List);
       // ArrayList<PostBasic> fl=new ArrayList<>();
        Community_Adapter community_adapter=new Community_Adapter(getContext(), Homeactivity.postsArr,Homeactivity.postsArr1);
        lv.setAdapter(community_adapter);
         return view;
    }

}
