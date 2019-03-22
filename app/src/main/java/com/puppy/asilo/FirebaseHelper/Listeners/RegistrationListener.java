package com.puppy.asilo.FirebaseHelper.Listeners;

public interface RegistrationListener {
    void onRegistrationSuccess(String mMessage);
    void onRegistrationFail(String mMessage);
    void showToastRegistration(String mMessage);
}
