package com.example.tipun_android_app.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.tipun_android_app.Login;
import com.example.tipun_android_app.R;
import com.example.tipun_android_app.api.UserService;
import com.example.tipun_android_app.models.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountFragment extends Fragment {

    private User current_user;
    private View mView;
    TextView fullname, posted_room;
    EditText gender,dob,phone,email;
    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    ImageButton btnEdit;
    Button  btnSave, btnLogout;
    private ProgressDialog progressDialog;
    AlertDialog alertDialog;

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
        btnEdit = mView.findViewById(R.id.account_btnEdit);
        btnSave = mView.findViewById(R.id.account_btnSave);
        btnLogout = mView.findViewById(R.id.account_btnLogout);
        posted_room = mView.findViewById(R.id.account_room);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Logging out ...");

        fullname.setText(current_user.getFullname());
        gender.setText(current_user.getGender());
        dob.setText(df.format(current_user.getDob()));
        phone.setText(current_user.getPhone());
        email.setText(current_user.getEmail());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gender.setEnabled(true);
                dob.setEnabled(true);
                email.setEnabled(true);
                phone.setEnabled(true);
                btnSave.setEnabled(true);
                btnLogout.setEnabled(false);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    current_user.setGender(gender.getText().toString());
                    current_user.setEmail(email.getText().toString());
                    current_user.setDob(df.parse(dob.getText().toString()));
                    current_user.setPhone(phone.getText().toString());

                    UserService.userService.updateUser(current_user).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();
                            if (user != null){
                                alertDialog = new AlertDialog.Builder(getActivity()).setMessage("User updated !!")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                alertDialog.dismiss();
                                            }
                                        }).create();
                                alertDialog.show();
                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                        }
                    });

                    gender.setEnabled(false);
                    dob.setEnabled(false);
                    email.setEnabled(false);
                    phone.setEnabled(false);
                    btnLogout.setEnabled(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Login.class);
                startActivity(i);
            }
        });

        posted_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PostedRoomFragment postedRoomFragment = new PostedRoomFragment();
                Bundle b1 = new Bundle();
                b1.putSerializable("CURRENT_USER", current_user);
                postedRoomFragment.setArguments(b1);
                replaceFragment(postedRoomFragment);
            }
        });
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}