package io.nothing.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by aangjnr on 04/11/2017.
 */

public class FlightSummaryActivity extends BaseActivity {

    //Views

    Button shareStatusButton;

    TextView customToolbarTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_booking);

        customToolbarTextView = setCustomToolbarTitle("Flight Summary");
        findViewById(R.id.menu).setVisibility(View.GONE);


        setBackListener();


    }


    void setupAndInitializeViews() {


    }


}
