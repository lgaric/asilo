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
import android.widget.ImageButton;
import android.widget.CheckBox;
import android.widget.TextView;

import com.puppy.asilo.Model.User;
import com.puppy.asilo.R;

public class RegistrationFragmentTwo extends Fragment implements View.OnClickListener {

    /**
     * Variables.
     * */

    private EditText etRegAdress;
    private TextView tvRegTitle2;
    private ImageButton btnRegDog;
    private ImageButton btnRegCat;
    private CheckBox rbRegFood;
    private CheckBox rbRegMoney;
    private CheckBox rbRegVolunteer;
    private CheckBox rbRegHelp;
    private CheckBox rbRegAdopt;
    private Button btnRegNext2;

    private User userData;
    private RegFragmentTwoListener listener;

    public interface RegFragmentTwoListener{
        void sendDataToRegActivity(User userData);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View currentView = inflater.inflate(R.layout.registration_fragment_two, container, false);
        /**
         * Extract user data from received from the Activity.
         * userData for now contains only the data entered so far.
         * */
        userData = (User) getArguments().getSerializable("USER_DATA");

        /**
         * Connecting variables with the XML (UI elements).
         * */
        etRegAdress = (EditText) currentView.findViewById(R.id.etRegAdress);
        tvRegTitle2 = (TextView) currentView.findViewById(R.id.tvRegTitle2);
        btnRegDog = (ImageButton) currentView.findViewById(R.id.btnRegDog);
        btnRegCat = (ImageButton) currentView.findViewById(R.id.btnRegCat);
        rbRegFood = (CheckBox) currentView.findViewById(R.id.cbRegFood);
        rbRegMoney = (CheckBox) currentView.findViewById(R.id.cbRegMoney);
        rbRegVolunteer = (CheckBox) currentView.findViewById(R.id.cbRegVolunteer);
        rbRegHelp = (CheckBox) currentView.findViewById(R.id.cbRegHelp);
        rbRegAdopt = (CheckBox) currentView.findViewById(R.id.cbRegAdopt);
        btnRegNext2 = (Button) currentView.findViewById(R.id.btnRegNext2);

        btnRegNext2.setOnClickListener(this);
        btnRegCat.setOnClickListener(this);
        btnRegDog.setOnClickListener(this);

        return currentView;
    }

    private void checkCheckBoxs(){

        /**
         * Check the radio buttons, set class attribute values
         * accordingly. If something is checked set attribute
         * value to true, otherwise set it to false.
         * */

        if(rbRegFood.isChecked()){userData.setmGiveFood(true);}
        else userData.setmGiveFood(false);

        if(rbRegMoney.isChecked()){userData.setmGiveMoney(true);}
        else userData.setmGiveMoney(false);

        if(rbRegVolunteer.isChecked()){userData.setmVolunteer(true);}
        else userData.setmVolunteer(false);

        if(rbRegHelp.isChecked()){userData.setmHelp(true);}
        else userData.setmHelp(false);

        if(rbRegAdopt.isChecked()){userData.setmAdopt(true);}
        else userData.setmAdopt(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegNext2:
                userData.setmAddress(etRegAdress.getText().toString());
                checkCheckBoxs();
                listener.sendDataToRegActivity(userData);
                break;
            case R.id.btnRegDog:
                userData.setmDogPerson(true);
                userData.setmCatPerson(false);
                break;
            case R.id.btnRegCat:
                userData.setmDogPerson(false);
                userData.setmCatPerson(true);
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /**
         * Check if the RegistrationActivity implements the interface.
         * If it doesn't throw an exception, good practise.
         * */
        if(context instanceof RegFragmentTwoListener){
            listener = (RegFragmentTwoListener) context;
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
