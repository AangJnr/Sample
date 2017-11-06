package io.nothing.sample;

import android.content.Intent;
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

    Button continueButton;

    TextView customToolbarTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flight_summary);

        customToolbarTextView = setCustomToolbarTitle("Flight Summary");
        findViewById(R.id.menu).setVisibility(View.GONE);


        setBackListener();
        setupAndInitializeViews();


    }


    void setupAndInitializeViews() {

        continueButton = (Button) findViewById(R.id.button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(FlightSummaryActivity.this, FlightPassengerInfoActivity.class));


            }
        });


    }


}
