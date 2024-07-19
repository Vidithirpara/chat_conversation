package com.example.whatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();

        progressDialog = new ProgressDialog(signInActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your Account");




        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(binding.etEmail.getText().toString().isEmpty()){
                    binding.etEmail.setError("Email is empty");
                    return;
                }
                if(binding.etPassword.getText().toString().isEmpty()){
                    binding.etPassword.setError("Password is empty");
                    return;
                }
                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.etEmail.getText().toString(), binding.etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                    if(task.isSuccessful()){
                        Intent intent = new Intent(signInActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                       Toast.makeText(signInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    }
                });
            }
        });
        binding.tvClickSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        if(auth.getCurrentUser() != null){
            Intent intent = new Intent(signInActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}

