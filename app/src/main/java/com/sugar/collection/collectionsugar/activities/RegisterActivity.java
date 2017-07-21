package com.sugar.collection.collectionsugar.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sugar.collection.collectionsugar.R;
import com.sugar.collection.collectionsugar.services.UserService;

public class RegisterActivity extends Activity {

    private EditText fullName;
    private EditText login;
    private EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.fullName = (EditText) findViewById(R.id.fullname);
        this.login = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        Button btn_register = (Button) findViewById(R.id.btnRegister);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(saveUser()){
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.create_user_success, Toast.LENGTH_LONG);
                    toast.show();
                    backToLogin();
                }else{
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.create_user_error, Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });

        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                finish();
            }
        });
    }

    public boolean saveUser(){
        String fullname = this.fullName.getText().toString();
        String username = this.login.getText().toString();
        String password = this.password.getText().toString();
        return UserService.saveUser(fullname, username, password);
    }

    public void backToLogin(){
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
    }
}