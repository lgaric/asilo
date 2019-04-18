package com.puppy.asilo.Core.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puppy.asilo.FirebaseHelper.Listeners.AnimalClickListener;
import com.puppy.asilo.Model.Animal;
import com.puppy.asilo.R;

import java.util.ArrayList;

public class AnimalsAdapter extends RecyclerView.Adapter<AnimalsHolder> {
    private ArrayList<Animal> mAnimalsList;
    private AnimalClickListener mListener;

    public AnimalsAdapter(ArrayList<Animal> mAnimalsList, AnimalClickListener listener){
        this.mAnimalsList = mAnimalsList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public AnimalsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.animal_list_item, parent, false);
        return new AnimalsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalsHolder holder, int position) {
        holder.bind(mAnimalsList.get(position), this.mListener);
    }

    @Override
    public int getItemCount() {
        return mAnimalsList.size();
    }
}
