package io.nothing.sample;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.nothing.sample.utility.Constants;

import static java.lang.Thread.sleep;

/**
 * Created by aangjnr on 17/06/2017.
 */

public class SplashActivity extends BaseActivity {

    TextView text;
    ImageView image1;
    LinearLayout textLayout;
     String TAG = SplashActivity.class.getSimpleName();


    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash);


        textLayout = (LinearLayout) findViewById(R.id.ll1);

        image1 = (ImageView) findViewById(R.id.image_view1);
         text = (TextView) findViewById(R.id.text_1);

       /* image1.setTranslationY(Utils.getScreenHeight(this));
        image2.setTranslationY(image1.getHeight());*/

       image1.setAlpha(0f);
        textLayout.setTranslationY(-100f);
        textLayout.setAlpha(0f);


        startAnimations();


    }

    private void startAnimations() {

        image1.animate()
                .alpha(1f)
                .setDuration(1500)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {


                        textLayout.animate()
                                .translationY(0)
                                .alpha(1f)
                                .setDuration(500)
                                .setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        try {
                                            int waited = 0;
                                            // Splash screen pause time
                                            while (waited < 1000) {
                                                sleep(100);
                                                waited += 100;
                                            }


                                            moveToNextActivity();
                                            // startActivity(new Intent(SplashActivity.this, LoginActivity.class));


                                        } catch (InterruptedException e) {
                                            // do nothing
                                        } finally {
                                            supportFinishAfterTransition();

                                        }


                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                })
                                .setStartDelay(200)
                                .start();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();


    }

    private void moveToNextActivity() {


       /* if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(Constants.WAS_SIGNING_UP, false)) {

            Log.i(TAG, "Was signing up was set to true. Check which activity we were at if not null");

            String activity_name = PreferenceManager.getDefaultSharedPreferences(this).getString(Constants.PENDING_ACTIVITY, null);
            if (activity_name != null) {
                Log.i(TAG, "Activity name is not null " + activity_name );

                Intent intent = null;

                switch (activity_name) {

                    case "SignUpActivity":
                        Log.i(TAG, "SignUpActivity was pending");

                        intent = new Intent(SplashActivity.this, SignUpActivity.class);

                        break;

                    case "OtpConfirmationActivity":
                        Log.i(TAG, "OtpConfirmationActivity was pending");

                        intent = new Intent(SplashActivity.this, OtpConfirmationActivity.class);

                        break;

                    case "UserDetailsActivity":
                        Log.i(TAG, "UserDetailsActivity was pending");
                        intent = new Intent(SplashActivity.this, UserDetailsActivity.class);


                        break;

                    case "SetupPaymentsActivity":
                        Log.i(TAG, "SetupPaymentsActivity was pending");

                        intent = new Intent(SplashActivity.this, SetupPaymentsActivity.class);

                        break;


                }

                startActivity(intent);

            }
        } else*/
        if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(Constants.IS_USER_SIGNED_IN, false)) {
            Log.i(TAG, "Activity name was  null, It means user completed SignUp process");
            Log.i(TAG, "Is signed in? " + PreferenceManager.getDefaultSharedPreferences(this).getBoolean(Constants.IS_USER_SIGNED_IN, false));

            startActivity(new Intent(this, TestActivity.class));

        } else {

            Log.i(TAG, "User is signed in, Start main activity");
            startActivity(new Intent(this, DrawerBottomSheetActivity.class));

        }
    }


}
