package com.example.tipun_android_app.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tipun_android_app.R;
import com.example.tipun_android_app.room_details.SliderAdapter;
import com.example.tipun_android_app.room_details.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class RoomDetails_fragment extends Fragment {

    private View mView;
    private ViewPager2 viewPager2;


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
        viewPager2 = mView.findViewById(R.id.viewPagerImageSlider);
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.phongkhach1));
        sliderItems.add(new SliderItem(R.drawable.phongngu1));
        sliderItems.add(new SliderItem(R.drawable.phongbep1));
        sliderItems.add(new SliderItem(R.drawable.phongvs1));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
    }
}