package com.example.tipun_android_app.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tipun_android_app.BillConfirm;
import com.example.tipun_android_app.Login;
import com.example.tipun_android_app.MainActivity;
import com.example.tipun_android_app.R;
import com.example.tipun_android_app.api.RoomService;
import com.example.tipun_android_app.api.UserService;
import com.example.tipun_android_app.models.Room;
import com.example.tipun_android_app.models.User;
import com.example.tipun_android_app.models.Utilities;
import com.example.tipun_android_app.room_details.SliderAdapter;
import com.example.tipun_android_app.room_details.SliderItem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailRoomFragment extends Fragment {

    private View mView;
    private ViewPager2 viewPager2;
    private Room roomDetail = new Room();
    private List<Room> list = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("###,###");
    private User current_user;

    TextView title, price, address, phone_contact, acreage, description, water_price, electric_price, capacity;
    Button btnBack, btnContact, btnRent;
    ImageView btnFavorite;
    LinearLayout linearLayout;
    AlertDialog alertDialog;

    public DetailRoomFragment() {
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
        mView = inflater.inflate(R.layout.fragment_detail_room, container, false);
        init();
        long id = (long) getArguments().get("ROOM_DETAIL");
        RoomService.roomService.getRoomById(id).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                roomDetail = response.body();
                if (roomDetail != null){
                    title.setText(roomDetail.getTitle());
                    String address1 = roomDetail.getBuilding() + ", " + roomDetail.getDistrict() + ", " + roomDetail.getCity();
                    address.setText(address1);
                    phone_contact.setText(roomDetail.getPhone_contact());
                    price.setText(df.format(roomDetail.getPrice())+ " vnđ/tháng");
                    water_price.setText("Tiền nước: " + df.format(roomDetail.getWater_price())+ " vnđ/tháng");
                    electric_price.setText("Tiền điện: " +df.format(roomDetail.getElectric_price())+ " vnđ/tháng");
                    electric_price.setText("Sức chứa: " +df.format(roomDetail.getCapacity())+ " người");
                    acreage.setText(roomDetail.getAcreage()+"m2");
                    description.setText(roomDetail.getDescription());
                    for (Utilities i : roomDetail.getRoomUtilities()){
                        TextView tx = new TextView(getActivity());
                        tx.setText(i.getName());
                        linearLayout.addView(tx);
                    }
                }
                else {
                    Toast.makeText(getActivity(), "Error no room found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFavorite.setImageResource(R.drawable.ic_baseline_red_24);
                list = current_user.getFavoriteRooms();
                for (Room r : list){
                    if (r.getId() == roomDetail.getId()){
                        list.remove(r);
                        list.add(roomDetail);
                    }
                    else {
                        list.add(roomDetail);
                    }
                }

                current_user.setFavoriteRooms(list);

                UserService.userService.favorite(current_user).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if(user != null){
                            Toast.makeText(getActivity(), "Add room to favorite !!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        });
        return mView;
    }

    private void init() {
//        roomDetail = (Room) getArguments().get("ROOM_DETAIL");
        viewPager2 = mView.findViewById(R.id.viewPagerImageSlider);
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.phongkhach1));
        sliderItems.add(new SliderItem(R.drawable.phongngu1));
        sliderItems.add(new SliderItem(R.drawable.phongbep1));
        sliderItems.add(new SliderItem(R.drawable.phongvs1));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        current_user = (User) getArguments().get("CURRENT_USER");
        title = mView.findViewById(R.id.detail_title);
        price = mView.findViewById(R.id.detail_price);
        address = mView.findViewById(R.id.detail_address);
        phone_contact = mView.findViewById(R.id.detail_phone);
        acreage = mView.findViewById(R.id.detail_acreage);
        description = mView.findViewById(R.id.detail_description);
        water_price = mView.findViewById(R.id.detail_water);
        electric_price = mView.findViewById(R.id.detail_electric);
        capacity = mView.findViewById(R.id.detail_capacity);
        btnBack = mView.findViewById(R.id.detail_backBtn);
        btnFavorite = mView.findViewById(R.id.detail_favoriteBtn);
        btnContact = mView.findViewById(R.id.detail_contactBtn);
        btnRent = mView.findViewById(R.id.detail_rent);
        linearLayout = mView.findViewById(R.id.detail_util);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                Bundle b6 = new Bundle();
                b6.putSerializable("CURRENT_USER", current_user);
                homeFragment.setArguments(b6);
                replaceFragment(homeFragment);
            }
        });


        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = "tel:" + phone_contact.getText().toString().trim();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse(phone));
                startActivity(i);
            }
        });



        btnRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (roomDetail.isStatus()){
                    alertDialog = new AlertDialog.Builder(getActivity()).setMessage("Room has been rented !!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    alertDialog.dismiss();
                                }
                            }).create();
                    alertDialog.show();
                }
                else {
                    moveToMainActivity();
                }

            }
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void moveToMainActivity() {
        Intent i = new Intent(getActivity(), BillConfirm.class);
        Bundle b = new Bundle();
        b.putSerializable("CURRENT_USER", current_user);
        b.putSerializable("ROOM", roomDetail);
        i.putExtras(b);
        startActivity(i);
    }
}