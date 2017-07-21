package com.sugar.collection.collectionsugar.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sugar.collection.collectionsugar.R;
import com.sugar.collection.collectionsugar.Utils;
import com.sugar.collection.collectionsugar.services.UserService;

public class RegisterActivity extends Activity {

    /**
     * Full name of user.
     */
    private EditText fullName;
    /**
     * Login user.
     */
    private EditText username;
    /**
     * Password user.
     */
    private EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.fullName = (EditText) findViewById(R.id.fullname);
        this.username = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        Button btn_register = (Button) findViewById(R.id.btnRegister);

        // Click event for save user and navigate to username.
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saveUser()) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.create_user_success, Toast.LENGTH_LONG);
                    toast.show();
                    finish();
                } else {
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

    /**
     * This method save the new user, if user not exists.
     *
     * @return True if created with success.
     */
    public boolean saveUser() {
        String fullname = this.fullName.getText().toString().trim();
        String username = this.username.getText().toString().trim();
        String password = this.password.getText().toString().trim();
        if (username.trim().length() == Utils.ZERO) {
            this.username.setError("Nome de usuário não foi inserido");
            this.username.requestFocus();
        }
        if (username.trim().length() < Utils.LOGIN_MIN) {
            this.username.setError("Nome de usuário não pode ter menos de 5 caracteres");
            this.username.requestFocus();
        }
        if (password.trim().length() == Utils.ZERO) {
            this.password.setError("Senha não foi inserida");
            this.password.requestFocus();
        }
        if (password.trim().length() < Utils.PASSWORD_MIN) {
            this.password.setError("Senha não pode ter menos de 5 caracteres");
            this.password.requestFocus();
        } else {
            return UserService.saveUser(fullname, username, password);
        }
        return false;
    }

//    public void backToLogin(){
//        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
//        startActivity(i);
//    }
}