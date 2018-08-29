package com.example.half_bloodprince.trebble;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.half_bloodprince.trebble.Activities.CallActivity;
import com.example.half_bloodprince.trebble.Fragments.MainFragment;
import com.example.half_bloodprince.trebble.Fragments.PageFragment;
import com.example.half_bloodprince.trebble.Fragments.SupportFragment;
import com.example.half_bloodprince.trebble.Fragments.communityFragment;

import com.example.half_bloodprince.trebble.POJO.PostBasic;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Homeactivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
 //  public static ArrayList <PostBasic >postsArr=new ArrayList<>();
  // public static ArrayList <String >postsArr1=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Trebble");
        setSupportActionBar(toolbar);
       // getPosts();


        //fabButton();

        //Navigation Layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //Tab Layout
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                Homeactivity.this));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        tabLayout.setupWithViewPager(viewPager);



    }

/*
    void fabButton()
    {
        SpeedDialView speedDialView = findViewById(R.id.speedDial);

//        activity_homeactivity_drawer

        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_support, R.drawable.ic_menu_camera)
                        .setFabBackgroundColor(Color.TRANSPARENT)
                        .setLabel("Support")
                        .setLabelColor(Color.WHITE)
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.cardview_dark_background, getTheme()))
                        .setLabelClickable(false)
                        .create()
        );

        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_community, R.drawable.ic_menu_camera)
                        .setFabBackgroundColor(Color.TRANSPARENT)
                        .setLabel("Community")
                        .setLabelColor(Color.WHITE)
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.cardview_dark_background, getTheme()))
                        .setLabelClickable(false)
                        .create()
        );

        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_news, R.drawable.ic_menu_camera)
                        .setFabBackgroundColor(Color.TRANSPARENT)
                        .setLabelColor(Color.WHITE)
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.cardview_dark_background, getTheme()))
                        .setLabelClickable(false)
                        .setLabel("News and Tips")
                        .create()
        );

        speedDialView.addActionItem(
                new SpeedDialActionItem.Builder(R.id.fab_issues, R.drawable.ic_menu_camera)
                        .setFabBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, getTheme()))
                        .setFabImageTintColor(ResourcesCompat.getColor(getResources(), R.color.cardview_light_background, getTheme()))
                        .setLabelColor(Color.WHITE)
                        .setLabelBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.cardview_dark_background, getTheme()))
                        .setLabelClickable(false)
                        .setLabel("Issues")
                        .create()
        );



        speedDialView.setOnActionSelectedListener(new SpeedDialView.OnActionSelectedListener() {
            @Override
            public boolean onActionSelected(SpeedDialActionItem speedDialActionItem) {
                switch (speedDialActionItem.getId()) {
                    case R.id.fab_community:
                        Toast.makeText(getApplicationContext(),"Community",Toast.LENGTH_LONG).show();
                        return false; // true to keep the Speed Dial open
                    default:
                        return false;
                }
            }
        });

    }


*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homeactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            Intent intent=new Intent(Homeactivity.this, SearchActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Log.d("TAG", "onNavigationItemSelected: camera");
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            viewPager.setCurrentItem(1);
        } else if (id == R.id.nav_gallery) {
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            viewPager.setCurrentItem(2);

        } else if (id == R.id.nav_slideshow) {
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            viewPager.setCurrentItem(3);

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(Homeactivity.this,FAQActivity.class));

        } else if (id == R.id.nav_call) {
            startActivity(new Intent(Homeactivity.this, CallActivity.class));

        } else if (id == R.id.nav_chat) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//    public void getPosts()
//    {
//        RequestQueue queue = Volley.newRequestQueue(Homeactivity.this);
//        final String url = "https://trebble-b578d.firebaseio.com/shortPosts.json?orderBy=%22$key%22&limitToFirst=10";
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("response",response);
//                        try {
//                            JSONObject object = new JSONObject(response);
//                            Iterator<String> iterator = object.keys();
//                            int count=0;
//                            while (iterator.hasNext()) {
//                                JSONObject obj = object.getJSONObject(iterator.next());
//                                Gson gson=new Gson();
//                                postsArr.add(gson.fromJson(obj.toString(),PostBasic.class));
//                                Log.d("TAG", obj.toString());
//                                Log.d("lol",postsArr.get(count).getName());
//                                count++;
//                            }
//                            iterator=object.keys();
//                            for(int i=0;i<count;i++)
//                            {
//                                postsArr1.add(iterator.next());
//                                Log.d("hey",postsArr1.get(i));
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("place", "That didn't work!");
//            }
//        });
//
//    queue.add(stringRequest);
//    Log.d("size",postsArr.size()+"");
//// Access the RequestQueue through your singleton class.
//
//
//    }
}

class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[] { "Home", "Support", "Community", "News" };
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {


        switch (position){
            case 0: return PageFragment.newInstance(position + 1);
            case 1: return new SupportFragment();
               //return PageFragment.newInstance(position + 1);

            case 2:
                //communityFragment cf=new communityFragment();
                //Bundle bundle=new Bundle();
                //bundle.putSerializable("postArr",postsArr);

                return new communityFragment();
            case 3: return new MainFragment();
        }
        return PageFragment.newInstance(position + 1);

         }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }


}


