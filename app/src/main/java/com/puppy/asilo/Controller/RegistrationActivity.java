package com.puppy.asilo.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.puppy.asilo.Model.User;
import com.puppy.asilo.R;

public class RegistrationActivity extends AppCompatActivity implements RegistrationFragmentOne.RegFragmentOneListener,
        RegistrationFragmentTwo.RegFragmentTwoListener, RegistrationFragmentThree.RegFragmentThreeListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    public User tempUser = new User();

    /**
     * Fragments currentActiveFragment and newActiveFragment
     * are being used for exchange of fragments inside the
     * activity.
     * */
    private Fragment currentActiveFragment;
    private Fragment newActiveFragment;

    private Fragment regFragmentOne;
    private Fragment regFragmentTwo;
    private Fragment regFragmentThree;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.registration_activity);

        fragmentManager=getSupportFragmentManager();
        /**
         *  changeFragment method is called here to set the inital
         *  fragment -> RegistrationFragmentOne, otherwise the
         *  activity would be empty.
         * */
        changeFragment(null);
    }

    /**
     * Method sendDataToRegActivity receives data from the RegistrationFragmentOne.
     * The received data is an object of a class User. It contains relevant
     * data gathered in the first part of the registration process (name, phone, etc.).
     * */
    @Override
    public void sendDataToRegActivity(User userData) {
        tempUser = userData;

        Bundle userDataBundle = new Bundle();
        userDataBundle.putSerializable("USER_DATA", userData);

        changeFragment(userDataBundle);
    }

    /**
     * Method changeFragment changes visible fragment inside the
     * Registration Activity. It checks the current "active" fragment
     * and acts accordingly. If there are no active fragments it
     * initialises the RegistrationFragmentOne.
     * */

    private void changeFragment(Bundle userDataBundle){
        fragmentTransaction = fragmentManager.beginTransaction();
        currentActiveFragment = fragmentManager.findFragmentById(R.id.fragmentContainer);

        if(currentActiveFragment instanceof RegistrationFragmentOne){
            fragmentTransaction.hide(regFragmentOne);
            regFragmentTwo = new RegistrationFragmentTwo();
            newActiveFragment = regFragmentTwo;
            newActiveFragment.setArguments(userDataBundle);
        }
        else if(currentActiveFragment instanceof  RegistrationFragmentTwo){
            fragmentTransaction.hide(regFragmentTwo);
            regFragmentThree = new RegistrationFragmentThree();
            newActiveFragment = regFragmentThree;
            newActiveFragment.setArguments(userDataBundle);
        }
        else{
            regFragmentOne= new RegistrationFragmentOne();
            newActiveFragment = regFragmentOne;
        }

        fragmentTransaction.add(R.id.fragmentContainer, newActiveFragment);
        fragmentTransaction.commit();
    }

    /**
     * Method onBackPressed handles the pressing of the android
     * back button. If the active fragment is RegistrationFragmentOne
     * user is redirected to the LoginActivity, otherwise
     * */
    @Override
    public void onBackPressed() {
        fragmentTransaction = fragmentManager.beginTransaction();
        currentActiveFragment = fragmentManager.findFragmentById(R.id.fragmentContainer);

        if(currentActiveFragment instanceof RegistrationFragmentOne){
            startActivity(new Intent(this, LoginActivity.class));
            this.finish();
        }
        else{
            if(currentActiveFragment instanceof  RegistrationFragmentTwo){
                fragmentTransaction.show(regFragmentOne);
            }
            else if(currentActiveFragment instanceof  RegistrationFragmentThree){
                fragmentTransaction.show(regFragmentTwo);
            }
            fragmentTransaction.remove(currentActiveFragment);
            fragmentTransaction.commit();
        }
    }
}
