package com.puppy.asilo.FirebaseHelper;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.puppy.asilo.FirebaseHelper.Listeners.RegistrationListener;
import com.puppy.asilo.Model.User;
import com.puppy.asilo.R;

public class RegistrationHelper extends FirebaseBaseHelper {

    RegistrationListener mRegistration;

    public RegistrationHelper(RegistrationListener mRegistration){
        this.mRegistration = mRegistration;
    }

    public registerUser(final User userData) {
        mAuth.createUserWithEmailAndPassword(userData.getmEmail(), userData.getmPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    final String uId = mAuth.getCurrentUser().getUid();
                    mDatabase.getReference().child("users").child(uId).setValue(userData);
                    mAuth.getCurrentUser().sendEmailVerification();
                    mAuth.signOut();
                    mRegistration.onRegistrationSuccess();
                } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                    mRegistration.onRegistrationFailed(mContext.getResources().getString(R.string.emailInUse));
                } else {
                    mRegistration.onRegistrationFailed(mContext.getResources().getString(R.string.registrationFailed));
                }
            }
        });
    }

}
