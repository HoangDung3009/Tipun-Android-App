package com.example.tipun_android_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.tipun_android_app.fragments.AccountFragment;
import com.example.tipun_android_app.fragments.HomeFragment;
import com.example.tipun_android_app.fragments.SearchFragment;
import com.example.tipun_android_app.databinding.ActivityMainBinding;
import com.example.tipun_android_app.fragments.*;
import com.example.tipun_android_app.models.User;

public class MainActivity extends AppCompatActivity {
    long backPressedTime = 0;
    ActivityMainBinding binding;
    Button button2;

    private User current_user;

    public User getCurrent_user() {
        return current_user;
    }

    public void setCurrent_user(User current_user) {
        this.current_user = current_user;
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            super.onBackPressed();
            moveTaskToBack(true);
        } else {
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getIntent().getExtras() != null){
            current_user = (User) getIntent().getExtras().get("CURRENT_USER");
        }

        HomeFragment homeFragment = new HomeFragment();
        Bundle b = new Bundle();
        b.putSerializable("CURRENT_USER", current_user);
        homeFragment.setArguments(b);
        replaceFragment(homeFragment);
        binding.bottomNavBar.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.miHome:
                    HomeFragment homeFragment1 = new HomeFragment();
                    Bundle b1 = new Bundle();
                    b1.putSerializable("CURRENT_USER", current_user);
                    homeFragment1.setArguments(b1);
                    replaceFragment(homeFragment1);
                    break;
                case R.id.miSearch:
                    SearchFragment searchFragment = new SearchFragment();
                    Bundle b5 = new Bundle();
                    b5.putSerializable("CURRENT_USER", current_user);
                    searchFragment.setArguments(b5);
                    replaceFragment(searchFragment);
                    break;

                case R.id.miFavorite:
                    FavoriteFragment favoriteFragment = new FavoriteFragment();
                    Bundle b6 = new Bundle();
                    b6.putSerializable("CURRENT_USER", current_user);
                    favoriteFragment.setArguments(b6);
                    replaceFragment(favoriteFragment);
                    break;


                case R.id.miAccount:
                    AccountFragment accountFragment = new AccountFragment();
                    Bundle b2 = new Bundle();
                    b2.putSerializable("CURRENT_USER", current_user);
                    accountFragment.setArguments(b2);
                    replaceFragment(accountFragment);
                    break;

                case R.id.miPostRoom:
                    up_room1 upRoom1 = new up_room1();
                    Bundle b3 = new Bundle();
                    b3.putSerializable("CURRENT_USER", current_user);
                    upRoom1.setArguments(b3);
                    replaceFragment(upRoom1);
                    break;
            }

            return true;
        });

//        button2 = findViewById(R.id.button2);
//        button2.setOnClickListener(this);
//
//        setContentView(R.layout.activity_main);
//
//        viewPager2 = findViewById(R.id.viewPagerImageSlider);
//        List<SliderItem> sliderItems = new ArrayList<>();
//        sliderItems.add(new SliderItem(R.drawable.phongkhach1));
//        sliderItems.add(new SliderItem(R.drawable.phongngu1));
//        sliderItems.add(new SliderItem(R.drawable.phongbep1));
//        sliderItems.add(new SliderItem(R.drawable.phongvs1));
//
//        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

//    private static final String LOG_TAG = MainActivity.class.getSimpleName();
//
//    @SuppressLint("RestrictedApi")
//    public void launchSecondActivity(View view) {
//        Log.d(LOG_TAG, "Đã chọn Phòng 1!");
//    }
//
//
//
//    @Override
//    public void onClick(View view) {
//        if(view.getId()==R.id.button2){
//            getSupportFragmentManager().beginTransaction().replace(R.id.roomdetails, new RoomDetails_fragment()).commit();
//            button2.setVisibility(View.GONE);
//        }
//    }
}