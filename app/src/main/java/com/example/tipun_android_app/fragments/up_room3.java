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
import android.widget.Toast;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.models.Room;

public class up_room3 extends Fragment {

    private View mView;
    private Room room;
    CheckBox wifi,pet,ac,fridge,toilet,closet,parking,freetime;
    Button btn3;

    public up_room3() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_up_room3, container, false);
        init();
        return mView;
    }

    private void init() {
        room = (Room) getArguments().get("POST_ROOM");

        wifi = mView.findViewById(R.id.postroom_wifi);
        pet = mView.findViewById(R.id.postroom_pet);
        ac = mView.findViewById(R.id.postroom_ac);
        fridge = mView.findViewById(R.id.postroom_fridge);
        freetime = mView.findViewById(R.id.postroom_freetime);
        toilet = mView.findViewById(R.id.postroom_toiler);
        closet = mView.findViewById(R.id.postroom_closet);
        parking = mView.findViewById(com.google.android.material.R.id.parent);

        if (wifi.isChecked()){
            Toast.makeText(getActivity(), wifi.getId()+"", Toast.LENGTH_SHORT).show();
        }
    }

    private void moveToUpRoomConfirm() {



        up_room_confirmation upRoomConfirm = new up_room_confirmation();
        Bundle b1 = new Bundle();
        b1.putSerializable("POST_ROOM", room);
        upRoomConfirm.setArguments(b1);
        replaceFragment(upRoomConfirm);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}