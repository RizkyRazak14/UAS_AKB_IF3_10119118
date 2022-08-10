package com.rizkyrazak.uas_akb_if3_10119118;

//NIM :10119118
//Nama : Rizky Septiana Abdul Razak
//Kelas : IF - 3

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnSignIn, btnSignUp;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = inputEmail.getText().toString();
                String Password = inputPassword.getText().toString();

                if(TextUtils.isEmpty(inputEmail.getText().toString())){
                    inputEmail.setError("Email Harus Diisi!");
                }else if(TextUtils.isEmpty(inputPassword.getText().toString())){
                    inputPassword.setError("Password Harus Diisi!");
                }else if(Password.length() < 6){
                    inputPassword.setError("Password Minimal 6 Karakter!");
                }else{
                    auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignInActivity.this, "Sign in berhasil!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                            }else{
                                Toast.makeText(SignInActivity.this, "Sign in gagal", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }
}