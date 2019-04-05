package com.puppy.asilo.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.puppy.asilo.FirebaseHelper.Listeners.RegistrationListener;
import com.puppy.asilo.FirebaseHelper.RegistrationHelper;
import com.puppy.asilo.Model.User;
import com.puppy.asilo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity implements RegistrationListener {
    private TextView mDateOfBirth, mCities;
    private EditText mEmail, mPassword, mFirstName, mLastName, mAddress, mPhone, mRetypedPassword;
    private Button btnRegister, btnBackToLogin;
    private ProgressBar mProgressSpinner;

    private RegistrationHelper mRegistrationHelper;

    /**
     * Inicijalizacija
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        //getSupportActionBar().setTitle(R.string.register); // Ako hoces staviti ActionBar na registraciju!

        mRegistrationHelper = new RegistrationHelper(this);


        mFirstName = findViewById(R.id.firstnameRegistration);
        mLastName = findViewById(R.id.lastnameRegistration);
        mPassword = findViewById(R.id.passwordRegistration);
        mRetypedPassword = findViewById(R.id.repeatPasswordRegistration);
        mEmail =  findViewById(R.id.emailRegistration);
        mAddress = findViewById(R.id.addressRegistration);
        mPhone = findViewById(R.id.contactRegistration);

        mProgressSpinner = findViewById(R.id.progressBarRegistration);
        mProgressSpinner.setVisibility(View.GONE);

        btnRegister =  findViewById(R.id.buttonRegister);
        btnBackToLogin =  findViewById(R.id.buttonBackToLogin);

        /**
         * Listeneri za registracijsku formu.
         * */

        mFirstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(mFirstName.getText().toString().trim().length() <= 0){
                        mFirstName.setError(getResources().getString(R.string.required));
                    }
                }
            }
        });

        mLastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(mLastName.getText().toString().trim().length() <= 0){
                        mLastName.setError(getResources().getString(R.string.required));
                    }
                }
            }
        });


        mEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(mEmail.getText().toString().trim().length() > 0){
                        boolean validationSuccess = mRegistrationHelper.validateEmail(mEmail.getText().toString().trim());
                        if(!validationSuccess){
                            mEmail.setError(getResources().getString(R.string.emailError));
                        }
                    }
                    else {
                        mEmail.setError(getResources().getString(R.string.required));
                    }
                }
            }
        });


        mPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    if (mPassword.getText().toString().trim().length() > 0) {
                        boolean validationSuccess = mRegistrationHelper.validatePassword(mPassword.getText().toString().trim());
                        if (!validationSuccess)
                            mPassword.setError(getResources().getString(R.string.passwordError));
                    } else {
                        mPassword.setError(getResources().getString(R.string.required));
                    }
                }
            }
        });

        mRetypedPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(mRetypedPassword.getText().toString().trim().length() > 0){
                        boolean validationSuccess = mRegistrationHelper.validateRetypedPassword(mPassword.getText().toString().trim(), mRetypedPassword.getText().toString().trim());
                        if(!validationSuccess){
                            mRetypedPassword.setError(getResources().getString(R.string.retypedPasswordError));
                        }
                    }
                    else{
                        mRetypedPassword.setError(getResources().getString(R.string.required));
                    }
                }
            }
        });

        mAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(mAddress.getText().toString().trim().length() <= 0){
                        mAddress.setError(getResources().getString(R.string.required));
                    }
                }
            }
        });

        mPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(mPhone.getText().toString().trim().length() <= 0){
                        mPhone.setError(getResources().getString(R.string.required));
                    }
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressSpinner.setVisibility(View.VISIBLE);
                mRegistrationHelper.registerUser(createUser(), mRetypedPassword.getText().toString().trim());
            }
        });

        btnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogin();
            }
        });

    }

    /**
     * U slučaju pritiska standard android "Back" gumba
     * prikaži LoginActivity. Poboljšava UX navigacije.
     * */

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showLogin();
    }

    /**
     * Otvori Activity login
     */
    public void showLogin(){
        startActivity(new Intent(this,LoginActivity.class));
        this.finish();
    }

    /**
     * Prikazi poruku korisniku
     * @param mMessage
     */
    public void showToastRegistration(String mMessage){
        Toast.makeText(this, mMessage, Toast.LENGTH_LONG).show();
    }

    /**
     * Kreiraj novog korisnika
     * @return
     */
    private User createUser(){
        User newUser = new User();
        newUser.setmFirstName(mFirstName.getText().toString().trim());
        newUser.setmLastName(mLastName.getText().toString().trim());
        newUser.setmPassword(mPassword.getText().toString().trim());
        newUser.setmEmail(mEmail.getText().toString().trim());
        newUser.setmAddress(mAddress.getText().toString().trim());
        newUser.setmPhone(mPhone.getText().toString().trim());
        // I ostali atributi!
        return newUser;
    }

    /**
     * U slucaju uspjesne registracije prikazi poruku
     * @param mMessage
     */
    @Override
    public void onRegistrationSuccess(String mMessage) {
        mProgressSpinner.setVisibility(View.GONE);
        showLogin();
        showToastRegistration(mMessage);
    }

    /**
     * U slucaju neuspjesne registracije prikazi poruku
     * @param mMessage
     */
    @Override
    public void onRegistrationFail(String mMessage) {
        mProgressSpinner.setVisibility(View.GONE);
        showToastRegistration(mMessage);
    }

}
