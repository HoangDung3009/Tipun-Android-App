package com.example.tipun_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tipun_android_app.api.UserService;
import com.example.tipun_android_app.models.User;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    private EditText firstname, lastname, username,password1,password2,dob,email, phone;
    private Button btnRegister;
    CheckBox termCheckBox;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    private ProgressDialog progressDialog;
    private Spinner spinnerGender;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    //them cac gia tri cho gioi tinh
        String[] gender = {"Nam", "Nữ"};
        spinnerGender = findViewById(R.id.register_gender);
        ArrayAdapter<CharSequence> genderAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, gender);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(genderAdapter);
        init();
    }

    private void init(){
        firstname = findViewById(R.id.register_firstname);
        lastname = findViewById(R.id.register_lastname);
        username = findViewById(R.id.register_username);
        password1 = findViewById(R.id.register_password);
        password2 = findViewById(R.id.register_reenter_password);
        dob = findViewById(R.id.register_dob);
        email = findViewById(R.id.register_email);
        phone = findViewById(R.id.register_phone);
        btnRegister = findViewById(R.id.register_registerBtn);
        termCheckBox = findViewById(R.id.register_termCheckBox);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing up ...");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void register() {
        progressDialog.show();
        try {
            user = new User();
            String fullname = firstname.getText().toString() + " " + lastname.getText().toString();
            user.setFullname(fullname);
            Date date = df.parse(dob.getText().toString());
            user.setDob(date);
            user.setUsername(username.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPhone(phone.getText().toString());
            user.setGender(spinnerGender.getSelectedItem().toString());
            if(password1.getText().toString().equals(password2.getText().toString())){
                user.setPassword(password1.getText().toString());
            }

            if(password1.getText().toString().equals(password2.getText().toString()) && termCheckBox.isChecked()){
                UserService.userService.register(user).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        progressDialog.dismiss();
                        User user1 = response.body();
                        if(user1 != null){
                            moveToMainActivity(user1);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(Register.this, "An error occurred", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                progressDialog.dismiss();
                Toast.makeText(this, "Please check the term !!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }


    public void navigateToLogin(View view){
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
    }

    private void moveToMainActivity(User user) {
        Intent i = new Intent(Register.this, MainActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("CURRENT_USER", user);
        i.putExtras(b);
        startActivity(i);
    }
}