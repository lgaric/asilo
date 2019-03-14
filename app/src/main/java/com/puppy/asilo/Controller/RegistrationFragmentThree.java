package com.puppy.asilo.Controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.puppy.asilo.Model.User;
import com.puppy.asilo.R;

public class RegistrationFragmentThree extends Fragment implements View.OnClickListener {

    private User userData;
    private TextView test;
    private Button btnRegRegister;
    private ProgressDialog pdRegUserReg;

    private FirebaseAuth firebaseAuth;
    private RegistrationFragmentThree.RegFragmentThreeListener listener;

    public interface RegFragmentThreeListener{
        void sendDataToRegActivity(User userData);
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

        test = (TextView) currentView.findViewById(R.id.tvTEST);
        btnRegRegister = (Button) currentView.findViewById(R.id.btnRegRegister);

        /**
        Additional objects.
        */
        firebaseAuth = FirebaseAuth.getInstance();
        pdRegUserReg = new ProgressDialog(this.getContext());

        /**FOR TESTS*/
        test.setText("AAAAA :D");
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

        firebaseAuth.createUserWithEmailAndPassword(userData.getmEmail(), userData.getmPassword())
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        pdRegUserReg.dismiss();
                        if(task.isSuccessful()){
                            test.setText("DA");
                        }
                        else{
                            test.setText("NE");
                        }
                    }
                });

        /**
         * Change activity, last step of the registration.
         * *//*
        startActivity(new Intent(this,LoginActivity.class));
        this.finish();*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegRegister:
                registerUser();
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /**
         * Check if the RegistrationActivity implements the interface.
         * If it doesn't throw an exception, good practise.
         * */
        if(context instanceof RegistrationFragmentThree.RegFragmentThreeListener){
            listener = (RegistrationFragmentThree.RegFragmentThreeListener) context;
        }
        else{
            throw new RuntimeException(context.toString()
                    + R.string.fragmentListenerNotImplemented);
        }
    }

    @Override
    public void onDetach() {
        /**
         * When you remove this fragment set the listener to null.
         * In other words reset the listener.
         * */
        listener = null;
        super.onDetach();
    }
}
