package com.example.entrymanagement.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.entrymanagement.adapter.CheckOutAdapter;
import com.example.entrymanagement.utils.CommonProgressDialog;
import com.example.entrymanagement.R;
import com.example.entrymanagement.utils.SendMail;
import com.example.entrymanagement.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity implements CheckOutAdapter.OnClickListener {

    private TextView noChekedIn;
    private RecyclerView recyclerView;
    private CheckOutAdapter adapter;
    private DatabaseReference myRef;
    private Dialog progressDialog;

    private ArrayList<User> checkedInUser;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intent = new Intent(CheckOutActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.navigation_dashboard:
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        progressDialog = CommonProgressDialog.LoadingSpinner(CheckOutActivity.this);
        checkedInUser = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("user");


        recyclerView = findViewById(R.id.recycler);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CheckOutActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        noChekedIn = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.navigation_dashboard);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getUsers();
    }

    public void getUsers(){
        progressDialog.show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                checkedInUser.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    if(user!= null && user.isCheckedIn()){
                        checkedInUser.add(user);
                    }
                }
                if(checkedInUser.size() > 0){
                    adapter = new CheckOutAdapter(CheckOutActivity.this,checkedInUser,CheckOutActivity.this);
                    recyclerView.setAdapter(adapter);
                }else {
                    noChekedIn.setVisibility(View.VISIBLE);
                }
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    }

    @Override
    public void onClick(User user) {
        user.checkOut();
        myRef.child(user.getuId()).setValue(user);
        SendMail sendMail = new SendMail(CheckOutActivity.this,user.getVisitorEmail(),"Your Meeting Details",createMsg(user));
        sendMail.execute();
    }
    private String createMsg(User entree){
        String s = "Check out Successful"
                + "\nName: " + entree.getVisitorName()
                + "\nPhone: " + entree.getVisitorPhone()
                + "\nHost Name: " + entree.getHostName()
                + "\nHost Email: " + entree.getHostEmail()
                + "\nCheck-in time: " + entree.getCheckInTime(1)
                + "\nCheck-out time: " + entree.getCheckOutTime(1);
        return s;
    }
}
