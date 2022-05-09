package com.example.tipun_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tipun_android_app.api.UserService;
import com.example.tipun_android_app.dto.LoginModel;
import com.example.tipun_android_app.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    Button btnLogin;
    EditText username, password;

    private ProgressDialog progressDialog;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login_loginBtn);
        username = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in ...");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    private void checkLogin() {
        progressDialog.show();
        LoginModel loginModel = new LoginModel(username.getText().toString(), password.getText().toString());
        UserService.userService.checkLogin(loginModel).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                progressDialog.dismiss();
                user = response.body();
                if(user != null){
                    moveToMainActivity();
                } else {
                    Toast.makeText(Login.this, "Wrong username or password !!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Login.this, "An error occurred !!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void moveToMainActivity() {
        Intent i = new Intent(Login.this, MainActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("CURRENT_USER", user);
        i.putExtras(b);
        startActivity(i);
    }

    public void navigateToRegister(View view){
        Intent i = new Intent(Login.this, Register.class);
        startActivity(i);
    }

}