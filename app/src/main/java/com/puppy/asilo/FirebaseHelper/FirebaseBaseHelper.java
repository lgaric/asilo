package com.puppy.asilo.FirebaseHelper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
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
    protected static StorageReference mStorageReference;

    /**
     * Konstruktor
     */
    public FirebaseBaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance();
        mStorageReference = mStorage.getReference();
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
     * Prijenos fotografija
     * */

    public void uploadImage(Uri filePath){
        if(filePath != null)
        {
            mStorageReference = mStorageReference.child("images/" + UUID.randomUUID().toString());
            mStorageReference.putFile(filePath);
        }
    }

    /* U RAZVOJU :
    public void uploadImage(Uri filePath) throws Exception {

        if(filePath != null)
        {
            File file = new File(filePath.getPath());
            long fileSize = file.length();

            if(fileSize/1000000 < 0.5) {
                mStorageReference = mStorageReference.child("images/" + UUID.randomUUID().toString());
                try {
                    mStorageReference.putFile(filePath);
                } catch (Exception e) {
                    throw new Exception("Image upload has failed!", e);
                }
            }
            else {
                throw new java.lang.RuntimeException("File is too large!");
            }
        }
    }*/

}
