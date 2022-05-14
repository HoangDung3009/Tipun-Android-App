package com.example.tipun_android_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.models.Room;
import com.example.tipun_android_app.room_details.SliderAdapter;
import com.example.tipun_android_app.room_details.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class RoomDetails_fragment extends Fragment {

    private View mView;
    private ViewPager2 viewPager2;
    private Room roomDetail;

    TextView title, price, address, phone_contact, acreage, description, water_price, electric_price;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_room_details_fragment, container, false);
        init();
        return mView;
    }

    private void init() {
        roomDetail = (Room) getArguments().get("ROOM_DETAIL");
        viewPager2 = mView.findViewById(R.id.viewPagerImageSlider);
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.phongkhach1));
        sliderItems.add(new SliderItem(R.drawable.phongngu1));
        sliderItems.add(new SliderItem(R.drawable.phongbep1));
        sliderItems.add(new SliderItem(R.drawable.phongvs1));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));

        title = mView.findViewById(R.id.detail_title);
        price = mView.findViewById(R.id.detail_price);
        address = mView.findViewById(R.id.detail_address);
        phone_contact = mView.findViewById(R.id.detail_phone);
        acreage = mView.findViewById(R.id.detail_acreage);
        description = mView.findViewById(R.id.detail_description);

        title.setText(roomDetail.getTitle());
        String address1 = roomDetail.getBuilding() + " " + roomDetail.getDistrict() + " " + roomDetail.getCity();
        address.setText(address1);
        phone_contact.setText(roomDetail.getPhone_contact());
        price.setText(roomDetail.getPrice()+ " vnđ");
        acreage.setText(roomDetail.getAcreage()+"m2");
        description.setText(roomDetail.getDescription());
    }
}