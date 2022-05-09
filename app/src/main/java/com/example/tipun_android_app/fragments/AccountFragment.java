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

import java.text.SimpleDateFormat;


public class AccountFragment extends Fragment {

    private User current_user;
    private View mView;
    TextView fullname,gender,dob,phone,email;
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    public AccountFragment() {
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
        mView = inflater.inflate(R.layout.fragment_account, container, false);
        init();
        return mView;
    }

    private void init() {
        current_user = (User) getArguments().get("CURRENT_USER");
        fullname = mView.findViewById(R.id.account_fullname);
        gender = mView.findViewById(R.id.account_gender);
        dob = mView.findViewById(R.id.account_dob);
        email = mView.findViewById(R.id.account_email);
        phone = mView.findViewById(R.id.account_phone);

        fullname.setText(current_user.getFullname());
        gender.setText(current_user.getGender());
        dob.setText(df.format(current_user.getDob()));
        phone.setText(current_user.getPhone());
        email.setText(current_user.getEmail());

    }


}