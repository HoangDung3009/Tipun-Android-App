package com.example.tipun_android_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tipun_android_app.api.UserService;
import com.example.tipun_android_app.dto.LoginModel;
import com.example.tipun_android_app.models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    Button btnLogin;
    EditText username, password;
    ImageView facebook, google;

    private ProgressDialog progressDialog;
    private User user;


    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.login_loginBtn);
        username = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        facebook = findViewById(R.id.login_facebookLogin);
        google = findViewById(R.id.login_googleLogin);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in ...");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_client_id))
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);

        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });




        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facebookLogin();
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleLogin();
            }
        });
    }

    private void facebookLogin() {

    }

    private void googleLogin() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 10000);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 10000) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        progressDialog.show();
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String googleAccessToken = account.getIdToken();
            System.out.println(googleAccessToken);
            // Signed in successfully, show authenticated UI.
            UserService.userService.loginWithGoogle(googleAccessToken).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    user = response.body();
                    if (user != null){
                        progressDialog.dismiss();
                        moveToMainActivity();
                    }
                    else {
                        Toast.makeText(Login.this, "Unauthorized", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, "An error occurred !!", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (ApiException e) {
            Log.w("", "signInResult:failed code=" + e.getStatusCode());
        }
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
                    progressDialog.dismiss();
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

    public void notification(){
        Toast.makeText(this, "Tính năng này chưa phát triển !!", Toast.LENGTH_SHORT).show();
    }

}