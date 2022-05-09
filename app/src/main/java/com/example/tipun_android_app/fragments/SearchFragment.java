package com.example.tipun_android_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.adapter.RoomAdapter;
import com.example.tipun_android_app.models.Room;

import java.util.List;


public class SearchFragment extends Fragment {

    private RecyclerView rcv_room;
    private RoomAdapter roomAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_search, container, false);

        rcv_room = mView.findViewById(R.id.rcv_roomlist);
        roomAdapter = new RoomAdapter(getContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rcv_room.setLayoutManager(gridLayoutManager);

//        roomAdapter.setData(getListRoom());


        return mView;
    }

}