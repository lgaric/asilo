package com.puppy.asilo.Core.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.puppy.asilo.FirebaseHelper.Listeners.AnimalClickListener;
import com.puppy.asilo.Model.Animal;
import com.puppy.asilo.R;

public class AnimalsHolder extends RecyclerView.ViewHolder {
    private Animal mAnimal;

    private TextView mAnimalName, mAnimalType;
    private ImageView mAnimalImage;
    private AnimalClickListener mAnimalClickListener;

    //ONCLICKLISTENER -> ANDROID INTERFACE
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mAnimalClickListener.animalSelected(mAnimal);
        }
    };

    public AnimalsHolder(@NonNull View itemView) {
        super(itemView);

        mAnimalName = itemView.findViewById(R.id.animal_name);
        mAnimalType = itemView.findViewById(R.id.animal_type);
        mAnimalImage = itemView.findViewById(R.id.animal_image);

        itemView.setOnClickListener(mClickListener);
    }

    public void bind(Animal mAnimal, AnimalClickListener mAnimalClickListener){
        this.mAnimal = mAnimal;

        mAnimalName.setText(mAnimal.getName());
        mAnimalType.setText(mAnimal.getAnimalType());
        //TODO postaviti sliku životinje

        this.mAnimalClickListener = mAnimalClickListener;
    }

}
