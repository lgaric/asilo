package com.puppy.asilo.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.puppy.asilo.FirebaseHelper.Listeners.LoginListener;
import com.puppy.asilo.FirebaseHelper.LoginHelper;
import com.puppy.asilo.R;

public class LoginActivity extends AppCompatActivity implements LoginListener {
    private LoginHelper mLoginHelper;

    private EditText mUsernameLogin, mPasswordLogin;
    private Button btnLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
//        getSupportActionBar().hide();
        mUsernameLogin = findViewById(R.id.mUsernameLogin);
        mPasswordLogin = findViewById(R.id.mPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);

        mLoginHelper = new LoginHelper(this);

        //Anonimna metoda
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginHelper.login(mUsernameLogin.getText().toString(), mPasswordLogin.getText().toString());
            }
        });
    }

    /**
     * Prikazi Activity za obnovu lozinke
     * @param view
     */

    public void showRecoveryPassword(View view) {
        startActivity(new Intent(this,PasswordRecoveryActivity.class));
        this.finish();
    }

    /**
     * Prikazi Activity za registraciju korisnika.
     * @param view
     */

    public void showRegistration(View view){
        startActivity(new Intent(this, RegistrationActivity.class));
        this.finish();
    }

    /**
     * Adekvatne poruke.
     * */

    @Override
    public void onStatusFailed(String mMessage) {
        Toast.makeText(this, mMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}
