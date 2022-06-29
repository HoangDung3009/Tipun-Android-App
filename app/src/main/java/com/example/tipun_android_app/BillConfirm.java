package com.example.tipun_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tipun_android_app.api.RoomService;
import com.example.tipun_android_app.models.Bill;
import com.example.tipun_android_app.models.Payment;
import com.example.tipun_android_app.models.Room;
import com.example.tipun_android_app.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillConfirm extends AppCompatActivity {
    TextView username, email, phone, roomid, acreage, capacity, address;
    private User current_user;
    private Room room;
    Button btnConfirm;
    Spinner spnPayment;
    Bill bill;
    private ProgressDialog progressDialog;
    private List<Payment> listPayment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_confirm);

        if(getIntent().getExtras() != null){
            current_user = (User) getIntent().getExtras().get("CURRENT_USER");
            room = (Room) getIntent().getExtras().get("ROOM");
        }

        username = findViewById(R.id.bill_username);
        email = findViewById(R.id.bill_useremail);
        phone = findViewById(R.id.bill_phone);
        roomid = findViewById(R.id.bill_roomid);
        acreage = findViewById(R.id.bill_acreage);
        capacity = findViewById(R.id.bill_capacity);
        address = findViewById(R.id.bill_address);
        btnConfirm = findViewById(R.id.bill_btnConfirm);
        spnPayment = findViewById(R.id.bill_payment);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing ...");

        username.setText(current_user.getUsername());
        email.setText(current_user.getEmail());
        phone.setText(current_user.getPhone());
        roomid.setText(room.getId()+"");
        acreage.setText(room.getAcreage()+ " m2");
        capacity.setText(room.getCapacity() + " người");
        String s = room.getBuilding()+ ", " + room.getDistrict() + ", " + room.getCity();
        address.setText(s);
        RoomService.roomService.getAllPayments().enqueue(new Callback<List<Payment>>() {
            @Override
            public void onResponse(Call<List<Payment>> call, Response<List<Payment>> response) {
                listPayment = response.body();
                if (!listPayment.isEmpty()){
                    ArrayAdapter<Payment> adapter = new ArrayAdapter<Payment>(BillConfirm.this, android.R.layout.simple_spinner_item, listPayment);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spnPayment.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Payment>> call, Throwable t) {
                Toast.makeText(BillConfirm.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                confirmBill();
            }
        });
    }

    private void confirmBill() {
        Payment payment = (Payment) spnPayment.getSelectedItem();

        RoomService.roomService.createBill(current_user.getId(), room.getId(), payment.getId()).enqueue(new Callback<Bill>() {
            @Override
            public void onResponse(Call<Bill> call, Response<Bill> response) {
                progressDialog.dismiss();
                bill = response.body();
                if (bill != null){
                    Toast.makeText(BillConfirm.this, "Bill created", Toast.LENGTH_SHORT).show();
                    moveToMainActivity();
                }
            }

            @Override
            public void onFailure(Call<Bill> call, Throwable t) {

            }
        });
    }

    private void moveToMainActivity() {
        Intent i = new Intent(BillConfirm.this, MainActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("CURRENT_USER", current_user);
        i.putExtras(b);
        startActivity(i);
    }
}