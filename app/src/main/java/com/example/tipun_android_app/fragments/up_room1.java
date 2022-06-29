package com.example.tipun_android_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.models.Room;
import com.example.tipun_android_app.models.User;


public class up_room1 extends Fragment {

    private User current_user;
    private Room room;
    private View mView;

    RadioGroup roomType,genderAllow;
    EditText capacity, acreage, price, water_price, elec_price;
    Button btn1;
    CheckBox freeWater, freeElec;

    public up_room1() {
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
        mView = inflater.inflate(R.layout.fragment_up_room1, container, false);
        current_user = (User) getArguments().get("CURRENT_USER");
        init();
        return mView;
    }

    private void init() {
        roomType = mView.findViewById(R.id.postroom_roomType);
        capacity = mView.findViewById(R.id.postroom_capacity);
        genderAllow = mView.findViewById(R.id.postroom_genderAllow);
        acreage = mView.findViewById(R.id.postroom_acreage);
        price = mView.findViewById(R.id.postroom_price);
        water_price = mView.findViewById(R.id.postroom_waterPrice);
        elec_price = mView.findViewById(R.id.postroom_electricPrice);
        freeElec = mView.findViewById(R.id.postroom_freeElectric);
        freeWater = mView.findViewById(R.id.postroom_freeWater);
        btn1 = mView.findViewById(R.id.postroom_btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToUpRoom2();
            }
        });
    }

    private void moveToUpRoom2() {
            room = new Room();
            room.setPostUser(current_user);
            int roomTypeID = roomType.getCheckedRadioButtonId();
            View rd_roomType = roomType.findViewById(roomTypeID);
            int idx = roomType.indexOfChild(rd_roomType);
            RadioButton rb1 = (RadioButton) roomType.getChildAt(idx);
            room.setType(rb1.getText().toString());

            int genderID = genderAllow.getCheckedRadioButtonId();
            View rd_genderAllow = genderAllow.findViewById(genderID);
            int idx1 = genderAllow.indexOfChild(rd_genderAllow);
            RadioButton rb2 = (RadioButton) genderAllow.getChildAt(idx1);
            room.setGender_allow(rb2.getText().toString());


            room.setCapacity(Integer.parseInt(capacity.getText().toString()));
            room.setAcreage(Double.parseDouble(acreage.getText().toString()));
            room.setPrice(Double.parseDouble(price.getText().toString()));
            room.setElectric_price(Double.parseDouble(elec_price.getText().toString()));
            room.setWater_price(Double.parseDouble(water_price.getText().toString()));

            up_room2 upRoom2 = new up_room2();
            Bundle b1 = new Bundle();
            b1.putSerializable("POST_ROOM", room);
            upRoom2.setArguments(b1);
            replaceFragment(upRoom2);
    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}