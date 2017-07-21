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
import com.sugar.collection.collectionsugar.services.SessionService;
import com.sugar.collection.collectionsugar.services.SettingsService;
import com.sugar.collection.collectionsugar.services.UserService;

public class LoginActivity extends Activity {

    private EditText username;
    private EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (checkPreviousLogin()){
            goToMainActivity();
        }
        setContentView(R.layout.activity_login);

        this.username = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        Button btnEntrar = (Button) findViewById(R.id.btnLogin);

        registerScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUser()) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.login_success, Toast.LENGTH_SHORT);
                    toast.show();
                    goToMainActivity();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.login_fail, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }


    public boolean isUser() {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
        return saveSessionForUser(username, password);
    }


    private boolean saveSessionForUser(String username, String password) {
        SettingsService.USER_TEMPORARY_SESSION = UserService.checkUserByLogin(username, password);
        if (SettingsService.USER_TEMPORARY_SESSION != null) {
            SessionService.saveSession(SettingsService.USER_TEMPORARY_SESSION.getId().intValue(), true);
            return true;
        } else {
            return false;
        }
    }

    public void goToMainActivity() {
        if (SettingsService.USER_TEMPORARY_SESSION != null) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }


    //TODO: Implementar verificação de usuário anteriormente logado, para evitar login repetitivo.
    public boolean checkPreviousLogin() {
        if (SessionService.getAllSessions().isEmpty()) {
            return false;
        } else {
            SettingsService.PREVIOUS_SESSION = SessionService.getAllSessions().get(SessionService
                    .getAllSessions().size() - 1);
            SettingsService.USER_TEMPORARY_SESSION = UserService
                    .getUserById(SettingsService.PREVIOUS_SESSION.getIdUser());
            if (!SettingsService.PREVIOUS_SESSION.isActive()) {
                return false;
            } else {
                return saveSessionForUser(SettingsService.USER_TEMPORARY_SESSION.getLogin(),
                        SettingsService.USER_TEMPORARY_SESSION.getPassword());
            }
        }
    }

}