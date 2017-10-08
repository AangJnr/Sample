package io.nothing.sample.Application;

import android.app.Application;
import android.os.StrictMode;

/**
 * Created by aangjnr on 16/06/2017.
 */

public class NothingApplication extends Application {

    String TAG = NothingApplication.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();




        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


     }


}
