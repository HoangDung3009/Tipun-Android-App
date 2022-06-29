package com.example.tipun_android_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.adapter.RoomAdapter;
import com.example.tipun_android_app.api.RoomService;
import com.example.tipun_android_app.models.Room;
import com.example.tipun_android_app.models.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostedRoomFragment extends Fragment {
    private View mView;
    private List<Room> postedRooms = new ArrayList<>();
    private RoomAdapter roomAdapter;
    private User current_user;
    ListView lv;

    public PostedRoomFragment() {
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
        mView = inflater.inflate(R.layout.fragment_posted_room, container, false);
        init();
        return mView;
    }

    private void init() {
        current_user = (User) getArguments().get("CURRENT_USER");
        lv = mView.findViewById(R.id.postedroom_listRoom);

        RoomService.roomService.getRoomByUser(current_user.getId()).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                postedRooms = response.body();
                if (!postedRooms.isEmpty()){
                    roomAdapter = new RoomAdapter(getActivity(), postedRooms);
                    lv.setAdapter(roomAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}