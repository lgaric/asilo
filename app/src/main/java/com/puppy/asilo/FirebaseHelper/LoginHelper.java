package com.puppy.asilo.FirebaseHelper;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.puppy.asilo.Core.CurrentUser;
import com.puppy.asilo.FirebaseHelper.Listeners.LoginListener;
import com.puppy.asilo.Model.User;
import com.puppy.asilo.R;

public class LoginHelper extends FirebaseBaseHelper{
    private LoginListener mLoginListener;

    /**
     * Konstruktor
     * @param mLoginListener
     */
    public LoginHelper(LoginListener mLoginListener){
        this.mContext = (Context) mLoginListener;
        this.mLoginListener = mLoginListener;
    }

    /**
     * Prijava korisnika
     * @param mUsername
     * @param mPassword
     */
    public void login(String mUsername, String mPassword){
        if(mUsername.isEmpty() || mPassword.isEmpty()) {
            mLoginListener.onStatusFailed(mContext.getResources().getString(R.string.fillAllFields));
            return;
        }

        if(isNetworkAvailable()) {
            mAuth.signInWithEmailAndPassword(mUsername, mPassword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (mAuth.getCurrentUser().isEmailVerified()) {
                                    getUserInformation(mAuth.getCurrentUser().getUid());
                                } else
                                   // mLoginListener.onStatusFailed(mContext.getResources().getString(R.string.emailNotVerified));
                                        mLoginListener.onStatusSuccess();
                            } else {
                                String errorMessage = checkErrorCode(((FirebaseAuthException) task.getException()).getErrorCode());
                                mLoginListener.onStatusFailed(errorMessage);
                            }
                        }
                    });
        }
        else
            showInternetMessageWarning();
    }

    /**
     * Dohvati razlog neuspjesne prijave
     * @param mError
     * @return
     */
    private String checkErrorCode(String mError){
        String errorMessage;

        // PRIVREMENI "FIX"
        if(mError == null){
            mError = "Unexpected error!";
        }

        switch(mError) {
            case "ERROR_INVALID_EMAIL":
                errorMessage =mContext.getResources().getString(R.string.emailLoginError);
                break;
            case "ERROR_WRONG_PASSWORD":
                errorMessage = mContext.getResources().getString(R.string.passwordLoginError);
                break;
            case "ERROR_USER_NOT_FOUND":
                errorMessage = mContext.getResources().getString(R.string.userNotFoundError);
                break;
            default:
                errorMessage = mContext.getResources().getString(R.string.defaultErrorMessage);
                break;
        }

        return  errorMessage;
    }

    /**
     * Dohvati sve informacije za trenutnog korisnika koji se logira
     * @param mUserUID
     */
    private void getUserInformation(String mUserUID){

        if(isNetworkAvailable()) {
            mReference = mDatabase.getReference().child(USER_NODE).child(mUserUID);

            mReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User temp = dataSnapshot.getValue(User.class);
                    if (temp == null) {
                        mLoginListener.onStatusFailed(checkErrorCode(null));
                    }
                    else {
                        CurrentUser.getCurrentUser = temp;
                        CurrentUser.getCurrentUser.setmUserID(dataSnapshot.getKey());
                        mLoginListener.onStatusSuccess();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
        else showInternetMessageWarning();
    }

}
