package com.example.entrymanagement.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.entrymanagement.utils.CommonProgressDialog;
import com.example.entrymanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class AnonymousLogin extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymous_login);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = CommonProgressDialog.LoadingSpinner(AnonymousLogin.this);
        Log.d("test","pass1");

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        Thread background = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
        FirebaseUser currentUser = mAuth.getCurrentUser();
                    Log.d("test","pass2");
        if(currentUser != null){
            Log.d("test","pass3");
            Intent intent = new Intent(AnonymousLogin.this, MainActivity.class);
            intent.putExtra("ID",mAuth.getCurrentUser().getUid());
            startActivity(intent);
            finish();
        }else{
            signIn();
            Log.d("test","pass4");
        }} catch (Exception e) {
                    e.printStackTrace();
                    Log.d("test","pass5");
                }
            }
        });
        background.start();

    }
    public void signIn(){
       // progressDialog.show();
        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //progressDialog.dismiss();

                            Intent intent = new Intent(AnonymousLogin.this,MainActivity.class);
                            intent.putExtra("ID", Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getUser()).getUid());
                            startActivity(intent);
                            finish();
                        }
                    }
                });
    }
}
