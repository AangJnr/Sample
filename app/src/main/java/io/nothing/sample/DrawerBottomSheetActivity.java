package io.nothing.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import io.nothing.sample.utility.Utils;


public class DrawerBottomSheetActivity extends BaseActivity {


    NavigationView navigationView;
    private DrawerLayout navigationDrawer;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    FrameLayout bottomSheetFrameLayout;

    BottomSheetBehavior bottomSheetBehavior;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Utils.updateTheme(this);
        setContentView(R.layout.activity_nothing);

        Toolbar toolbar = setToolbar(getResources().getString(R.string.app_name));





        navigationDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        bottomSheetFrameLayout = (FrameLayout) findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = (BottomSheetBehavior) BottomSheetBehavior.from(bottomSheetFrameLayout);





        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                navigationDrawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        )

        {

            @Override
            public void onDrawerSlide(View drawer, float slideOffset) {

                findViewById(R.id.main_content).setX(drawer.getWidth() * slideOffset);

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
        actionBarDrawerToggle.syncState();






        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                navigationDrawer.closeDrawers();
                switch (id){

                    case R.id.place_call:

                        placeCallIntent("0000000000");

                        break;

                    case R.id.load_image:

                        loadImagefromGallery();

                        break;

                    case R.id.start_camera:

                        startCameraIntent();

                        break;


                }
                return true;
            }
        });





    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            startActivity(new Intent(this, UserPreferencesActivity.class));

         }else  if (id == R.id.bottom_navigation) {

            startActivity(new Intent(this, BottomNavigationViewActivity.class));

        }


        return true;


      //  return super.onOptionsItemSelected(item);
    }




}
