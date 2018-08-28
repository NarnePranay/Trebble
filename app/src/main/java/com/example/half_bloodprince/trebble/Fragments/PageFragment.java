package com.example.half_bloodprince.trebble.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.half_bloodprince.trebble.Activities.FeedBackActivity;
import com.example.half_bloodprince.trebble.R;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    private Boolean isFabOpen=false;
    private FloatingActionButton fab,fab1,fab2;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;
    private FloatingActionButton fabcall;
    private FloatingActionButton fabchat;
    private FloatingActionButton fabfeedback;
    private FloatingActionMenu fam;


    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test, container, false);


        fabcall = (FloatingActionButton) view.findViewById(R.id.scall);
        fabchat = (FloatingActionButton) view.findViewById(R.id.schat);
        fabfeedback = (FloatingActionButton) view.findViewById(R.id.sfeedback);
        fam = (FloatingActionMenu) view.findViewById(R.id.fab_menu);

        //handling menu status (open or close)
        fam.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                  //  showToast("Menu is opened");
                } else {
                   // showToast("Menu is closed");
                }
            }
        });

        //handling each floating action button clicked
        fabchat.setOnClickListener(onButtonClick());
        fabfeedback.setOnClickListener(onButtonClick());
        fabcall.setOnClickListener(onButtonClick());

        fam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fam.isOpened()) {
                    fam.close(true);
                }
            }
        });

        //TextView textView = (TextView) textView.findViewById(R.id.txt_test);
        //textView.setText("Fragment #" + mPage);
        return view;
    }


    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == fabcall) {
                    showToast("Button call clicked");
                } else if (view == fabchat) {
                    showToast("Button chat clicked");
                } else {
                    startActivity(new Intent(getContext(), FeedBackActivity.class));

                }
                fam.close(true);
            }
        };
    }

    private void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }




}

