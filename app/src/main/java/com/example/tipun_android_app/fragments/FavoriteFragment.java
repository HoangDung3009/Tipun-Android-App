package com.example.tipun_android_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.adapter.RoomAdapter;
import com.example.tipun_android_app.models.Room;
import com.example.tipun_android_app.models.User;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {
    private View mView;
    ListView lvRoom;
    List<Room> listRoom = new ArrayList<>();
    RoomAdapter roomAdapter;
    User current_user;


    public FavoriteFragment() {
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
        mView = inflater.inflate(R.layout.fragment_favorite, container, false);
        init();
        return mView;
    }

    private void init() {
        current_user = (User) getArguments().get("CURRENT_USER");
        listRoom = current_user.getFavoriteRooms();
        lvRoom = mView.findViewById(R.id.favorite_listRoom);
        roomAdapter = new RoomAdapter(getActivity(), listRoom);
        lvRoom.setAdapter(roomAdapter);

        lvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                long id = roomAdapter.getItemId(position);
                User user = (User) getArguments().get("CURRENT_USER");
                DetailRoomFragment roomDetail = new DetailRoomFragment();
                Bundle b1 = new Bundle();
                b1.putSerializable("CURRENT_USER", user);
                b1.putSerializable("ROOM_DETAIL", id);
                roomDetail.setArguments(b1);
                replaceFragment(roomDetail);
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