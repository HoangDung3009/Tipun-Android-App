package com.example.tipun_android_app;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.tipun_android_app.bottom_nav.AccountFragment;
import com.example.tipun_android_app.bottom_nav.FavoriteFragment;
import com.example.tipun_android_app.bottom_nav.HomeFragment;
import com.example.tipun_android_app.bottom_nav.SearchFragment;
import com.example.tipun_android_app.databinding.ActivityMainBinding;
import com.example.tipun_android_app.postRoom.up_room_confirmation;
import com.example.tipun_android_app.room_details.RoomDetails_fragment;
import com.example.tipun_android_app.room_details.SliderAdapter;
import com.example.tipun_android_app.room_details.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    ActivityMainBinding binding;
    Button button2;

    private ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavBar.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.miHome:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.miSearch:
                    replaceFragment(new SearchFragment());
                    break;

                case R.id.miFavorite:
                    replaceFragment(new FavoriteFragment());
                    break;

                case R.id.miAccount:
                    replaceFragment(new AccountFragment());
                    break;

                case R.id.miPostRoom:
                    replaceFragment(new up_room_confirmation());
                    break;

            }

            return true;
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);

        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewPagerImageSlider);
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.phongkhach1));
        sliderItems.add(new SliderItem(R.drawable.phongngu1));
        sliderItems.add(new SliderItem(R.drawable.phongbep1));
        sliderItems.add(new SliderItem(R.drawable.phongvs1));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @SuppressLint("RestrictedApi")
    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Đã chọn Phòng 1!");
    }



    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button2){
            getSupportFragmentManager().beginTransaction().replace(R.id.roomdetails, new RoomDetails_fragment()).commit();
            button2.setVisibility(View.GONE);
        }
    }
}