package com.puppy.asilo.Controller;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.puppy.asilo.Model.User;
import com.puppy.asilo.R;

public class RegistrationFragmentThree extends Fragment implements View.OnClickListener {

    private User userData;
    private TextView test;

    private RegistrationFragmentThree.RegFragmentThreeListener listener;

    public interface RegFragmentThreeListener{
        void sendDataToRegActivity(User userData);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View currentView = inflater.inflate(R.layout.registration_fragment_three, container, false);

        userData = (User) getArguments().getSerializable("USER_DATA");

        test = (TextView) currentView.findViewById(R.id.tvTEST);
        test.setText(userData.getmAddress());

        return currentView;
    }

    @Override
    public void onClick(View v) {

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
