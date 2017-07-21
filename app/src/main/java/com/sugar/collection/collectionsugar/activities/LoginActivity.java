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
import com.sugar.collection.collectionsugar.Utils;
import com.sugar.collection.collectionsugar.services.SessionService;
import com.sugar.collection.collectionsugar.services.SettingsService;
import com.sugar.collection.collectionsugar.services.UserService;

public class LoginActivity extends Activity {

    /**
     * The username Edittext.
     */
    private EditText username;
    /**
     * The password Edittext.
     */
    private EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Verifica se usuário já fez algum login previamente, e faz alterações necessárias na
        // sessao do usuário.
        if (checkPreviousLogin()) {
            goToMainActivity();
        }
        setContentView(R.layout.activity_login);

        this.username = (EditText) findViewById(R.id.username);
        this.password = (EditText) findViewById(R.id.password);
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        Button btnEntrar = (Button) findViewById(R.id.btnLogin);

        // Click Event para navegar para a tela de registro.
        registerScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        // Click Event para logar usuário ou não.
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verifica de os dados existem no BD e seta sessao do usuario.
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

    /**
     * Verify if user exists.
     *
     * @return True if user exists.
     */
    private boolean isUser() {
        String username = this.username.getText().toString();
        String password = this.password.getText().toString();
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
            return saveSessionForUser(username, password);
        }
        return false;
    }


    /**
     * Save the temporary session for the user. If user not exists, SettingsService.USER_TEMPORARY_SESSION
     * is null.
     *
     * @param username String username inserted in Edit Text.
     * @param password String password inserted in Edit Text.
     * @return True if user exists.
     */
    private boolean saveSessionForUser(String username, String password) {
        SettingsService.USER_TEMPORARY_SESSION = UserService.checkUserByLogin(username, password);
        if (SettingsService.USER_TEMPORARY_SESSION != null) {
            SessionService.saveSession(SettingsService.USER_TEMPORARY_SESSION.getId().intValue(), true);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Navigate to Main Activity if SettingsService.USER_TEMPORARY_SESSION diff null.
     */
    public void goToMainActivity() {
        if (SettingsService.USER_TEMPORARY_SESSION != null) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }

    /**
     * Check if user is previous login in the app, and set the user temporary session
     * and previous session.
     *
     * @return True if previous session ( not logout ).
     */
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