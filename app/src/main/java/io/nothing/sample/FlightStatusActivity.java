package io.nothing.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import io.nothing.sample.adapter.FlightStatusRecyclerViewAdapter;
import io.nothing.sample.pojo.FlightStatus;

/**
 * Created by aangjnr on 04/11/2017.
 */

public class FlightStatusActivity extends BaseActivity {

    //Views

    List<FlightStatus> flightStatusList;

    RecyclerView mRecycler;
    Button continueButton;
    TextView customToolbarTextView;

    MaterialSpinner departureDateSpinner;
    EditText bookingReferenceNo;

    RelativeLayout placeHolder;


    FlightStatusRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flight_status);
        mRecycler = (RecyclerView) findViewById(R.id.recycler_view);

        customToolbarTextView = setCustomToolbarTitle("Flight Status");
        findViewById(R.id.menu).setVisibility(View.GONE);


        setBackListener();
        setupAndInitializeViews();


        flightStatusList = new ArrayList<>();
        flightStatusList.add(new FlightStatus("air1290", "Sunday, 21", "jfk", "13:00 pm", "acc", "18:21 pm", "3 hours", "50 mins"));
        flightStatusList.add(new FlightStatus("bru1430", "Monday, 22", "acc", "1:00 pm", "ksi", "1:21 pm", "4 hours 30 mins", "1 hour"));
        flightStatusList.add(new FlightStatus("cal560", "Monday, 22", "ksi", "5:00 pm", "acc", "8:21 pm", "6 hours 00 mins", "2 hours 30 mins"));
        flightStatusList.add(new FlightStatus("awa390", "Tuesday, 23", "jfk", "18:00 pm", "acc", "21:21 pm", "4 hours", "1 hour"));
        flightStatusList.add(new FlightStatus("awa120", "Wednesday, 24", "acc", "6:00 pm", "mia", "13:00 pm", "2 hours 30 mins", "3 hours 40 mins"));
        flightStatusList.add(new FlightStatus("air560", "Wednesday, 24", "acc", "3:00 pm", "was", "2:00 pm", "4 hours 10 mins", "2 hours 20 mins"));
        flightStatusList.add(new FlightStatus("air90", "Wednesday, 24", "acc", "6:00 pm", "mia", "14:00 pm", "5 hours 30 mins", "5 hours 10 mins"));
        flightStatusList.add(new FlightStatus("air127", "Thursday, 25", "acc", "23:30 pm", "jfk", "18:50 pm", "2 hours 30 mins", "3 hours 50 mins"));


        if (flightStatusList != null)
            if (flightStatusList.size() == 0) {

                placeHolder.setVisibility(View.VISIBLE);


            } else {

                if (placeHolder.getVisibility() == View.VISIBLE)
                    placeHolder.setVisibility(View.GONE);

                mAdapter = new FlightStatusRecyclerViewAdapter(this, flightStatusList);
                mRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                mAdapter.setHasStableIds(true);
                mRecycler.setAdapter(mAdapter);
                startFadeInAnimation(mRecycler);


                mAdapter.setOnItemClickListener(new FlightStatusRecyclerViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                });

            }







    }


    void setupAndInitializeViews() {


        placeHolder = (RelativeLayout) findViewById(R.id.placeHolder);
        departureDateSpinner = (MaterialSpinner) findViewById(R.id.departure_date_spinner);

        bookingReferenceNo = (EditText) findViewById(R.id.reference_no_edittext);


        continueButton = (Button) findViewById(R.id.button);


    }


    private void startFadeInAnimation(View v) {

        v.animate()
                .alpha(1)
                .setInterpolator(new OvershootInterpolator(1.f))
                .setDuration(500)
                .start();

        v.setVisibility(View.VISIBLE);
    }

}
