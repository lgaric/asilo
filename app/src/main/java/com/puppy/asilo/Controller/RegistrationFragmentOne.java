package com.puppy.asilo.Controller;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.puppy.asilo.Model.User;
import com.puppy.asilo.R;

public class RegistrationFragmentOne extends Fragment implements View.OnClickListener {

    /**
     * Variables.
     * */

    private EditText etRegName;
    private EditText etRegSName;
    private EditText etRegEmail;
    private EditText etRegPass;
    private EditText etRegPassAgain;
    private EditText etRegPhone;
    private Button btnRegNext;

    private RegFragmentOneListener listener;

    public interface RegFragmentOneListener{
        void sendDataToRegActivity(User userData);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View currentView = inflater.inflate(R.layout.registration_fragment_one, container,false);

         /**
          * Connecting variables with the XML (UI elements).
          * */

        etRegName = (EditText) currentView.findViewById(R.id.etRegName);
        etRegSName = (EditText) currentView.findViewById(R.id.etRegSName);
        etRegEmail = (EditText) currentView.findViewById(R.id.etRegEmail);
        etRegPass = (EditText) currentView.findViewById(R.id.etRegPass);
        etRegPassAgain = (EditText) currentView.findViewById(R.id.etRegPassAgain);
        etRegPhone = (EditText) currentView.findViewById(R.id.etRegPhone);
        btnRegNext = (Button) currentView.findViewById(R.id.btnRegNext);

        btnRegNext.setOnClickListener(this);

        return currentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegNext:
                /**
                 * User is an entity class. Defined in User.java file.
                 * It is used for easier data transfer between activities
                 * and fragments. After the data is gathered it is being
                 * sent to the RegistrationActivity via the listener.
                 * */

                User userData = new User();
                userData.setmFirstName(etRegName.getText().toString());
                userData.setmLastName(etRegSName.getText().toString());
                userData.setmEmail(etRegEmail.getText().toString());
                userData.setmPassword(etRegPass.getText().toString());
                userData.setmPhone(etRegPhone.getText().toString());

                listener.sendDataToRegActivity(userData);
        }
    }
/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof RegFragmentOneListener){
            listener = (RegFragmentOneListener) context;
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
