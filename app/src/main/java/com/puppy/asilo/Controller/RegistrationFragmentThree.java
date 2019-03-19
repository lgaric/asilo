package com.puppy.asilo.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.puppy.asilo.FirebaseHelper.Listeners.RegistrationListener;
import com.puppy.asilo.FirebaseHelper.RegistrationHelper;
import com.puppy.asilo.Model.User;
import com.puppy.asilo.R;

public class RegistrationFragmentThree extends Fragment implements View.OnClickListener, RegistrationListener {

    private User userData;
    private Button btnRegRegister;
    private ProgressDialog pdRegUserReg;

    private RegistrationHelper mRegistrationHelper;

    @Override
    public void onRegistrationFailed(String mMessage) {
        Toast.makeText(getActivity(), R.string.defaultErrorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRegistrationSuccess() {
        Toast.makeText(getActivity(), R.string.registrationComplete, Toast.LENGTH_LONG).show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View currentView = inflater.inflate(R.layout.registration_fragment_three, container, false);
        /**
         * Extract user data from received from the Activity.
         * userData for now contains only the data entered so far.
         * */
        userData = (User) getArguments().getSerializable("USER_DATA");
        btnRegRegister = (Button) currentView.findViewById(R.id.btnRegRegister);

        /**
        Additional objects.
        */
        mRegistrationHelper = new RegistrationHelper(this);
        //pdRegUserReg = new ProgressDialog(this.getContext());

        /**FOR TESTS*/
        btnRegRegister.setOnClickListener(this);

        return currentView;
    }

    /**
     * Method that registers the user. Stores all data to the
     * Firebase.
     * */
    private void registerUser(){
        pdRegUserReg.setMessage("Registracija u tijeku, pricekajte trenutak ...");
        pdRegUserReg.show();



        /**
         * Change activity, last step of the registration.
         * */
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegRegister:
                registerUser();
        }

    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof RegistrationFragmentThree){
             = (RegistrationFragmentThree.RegFragmentThreeListener) context;
        }
        else{
            throw new RuntimeException(context.toString()
                    + R.string.fragmentListenerNotImplemented);
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        super.onDetach();
    }*/
}
