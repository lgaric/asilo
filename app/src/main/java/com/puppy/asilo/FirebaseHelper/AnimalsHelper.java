package com.puppy.asilo.FirebaseHelper;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.puppy.asilo.FirebaseHelper.Listeners.AnimalsListener;
import com.puppy.asilo.Model.Animal;
import com.puppy.asilo.R;

import java.util.ArrayList;

public class AnimalsHelper extends FirebaseBaseHelper {
    private AnimalsListener mAnimalsListener;

    public AnimalsHelper(AnimalsListener mAnimalsListener){
        this.mContext = ((Fragment)mAnimalsListener).getContext();
        this.mAnimalsListener = mAnimalsListener;
    }

    public void loadAnimals(){
        if (mAnimalsListener == null)
            return;

        if(isNetworkAvailable()){
            mQuery = mDatabase.getReference().child(ANIMAL_NODE);

            mQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ArrayList<Animal> animalList = new ArrayList<>();
                    for (DataSnapshot temp : dataSnapshot.getChildren()) {
                        Animal mAnimal = temp.getValue(Animal.class);
                        mAnimal.setAnimalID(temp.getKey());
                        animalList.add(mAnimal);
                    }
                    if(mAnimalsListener != null)
                        mAnimalsListener.animalsListReceived(animalList);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    mAnimalsListener.errorWhileLoadingAnimalsList(mContext.getString(R.string.errorWhileLoadingAnimals));
                }
            });
        }else
            showInternetMessageWarning();
    }


}
