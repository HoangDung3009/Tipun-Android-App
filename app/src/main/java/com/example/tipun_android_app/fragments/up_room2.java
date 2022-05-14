package com.example.tipun_android_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.models.Room;


public class up_room2 extends Fragment {

    private View mView;
    EditText city, district, commune;
    EditText address;
    Button btn2;
    private Room room;

    public up_room2() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_up_room2, container, false);
        init();
        return mView;
    }

    private void init() {
        city = mView.findViewById(R.id.postroom_city);
        district = mView.findViewById(R.id.postroom_district);
        commune = mView.findViewById(R.id.postroom_commune);
        address = mView.findViewById(R.id.postroom_address);
        room = (Room) getArguments().get("POST_ROOM");
        btn2 = mView.findViewById(R.id.postroom_btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToUpRoom3();
            }
        });

    }

    private void moveToUpRoom3() {
        room.setCity(city.getText().toString());
        room.setProvince(city.getText().toString());
        room.setDistrict(district.getText().toString());
        room.setCommune(commune.getText().toString());
        room.setBuilding(address.getText().toString());
        room.setStreet_name(address.getText().toString());

        up_room3 upRoom3 = new up_room3();
        Bundle b1 = new Bundle();
        b1.putSerializable("POST_ROOM", room);
        upRoom3.setArguments(b1);
        replaceFragment(upRoom3);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}