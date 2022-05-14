package com.example.tipun_android_app.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.adapter.RoomAdapter;
import com.example.tipun_android_app.api.RoomService;
import com.example.tipun_android_app.dto.SearchModel;
import com.example.tipun_android_app.models.Room;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {

    private View mView;
    Spinner spinnerLocation;
    EditText et_search;
    List<Room> listRoom = new ArrayList<>();
    RoomAdapter roomAdapter;
    ListView lvRoom;
    Button searchBtn;

    public SearchFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search, container, false);
        init();
        return mView;
    }

    private void init() {
        et_search = mView.findViewById(R.id.search_searchView);
        searchBtn = mView.findViewById(R.id.search_searchBtn);
        String[] location = {"Hà Đông","Hoàng Mai","Thanh Xuân","Đống Đa", "Cầu Giấy", "Nam Từ Liêm","Hai Bà Trưng"};
        spinnerLocation = mView.findViewById(R.id.search_location_spinner);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_spinner_item, location);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerLocation.setAdapter(adapter);

        loadList();
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchModel searchModel = new SearchModel(et_search.getText().toString(), spinnerLocation.getSelectedItem().toString());
                RoomService.roomService.searchRoom(searchModel).enqueue(new Callback<List<Room>>() {
                    @Override
                    public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                        List<Room> rooms = response.body();
                        if(!rooms.isEmpty()){
                            roomAdapter.getData().clear();
                            roomAdapter.getData().addAll(rooms);
                            roomAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Room>> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void loadList(){
        roomAdapter = new RoomAdapter(getActivity(), listRoom);
        lvRoom = mView.findViewById(R.id.lv_roomlist);
        lvRoom.setAdapter(roomAdapter);

        lvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                listRoom.clear();
                Room room = (Room) roomAdapter.getItem(position);
                RoomDetails_fragment roomDetail = new RoomDetails_fragment();
                Bundle b1 = new Bundle();
                b1.putSerializable("ROOM_DETAIL", room);
                roomDetail.setArguments(b1);
                replaceFragment(roomDetail);
            }
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }


}
