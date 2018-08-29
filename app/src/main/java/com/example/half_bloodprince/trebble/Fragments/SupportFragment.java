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
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.half_bloodprince.trebble.Activities.CallActivity;
import com.example.half_bloodprince.trebble.Activities.FeedBackActivity;
import com.example.half_bloodprince.trebble.Activities.SplashScreenActivity;
import com.example.half_bloodprince.trebble.FAQActivity;
import com.example.half_bloodprince.trebble.Homeactivity;
import com.example.half_bloodprince.trebble.MapsActivity;
import com.example.half_bloodprince.trebble.POJO.Post;
import com.example.half_bloodprince.trebble.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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
