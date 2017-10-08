package io.nothing.sample;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import de.hdodenhof.circleimageview.CircleImageView;
import io.nothing.sample.fragment.CallsFragment;
import io.nothing.sample.fragment.ContactsFragment;
import io.nothing.sample.fragment.MessagesFragment;
import io.nothing.sample.utility.Constants;
import io.nothing.sample.utility.Utils;


public class BottomNavigationViewActivity extends BaseActivity {



    private int lastSelectedId = 0;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    NavigationView navigationView;
    private DrawerLayout navigationDrawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Utils.updateTheme(this);

        setContentView(R.layout.activity_bottom_navigation_view);

        navigationDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        frameLayout = (FrameLayout) findViewById(R.id.frame_layout);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);





        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                navigationDrawer,
                R.string.open,
                R.string.close
        )

        {

            @Override
            public void onDrawerSlide(View drawer, float slideOffset) {

                findViewById(R.id.main_content).setX(- drawer.getWidth() * slideOffset);

            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                syncState();


            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();

             }


        };


        navigationDrawer.addDrawerListener(actionBarDrawerToggle);




        View hView = navigationView.getHeaderView(0);

        CircleImageView profile_photo = (CircleImageView) hView.findViewById(R.id.profile_photo);







        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        Fragment selectedFragment = null;

                        switch (item.getItemId()) {
                            case R.id.call:
                                selectedFragment = CallsFragment.newInstance();
                                lastSelectedId = 0;
                                break;
                            case R.id.contacts:
                                selectedFragment = ContactsFragment.newInstance();
                                lastSelectedId = 1;
                                break;
                            case R.id.messages:
                                selectedFragment = MessagesFragment.newInstance();
                                lastSelectedId = 2;
                                break;

                        }

                        if(navigationDrawer.isShown())
                        navigationDrawer.closeDrawers();
                        replaceFragment(selectedFragment);

                        return true;

                    }
                });



        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, CallsFragment.newInstance(), CallsFragment.newInstance().getClass().getSimpleName());
        transaction.commit();

        navigationView.getMenu().getItem(0).setChecked(true);






        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.call:
                        selectedFragment = CallsFragment.newInstance();
                        lastSelectedId = 0;
                        break;
                    case R.id.contacts:
                        selectedFragment = ContactsFragment.newInstance();
                        lastSelectedId = 1;
                        break;
                    case R.id.messages:
                        selectedFragment = MessagesFragment.newInstance();
                        lastSelectedId = 2;
                        break;

                }

                // item.setChecked(true);


                if(navigationDrawer.isShown())
                    navigationDrawer.closeDrawers();

                replaceFragment(selectedFragment);

                return true;
            }
        });




    }







    public void toggleDrawer(View v){

        if (navigationDrawer.isDrawerOpen(GravityCompat.END)) {
            navigationDrawer.closeDrawer(GravityCompat.END);
        }         else navigationDrawer.openDrawer(GravityCompat.END);


    }



    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        //viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }






    void replaceFragment(final Fragment selectedFragment) {

        if (Constants.LAST_SELECTED_FRAGMENT != lastSelectedId) {

            Constants.LAST_SELECTED_FRAGMENT = lastSelectedId;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, selectedFragment, selectedFragment.getClass().getSimpleName());
                    transaction.commit();

                }
            }, 500);


        }




    }


}
