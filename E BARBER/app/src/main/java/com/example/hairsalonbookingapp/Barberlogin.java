package com.example.hairsalonbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Barberlogin extends AppCompatActivity {
    TextView userlogin,email,password2;
    Button loginBtn2;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barberlogin);
        if (getSupportActionBar() != null)  //remove top actionbar
        {
            getSupportActionBar().hide();
        }
        email = findViewById(R.id.email);
        password2 = findViewById(R.id.password2);
        userlogin = findViewById(R.id.userlogin);
        loginBtn2 = findViewById(R.id.loginBtn2);

        loginBtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String username = email.getText().toString() ;
                String password = password2.getText().toString();
                String bname = "";


                if(username.equals("mraheez786@gmail.com") && password.equals("12345678")){
                    Toast.makeText(Barberlogin.this, "Logged in Successfully !!", Toast.LENGTH_SHORT).show();
                    bname = "mohammed raheez";
                    Bundle bundle = new Bundle();
                    bundle.putString("username",bname);
                    Intent intent = new Intent(Barberlogin.this,Barberhome.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
                else if(username.equals("shifana@gmail.com") && password.equals("12345678")){
                    Toast.makeText(Barberlogin.this, "Logged in Successfully !!", Toast.LENGTH_SHORT).show();
                    bname = "shifana";
                    Bundle bundle = new Bundle();
                    bundle.putString("username",bname);
                    Intent intent = new Intent(Barberlogin.this,Barberhome.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Barberlogin.this, "Login Failed ! ", Toast.LENGTH_SHORT).show();
                }



            }
        });


        userlogin.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view){
               startActivity(new Intent(Barberlogin.this,Login.class));
           }
        });

    }
}
