package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    EditText username;
    EditText email;
    EditText password;
    EditText conformPassword;
    Button register;
    Button loginPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        loginPage.setOnClickListener(view -> backToLoginPage());
        register.setOnClickListener(view -> register());

    }

    private void initView(){
        register = findViewById(R.id.bt_register);
        loginPage = findViewById(R.id.bt_login);
        username = findViewById(R.id.txt_username);
        email = findViewById(R.id.txt_email);
        password = findViewById(R.id.txt_password);
        conformPassword = findViewById(R.id.txt_password_2);
    }

    private void backToLoginPage(){
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
    }

    private void register(){
        Pair<String, Boolean> usernameValidation = Validation.usernameValidation(username.getText().toString());
        Pair<String, Boolean> passwordValidation = Validation.conformPassword(password.getText().toString(),
                conformPassword.getText().toString());
        Pair<String, Boolean> emailValidation = Validation.emailValidation(email.getText().toString());
        boolean t = usernameValidation.second && passwordValidation.second && emailValidation.second;
        if(!usernameValidation.second){
            username.setError(usernameValidation.first);
        }
        if(!emailValidation.second){
            email.setError(emailValidation.first);
        }
        if(!passwordValidation.second){
            password.setError(passwordValidation.first);
            conformPassword.setError(passwordValidation.first);
        }
        if(t){
            Toast.makeText(getApplicationContext(), "Done, Now Please Login", Toast.LENGTH_LONG).show();
            Validation.addUser(username.getText().toString(), password.getText().toString(),
                    email.getText().toString());
            backToLoginPage();
        }
    }




}