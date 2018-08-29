package com.example.half_bloodprince.trebble.Fragments;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.half_bloodprince.trebble.Activities.SplashScreenActivity;
import com.example.half_bloodprince.trebble.Adapters.Community_Adapter;
import com.example.half_bloodprince.trebble.Adapters.ListNewsAdapter;
import com.example.half_bloodprince.trebble.DetailsActivity;
import com.example.half_bloodprince.trebble.NewsUtility;
import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.example.half_bloodprince.trebble.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.half_bloodprince.trebble.Fragments.MainFragment.KEY_AUTHOR;
import static com.example.half_bloodprince.trebble.Fragments.MainFragment.KEY_DESCRIPTION;
import static com.example.half_bloodprince.trebble.Fragments.MainFragment.KEY_PUBLISHEDAT;
import static com.example.half_bloodprince.trebble.Fragments.MainFragment.KEY_TITLE;
import static com.example.half_bloodprince.trebble.Fragments.MainFragment.KEY_URL;
import static com.example.half_bloodprince.trebble.Fragments.MainFragment.KEY_URLTOIMAGE;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
    ArrayList<HashMap<String, String>> dataList = new ArrayList<HashMap<String, String>>();
    ListView listNews,listPosts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home,container,false);
        listNews=(ListView)view.findViewById(R.id.listNews);
        listPosts=(ListView)view.findViewById(R.id.listPosts);
        if(NewsUtility.isNetworkAvailable(getActivity()))
        {
           DownloadNews newsTask = new DownloadNews();
            newsTask.execute();
        }
        else{
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
        }
        ArrayList<PostBasic> postBasics=new ArrayList<PostBasic>();
        ArrayList<String> strings=new ArrayList<>();
        for(int i=0;i<2;i++){
            postBasics.add(SplashScreenActivity.postsArr.get(i));
            strings.add(SplashScreenActivity.postsArr1.get(i));
        }

        Community_Adapter community_adapter=new Community_Adapter(getContext(),postBasics,strings);
        listPosts.setAdapter(community_adapter);
        return view;
    }
    public class DownloadNews extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected String doInBackground(String... args) {
            String xml = "";
            String urlParameters = "";
            xml = NewsUtility.excuteGet("https://newsapi.org/v2/top-headlines?sources=techradar&apiKey=421b77bcbf5e4f3e859e974dbc7cfccf", urlParameters);
            return  xml;
        }
        @Override
        protected void onPostExecute(String xml) {

            if(xml.length()>10){ // Just checking if not empty

                try {
                    JSONObject jsonResponse = new JSONObject(xml);
                    JSONArray jsonArray = jsonResponse.optJSONArray("articles");

                    for (int i = 0; i <2; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        HashMap<String, String> map = new HashMap<String, String>();
                        map.put(KEY_AUTHOR, jsonObject.optString(KEY_AUTHOR).toString());
                        map.put(KEY_TITLE, jsonObject.optString(KEY_TITLE).toString());
                        map.put(KEY_DESCRIPTION, jsonObject.optString(KEY_DESCRIPTION).toString());
                        map.put(KEY_URL, jsonObject.optString(KEY_URL).toString());
                        map.put(KEY_URLTOIMAGE, jsonObject.optString(KEY_URLTOIMAGE).toString());
                        map.put(KEY_PUBLISHEDAT, jsonObject.optString(KEY_PUBLISHEDAT).toString());
                        dataList.add(map);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getActivity(), "Unexpected error", Toast.LENGTH_SHORT).show();
                }


                ListNewsAdapter adapter = new ListNewsAdapter(getActivity(), dataList);
                listNews.setAdapter(adapter);

                listNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(getActivity(), DetailsActivity.class);
                        i.putExtra("url", dataList.get(position).get(KEY_URL));
                        startActivity(i);
                    }
                });

            }else{
                Toast.makeText(getActivity(), "No news found", Toast.LENGTH_SHORT).show();
            }
        }



    }

}
