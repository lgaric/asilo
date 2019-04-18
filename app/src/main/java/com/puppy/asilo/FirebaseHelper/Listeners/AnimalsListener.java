package com.puppy.asilo.FirebaseHelper.Listeners;

import com.puppy.asilo.Model.Animal;

import java.util.ArrayList;

public interface AnimalsListener {
    void animalsListReceived(ArrayList<Animal> mAnimalsList);
    void errorWhileLoadingAnimalsList(String message);
}
