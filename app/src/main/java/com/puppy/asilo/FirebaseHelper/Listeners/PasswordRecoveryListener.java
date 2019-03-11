package com.puppy.asilo.FirebaseHelper.Listeners;

public interface PasswordRecoveryListener {
    void onRecoverySuccess(String mMessage);
    void onRecoveryFail(String mMessage);

}