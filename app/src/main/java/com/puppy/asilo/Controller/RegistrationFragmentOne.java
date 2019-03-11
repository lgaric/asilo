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

import com.puppy.asilo.R;

public class RegistrationFragmentOne extends Fragment {

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
        View v = inflater.inflate(R.layout.registration_fragment_one, container,false);

         /**
          * Connecting variables with the UI elements.
          * */

        etRegName = (EditText) v.findViewById(R.id.etRegName);
        etRegSName = (EditText) v.findViewById(R.id.etRegSName);
        etRegEmail = (EditText) v.findViewById(R.id.etRegEmail);
        etRegPass = (EditText) v.findViewById(R.id.etRegPass);
        etRegPassAgain = (EditText) v.findViewById(R.id.etRegPassAgain);
        etRegPhone = (EditText) v.findViewById(R.id.etRegPhone);
        btnRegNext = (Button) v.findViewById(R.id.btnRegNext);

        btnRegNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * User is an entity class. Defined in User.java file.
                 * It is used for easier data transfer between activities
                 * and fragments. After the data is gathered it is being
                 * sent to the RegistrationActivity via the listener.
                 * */

                User userData = new User();
                userData.setName(etRegName.getText());
                userData.setSurname(etRegSName.getText());
                userData.setEmail(etRegEmail.getText());
                userData.setPassword(etRegPass.getText());
                userData.setPhone(etRegPhone.getText());

                listener.sendDataToRegActivity(userData);

            }
        });
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /**
         * Check if the RegistrationActivity implements the interface.
         * If it doesn't throw an exception, good practise.
         * */
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
        /**
         * When you remove this fragment set the listener to null.
         * In other words reset the listener.
         * */
        listener = null;
        super.onDetach();
    }
}
