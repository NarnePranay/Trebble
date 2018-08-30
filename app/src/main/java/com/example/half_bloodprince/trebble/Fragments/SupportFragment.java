package com.example.half_bloodprince.trebble.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.half_bloodprince.trebble.Activities.CallActivity;
import com.example.half_bloodprince.trebble.Activities.FeedBackActivity;
import com.example.half_bloodprince.trebble.Activities.FeedbackListActivity;
import com.example.half_bloodprince.trebble.Activities.SplashScreenActivity;
import com.example.half_bloodprince.trebble.FAQActivity;
import com.example.half_bloodprince.trebble.Homeactivity;
import com.example.half_bloodprince.trebble.MapsActivity;
import com.example.half_bloodprince.trebble.POJO.Post;
import com.example.half_bloodprince.trebble.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupportFragment extends Fragment {
    SharedPreferences sharedPreferences;
    String Uid;
    int x=0;
    ProgressDialog dialog;
    public SupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_support,container,false);
        Button b1=(Button)view.findViewById(R.id.faqs);
        sharedPreferences=getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Uid=sharedPreferences.getString("id","Akhil");
        Log.d("Nmae/Support",Uid);


        final Spinner spinner = (Spinner) view.findViewById(R.id.laptop_name);

        // Spinner click listener


        List<String> categories = new ArrayList<String>();
        categories.add("Inspiron 15 7570");
        categories.add("Inspiron 15 1879");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);




        Button button=(Button)view.findViewById(R.id.feedback_list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FeedbackListActivity.class));
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FAQActivity.class));
            }
        });

        Button sendFeedback=view.findViewById(R.id.send_feedback);
        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FeedBackActivity.class));


            }
        });

        Button b2=(Button)view.findViewById(R.id.serviceCenter);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MapsActivity.class));
            }
        });

        Button call=view.findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog= ProgressDialog.show(getContext(), "", "Checking..!!", true);
                checkRequest();
            }
        });

        return view;
    }

    void checkRequest()
    {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        final String url = "https://trebble-b578d.firebaseio.com/requests/"+Uid+".json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response",response);
                        if(!response.contentEquals("null"))
                        {
                            dialog.dismiss();
                           Toast.makeText(getContext(),"Please Wait Our Support Team Is working on your Request",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            dialog.dismiss();
                            startActivity(new Intent(getActivity(), CallActivity.class));
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("place", "That didn't work!");
                Toast.makeText(getContext(),"Sorry Something went wrong",Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }


}
