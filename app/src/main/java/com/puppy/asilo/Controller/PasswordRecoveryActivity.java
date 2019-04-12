package com.puppy.asilo.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.puppy.asilo.FirebaseHelper.Listeners.PasswordRecoveryListener;
import com.puppy.asilo.FirebaseHelper.PasswordRecoveryHelper;
import com.puppy.asilo.R;

public class PasswordRecoveryActivity extends AppCompatActivity implements PasswordRecoveryListener {
    private EditText mUserEmail;
    private Button btnRecoveryPassword, btnShowLogin;
    private PasswordRecoveryHelper mPasswordRecoveryHelper;

    /**
     * Inicijalizacija
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_recovery_activity);

        mPasswordRecoveryHelper = new PasswordRecoveryHelper(this);

        btnRecoveryPassword = findViewById(R.id.buttonRecoveryPassword);
        btnShowLogin = findViewById(R.id.buttonShowLogin);

        mUserEmail =(EditText) findViewById(R.id.emailRecoveryPassword);

        btnRecoveryPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPasswordRecoveryHelper.sendRecoveryMail(mUserEmail.getText().toString());
            }
        });

        btnShowLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogin();
            }
        });

    }

    /**
     * Prikazi login Activity
     */
    public void showLogin(){
        startActivity(new Intent(this,LoginActivity.class));
        this.finish();
    }

    /**
     * Poslan zahtjev za novom lozinkom
     * @param mMessage
     */
    @Override
    public void onRecoverySuccess(String mMessage) {
        Toast.makeText(PasswordRecoveryActivity.this, mMessage, Toast.LENGTH_LONG).show();
        showLogin();
    }

    /**
     * Odbijen zahtjev za novom lozinkom
     * @param mMessage
     */
    @Override
    public void onRecoveryFail(String mMessage) {
        Toast.makeText(PasswordRecoveryActivity.this, mMessage, Toast.LENGTH_LONG).show();
    }
}