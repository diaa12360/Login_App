package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button regester;
    Button login;
    EditText username;
    EditText password;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        regester.setOnClickListener(view -> goToRegisterPage());
        login.setOnClickListener(view -> login());
    }

    private void initView(){
        regester = findViewById(R.id.bt_login);
        login = findViewById(R.id.bt_register);
        username = findViewById(R.id.txt_email);
        txt = findViewById(R.id.textView);
        password = findViewById(R.id.txt_password);

    }

    private void goToRegisterPage(){
        Intent in = new Intent(this, RegisterActivity.class);
        startActivity(in);
    }

    private void login(){
        Pair<String, Boolean> usernameValidation =
                Validation.usernameValidation(username.getText().toString());
        Pair<String, Boolean> passwordValidation =
                Validation.passwordValidation(password.getText().toString());

        boolean t = usernameValidation.second && passwordValidation.second;
        if(!usernameValidation.second){
            username.setError(usernameValidation.first);
        }
        if(!passwordValidation.second) {
            password.setError(passwordValidation.first);
        }
        // here we must compare with database
        if(t && Validation.userExist(username.getText().toString(), password.getText().toString())){
            // go to next page Login success
            Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
            Intent in = new Intent(this, HomePageActivity.class);
            startActivity(in);
        }
        else if(t){
//            Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_LONG).show();
            Validation.changeText(txt, "Wrong username or password");
        }
    }

}