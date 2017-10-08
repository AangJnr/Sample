package io.nothing.sample;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import io.nothing.sample.utility.Constants;
import io.nothing.sample.utility.DbHelper;
import io.nothing.sample.utility.NetworkBroadcastReceiver;
import io.nothing.sample.utility.PermissionsUtils;



public class BaseActivity extends AppCompatActivity {

    public static DbHelper databaseHelper;
    String TAG = BaseActivity.class.getSimpleName();
    public static int RESULT_LOAD_IMG = 1;
    public static int PERMISSION_CAMERA = 11;
    public static int CAMERA_INTENT = 90;
    int PERMISSION_STORAGE = 2;
    SharedPreferences prefs;



    NetworkBroadcastReceiver networkChangeReceiver = new NetworkBroadcastReceiver();





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = DbHelper.getInstance(this);

        prefs = PreferenceManager.getDefaultSharedPreferences(this);



    }



    public void showAlertDialog(@Nullable String title, @Nullable String message,
                                @Nullable DialogInterface.OnClickListener onPositiveButtonClickListener,
                                @NonNull String positiveText,
                                @Nullable DialogInterface.OnClickListener onNegativeButtonClickListener,
                                @NonNull String negativeText, @Nullable int icon_drawable) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppDialog);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setIcon(icon_drawable);
        builder.setMessage(message);
        builder.setPositiveButton(positiveText, onPositiveButtonClickListener);
        builder.setNegativeButton(negativeText, onNegativeButtonClickListener);
        builder.show();
    }


    public void loadImagefromGallery() {
        // Create intent to Open Image applications like Gallery, Google Photos


        if (!PermissionsUtils.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_STORAGE);
        } else {

            try {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            } catch (Exception e) {
                e.printStackTrace();

                Toast.makeText(this, "Oops! Nothing happened", Toast.LENGTH_SHORT).show();

            }
        }

    }

    Toolbar setToolbar(String title) {

        Toolbar toolbar = null;
        try {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);


            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_white_24dp);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return toolbar;

    }


    void startCameraIntent() {


        if (!PermissionsUtils.hasPermissions(this, android.Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CAMERA);


         }else {


            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

                Log.d(TAG, "Starting camera intent");

                startActivityForResult(takePictureIntent, CAMERA_INTENT);

            }


        }
    }

    void showSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "ON RESUME - setting broadcast");

        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkChangeReceiver, filter);

    }


    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "ON PAUSE - removing  broadcast");

        unregisterReceiver(networkChangeReceiver);
    }


    public void placeCallIntent(String phoneNumber) {


        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            return;
        }

        startActivity(intent);


    }

}
