package com.puppy.asilo.FirebaseHelper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.puppy.asilo.R;

import java.io.File;
import java.util.UUID;

public class FirebaseBaseHelper{
    //Firebase tablice
    protected static final String SHELTER_NODE = "Shelter";
    protected static final String DONATION_NODE = "Donation";
    protected static final String USER_NODE = "User";
    protected static final String ANIMAL_NODE = "Animal";
    protected static final String ADOPTION_REQUEST_NODE = "AdoptionRequest";


    //Firebase varijable
    protected FirebaseDatabase mDatabase;
    protected FirebaseAuth mAuth;
    protected DatabaseReference mReference;
    protected Query mQuery;
    protected Context mContext;
    protected static FirebaseStorage mStorage;
    protected static StorageReference mStorageReference, mimageFilePath;

    /**
     * Konstruktor
     */
    public FirebaseBaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    /**
     * Provjera interneta
     * @return
     */
    protected Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Poruka o nepovezanosti
     */
    protected void showInternetMessageWarning(){
        if(mContext != null){
            Toast.makeText(mContext, mContext.getResources().getString(R.string.noInternetConnectionMessage), Toast.LENGTH_LONG).show();
        }
    }
    /**
     * Ažuriranje fotografije profila
     * */

    public void updateProfileImage(final FirebaseUser currentUser, Uri filePath){
        if(filePath != null)
        {
            mStorageReference = FirebaseStorage.getInstance().getReference().child("profilePhotos");
            mimageFilePath = mStorageReference.child(currentUser.getUid());
            mimageFilePath.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Image uploaded successfully

                    mimageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                    .setPhotoUri(uri)
                                    .build();

                            currentUser.updateProfile(profileUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        // user pic is updated
                                    }
                                }
                            });

                        }
                    });
                }
            });
        }
    }



}
