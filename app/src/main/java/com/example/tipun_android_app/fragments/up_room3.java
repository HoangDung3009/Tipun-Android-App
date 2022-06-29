package com.example.tipun_android_app.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.api.UtilService;
import com.example.tipun_android_app.models.Room;
import com.example.tipun_android_app.models.Utilities;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class up_room3 extends Fragment {

    private static final int REQUEST_CODE = 10;
    private View mView;
    private Room room;
    CheckBox wifi,pet,ac,fridge,toilet,closet,parking, freetime;
    Button btn3, btnUpload;
    ImageView img;
    List<Utilities> utilities = new ArrayList<>();

    private ActivityResultLauncher<Intent> mActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if (data == null){
                            return;
                        }
                        Uri uri = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                            img.setImageBitmap(bitmap);
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

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
        parking = mView.findViewById(R.id.postroom_parking);
        img = mView.findViewById(R.id.postroom_img);
        btnUpload = mView.findViewById(R.id.postroom_uploadBtn);
        btn3 = mView.findViewById(R.id.postroom_btn3);

        UtilService.utilService.getAllUtilities().enqueue(new Callback<List<Utilities>>() {
            @Override
            public void onResponse(Call<List<Utilities>> call, Response<List<Utilities>> response) {
                utilities = response.body();
            }

            @Override
            public void onFailure(Call<List<Utilities>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Utilities> list = new ArrayList<>();
                if (wifi.isChecked()){
                    list.add(utilities.get(0));
                }
                if (toilet.isChecked()){
                    list.add(utilities.get(1));
                }
                if (fridge.isChecked()){
                    list.add(utilities.get(2));
                }
                if (ac.isChecked()){
                    list.add(utilities.get(3));
                }
                if (pet.isChecked()){
                    list.add(utilities.get(4));
                }
                if (closet.isChecked()){
                    list.add(utilities.get(5));
                }
                if (parking.isChecked()){
                    list.add(utilities.get(6));
                }
                if (freetime.isChecked()){
                    list.add(utilities.get(7));
                }
                room.setRoomUtilities(list);

                moveToUpRoomConfirm();
            }
        });


        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
            }
        });

    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if (ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallery();
        } else {
            String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(getActivity(),permission, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }

    }


    private void openGallery() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResult.launch(Intent.createChooser(i, "Select image"));

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
