package com.puppy.asilo.Core;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.puppy.asilo.Controller.AnimalsFragment;
import com.puppy.asilo.Controller.LoginActivity;
import com.puppy.asilo.Controller.NewAnimalFragment;
import com.puppy.asilo.R;

import java.util.ArrayList;

public class NavigationManager {
    private static NavigationManager instance;

    public static NavigationManager getInstance(){
        if(instance == null)
            instance = new NavigationManager();
        return instance;
    }

    private ArrayList<NavigationItem> navigationItems;
    private DrawerLayout drawerLayout;
    private AppCompatActivity activity;
    private NavigationView navigationView;
    private int dynamicGroupId;

    private NavigationManager(){
        set();
    }

    private void set(){
        navigationItems = new ArrayList<>();
        navigationItems.add(new AnimalsFragment());
        navigationItems.add(new NewAnimalFragment());
        /*
        navigationItems.add(new NewsFragment());
        navigationItems.add(new DonationsFragment());
        navigationItems.add(new SettingsFragment());
        */
    }

    public void startMainModule(){
        NavigationItem mainModule = navigationItems != null ? navigationItems.get(0) : null;
        if (mainModule != null)
            startModule(mainModule);
    }

    public void setDrawerDependencies(AppCompatActivity activity, NavigationView navigationView,
                                      DrawerLayout drawerLayout, int dynamicGroupId)
    {
        this.activity = activity;
        this.navigationView = navigationView;
        this.drawerLayout = drawerLayout;
        this.dynamicGroupId = dynamicGroupId;
        setupDrawer();
    }

    private void setupDrawer()
    {
        for (int i = 0; i < navigationItems.size(); i++) {
            NavigationItem item = navigationItems.get(i);
            navigationView.getMenu()
                    .add(dynamicGroupId, i, i+1, item.getName(activity))
                    .setCheckable(true).setIcon(item.getIcon(activity));
        }
    }

    private void startModule(NavigationItem item){
        FragmentManager mFragmentManager = activity.getSupportFragmentManager();
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, item.getFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public void selectNavigationItem(MenuItem menuItem) {
        if (!menuItem.isChecked()) {
            menuItem.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);
            NavigationItem selectedItem = navigationItems.get(menuItem.getItemId());
            startModule(selectedItem);
        }
    }

    /**
     * Pitaj korisnika zeli li se odjaviti iz aplikacije
     */
    public void endOfWork(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle(activity.getString(R.string.logOutQuestion));
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton(activity.getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                logout();
                            }
                        })
                .setNegativeButton(activity.getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    /**
     * Odjava korisnika iz aplikacije i prikaz login Activitija
     */
    private void logout(){
        CurrentUser.getCurrentUser = null;
        getInstance().set();
        activity.startActivity(new Intent(activity, LoginActivity.class));
        activity.finish();
    }

}
