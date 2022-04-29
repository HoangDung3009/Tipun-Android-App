package com.example.tipun_android_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tipun_android_app.fragments.AccountFragment;
import com.example.tipun_android_app.fragments.FavoriteFragment;
import com.example.tipun_android_app.fragments.HomeFragment;
import com.example.tipun_android_app.fragments.SearchFragment;
import com.example.tipun_android_app.databinding.ActivityMainBinding;
import com.example.tipun_android_app.fragments.up_room1;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
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
                    replaceFragment(new up_room1());
                    break;

            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }
}