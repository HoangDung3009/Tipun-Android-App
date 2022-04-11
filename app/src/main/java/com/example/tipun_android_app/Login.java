package com.example.tipun_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.login_loginBtn);
    }

    public void navigateToRegister(View view){
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }

    public void toMenu(View view){
        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
    }
}