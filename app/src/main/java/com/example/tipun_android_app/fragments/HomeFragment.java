package com.example.tipun_android_app.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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

public class HomeFragment extends Fragment {
    private View mView;
    private User current_user;
    ListView lvRoom;
    private List<Room> listRoom = new ArrayList<>();
    RoomAdapter roomAdapter;

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
        RoomService.roomService.getAllRooms(current_user.getId()).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                listRoom = response.body();
                if (!listRoom.isEmpty()) {
                    loadList();
                }
            }

            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
            }
        });
    }

    private void loadList() {
        lvRoom = mView.findViewById(R.id.home_listRoom);
        roomAdapter = new RoomAdapter(getActivity(), listRoom);
        lvRoom.setAdapter(roomAdapter);

        lvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                long id = roomAdapter.getItemId(position);
                DetailRoomFragment roomDetail = new DetailRoomFragment();
                Bundle b1 = new Bundle();
                b1.putSerializable("CURRENT_USER", current_user);
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

