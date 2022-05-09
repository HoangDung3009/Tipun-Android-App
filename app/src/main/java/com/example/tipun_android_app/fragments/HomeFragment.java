package com.example.tipun_android_app.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.models.User;
public class HomeFragment extends Fragment {
    private View mView;
    private User current_user;
    TextView t;
    public HomeFragment() {
        // Required empty public constructor

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return mView;
    }

    private void init() {
        current_user = (User) getArguments().get("CURRENT_USER");
        t = mView.findViewById(R.id.lbHomeFragment);
        t.setText(current_user.getFullname());
    }

}