package com.example.cutguruapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText name_register;
    private EditText email_register;
    private EditText password_register;
    private Button exit_register;
    private FirebaseAuth mAuth;
    private DatabaseReference database;
    private FirebaseDatabase user_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();
        user_register=FirebaseDatabase.getInstance();
        database= user_register.getReference();


        name_register= findViewById(R.id.name_register);
        email_register= findViewById(R.id.email_register);
        password_register= findViewById(R.id.password_register);
        exit_register= findViewById(R.id. exit_register);

        exit_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name_register.getText().toString().isEmpty()|| email_register.getText().toString().isEmpty()|| password_register.getText().toString().isEmpty()) {
                    Toast.makeText(RegisterActivity.this,"Поля не можуть бути пoрожніми.", Toast.LENGTH_LONG).show();
                }else {
                    mAuth.createUserWithEmailAndPassword(email_register.getText().toString(),password_register.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        database.child("Users").child(mAuth.getCurrentUser().getUid()).child("name").setValue(name_register.getText().toString());
                                        database.child("Users").child(mAuth.getCurrentUser().getUid()).child("email").setValue(email_register.getText().toString());
                                        database.child("Users").child(mAuth.getCurrentUser().getUid()).child("password").setValue(password_register.getText().toString());

                                        Intent intent= new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(RegisterActivity.this,"Не правельно введений пароль або логін\n"
                                                +"Спробуйте ще раз",Toast.LENGTH_LONG);
                                    }

                                }
                            });

                }
            }
        });

    }
    }