package com.puppy.asilo.FirebaseHelper;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.UploadTask;
import com.puppy.asilo.FirebaseHelper.Listeners.RegistrationListener;
import com.puppy.asilo.Model.User;
import com.puppy.asilo.R;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationHelper extends FirebaseBaseHelper{
    private RegistrationListener mRegistrationListener;

    /**
     * Konstruktor
     * @param mRegistrationListener
     */
    public RegistrationHelper(RegistrationListener mRegistrationListener) {
        this.mContext = (Context) mRegistrationListener;
        this.mRegistrationListener = mRegistrationListener;
    }

    /**
     * Registriraj korisnika
     * @param mNewUser
     */

    public void registration(User mNewUser, final Uri filePath){
        final User user = mNewUser;

        if(isNetworkAvailable()) {
            mQuery = mDatabase.getReference().child("User").orderByChild("email").equalTo(user.getmEmail());
            mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    //username exists
                    if (dataSnapshot.getChildrenCount() > 0) {
                        mRegistrationListener.showToastRegistration(mContext.getResources().getString(R.string.usernameInUse));
                    } else {
                        mAuth.createUserWithEmailAndPassword(user.getmEmail(), user.getmPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    final String uId = mAuth.getCurrentUser().getUid();
                                    mDatabase.getReference().child("User").child(uId).setValue(user);
                                    uploadImage(filePath);
                                    mAuth.getCurrentUser().sendEmailVerification();
                                    mAuth.signOut();
                                    mRegistrationListener.onRegistrationSuccess(mContext.getResources().getString(R.string.registrationSuccess));
                                } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    mRegistrationListener.onRegistrationFail(mContext.getResources().getString(R.string.emailInUse));
                                } else {
                                    mRegistrationListener.onRegistrationFail(mContext.getResources().getString(R.string.registrationFail));
                                }
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Do nothing
                }
            });
        }
        else
            showInternetMessageWarning();
    }

    /**
     * Pozovi metodu za registraciju ako je input korisnika valjan
     * @param mNewUser
     * @param mRetypedPassword
     */

    public void registerUser(User mNewUser, String mRetypedPassword, Uri filePath ) {
        if (InputCorrect(mNewUser, mRetypedPassword)) {
            registration(mNewUser, filePath);
        }
        else{
            mRegistrationListener.onRegistrationFail(mContext.getResources().getString(R.string.checkInputMessage));
        }
    }

    private void uploadImage(Uri filePath) {

        if(filePath != null)
        {
            mStorageReference = mStorageReference.child("images/"+ UUID.randomUUID().toString());
            mStorageReference.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //Toast.makeText(MainActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });/*
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());

                        }
                    });*/
        }
    }

    /**
     * Metode za validaciju unosa.
     * */

    public boolean validateEmail(String email){
        return(!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public boolean validatePassword(String password){
        Pattern pattern;
        Matcher matcher;

        /*
          Regexp: Mora sadržavati barem jedan broj, jedno veliko
          te jedno malo slovo. Sveukupno pass mora imati 8 znakova
          minimalno.
          */

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean validateRetypedPassword(String password, String retypedPassword){
        return (password.equals(retypedPassword));
    }

    /**
     * Provjeri valjanost korisnikovog inputa finalno
     * @param mNewUser
     * @param mRetypedPassword
     * @return
     */

    private boolean InputCorrect(User mNewUser, String mRetypedPassword){
        if(mNewUser.getmFirstName().length() > 0 && mNewUser.getmLastName().length() > 0 &&
                mNewUser.getmAddress().length() > 0 &&
                mNewUser.getmPhone().length() > 0 && validateEmail(mNewUser.getmEmail())
                && validatePassword(mNewUser.getmPassword()) && validateRetypedPassword(mNewUser.getmPassword(), mRetypedPassword))
            return true;
        else
            return false;
    }


}
