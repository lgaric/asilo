package com.puppy.asilo.Controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.puppy.asilo.Core.Adapters.AnimalsAdapter;
import com.puppy.asilo.Core.NavigationItem;
import com.puppy.asilo.FirebaseHelper.AnimalsHelper;
import com.puppy.asilo.FirebaseHelper.Listeners.AnimalClickListener;
import com.puppy.asilo.FirebaseHelper.Listeners.AnimalsListener;
import com.puppy.asilo.Model.Animal;
import com.puppy.asilo.R;

import java.util.ArrayList;

public class AnimalsFragment extends Fragment implements AnimalsListener, NavigationItem, AnimalClickListener {
    private ArrayList<Animal> mAnimalsList = null;
    SwipeRefreshLayout mSwipeRefresh;
    RecyclerView mRecyclerView;

    AnimalsHelper mAnimalsHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animals, container, false);
        mSwipeRefresh = view.findViewById(R.id.swipe_refresh);
        mRecyclerView = view.findViewById(R.id.animals_lists);
        mSwipeRefresh.setOnRefreshListener(mRefreshListener);
        if(mAnimalsHelper == null) {
            mAnimalsHelper = new AnimalsHelper(this);
            mAnimalsHelper.loadAnimals();
        }
        else
            mAnimalsHelper.loadAnimals();
        return view;
    }

    @Override
    public void onResume() {
        ((MainActivity)getActivity()).getSupportActionBar().setTitle(getActivity().getResources().getString(R.string.animals));
        super.onResume();
    }

    @Override
    public String getName(Context context) {
        return context.getString(R.string.animals);
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public Drawable getIcon(Context context) {
        return context.getDrawable(R.drawable.ic_paw);
    }

    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mAnimalsHelper.loadAnimals();
        }
    };

    @Override
    public void animalsListReceived(ArrayList<Animal> mAnimalsList) {
        if(mAnimalsList != null){
            this.mAnimalsList = mAnimalsList;
            setRecyclerView();
        }
    }

    @Override
    public void errorWhileLoadingAnimalsList(String message) {
        Toast.makeText(getContext(), getActivity().getResources().getString(R.string.errorWhileLoadingAnimals), Toast.LENGTH_LONG).show();
    }

    public void setRecyclerView(){
        if(mAnimalsList != null){
            mRecyclerView.setVisibility(View.VISIBLE);
            mRecyclerView.setAdapter(null);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            AnimalsAdapter mAnimalsAdapter = new AnimalsAdapter(this.mAnimalsList, this);
            mRecyclerView.setAdapter(mAnimalsAdapter);
            mSwipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public void animalSelected(Animal mAnimal) {
        //TODO prikazi detalje zivotinje
    }
}
