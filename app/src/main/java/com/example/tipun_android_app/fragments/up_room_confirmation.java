package com.example.tipun_android_app.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tipun_android_app.MainActivity;
import com.example.tipun_android_app.R;
import com.example.tipun_android_app.api.RoomService;
import com.example.tipun_android_app.models.Room;
import com.example.tipun_android_app.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class up_room_confirmation extends Fragment {
    private View mView;
    private Room room;
    EditText title, phone_contact, description;
    Button btnConfirm;
    private ProgressDialog progressDialog;
    private User current_user = new User();
    public up_room_confirmation() {
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
        mView = inflater.inflate(R.layout.fragment_up_room_confirmation, container, false);
        init();
        return mView;

    }

    private void init() {
        room = (Room) getArguments().get("POST_ROOM");
        current_user = room.getPostUser();
        title = mView.findViewById(R.id.postroom_title);
        phone_contact = mView.findViewById(R.id.postroom_phone);
        description = mView.findViewById(R.id.postroom_description);
        btnConfirm = mView.findViewById(R.id.postroom_btnConfirm);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Processing ...");

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                room.setTitle(title.getText().toString());
                room.setPhone_contact(phone_contact.getText().toString());
                room.setDescription(description.getText().toString());
                room.setStatus(false);
                Long id = current_user.getId();

                RoomService.roomService.addRoom(room, id).enqueue(new Callback<Room>() {
                    @Override
                    public void onResponse(Call<Room> call, Response<Room> response) {
                        progressDialog.dismiss();
                        Room room1 = response.body();
                        if(room1 != null){
                            Toast.makeText(getActivity(), "Room Added !!!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getActivity(), MainActivity.class);
                            Bundle b = new Bundle();
                            b.putSerializable("CURRENT_USER", current_user);
                            i.putExtras(b);
                            startActivity(i);
                        }
                    }

                    @Override
                    public void onFailure(Call<Room> call, Throwable t) {
                        Toast.makeText(getActivity(), "Error !!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}