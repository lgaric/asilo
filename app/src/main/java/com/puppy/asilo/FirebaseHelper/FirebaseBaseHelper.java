package com.puppy.asilo.FirebaseHelper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.puppy.asilo.R;

public class FirebaseBaseHelper {
    //Firebase tablice
    protected static final String SHELTER_NODE = "Shelter";
    protected static final String DONATION_NODE = "Donation";
    protected static final String USER_NODE = "User";
    protected static final String ANIMAL_NODE = "Animal";


    //Firebase varijable
    protected FirebaseDatabase mDatabase;
    protected FirebaseAuth mAuth;
    protected DatabaseReference mReference;
    protected Query mQuery;
    protected Context mContext;

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
}
