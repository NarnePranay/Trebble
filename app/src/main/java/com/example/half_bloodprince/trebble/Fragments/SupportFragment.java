package com.example.half_bloodprince.trebble.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.half_bloodprince.trebble.FAQActivity;
import com.example.half_bloodprince.trebble.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupportFragment extends Fragment {


    public SupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_support,container,false);
        Button b1=(Button)view.findViewById(R.id.faqs);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FAQActivity.class));
            }
        });
        return view;
    }

}
